package com.project.ifms.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Details about the Customer's Transaction")
@Entity
@Table(name="tx_table")
public class Transaction
{
	@ApiModelProperty(notes="Primary Key of the Transaction")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ApiModelProperty(notes="Transaction ID")
	private String transactionId;

	@ApiModelProperty(notes="Account Number Associate to the Transaction")
	private String associatedAccount;
	
	@ApiModelProperty(notes="With whom transaction is done")
	private String particular;

	@ApiModelProperty(notes="Date of Transaction")
	private Date transactionDate;
	
	@ApiModelProperty(notes="Mode of Transaction (UPI, IMPS, NEFT, CHEQUE, DEMAND_DRAFT, WITHDRAWAL, DEBIT_CARD, CREDIT_CARD")
	private TransactionMode transactionMode;
	
	@ApiModelProperty(notes="Type of Transaction (DEBIT, CREDIT")
	private TransactionType transactionType; 
	
	@ApiModelProperty(notes="Amount of Transactio (in Rupees)")
	private double amount;
	
	@ApiModelProperty(notes="remarks associated with the Transaction")
	private String remarks;


	public Transaction()	{
		
	}
	public Transaction(int id, String transactionId, String associatedAccount, String particular, Date transactionDate,
			TransactionMode transactionMode, TransactionType transactionType, double amount, String remarks) {
		super();
		this.id = id;
		this.transactionId = transactionId;
		this.associatedAccount = associatedAccount;
		this.particular = particular;
		this.transactionDate = transactionDate;
		this.transactionMode = transactionMode;
		this.transactionType = transactionType;
		this.amount = amount;
		this.remarks = remarks;
	}


	public int getId() {
		return id;
	}
	public Transaction setId(int id) {
		this.id = id;
		return this;
	}

	public String getTransactionId() {
		return transactionId;
	}
	public Transaction setTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getAssociatedAccount() {
		return associatedAccount;
		
	}
	public Transaction setAssociatedAccount(String associatedAccount) {
		this.associatedAccount = associatedAccount;
		return this;
	}

	public String getParticular() {
		return particular;
	}
	public Transaction setParticular(String particular) {
		this.particular = particular;
		return this;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}
	public Transaction setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
		return this;
	}

	public TransactionMode getTransactonMode() {
		return transactionMode;
	}
	public Transaction setTransactonMode(TransactionMode transactionMode) {
		this.transactionMode = transactionMode;
		return this;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}
	public Transaction setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
		return this;
	}

	public double getAmount() {
		return amount;
	}
	public Transaction setAmount(double amount) {
		this.amount = amount;
		return this;
	}

	public String getRemarks() {
		return remarks;
	}
	public Transaction setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}


	@Override
	public String toString() {
		return "Transaction [id=" + id + ", transactionId=" + transactionId + ", associatedAccount=" + associatedAccount
				+ ", particular=" + particular + ", transactionDate=" + transactionDate + ", transactonMode="
				+ transactionMode + ", transactionType=" + transactionType + ", amount=" + amount + ", remarks="
				+ remarks + "]";
	}
}
