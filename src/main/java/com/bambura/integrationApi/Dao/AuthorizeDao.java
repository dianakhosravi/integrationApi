package com.bambura.integrationApi.Dao;

import com.bambura.integrationApi.model.Authorize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizeDao extends JpaRepository<Authorize,String> {
}
