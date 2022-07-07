package com.base.thymeleaf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.MimeType;

import java.nio.charset.Charset;

@Configuration
@ConfigurationProperties(prefix = "spring.thymeleaf")
@Setter
@Getter
public class ThymeleafProperties {

    private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");

    private static final MimeType DEFAULT_CONTENT_TYPE = MimeType.valueOf("text/html");

    public static final String DEFAULT_PREFIX = "classpath:/templates/";

    public static final String DEFAULT_SUFFIX = ".html";
    //只要把HTML文件方法类路径下的template文件夹下，就会自动导入
}
