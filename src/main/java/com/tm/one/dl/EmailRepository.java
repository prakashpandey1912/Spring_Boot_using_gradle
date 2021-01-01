package com.tm.one.dl;
import javax.persistence.*;
import org.springframework.data.repository.*;
public interface EmailRepository extends CrudRepository<Email,String>
{
public Email findByUsername(String username);
public Email findByEmailKey(String emailKey);
}
