/**
 * 
 */
package com.unicomer.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicomer.demo.domain.RiVendorDomain;

/**
 * @author oracle
 *
 */
public interface RiVendorJpa extends JpaRepository<RiVendorDomain, String> {

}
