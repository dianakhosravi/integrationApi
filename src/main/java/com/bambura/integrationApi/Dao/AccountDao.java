package com.bambura.integrationApi.Dao;

import com.bambura.integrationApi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account,String> {
}
