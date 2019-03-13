package com.shailesh.user.apis.UserManagementApi.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.shailesh.user.apis.UserManagementApi.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * MailSenderService is used to send automatic email to users.
 * @author Shailesh
 *
 */
@Service
public class MailSenderServiceImpl implements MailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Use it to send Simple text email
	 */
	@Override
	public void sendSimpleMail(Users user) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(user.getEmailId());
		message.setSubject("Welcome in User Portal !");
		String messageBody = "Hello " + user.getFirstName()+" "+ user.getLastName() + ",\n Welcome in user portal.\n\nThanks,\nPortal Team";
		message.setText(messageBody);
		mailSender.send(message);
	}

	/**
	 * Use it to send email with HTML format
	 * 
	 * @param user
	 * @throws MessagingException
	 */
	public void sendHTMLMail(Users user) throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

		helper.setTo(user.getEmailId());
		helper.setSubject("Welcome in user portal !");
		StringBuilder messageBody = new StringBuilder();
		messageBody.append("<html>").append("<body>");
		messageBody.append("<p>").append("Hi " + user.getFirstName() + " " + user.getLastName()+",").append("</p></br>");
		messageBody.append("<p>").append("Welcome in user management portal.").append("</p></br></br>");

		messageBody.append("<p><b>").append("Thanks,").append("</b></p>");
		messageBody.append("<p><b>").append("Portal Team,").append("</b></p>");

		message.setContent(messageBody.toString(), "text/html");

		mailSender.send(message);
	}
}
