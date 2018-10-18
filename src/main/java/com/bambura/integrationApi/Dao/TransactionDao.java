package com.bambura.integrationApi.Dao;

import com.bambura.integrationApi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, String> {
}
