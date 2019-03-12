/**
 * 
 */
package com.se.main.Repository;

import org.springframework.data.repository.CrudRepository;

import com.se.main.pojos.CountObject;


/**
 * @author Glen
 *
 */
public interface CountObjectRepository extends CrudRepository<CountObject, String> {

}
