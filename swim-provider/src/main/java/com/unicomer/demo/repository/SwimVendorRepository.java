/**
 * 
 */
package com.unicomer.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicomer.demo.domain.SwimVendorDomain;

/**
 * @author oracle
 *
 */
public interface SwimVendorRepository extends JpaRepository<SwimVendorDomain, String> {

}
