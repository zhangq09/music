package com.study.music.controller;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/wx")
public class WxController {

    private WxMpService wxMpService;

    @GetMapping("/auth_url")
    public String getAuthUrl(@PathParam("redirectUrl") String redirectUrl) {
        return wxMpService.getOAuth2Service().buildAuthorizationUrl(redirectUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
    }

    @GetMapping("/get_user_info")
    public WxOAuth2UserInfo getUserInfo(@PathParam("code") String code) throws WxErrorException {
        WxOAuth2AccessToken wxOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(code);
        return wxMpService.getOAuth2Service().getUserInfo(wxOAuth2AccessToken, null);

    }

    @Autowired
    private void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }
}
