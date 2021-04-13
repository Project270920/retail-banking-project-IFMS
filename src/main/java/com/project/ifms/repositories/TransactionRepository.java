package com.project.ifms.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.project.ifms.entities.Transaction;
import com.project.ifms.entities.TransactionMode;
import com.project.ifms.entities.TransactionType;

@EnableJpaRepositories
public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
	public List<Transaction> findByAssociatedAccount(String associatedAccount);
	
	public List<Transaction> findByAssociatedAccountAndTransactionDate(String associatedAccount, Date transactionDate);
	public List<Transaction> findByAssociatedAccountAndTransactionDateBetween(String associatedAccount, Date startDate, Date endDate);
	
	public List<Transaction> findByAssociatedAccountAndAmountGreaterThanEqual(String accountNumber, double amount);
	public List<Transaction> findByAssociatedAccountAndAmountLessThan(String accountNumber, double amount);
	
	public List<Transaction> findByAssociatedAccountAndTransactionType(String accountNumber, TransactionType transactionType);
	public List<Transaction> findByAssociatedAccountAndTransactionMode(String accountNumber, TransactionMode transactionMode);
}
