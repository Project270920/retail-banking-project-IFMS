package com.project.ifms.resources;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ifms.entities.Transaction;
import com.project.ifms.entities.TransactionMode;
import com.project.ifms.entities.TransactionType;
import com.project.ifms.generator.SampleDataGenerator;
import com.project.ifms.services.TransactionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/tx")
public class TransactionResources
{
	@Autowired
	private TransactionService txService;
	
	@GetMapping(value="{accNo}")
	@ApiOperation(value="Retrive All Transactions for Customers", notes="", response=Transaction.class)
	public ResponseEntity<?> customerAllTransactions(@PathVariable("accNo") String accountNumber)					{

		if(accountNumber == null)
			return new ResponseEntity<String>("Customer Account Number not entered", HttpStatus.NOT_ACCEPTABLE);
		else
		{
			if(txService.isAccountExists(accountNumber))
			{
				List<Transaction> tx = txService.retriveAllTransaction(accountNumber);
				
				if(tx == null)
					return new ResponseEntity<String>("No Data Found", HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<List<Transaction>>(tx, HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>("Account doesn't exist", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="{accNo}/{date}")
	public ResponseEntity<?> customerTransactionsAtDate(@PathVariable("accNo") String accountNumber,
													@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date)	{
		
		if(accountNumber == null || date == null)
			return new ResponseEntity<String>("Customer Account Number or Transaction Date not entered", HttpStatus.NOT_ACCEPTABLE);
		else
		{
			if(txService.isAccountExists(accountNumber))
			{
				List<Transaction> tx = txService.retriveTransactionsThroughAssociatedAccountAndTransactionDate(accountNumber, date);
				
				if(tx == null)
					return new ResponseEntity<String>("No Data Found", HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<List<Transaction>>(tx, HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>("Account doesn't exist", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="{accNo}/{start}/{end}")
	public ResponseEntity<?> customerTransactionsBetweenDates(@PathVariable("accNo") String accountNumber,
															@PathVariable("start") @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
															@PathVariable("end") @DateTimeFormat(pattern="yyyy-MM-dd") Date end)			{
		if(accountNumber == null || start == null || end == null)
			return new ResponseEntity<String>("Customer Account Number or Transaction Date not entered", HttpStatus.NOT_ACCEPTABLE);
		else
		{
			if(txService.isAccountExists(accountNumber))
			{
				List<Transaction> tx = txService.retriveTransactionsThroughAssociatedAccountAndBetweenTransactionDates(accountNumber, start, end);
				
				if(tx == null)
					return new ResponseEntity<String>("No Data Found", HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<List<Transaction>>(tx, HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>("Account doesn't exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value="{accNo}/type/{txType}")
	public ResponseEntity<?> customerTransactionsByTransactionType(@PathVariable("accNo") String accountNumber,
																@PathVariable("txType") String txType)								{
		if(accountNumber == null || txType == null)
			return new ResponseEntity<String>("Customer Account Number or Transaction Date not entered", HttpStatus.NOT_ACCEPTABLE);
		else
		{
			if(txService.isAccountExists(accountNumber))
			{
				List<Transaction> tx = txService.retriveTransactionsByTransactionType(accountNumber, TransactionType.fromValue(txType));
				
				if(tx == null)
					return new ResponseEntity<String>("No Data Found", HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<List<Transaction>>(tx, HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>("Account doesn't exist", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="{accNo}/mode/{txMode}")
	public ResponseEntity<?> customerTransactionsByTransactionMode(@PathVariable("accNo") String accountNumber, 
																@PathVariable("txMode") String mode)		{
		if(accountNumber == null || mode == null)
			return new ResponseEntity<String>("Customer Account Number or Transaction Date not entered", HttpStatus.NOT_ACCEPTABLE);
		else
		{
			if(txService.isAccountExists(accountNumber))
			{
				List<Transaction> tx = txService.retriveTransactionsByTransactionMode(accountNumber, TransactionMode.fromValue(mode));
				
				if(tx == null)
					return new ResponseEntity<String>("No Data Found", HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<List<Transaction>>(tx, HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>("Account doesn't exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value="{accNo}/amount/greater/{amount}")
	public ResponseEntity<?> customerTransactionsAmountGreaterThan(@PathVariable("accNo") String accountNumber,
																@PathVariable("amount") double amount)			{
		if(accountNumber == null || amount == 0.0)
			return new ResponseEntity<String>("Customer Account Number or Transaction Date not entered", HttpStatus.NOT_ACCEPTABLE);
		else
		{
			if(txService.isAccountExists(accountNumber))
			{
				List<Transaction> tx = txService.retriveTransactionsAmountGreaterThanEqual(accountNumber, amount);
				
				if(tx == null)
					return new ResponseEntity<String>("No Data Found", HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<List<Transaction>>(tx, HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>("Account doesn't exist", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="{accNo}/amount/lesser/{amount}")
	public ResponseEntity<?> customerTransactionsAmountLesserThan(@PathVariable("accNo") String accountNumber,
																@PathVariable("amount") double amount)			{
		if(accountNumber == null || amount == 0.0)
			return new ResponseEntity<String>("Customer Account Number or Transaction Date not entered", HttpStatus.NOT_ACCEPTABLE);
		else
		{
			if(txService.isAccountExists(accountNumber))
			{
				List<Transaction> tx = txService.retriveTransactionsAmountLesserThanEqual(accountNumber, amount);
				
				if(tx == null)
					return new ResponseEntity<String>("No Data Found", HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<List<Transaction>>(tx, HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>("Account doesn't exist", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/")
	public ResponseEntity<?> addOneTransaction() throws ParseException
	{
		Transaction obj = new SampleDataGenerator().getTransaction();
		
		if(obj == null)
			return new ResponseEntity<String>("No Data Found", HttpStatus.NOT_FOUND);
		else
		{
			if(txService.addOneTransaction(obj))
			{
				List<Transaction> txList = txService.retriveAllTransactions();
				
				if(txList.isEmpty())
					return new ResponseEntity<String>("No Data Found", HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<List<Transaction>>(txList, HttpStatus.OK);	
			}
			else
				return new ResponseEntity<String>("Data Not Saved", HttpStatus.NOT_FOUND);
		}
		
		
	}

	@GetMapping(value="all")
	public ResponseEntity<?> addAllTransactions() throws ParseException
	{
		List<Transaction> list = new SampleDataGenerator().getTransactionList();
		
		if(list.isEmpty())
			return new ResponseEntity<String>("No Data Found", HttpStatus.NOT_FOUND);
		else
		{
			if(txService.addMultipleTransaction(list))
			{
				List<Transaction> txList = txService.retriveAllTransactions();
				
				if(txList.isEmpty())
					return new ResponseEntity<String>("No Data Found", HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<List<Transaction>>(txList, HttpStatus.OK);	
			}
			else
				return new ResponseEntity<String>("Data Not Saved", HttpStatus.NOT_FOUND);
		}
		
		
	}
}
