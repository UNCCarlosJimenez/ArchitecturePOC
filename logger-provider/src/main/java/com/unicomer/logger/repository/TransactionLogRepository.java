/**
 * 
 */
package com.unicomer.logger.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unicomer.logger.domain.TransactionLogDomain;

/**
 * @author carlosj_rodriguez
 *
 */
public interface TransactionLogRepository extends JpaRepository<TransactionLogDomain, BigDecimal> {
	
	@Query("SELECT t FROM TransactionLogDomain t WHERE t.globalReferenceId = :globalReferenceId")
	public List<TransactionLogDomain> findByGlobalReferenceId(@Param("globalReferenceId") String globalReferenceId);
}
