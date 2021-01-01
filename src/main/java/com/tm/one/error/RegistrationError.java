package com.tm.one.error;
public class RegistrationError
{
private String username;
private String emailId;
private String password;
private String captchaCode;
public RegistrationError()
{
System.out.println("Error Object bana");
this.username="";
this.emailId="";
this.password="";
this.captchaCode="";
}
public void setUsername(String username)
{
System.out.println("user name set kiya");
this.username=username;
}
public void setEmailId(String emailId)
{
System.out.println("email set kiya");
this.emailId=emailId;
}
public String getUsername()
{
return this.username;
}
public String getEmailId()
{
return this.emailId;
}
public String getPassword()
{
return this.password;
}
public void setPassword(String password)
{
System.out.println("password set kiya");
this.password=password;
} 
public void setCaptchaCode(String captchaCode)
{
System.out.println("captcha set kiya");
this.captchaCode=captchaCode;
}
public String getCaptchaCode()
{
return this.captchaCode;
}
}