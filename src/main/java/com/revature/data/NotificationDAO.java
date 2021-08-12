package com.revature.data;

import java.util.UUID;

import com.revature.bean.Notification;

	public interface NotificationDAO {
	
	void createNotification(Notification noti);

	Notification getNotificationById(String username);
	
}
