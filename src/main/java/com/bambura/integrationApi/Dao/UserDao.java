package com.bambura.integrationApi.Dao;

import com.bambura.integrationApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UserDao extends JpaRepository<User, String> {


}
