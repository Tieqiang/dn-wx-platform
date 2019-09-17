package com.dn.common.config;

import com.dn.common.exception.DcException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    /**
     * 因为有starter 的存在，这个配置累似乎不用存在就可以使用默认的配置
     *
     * @return
     */

//    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer defaultHttpSessionSerializer() {

        ObjectMapper objectMapper = new ObjectMapper();
        DefaultDeserializer deserializer = new DefaultDeserializer(this.getClass().getClassLoader());
        DefaultSerializer serializer = new DefaultSerializer();
        return new RedisSerializer() {
            @Override
            public byte[] serialize(Object o) throws SerializationException {
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream(1024);
                try {
                    String s = objectMapper.writeValueAsString(o);

                    if (StringUtils.isNotBlank(s) && StringUtils.isNotEmpty(s) && !"".equals(s) &&!"null".equals(s)) {
                        System.out.println(s);
                        System.out.println("序列：");
                        System.out.println(s);
                        serializer.serialize(o, byteStream);
                        return byteStream.toByteArray();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Object deserialize(byte[] bytes) throws SerializationException {
                try {
//                    String objectString = new String(bytes);
//                    if (!StringUtils.isBlank(objectString) && objectString.contains("imageCode")) {
//                        return objectMapper.readValue(objectString, ImageCode.class);
//                    }else if (!StringUtils.isBlank(objectString) && objectString.contains("auth")){
//                        System.out.println(objectString);
//                    }
//                    return objectMapper.readValue(objectString, Object.class);
                    String s = new String(bytes);
                    System.out.println("反序列：");
                    System.out.println(s);
                    if (StringUtils.isNotBlank(s)&&StringUtils.isNotEmpty(s) && !"null".equals(s)) {
                        System.out.println("s:"+s);
                        ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
                        Object deserialize = deserializer.deserialize(byteStream);
                        return deserialize;
                    } else {
                        return null;
                    }

                } catch (Exception e) {
                    throw new DcException("序列化失败");
                }
            }
        };

    }
}
