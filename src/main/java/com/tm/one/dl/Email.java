package com.tm.one.dl;
import javax.persistence.*;
@Entity
@Table(name="email_key")
@IdClass(EmailId.class)
public class Email
{
@Id
private String username;
@Id
@Column(name="email_key")
private String emailKey;
public void setUsername(String username)
{
this.username=username;
}
public String getUsername()
{
return this.username;
}
public void setEmailKey(String emailKey)
{
this.emailKey=emailKey;
}
public String getEmailKey()
{
return this.emailKey;
}
}