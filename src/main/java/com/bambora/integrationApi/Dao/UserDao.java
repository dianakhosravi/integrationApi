package com.bambora.integrationApi.Dao;

import com.bambora.integrationApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UserDao extends JpaRepository<User, String> {


}
