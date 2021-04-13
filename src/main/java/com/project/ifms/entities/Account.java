package com.project.ifms.entities;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel(description="Details about the Customer's Transaction")
public class Account
{
	private int id;
	private String accountNumber;
	private AccountType type;
	private String emailId;
	private String phoneNumber;
	private String aadharaNumber;
	private String panNumber;
	private String branchAddress;
	private String ifsCode;
	private double availabeBalance;
	private List<Transaction> transactions;
	private List<AccountService> services;

}
