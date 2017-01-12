/**
 * 
 */
package com.unicomer.logger.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.unicomer.demo.common.entity.TransactionLogEndTrace;
import com.unicomer.demo.common.entity.TransactionLogInfoTrace;
import com.unicomer.logger.domain.TransactionLogDomain;

/**
 * @author carlosj_rodriguez
 *
 */
public interface LoggerService {
	
	public void save(TransactionLogDomain log);
	
	public void saveInfoTrace(TransactionLogInfoTrace log);
	
	public void saveErrorTrace(TransactionLogInfoTrace log);
	
	public void saveStartTrace(TransactionLogInfoTrace log);
	
	public void saveEndTrace(TransactionLogEndTrace log);
	
	public List<TransactionLogDomain> findAll();
	
	public List<TransactionLogDomain> listAllByPage(Pageable pageable);
	
	public List<TransactionLogDomain> findByGlobalReferenceId(String globalReferenceId);
	
}
