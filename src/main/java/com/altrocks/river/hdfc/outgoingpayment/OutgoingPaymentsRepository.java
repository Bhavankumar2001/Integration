package com.altrocks.river.hdfc.outgoingpayment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OutgoingPaymentsRepository extends JpaRepository<OutgoingPaymentEntity,Integer> {

	boolean existsByAccountingDocument(String accountingDocument);
//	boolean existsByDocumentReferenceID(String refNo);
}
