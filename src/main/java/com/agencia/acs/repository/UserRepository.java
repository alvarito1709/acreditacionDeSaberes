package com.agencia.acs.repository;


import com.agencia.acs.entities.User;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, String > {

    public  User findByUsername(String username);

    public User findById(Long id);

    public List<User> findByRol(String rol);

}
