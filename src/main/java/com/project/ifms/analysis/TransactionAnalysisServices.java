package com.project.ifms.analysis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ifms.entities.Transaction;
import com.project.ifms.entities.TransactionMode;
import com.project.ifms.entities.TransactionType;

@Service
public class TransactionAnalysisServices
{
	@Autowired
	private TransactionAnalysisRepositoryForCustomer txAnalysisRepoforCustomer;
	@Autowired
	private TransactionAnalysisRepository txAnalysisRepo;
	
	
	public AnalysisResponse<TransactionType> createAnalysisReportForTxType(String accountNo, TransactionType type)		{
		
		List<Transaction> txList = txAnalysisRepoforCustomer.findAllTxforTxTypeWithAssociatedAccount(accountNo, type.ordinal());
		
		if(txList.isEmpty())
			return null;
		else
		{
			Comparator<Transaction> compare = Comparator.comparing(Transaction::getAmount);
			
			Transaction minAmount = txList.stream().min(compare).get();
			Transaction maxAmount = txList.stream().max(compare).get();
				
			return new AnalysisResponse<TransactionType>()
						.setEntity(type)
						.setDateOfAnalysis(new Date())
						.setNoOfTx(txList.size())
						.setHighestAmountOfTx(maxAmount.getAmount())
						.setDateOfhighestTxAmount(maxAmount.getTransactionDate())
						.setLowestAmountOfTx(minAmount.getAmount())
						.setDateOflowestTxAmount(minAmount.getTransactionDate())
						.setAvgAmountOfTx(txAnalysisRepo.findAvgTxAmountforTxTypeWithAssociatedAccount(accountNo, type.ordinal()));
		}
	}

	public boolean isAccountExists(String accountNumber) 																{
		// TODO Auto-generated method stub
		List<Transaction> accounts = txAnalysisRepo.findAll();
		for(Transaction obj: accounts)
			if(obj.getAssociatedAccount().equals(accountNumber))
				return true;
		return false;
	}


	public AnalysisResponse<TransactionType> createAnalysisReportforTxType(TransactionType type)						{
		List<Transaction> txList = txAnalysisRepo.TxDetailsWithTxType(type.ordinal());
		
		if(txList.isEmpty())
			return null;
		else
		{
			Comparator<Transaction> compare = Comparator.comparing(Transaction::getAmount);
			
			Transaction minAmount = txList.stream().min(compare).get();
			Transaction maxAmount = txList.stream().max(compare).get();
				
			return new AnalysisResponse<TransactionType>()
						.setEntity(type)
						.setDateOfAnalysis(new Date())
						.setNoOfTx(txList.size())
						.setHighestAmountOfTx(maxAmount.getAmount())
						.setDateOfhighestTxAmount(maxAmount.getTransactionDate())
						.setLowestAmountOfTx(minAmount.getAmount())
						.setDateOflowestTxAmount(minAmount.getTransactionDate())
						.setAvgAmountOfTx(roundOffDoubleValue(txAnalysisRepo.avgTxAmountWithTxType(type.ordinal()), 2));
		}
	}
	
	public AnalysisResponse<TransactionMode> createAnalysisReportforTxMode(TransactionMode mode)						{
		List<Transaction> txList = txAnalysisRepo.TxDetailsWithTxMode(mode.ordinal());
		
		if(txList.isEmpty())
			return null;
		else
		{
			Comparator<Transaction> compare = Comparator.comparing(Transaction::getAmount);
			
			Transaction minAmount = txList.stream().min(compare).get();
			Transaction maxAmount = txList.stream().max(compare).get();
				
			return new AnalysisResponse<TransactionMode>()
						.setEntity(mode)
						.setDateOfAnalysis(new Date())
						.setNoOfTx(txList.size())
						.setHighestAmountOfTx(maxAmount.getAmount())
						.setDateOfhighestTxAmount(maxAmount.getTransactionDate())
						.setLowestAmountOfTx(minAmount.getAmount())
						.setDateOflowestTxAmount(minAmount.getTransactionDate())
						.setAvgAmountOfTx(roundOffDoubleValue(txAnalysisRepo.avgTxAmounthTxMode(mode.ordinal()),2));
		}
	}

	
	private double roundOffDoubleValue(double d, int places) 		{
		 
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
