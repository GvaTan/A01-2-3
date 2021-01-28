package com.zhys.zuul.fegin;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fangjia.auth.query.AuthQuery;
import com.zhys.base.ResponseData;

@Component
@FeignClient(value="auth",path="/oauth")
public interface AuthServiceFegin  {	

	@PostMapping("/token")
	public ResponseData auth(@RequestBody AuthQuery query) ;
	

}
