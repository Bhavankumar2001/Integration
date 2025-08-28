package com.altrocks.river.hdfc.incomingpayment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomingPaymentRepository  extends JpaRepository<IncomingPaymentModel, Integer>{

	List<IncomingPaymentModel> findByAlertSequenceNo(String string);
	
//	boolean existsByAlert_Sequence_NoIn(List<Long> Alert_Sequence_No);
	
//	boolean existsByAlert_Sequence_No(Long Alert_Sequence_No);

	boolean existsByAlertSequenceNoIn(List<Long> seqList);

	boolean existsByAlertSequenceNo(String string);

}
