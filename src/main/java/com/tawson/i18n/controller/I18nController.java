package com.tawson.i18n.controller;

import com.tawson.i18n.constants.I18nConstants;
import com.tawson.i18n.enums.I18nEnum;
import com.tawson.i18n.utils.I18nUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class I18nController {

    @GetMapping("/i18n")
    public String hello(HttpServletRequest request) {
        System.out.println(I18nUtil.getMessage(I18nEnum.A00001, request.getHeader(I18nConstants.LANG)));
        System.out.println(I18nUtil.getMessage(I18nEnum.A00001));
        return I18nUtil.getMessage(I18nEnum.A00001, request.getHeader(I18nConstants.LANG));
    }
}
