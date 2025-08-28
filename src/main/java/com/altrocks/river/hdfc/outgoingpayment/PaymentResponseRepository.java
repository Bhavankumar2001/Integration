package com.altrocks.river.hdfc.outgoingpayment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentResponseRepository extends JpaRepository<CorpPaymentResponseEntity, Integer> {
    // You can add custom query methods here if needed
}