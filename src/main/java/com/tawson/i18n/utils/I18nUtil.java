package com.tawson.i18n.utils;

import com.tawson.i18n.constants.I18nConstants;
import com.tawson.i18n.enums.I18nEnum;
import com.tawson.i18n.resolver.I18nLocaleResolver;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Slf4j
@Component
public class I18nUtil {

    @Value("${spring.messages.basename}")
    private String basename;

    @Autowired
    private I18nLocaleResolver resolver;

    private static I18nLocaleResolver customLocaleResolver;

    private static String path;

    @PostConstruct
    public void init() {
        I18nUtil.path = basename;
        I18nUtil.customLocaleResolver = resolver;
    }

    /**
     * 获取 国际化后内容信息
     *
     * @param i18nEnum 国际化key
     * @return 国际化后内容信息
     */
    public static String getMessage(I18nEnum i18nEnum) {
        Locale locale = customLocaleResolver.getLocal();
        return getMessage(i18nEnum.getCode(), i18nEnum.getDefaultMessage(), locale);
    }

    /**
     * 获取指定语言中的国际化信息，如果没有则走英文
     *
     * @param i18nEnum 国际化 key
     * @param lang 语言参数
     * @return 国际化后内容信息
     */
    public static String getMessage(I18nEnum i18nEnum, String lang) {
        Locale locale;
        if (StringUtils.isEmpty(lang)) {
            locale = Locale.US;
        } else {
            try {
                var split = lang.split(I18nConstants.SEPARATOR);
                locale = new Locale(split[0], split[1]);
            } catch (Exception e) {
                locale = Locale.US;
            }
        }
        return getMessage(i18nEnum.getCode(), i18nEnum.getDefaultMessage(), locale);
    }

    public static String getMessage(String code, String defaultMessage, Locale locale) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        messageSource.setBasename(path);
        String content;
        try {
            content = messageSource.getMessage(code, null, locale);
        } catch (Exception e) {
            log.error("国际化参数获取失败===>{}", e.getMessage(), e);
            content = defaultMessage;
        }
        return content;
    }
}
