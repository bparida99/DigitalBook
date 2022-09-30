package com.cts.ns.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.ns.entity.Notification;
@Repository
public interface NotificationDAO extends JpaRepository<Notification, Long>{

}
