/**
 * 
 */
package com.unicomer.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unicomer.demo.domain.RiVendor;

/**
 * @author oracle
 *
 */
public interface RiVendorJpa extends JpaRepository<RiVendor, String> {

}
