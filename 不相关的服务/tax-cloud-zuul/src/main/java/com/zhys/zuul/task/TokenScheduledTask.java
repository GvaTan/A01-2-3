package com.zhys.zuul.task;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fangjia.auth.query.AuthQuery;
import com.zhys.base.ResponseData;
import com.zhys.zuul.fegin.AuthServiceFegin;

/**
 * 定时刷新token
 *
 * @author yinjihuan
 * @create 2017-11-09 15:39
 **/
@Component
public class TokenScheduledTask {
    private static Logger logger = LoggerFactory.getLogger(TokenScheduledTask.class);

    public final static long fixedDelayTime = 60 * 1000 * 60 * 20;

    @Autowired
    private AuthServiceFegin authServiceFegin;

    /**
     * 刷新Token
     */
    @Scheduled(fixedDelay = fixedDelayTime)
    public void reloadApiToken() {
        String token = this.getToken();
        while (StringUtils.isBlank(token)) {
            try {
                Thread.sleep(1000);
                token = this.getToken();
            } catch (InterruptedException e) {
                logger.error("", e);
            }
        }
        System.setProperty("tax.auth.token", token);
    }

    public String getToken() {
        AuthQuery query = new AuthQuery();
        query.setAccessKey("1");
        query.setSecretKey("1");
        ResponseData response = authServiceFegin.auth(query);
        return response.getData() == null ? "" : response.getData().toString();
    }
}
