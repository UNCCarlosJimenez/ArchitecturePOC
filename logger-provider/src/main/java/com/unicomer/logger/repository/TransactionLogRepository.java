/**
 * 
 */
package com.unicomer.logger.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicomer.logger.domain.TransactionLogDomain;

/**
 * @author carlosj_rodriguez
 *
 */
public interface TransactionLogRepository extends JpaRepository<TransactionLogDomain, BigDecimal> {
	public List<TransactionLogDomain> findByGlobalReferenceId(String globalReferenceId);
}
