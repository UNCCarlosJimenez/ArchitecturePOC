/**
 * 
 */
package com.unicomer.logger.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unicomer.demo.common.entity.TransactionLogEndTrace;
import com.unicomer.demo.common.entity.TransactionLogInfoTrace;
import com.unicomer.logger.domain.TransactionLogDomain;
import com.unicomer.logger.repository.TransactionLogRepository;

/**
 * @author carlosj_rodriguez
 *
 */
@Service("loggerService")
public class LoggerServiceImpl implements LoggerService {
	private final Logger logger = LoggerFactory.getLogger(LoggerServiceImpl.class);
	@Autowired
	TransactionLogRepository logRepository;

	@Value("${log.level.info}")
	String logInfoLevel;
	
	@Value("${log.level.error}")
	String logErrorLevel;
	
	@Value("${log.level.start}")
	String logStartLevel;
	
	@Value("${log.level.end}")
	String logEndLevel;
	
	private TransactionLogDomain InfoEntityToDomain(TransactionLogInfoTrace inputLog) {
		TransactionLogDomain logDomain = new TransactionLogDomain();
		try {
			logDomain.setCreatedDate(inputLog.getCreatedDate());
			logDomain.setDetail(inputLog.getDetail());
			logDomain.setLookupData(inputLog.getLookupData());
			logDomain.setEndUserLocation(inputLog.getEndUserLocation());
			logDomain.setEventName(inputLog.getEventName());
			logDomain.setEventSource(inputLog.getEventSource());
			logDomain.setMessage(inputLog.getMessage());
			logDomain.setGlobalReferenceId(inputLog.getGlobalReferenceId());
			logDomain.setLocalReferenceId(inputLog.getLocalReferenceId());
			logDomain.setExternalReferenceId(inputLog.getExternalReferenceId());
			logDomain.setServiceVersion(inputLog.getServiceVersion());
			logDomain.setApplicationId(inputLog.getApplicationId());
			logDomain.setServerLocation(inputLog.getServerLocation());
		} catch (Exception e) {
			logger.error("Ha ocurrido un problema en InfoEntityToDomain: " + e.getMessage(), e);
		}

		return logDomain;
	}

	private TransactionLogDomain EndEntityToDomain(TransactionLogEndTrace inputLog) {
		TransactionLogDomain logDomain = new TransactionLogDomain();
		try {
			logDomain.setCreatedDate(inputLog.getCreatedDate());
			logDomain.setDetail(inputLog.getDetail());
			logDomain.setLookupData(inputLog.getLookupData());
			logDomain.setEndUserLocation(inputLog.getEndUserLocation());
			logDomain.setEventName(inputLog.getEventName());
			logDomain.setEventLevel(logEndLevel);
			logDomain.setEventSource(inputLog.getEventSource());
			logDomain.setMessage(inputLog.getMessage());
			logDomain.setGlobalReferenceId(inputLog.getGlobalReferenceId());
			logDomain.setLocalReferenceId(inputLog.getLocalReferenceId());
			logDomain.setExternalReferenceId(inputLog.getExternalReferenceId());
			logDomain.setServiceVersion(inputLog.getServiceVersion());
			logDomain.setApplicationId(inputLog.getApplicationId());
			logDomain.setResponseTime(inputLog.getResponseTime());
			logDomain.setResponseStatusCode(inputLog.getResponseStatusCode());
			logDomain.setServerLocation(inputLog.getServerLocation());
		} catch (Exception e) {
			logger.error("Ha ocurrido un problema en EndEntityToDomain: " + e.getMessage(), e);
		}

		return logDomain;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.unicomer.logger.service.LoggerService#save(com.unicomer.logger.domain
	 * .TransactionLogDomain)
	 */
	@Override
	public void save(TransactionLogDomain log) {
		logRepository.save(log);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.unicomer.logger.service.LoggerService#saveInfoTrace(com.unicomer.demo
	 * .common.entity.TransactionLogInfoTrace)
	 */
	@Override
	public void saveInfoTrace(TransactionLogInfoTrace log) {
		TransactionLogDomain logDomain = InfoEntityToDomain(log);
		logDomain.setEventLevel(logInfoLevel);
		logRepository.save(logDomain);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.unicomer.logger.service.LoggerService#saveErrorTrace(com.unicomer.
	 * demo.common.entity.TransactionLogInfoTrace)
	 */
	@Override
	public void saveErrorTrace(TransactionLogInfoTrace log) {
		TransactionLogDomain logDomain = InfoEntityToDomain(log);
		logDomain.setEventLevel(logErrorLevel);
		logRepository.save(logDomain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.unicomer.logger.service.LoggerService#saveStartTrace(com.unicomer.
	 * demo.common.entity.TransactionLogInfoTrace)
	 */
	@Override
	public void saveStartTrace(TransactionLogInfoTrace log) {
		TransactionLogDomain logDomain = InfoEntityToDomain(log);
		logDomain.setEventLevel(logStartLevel);
		logRepository.save(logDomain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.unicomer.logger.service.LoggerService#saveEndTrace(com.unicomer.demo.
	 * common.entity.TransactionLogEndTrace)
	 */
	@Override
	public void saveEndTrace(TransactionLogEndTrace log) {
		TransactionLogDomain logDomain = EndEntityToDomain(log);
		logRepository.save(logDomain);
	}
	
	@Override
	public List<TransactionLogDomain> findAll() {
		return logRepository.findAll();
	}

	@Override
	public List<TransactionLogDomain> listAllByPage(Pageable pageable) {
		List<TransactionLogDomain> result = new ArrayList<TransactionLogDomain>();
		Iterator<TransactionLogDomain> it = logRepository.findAll(pageable).iterator();
		while(it.hasNext()){
			result.add(it.next());
		}
		return result;
	}
	
	@Override
	public List<TransactionLogDomain> findByGlobalReferenceId(String globalReferenceId) {
		return logRepository.findByGlobalReferenceId(globalReferenceId);
	}

}
