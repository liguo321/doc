package com.lens.cloud.passport.service.impl;

import com.lens.cloud.passport.service.ILoginService;
import org.springframework.stereotype.Service;

@Service
public class WxLoginService implements ILoginService<WxLoginService.Param> {
	@Override
	public Object login(WxLoginService.Param param) {
		return "wx login:" + param.getOpenId() + "|||" + param.getCode();
	}

	public static class Param {
		private String openId;
		private String code;

		public Param() {
		}

		public String getOpenId() {
			return openId;
		}

		public void setOpenId(String openId) {
			this.openId = openId;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}
}
