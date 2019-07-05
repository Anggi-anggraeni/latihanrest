package com.eksad.latihanrest.DAO;

import org.springframework.data.repository.CrudRepository;

import com.eksad.latihanrest.model.Customer;

public interface CustomerDao extends CrudRepository<Customer, Long> {

}
