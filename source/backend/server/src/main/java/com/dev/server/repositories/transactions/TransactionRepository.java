package com.dev.server.repositories.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
    @Query("""
        SELECT
               te
        FROM TransactionEntity te
        WHERE te.fromIdAccount = :idAccount
        OR te.toIdAccount = :idAccount
    """)
    List<TransactionEntity> findAll(String idAccount);
}