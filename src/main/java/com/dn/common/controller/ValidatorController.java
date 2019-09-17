package com.dn.common.controller;

import com.dn.common.config.SystemProperties;
import com.dn.common.vo.ImageCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("api/validate")
public class ValidatorController {


    @Autowired
    private SystemProperties systemProperties ;


    /**
     * 验证码生成
     * @param response
     * @param request
     * @throws IOException
     */
    @GetMapping("get-code")
    @ApiOperation(value="获取验证码",httpMethod = "GET",notes = "获取验证码图片")
    public void getValidateCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("image/png");
        //禁止缓存

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        int x = 0, fontHeight = 0, codeY = 0;
        int red = 0, green = 0, blue = 0;

        int width = systemProperties.getValidateCode().getWidth() ;
        int codeCount = systemProperties.getValidateCode().getCodeCount();
        int height = systemProperties.getValidateCode().getHeight() ;
        int lineCount = systemProperties.getValidateCode().getLineCount();

        x = width / (codeCount + 2);// 每个字符的宽度(左右各空出一个字符)
        fontHeight = height - 2;// 字体的高度
        codeY = height - 4;

        // 图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        // 生成随机数
        Random random = new Random();
        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 创建字体,可以修改为其它的
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        // Font font = new Font("Times New Roman", Font.ROMAN_BASELINE, fontHeight);
        g.setFont(font);

        for (int i = 0; i < lineCount; i++) {
            // 设置随机开始和结束坐标
            int xs = random.nextInt(width);// x坐标开始
            int ys = random.nextInt(height);// y坐标开始
            int xe = xs + random.nextInt(width / 8);// x坐标结束
            int ye = ys + random.nextInt(height / 8);// y坐标结束

            // 产生随机的颜色值，让输出的每个干扰线的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }

        // randomCode记录随机产生的验证码
        StringBuffer randomCode = new StringBuffer();
        String[] codeSequence = systemProperties.getValidateCode().getCodeSequence() ;
        // 随机产生codeCount个字符的验证码。
        for (int i = 0; i < codeCount; i++) {
            String strRand = codeSequence[random.nextInt(codeSequence.length)];
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x, codeY);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        // 将四位数字的验证码保存到Session中。
        String code = randomCode.toString();
        ImageCode imageCode = new ImageCode() ;
        imageCode.setImageCode(code);
        imageCode.setCreateDateTime(new Date().getTime());
        HttpSession session = request.getSession();
        session.setAttribute(session.getId()+"|validateCode",imageCode);
        ImageIO.write(buffImg, "png",response.getOutputStream());
    }

}
