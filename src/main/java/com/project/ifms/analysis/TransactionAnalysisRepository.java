package com.project.ifms.analysis;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.ifms.entities.Transaction;

@EnableJpaRepositories
public interface TransactionAnalysisRepository extends CrudRepository<Transaction, Integer>, TransactionAnalysisRepositoryForCustomer
{	
	@Query(value = "SELECT * FROM tx_table u WHERE u.transaction_type = :type", nativeQuery = true)
	public List<Transaction> TxDetailsWithTxType(@Param("type") Integer type);
	
	@Query(value = "SELECT AVG(amount) FROM tx_table u WHERE u.transaction_mode = :mode", nativeQuery = true)
	public double avgTxAmountWithTxType(@Param("mode") Integer mode);
	
	
	@Query(value = "SELECT * FROM tx_table u WHERE u.transaction_mode = :mode", nativeQuery = true)
	public List<Transaction> TxDetailsWithTxMode(@Param("mode") Integer mode);

	@Query(value = "SELECT AVG(amount) FROM tx_table u WHERE u.transaction_mode = :mode", nativeQuery = true)
	public double avgTxAmounthTxMode(@Param("mode") Integer mode);
}
