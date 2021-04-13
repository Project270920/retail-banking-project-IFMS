package com.project.ifms.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ifms.entities.Transaction;
import com.project.ifms.entities.TransactionMode;
import com.project.ifms.entities.TransactionType;
import com.project.ifms.repositories.TransactionRepository;

@Service
public class TransactionService
{	
	@Autowired
	private TransactionRepository txRepo;

	public boolean isAccountExists(String accountNumber)																						{

		List<Transaction> accounts = txRepo.findAll();
		for(Transaction obj: accounts)
			if(obj.getAssociatedAccount().equals(accountNumber))
				return true;
		return false;
	}
	
	public List<Transaction> retriveAllTransaction(String accountNumber)																		{
		
		if(isAccountExists(accountNumber))
			return txRepo.findByAssociatedAccount(accountNumber);
		else
			return null;
	}

	public List<Transaction> retriveTransactionsThroughAssociatedAccountAndTransactionDate(String accountNumber, Date transactionDate)			{
		
		List<Transaction> list = txRepo.findByAssociatedAccountAndTransactionDate(accountNumber, transactionDate);
		if(list.isEmpty())
			return null;
		else
			return list;
	}

	public List<Transaction> retriveTransactionsThroughAssociatedAccountAndBetweenTransactionDates(String accountNumber, Date start, Date end)	{
		
		List<Transaction> list =  txRepo.findByAssociatedAccountAndTransactionDateBetween(accountNumber, start, end);
		if(list.isEmpty())
			return null;
		else
			return list;
	}

	public List<Transaction> retriveTransactionsByTransactionType(String accountNumber, TransactionType txType)									{
		List<Transaction> list = txRepo.findByAssociatedAccountAndTransactionType(accountNumber, txType);
		if(list.isEmpty())
			return null;
		else
			return list;
	}

	public List<Transaction> retriveTransactionsByTransactionMode(String accountNumber, TransactionMode mode)									{
		
		List<Transaction> list = txRepo.findByAssociatedAccountAndTransactionMode(accountNumber, mode);
		if(list.isEmpty())
			return null;
		else
			return list;
	}

	public List<Transaction> retriveTransactionsAmountGreaterThanEqual(String accountNumber, double amount)										{

		List<Transaction> list = txRepo.findByAssociatedAccountAndAmountGreaterThanEqual(accountNumber, amount);
		if(list.isEmpty())
			return null;
		else
			return list;
	}
	
	public List<Transaction> retriveTransactionsAmountLesserThanEqual(String accountNumber, double amount)										{

		List<Transaction> list = txRepo.findByAssociatedAccountAndAmountLessThan(accountNumber, amount);
		if(list.isEmpty())
			return null;
		else
			return list;
	}

	public List<Transaction> retriveAllTransactions()																							{
		List<Transaction> txList = txRepo.findAll();
		if(txList.isEmpty())
			return null;
		else
			return txList;
	}

	
	public boolean addOneTransaction(Transaction tx) {
		// TODO Auto-generated method stub
		if(tx == null)
			return false;
		else
		{
			txRepo.save(tx);
			return true;
		}
	}
	
	public boolean addMultipleTransaction(List<Transaction> list)
	{
		if(list.isEmpty())
			return false;
		else
		{
			txRepo.saveAll(list);
			return true;
		}
	}
}
