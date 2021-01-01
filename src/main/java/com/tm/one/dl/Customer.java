package com.tm.one.dl;
import javax.persistence.*;
@Entity
@Table(name="customer")
public class Customer
{
@Id
private String username;
private String password;
@Column(name="password_key")
private String passwordKey;
@Column(name="email_id")
private String emailId;
private String status;
public void setUsername(String username)
{
this.username=username;
}
public String getUsername()
{
return this.username;
}
public void setPassword(String password)
{
this.password=password;
}
public String getPassword()
{
return this.password;
}
public void setPasswordKey(String passwordKey)
{
this.passwordKey=passwordKey;
}
public String getPasswordKey()
{
return this.passwordKey;
}
public void setEmailId(String emailId)
{
this.emailId=emailId;
}
public String getEmailId()
{
return this.emailId;
}
public void setStatus(String status)
{
this.status=status;
}
public String getStatus()
{
return this.status;
}
}