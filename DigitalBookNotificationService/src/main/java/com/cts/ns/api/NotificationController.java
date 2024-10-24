package com.cts.ns.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.ns.bo.NotificationBo;
import com.cts.ns.entity.Notification;

@RestController
@RequestMapping("/notification")
@CrossOrigin("*")
public class NotificationController {

	@Autowired
	private NotificationBo bo;
	

	@GetMapping("/getnotification/{id}")
	public List<Notification> getAll(@PathVariable Long id){
		return bo.getAllByuId(id);
	}
}
