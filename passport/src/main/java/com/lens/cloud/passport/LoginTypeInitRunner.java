package com.lens.cloud.passport;

import com.lens.cloud.passport.config.LoginTypeConfig;
import com.lens.cloud.passport.enums.LoginType;
import com.lens.cloud.passport.service.ILoginService;
import com.lens.cloud.passport.utils.DynamicEnumUtil;
import com.lens.cloud.passport.utils.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class LoginTypeInitRunner implements CommandLineRunner {

	private LoginTypeConfig loginTypeConfig;

	@Autowired
	public LoginTypeInitRunner(LoginTypeConfig loginTypeConfig) {
		this.loginTypeConfig = loginTypeConfig;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("login type init!!!");
		loginTypeConfig.getTypes().forEach(param -> {
			try {
				DynamicEnumUtil.addEnum(LoginType.class, param.getName(),
						new Class[]{ ILoginService.class, Class.class},
						new Object[]{ SpringContextUtils.getBean(Class.forName(param.getServiceClass())),
										Class.forName(param.getParamClass())
						});
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
	}
}
