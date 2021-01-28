package com.zhys.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.zhys.base.ResponseCode;
import com.zhys.base.ResponseData;
import com.zhys.utils.JWTUtils;
import com.zhys.utils.JsonUtils;
import com.zhys.utils.RSAUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 认证过滤器
 *
 * @author yinjihuan
 * @create 2017-11-14 10:06
 **/

public class AuthFilter extends ZuulFilter {


    public AuthFilter() {
        super();

    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String token = ctx.getRequest().getHeader("Authorization");
        //JWTUtils jwtUtils = JWTUtils.getInstance(System.getProperty("rsa.modulus"), System.getProperty("rsa.privateExponent"), System.getProperty("rsa.publicExponent"));
        JWTUtils jwtUtils = JWTUtils.getInstance(RSAUtils.modulus,RSAUtils.private_exponent,RSAUtils.public_exponent);
        //验证TOKEN
        if (!StringUtils.hasText(token)) {
            ctx.setSendZuulResponse(false);
            ctx.set("isSuccess", false);
            ResponseData data = ResponseData.fail("非法请求【缺少Authorization信息】", ResponseCode.NO_AUTH_CODE.getCode());
            ctx.setResponseBody(JsonUtils.toJson(data));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }

        JWTUtils.JWTResult jwt = jwtUtils.checkToken(token);
        if (!jwt.isStatus()) {
            ctx.setSendZuulResponse(false);
            ctx.set("isSuccess", false);
            ResponseData data = ResponseData.fail(jwt.getMsg(), jwt.getCode());
            ctx.setResponseBody(JsonUtils.toJson(data));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        ctx.addZuulRequestHeader("uid", jwt.getUid());
        return null;
    }
}
