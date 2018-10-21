package com.bambora.integrationApi.Dao;

import com.bambora.integrationApi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<Account, String> {
}
