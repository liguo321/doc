package com.lens.cloud.passport.enums;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lens.cloud.passport.service.ILoginService;
/*
common(SpringContextUtils.getBean(CommonLoginService.class), CommonLoginService.Param.class);
qq(SpringContextUtils.getBean(toFirstLowerCase(QqLoginService.class.getSimpleName()),ILoginService.class)),
telphone(SpringContextUtils.getBean(toFirstLowerCase(TelphoneLoginService.class.getSimpleName()),ILoginService.class));
*/

import java.io.IOException;
import java.util.Map;

public enum LoginType {
	;
	private ILoginService loginService;
	private Class clazz;

	LoginType(ILoginService loginService, Class clazz) {
		this.loginService = loginService;
		this.clazz =clazz;
	}

	public Object login(Map paramMap) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return this.loginService.login(objectMapper.readValue(objectMapper.writeValueAsString(paramMap), this.clazz));
	}

}
