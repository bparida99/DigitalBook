package com.cts.ns.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.ns.dao.NotificationDAO;
import com.cts.ns.entity.Notification;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationDAO dao;
	
	@PostMapping("/")
	public String addMessage(@RequestBody Notification notification) {
		dao.saveAndFlush(notification);
		return "success";
	}
}
