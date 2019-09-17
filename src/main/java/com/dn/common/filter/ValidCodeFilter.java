package com.dn.common.filter;

import com.dn.common.config.SystemProperties;
import com.dn.common.exception.DcException;
import com.dn.common.vo.ImageCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Component
public class ValidCodeFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(ValidCodeFilter.class);

    @Autowired
    private SystemProperties systemProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        String needValidateCodeUrls = systemProperties.getValidateCode().getNeedValidateCodeUrls();
        boolean sessionEnable = systemProperties.getAuthention().isSessionEnable();
        //如果启用了Session,并且在认证范围内
        if (needValidateCodeUrls.contains(path)&&sessionEnable) {
            HttpSession session = request.getSession();
            ImageCode imageCode = (ImageCode) session.getAttribute(session.getId() + "|validateCode");

            String clientCode = request.getParameter("imageCode");
            if(StringUtils.isBlank(clientCode)){
                throw new DcException("验证码为空！");
            }
            if (imageCode != null) {
                Long nowTime = new Date().getTime();
                Long createDateTime = imageCode.getCreateDateTime();
                if (nowTime - createDateTime > systemProperties.getValidateCode().getExpireTime()) {
                    throw new DcException("验证码过期");
                }

                if(!clientCode.toUpperCase().equals(imageCode.getImageCode())){
                    throw new DcException("错误的验证码");
                }

            } else {
                throw new DcException("错误的验证码");
            }
            session.removeAttribute(session.getId() + "|validateCode");
        }

        logger.info("准备跳转的路径：" + path);
        filterChain.doFilter(request, response);
    }
}
