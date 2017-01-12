/**
 * 
 */
package com.unicomer.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unicomer.demo.domain.RiVendor;

/**
 * @author oracle
 *
 */
@RequestMapping(value="/vendors")
public interface RiVendorCrud extends CrudRepository<RiVendor, String> {

}
