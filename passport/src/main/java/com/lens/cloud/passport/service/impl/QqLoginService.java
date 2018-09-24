package com.lens.cloud.passport.service.impl;

import com.lens.cloud.passport.service.ILoginService;
import org.springframework.stereotype.Service;

@Service
public class QqLoginService implements ILoginService<QqLoginService.Param> {
	@Override
	public Object login(QqLoginService.Param param) {
		return "qq login:" + param.getOpenId();
	}

	public static class Param {
		private String openId;

		public Param() {
		}

		public String getOpenId() {
			return openId;
		}

		public void setOpenId(String openId) {
			this.openId = openId;
		}
	}
}
