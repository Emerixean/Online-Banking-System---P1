package com.revature.service;





import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.revature.models.Customer;

public class EmailHandler {


public void sendEmail(Customer c) throws MessagingException {
	Properties prop = new Properties();
	prop.put("mail.smtp.auth", true);
	prop.put("mail.smtp.starttls.enable", "true");
	prop.put("mail.smtp.host", "smtp.gmail.com");
	prop.put("mail.smtp.port", "587");
	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	
	Session session = Session.getInstance(prop, new Authenticator() {
	   
	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication("revtestbank@gmail.com", "R3vB4nkP4ss!");
	    }
	});
	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress("revtestbank@gmail.com"));
	message.setRecipients(
	  Message.RecipientType.TO, InternetAddress.parse(c.getEmail()));
	message.setSubject("Account Approval");

	String msg = "We are proud to infom you that your customer account with Revature Bank has been approved!";

	MimeBodyPart mimeBodyPart = new MimeBodyPart();
	mimeBodyPart.setContent(msg, "text/html");

	Multipart multipart = new MimeMultipart();
	multipart.addBodyPart(mimeBodyPart);

	message.setContent(multipart);
System.out.println("message sending");
Transport transport = session.getTransport("smtp");
	transport.send(message);
	System.out.println("message sent");
	}
}