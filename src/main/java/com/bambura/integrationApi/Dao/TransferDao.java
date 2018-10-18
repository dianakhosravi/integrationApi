package com.bambura.integrationApi.Dao;

import com.bambura.integrationApi.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferDao  extends JpaRepository<Transfer,String> {
}
