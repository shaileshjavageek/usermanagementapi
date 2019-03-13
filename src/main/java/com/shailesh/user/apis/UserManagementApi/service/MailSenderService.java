package com.shailesh.user.apis.UserManagementApi.service;

import javax.mail.MessagingException;

import com.shailesh.user.apis.UserManagementApi.model.Users;

/**
 * All operations related to mailing service
 *
 * @author Shailesh
 */
public interface MailSenderService {
	
	public void sendSimpleMail(Users users);
	
	public void sendHTMLMail(Users user) throws MessagingException;

}
