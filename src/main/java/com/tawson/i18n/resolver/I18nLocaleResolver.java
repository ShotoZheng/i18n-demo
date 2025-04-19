package com.tawson.i18n.resolver;

import com.tawson.i18n.constants.I18nConstants;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Configuration
public class I18nLocaleResolver implements LocaleResolver {

    @Autowired
    private HttpServletRequest request;

    public Locale getLocal() {
        return resolveLocale(request);
    }

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String language = httpServletRequest.getParameter(I18nConstants.LANG);
        if (StringUtils.isNotBlank(language)) {
            String[] s = language.split(I18nConstants.SEPARATOR);
            return new Locale(s[0], s[1]);
        }
        return Locale.getDefault();
    }

    @Override
    public void setLocale(@NonNull HttpServletRequest request, HttpServletResponse response, Locale locale) {
    }
}
