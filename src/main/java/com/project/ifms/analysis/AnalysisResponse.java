package com.project.ifms.analysis;

import java.util.Date;

import io.swagger.annotations.ApiModel;

@ApiModel(description="Detailed Analysis Report of Transaction")
public class AnalysisResponse<T>
{	
	private T entity;
	private int noOfTx;
	private Date dateOfAnalysis;
	private double highestAmountOfTx;
	private Date dateOfhighestTxAmount;
	private double lowestAmountOfTx;
	private Date dateOflowestTxAmount;
	private double avgAmountOfTx;


	public AnalysisResponse()	{

	}	
	public AnalysisResponse(T entity, Date dateOfAnalysis, int noOfTx, double highestAmountOfTx, Date dateOfhighestTxAmount,
			double lowestAmountOfTx, Date dateOflowestTxAmount, double avgAmountOfTx) {
		super();
		this.entity = entity;
		this.dateOfAnalysis = dateOfAnalysis;
		this.noOfTx = noOfTx;
		this.highestAmountOfTx = highestAmountOfTx;
		this.dateOfhighestTxAmount = dateOfhighestTxAmount;
		this.lowestAmountOfTx = lowestAmountOfTx;
		this.dateOflowestTxAmount = dateOflowestTxAmount;
		this.avgAmountOfTx = avgAmountOfTx;
	}


	public T getEntity() {
		return entity;
	}
	public AnalysisResponse<T> setEntity(T entity) {
		this.entity = entity;
		return this;
	}
	
	public int getNoOfTx() {
		return noOfTx;
	}
	public AnalysisResponse<T> setNoOfTx(int noOfTx) {
		this.noOfTx = noOfTx;
		return this;
	}
	
	public double getHighestAmountOfTx() {
		return highestAmountOfTx;
	}
	public AnalysisResponse<T> setHighestAmountOfTx(double highestAmountOfTx) {
		this.highestAmountOfTx = highestAmountOfTx;
		return this;
	}
	
	public Date getDateOfhighestTxAmount() {
		return dateOfhighestTxAmount;
	}
	public AnalysisResponse<T> setDateOfhighestTxAmount(Date dateOfhighestTxAmount) {
		this.dateOfhighestTxAmount = dateOfhighestTxAmount;
		return this;
	}
	
	public double getLowestAmountOfTx() {
		return lowestAmountOfTx;
	}
	public AnalysisResponse<T> setLowestAmountOfTx(double lowestAmountOfTx) {
		this.lowestAmountOfTx = lowestAmountOfTx;
		return this;
	}
	
	public Date getDateOflowestTxAmount() {
		return dateOflowestTxAmount;
	}
	public AnalysisResponse<T> setDateOflowestTxAmount(Date dateOflowestTxAmount) {
		this.dateOflowestTxAmount = dateOflowestTxAmount;
		return this;
	}
		
	public double getAvgAmountOfTx() {
		return avgAmountOfTx;
	}
	public AnalysisResponse<T> setAvgAmountOfTx(double avgAmountOfTx) {
		this.avgAmountOfTx = avgAmountOfTx;
		return this;
	}
	
	public Date getDateOfAnalysis() {
		return dateOfAnalysis;
	}
	public AnalysisResponse<T> setDateOfAnalysis(Date dateOfAnalysis) {
		this.dateOfAnalysis = dateOfAnalysis;
		return this;
	}
	

	@Override
	public String toString() {
		return "AnalysisResponse [entity=" + entity + ", noOfTx=" + noOfTx + ", dateOfAnalysis=" + dateOfAnalysis
				+ ", highestAmountOfTx=" + highestAmountOfTx + ", dateOfhighestTxAmount=" + dateOfhighestTxAmount
				+ ", lowestAmountOfTx=" + lowestAmountOfTx + ", dateOflowestTxAmount=" + dateOflowestTxAmount
				+ ", avgAmountOfTx=" + avgAmountOfTx + "]";
	}
}
