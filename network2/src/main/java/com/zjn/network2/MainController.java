package com.zjn.network2;

// 郑佳妮的计网实验1

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Controller
public class MainController {

	@RequestMapping("/task1")
	@ResponseBody
	public String task1() {

		return "Hello zjn 这是计网实验一的第一项";
	}

	@GetMapping("/task2")
	ResponseEntity<String> task2(WebRequest request) {
		ZonedDateTime expiresDate = ZonedDateTime.now().with(LocalTime.MAX);
		String expires = expiresDate.format(DateTimeFormatter.RFC_1123_DATE_TIME);
		String eTag = DigestUtils.md5DigestAsHex(expires.getBytes());
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);

		if (request.checkNotModified(eTag)) {
			return null;
		}

		return ResponseEntity.ok()
				.eTag(eTag)
				.cacheControl(cacheControl)
				.body("task 2");
	}

	@RequestMapping("/task3")
	public String task3() {

		return "task 3";
	}

	@RequestMapping("/task4")
	public String task4() {

		return "task 4";
	}
}