package com.bambura.integrationApi.Dao;

import com.bambura.integrationApi.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsDao extends JpaRepository<Transactions, String> {

}
