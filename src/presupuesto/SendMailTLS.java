/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presupuesto;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS extends Thread {
    String to;
    String from;
    String subject;
    String body;
    
    SendMailTLS (String to, String from, String subject,String body)
    {
        this.to=to;
        this.from=from;
        this.subject=subject;
        this.body=body;
    }
    
    
    public void run()                       
    {  	
        try {	          
            sendEmail ( to,  from,  subject, body)	;
        } catch (MessagingException ex) {

            DialogError error = new DialogError (null,true,ex);
            error.setVisible(true);
                   
        }
    }
        
    
            
    
    public  void sendEmail (String to, String from, String subject,String body) throws AddressException, MessagingException{
    
        final String username = "casafamiliafuentesmontoya@gmail.com";
        final String password = "200125433";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                }
          });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));

        message.setSubject(subject);

        message.setText(body);

        Transport.send(message);

        System.out.println("Email sent. Done");
    } 
}