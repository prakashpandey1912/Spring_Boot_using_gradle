package com.tm.one.dl;
import javax.persistence.*;
import org.springframework.data.repository.*;
public interface CustomerRepository extends CrudRepository<Customer,String>
{
public Customer findByUsername(String username);
public Customer findByEmailId(String emailId);
}
