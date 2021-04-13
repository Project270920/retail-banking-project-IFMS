package com.project.ifms.analysis;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.project.ifms.entities.Transaction;

@EnableJpaRepositories
@Primary
public interface TransactionAnalysisRepositoryForCustomer extends JpaRepository<Transaction, Integer>
{	

	@Query(value = "SELECT AVG(amount) FROM tx_table u WHERE u.associated_account = :associated_account AND u.transaction_type = :type",
			nativeQuery = true)
	public double findAvgTxAmountforTxTypeWithAssociatedAccount(
			@Param("associated_account") String associatedAccount,
			@Param("type") Integer type);

	@Query(value = "SELECT * FROM tx_table u WHERE u.associated_account = :associated_account AND u.transaction_type = :type AND u.transaction_date BETWEEN :start AND :end",
			nativeQuery = true)
	public List<Transaction> findAllTxforTxTypeWithAssociatedAccountBetweenDates(
			@Param("associated_account") String associatedAccount,
			@Param("type") Integer type,
			@Param("start") Date start,
			@Param("end") Date end);

	@Query(value = "SELECT * FROM tx_table u WHERE u.associated_account = :associated_account AND u.transaction_type = :type",
			nativeQuery = true)
	public List<Transaction> findAllTxforTxTypeWithAssociatedAccount(
			@Param("associated_account") String associatedAccount,
			@Param("type") Integer type);


/*******************************************************************************************************************************************************************************/


	@Query(value = "SELECT * FROM tx_table u WHERE u.associated_account = :associated_account AND u.transaction_mode = :mode",
			nativeQuery = true)
	public List<Transaction> findAllTxforTxModeWithAssociatedAccount(
			@Param("associated_account") String associatedAccount, @Param("mode") Integer mode);

}
