package com.tartarus.dao;

import com.tartarus.model.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserDao extends JpaRepository<DAOUser,Integer> {
    DAOUser findByUsername(String username);
}
