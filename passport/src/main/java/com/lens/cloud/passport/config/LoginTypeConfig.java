package com.lens.cloud.passport.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties("login")
public class LoginTypeConfig {

	private List<LoginTypeInitParam> types = new ArrayList<>();

	public List<LoginTypeInitParam> getTypes() {
		return types;
	}

	public void setTypes(List<LoginTypeInitParam> types) {
		this.types = types;
	}
}
