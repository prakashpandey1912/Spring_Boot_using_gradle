package com.tm.one;
import com.tm.one.error.*;
import com.tm.one.dl.*;
import com.tm.one.beans.*;
import com.tm.one.generator.*;
import com.tm.one.mail.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;
import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.*;
import org.springframework.web.servlet.*;
@Controller
public class MainController
{
@Autowired
private CustomerRepository customerRepository;
@Autowired
private EmailRepository emailRepository;
private RegistrationError registrationError;
private KeyGenerator keyGenerator;
@RequestMapping(value="/CaptchaCode",produces = MediaType.IMAGE_PNG_VALUE)
public @ResponseBody byte[] getCaptchaCode(HttpSession httpSession,@RequestParam String dt)
{
UUID uuid =UUID.randomUUID();
String str=uuid.toString().substring(0,6);  
BufferedImage bufferedImage= new  BufferedImage(150,50,  BufferedImage.TYPE_INT_RGB);
Graphics graphics=bufferedImage.getGraphics();
graphics.setColor(Color.white);
graphics.fillRect(3,3,146,46);
graphics.setColor(Color.black);
Font textFont = new Font("Verdana", Font.BOLD, 30);
graphics.setFont(textFont);
graphics.drawString(str,10,30);
graphics.setColor(Color.black);
for (int i = 0; i < 5000; i++) {
int X = (int) ((Math.random()*1000)%60);
int Y = (int) ((Math.random()*1000)%160);
graphics.draw3DRect(X*3, Y*3,1,1, true);
}
graphics.setColor(Color.black);
for (int i = 0; i < 500; i++) {
int X = (int) ((Math.random()*1000)%60)*3;
int Y = (int) ((Math.random()*1000)%160)*3;
int X1 = (int) ((Math.random()*1000)%60)*3;
int Y1 = (int) ((Math.random()*1000)%160)*3;
graphics.drawLine(X,Y,X1,Y1);
} 
ByteArrayOutputStream baos = new ByteArrayOutputStream();
try{
ImageIO.write(bufferedImage, "png", baos);
}catch(IOException ioe){}
httpSession.setAttribute("captchaCode",str);
return baos.toByteArray();
}


@RequestMapping(value="/login", method = RequestMethod.POST)
public String login(Model model,@RequestParam String username,@RequestParam String password)
{
System.out.println(username);
System.out.println(password);
Customer u=customerRepository.findByUsername(username);
registrationError=new RegistrationError();
if(u==null)
{
registrationError.setUsername("Invalid username and password");
model.addAttribute("registrationError",registrationError);
return "jsp/index";
}
String pass="";
keyGenerator=new KeyGenerator();
try
{
pass=keyGenerator.decrypt(u.getPassword(),u.getPasswordKey());
}
catch(Exception e)
{
System.out.println("Unable to proceed");
}
String passKey=u.getPasswordKey();
if(!(pass.equals(password)))
{
registrationError.setUsername("Invalid username and password");
model.addAttribute("registrationError",registrationError);
return "jsp/index";
}
return "jsp/UserHome";
}
@RequestMapping("/")
public String whatever()
{
return "jsp/index";
}

@RequestMapping("/registerForm")
public String what()
{
return "jsp/Register";
}

@RequestMapping("/validation")
public String whatdsfdsf(HttpSession session,@RequestParam String emailKey)
{
session.setAttribute("key",emailKey);
return "jsp/validate";
}

@RequestMapping(value="/validate" ,method = RequestMethod.POST)
public String watdsfdsf(HttpSession session,Model model,@RequestParam String username)
{
System.out.println(username);
String key=session.getAttribute("key").toString();
System.out.println(key);
Email e=emailRepository.findByUsername(username);
if(e==null) 
{
ValidationError validationError=new ValidationError();
validationError.setUsername("invalid username");
model.addAttribute("validationError",validationError);
return "jsp/validate";
}
String emailKey=e.getEmailKey();
if(!(emailKey.equals(key)))
{
ValidationError validationError=new ValidationError();
validationError.setUsername("invalid username");
model.addAttribute("validationError",validationError);
return "jsp/validate";
}
Customer c=customerRepository.findByUsername(username);
c.setStatus("y");
customerRepository.save(c);
return "jsp/confirm";
}
@RequestMapping(value="/register", method = RequestMethod.POST)
public String register(Model model,HttpSession httpSession,@RequestParam String username,@RequestParam String emailId,@RequestParam String password,@RequestParam String rpassword,@RequestParam String captcha) 
{
System.out.println(username);
System.out.println(emailId);
System.out.println(password);
System.out.println(rpassword);
System.out.println(captcha);
System.out.println(rpassword);
System.out.println(customerRepository.findByUsername(username));
Customer u=customerRepository.findByUsername(username);
System.out.println(captcha);
RegistrationError registrationError=new RegistrationError();
if(u!=null)
{
registrationError.setUsername("User name exist");
}
Customer v=customerRepository.findByEmailId(emailId);
if(v!=null)
{
registrationError.setEmailId("EmailId exist");
}
int flag=1;
if(!(password.equals(rpassword)))
{
flag=0;
registrationError.setPassword("Password not matched");
}
String cap=httpSession.getAttribute("captchaCode").toString();
if(!(cap.equals(captcha)))
{
registrationError.setCaptchaCode("Invalid Captcha");
flag=0;
}
if(u==null&&v==null&&flag==1)
{
Customer c=new Customer();
c.setUsername(username);
c.setEmailId(emailId);
keyGenerator=new KeyGenerator();
String key="";
UUID uuid =UUID.randomUUID();
String str=uuid.toString().substring(0,16);  
try{
key=keyGenerator.encrypt(password,str);
}catch(Exception e)
{
System.out.println("unable to proceed");
}
c.setPassword(key);
c.setPasswordKey(str);
c.setStatus("n");
System.out.println("one");
Email e=new Email();
System.out.println("two");
String emailKey=uuid.toString().substring(0,35);
System.out.println(emailKey);
System.out.println("three");
customerRepository.save(c);
e.setEmailKey(emailKey);
e.setUsername(username);
System.out.println("four");
emailRepository.save(e);
System.out.println("five");
System.out.println("6");
SendEmail s=new SendEmail();
System.out.println("7");
String to="vishal@somemail.com";
String from="admin@techforum.com";
s.sendMail(to,from,emailKey);
System.out.println("8");
return "jsp/save";
}
else
{
CustomerBean customerBean=new CustomerBean();
customerBean.setUsername(username);
customerBean.setEmailId(emailId);
model.addAttribute("customerBean",customerBean);
model.addAttribute("registrationError", registrationError);
return "jsp/Register";
}
}
}