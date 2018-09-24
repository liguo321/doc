package com.lens.cloud.passport.service.impl;

import com.lens.cloud.passport.service.ILoginService;
import org.springframework.stereotype.Service;

@Service
public class CommonLoginService implements ILoginService<CommonLoginService.Param> {
	@Override
	public Object login(CommonLoginService.Param param) {
		return "common login:" + param.getUsername() + "|||" + param.getPassword();
	}

	public static class Param {
		private String username;
		private String password;

		public Param() {
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
