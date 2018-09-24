package com.lens.cloud.passport.controller;

import com.lens.cloud.passport.enums.LoginType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class LoginController {

	@PostMapping("/login/{loginType}")
	public Object login(@RequestBody Map paramMap, @PathVariable LoginType loginType) throws IOException {
		return loginType.login(paramMap);
	}
}
