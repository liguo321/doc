package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

	//@Test
	public static void contextLoads() {
		String sql = "select orgid from public.sys_organization where source_code=#COLUMN1 and orgcode=#COLUMN2";
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put("column1", "001");
		dataMap.put("column2", "002");
		Pattern pattern = Pattern.compile("#[A-Za-z0-9]*");
		Matcher matcher = pattern.matcher(sql);
		while (matcher.find()) {
			String field = matcher.group();
			String value = dataMap.get(field.substring(1).toLowerCase());
			sql = sql.replaceFirst(field, "'".concat(value).concat("'"));
		}
		System.out.println(sql);
	}

	public static void main(String[] args) {
		contextLoads();
	}

}
