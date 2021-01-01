package com.tm.one.beans;
import org.springframework.stereotype.*;
public class CustomerBean
{
private String username;
private String emailId;
public CustomerBean()
{
System.out.println("Bean object bana");
this.username="";
this.emailId="";
}
public void setUsername(String username)
{
this.username=username;
}
public void setEmailId(String emailId)
{
this.emailId=emailId;
}
public String getEmailId()
{
return this.emailId;
}
public String getUsername()
{
return this.username;
}
}