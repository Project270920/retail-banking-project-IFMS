package com.project.ifms.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ifms.entities.TransactionMode;
import com.project.ifms.entities.TransactionType;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/report/v1")
public class TransactionAnalysisResource
{
	@Autowired
	private TransactionAnalysisServices analysisService;	
	
	
	@GetMapping(value="type/{txType}")
	@ApiOperation(value="Retrive Transaction Report with Transaction Type (CREDIT/DEBIT)", notes="", response=AnalysisResponse.class)
	public ResponseEntity<?> getTxReportWithTxType(@PathVariable("txType") String txType)
	{
		AnalysisResponse<TransactionType> report = analysisService.createAnalysisReportforTxType(TransactionType.fromValue(txType));
		
		if(report == null)
			return new ResponseEntity<String>("No Report Found", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<AnalysisResponse<TransactionType>>(report, HttpStatus.OK);
	}
	
	@GetMapping(value="mode/{txMode}")
	@ApiOperation(value="Retrive Transaction Report with Transaction Mode (UPI/IMPS/NEFT/CHEQUE/DEMAND DRAFT/WITHDRAWAL/DEBIT_CARD/CREDIT_CARD)", notes="", response=AnalysisResponse.class)
	public ResponseEntity<?> getTxReportWithTxMode(@PathVariable("txMode") String txMode)
	{
		AnalysisResponse<TransactionMode> report = analysisService.createAnalysisReportforTxMode(TransactionMode.fromValue(txMode));
		
		if(report == null)
			return new ResponseEntity<String>("No Report Found", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<AnalysisResponse<TransactionMode>>(report, HttpStatus.OK);
	}
	
	
	@GetMapping(value="{accNo}/type/{txType}")
	@ApiOperation(value="Retrive Transaction Report with Transaction Type of Specific Customer (CREDIT/DEBIT)", notes="", response=AnalysisResponse.class)
	public ResponseEntity<?> getTxReportforTxType(@PathVariable("accNo") String accountNumber,
												@PathVariable("txType") String txType)					{
		if(accountNumber == null)
			return new ResponseEntity<String>("Customer Account Number not entered", HttpStatus.NOT_ACCEPTABLE);
		else
		{
			if(analysisService.isAccountExists(accountNumber))
			{
				AnalysisResponse<TransactionType> report = analysisService.createAnalysisReportForTxType(accountNumber, TransactionType.fromValue(txType));
				
				if(report == null)
					return new ResponseEntity<String>("No Report Found", HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<AnalysisResponse<TransactionType>>(report, HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>("Account doesn't exist", HttpStatus.NOT_FOUND);
		}
	}

}
