package com.tm.one.mail;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
public class SendEmail  
{  
 public void sendMail(String to,String from,String emailKey)
{    
      String host = "localhost";//or IP address  
  
     //Get the session object  
      Properties properties = System.getProperties();  
      properties.setProperty("mail.smtp.host", host);  
      Session session = Session.getDefaultInstance(properties);  
  
     //compose the message  
      try{  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(from));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject("Verification");  
         message.setText("Hello, this is example of sending email<a href='http://localhost:8080/validation?emailKey="+emailKey+"'>hello</a>  ","UTF-8", "html");         
// Send message  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
   }  
}  