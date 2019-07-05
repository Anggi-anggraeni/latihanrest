package com.eksad.latihanrest.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.eksad.latihanrest.model.Transaction;

public interface TransactionDao extends CrudRepository<Transaction, Long>{

	@Query("select sum(quantity) from TransactionDetail "
			+ " where transaction.id=?1")
	public Integer findtotalItemBytransactionId(Long id);
	
	@Query("select td.transaction.id as transactionId, "
			+ " sum(td.quantity*pr.price) as total"
			+" from TransactionDetail td"
			+" join td.product pr"
			+" group by td.transaction.id")
	public List<Object[]> findTotalNominalPerTransaction();
	
	@Query(value=
			" select td.transaction_id, sum(td.quantity*pr.price) "+
			" from transaction_detail td "+
			" join product pr on pr.id=td.product_id "+
			" group by td.transaction_id", nativeQuery=true)
	public List<Object[]> findTotalNominalPerTransactionNative();
}
