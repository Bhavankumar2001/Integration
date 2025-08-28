package com.altrocks.river.hdfc.outgoingpayment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "corp_Payment")
public class CorpPaymentResponseEntity {
	@Id
	private Integer id;  // Assuming there is an 'id' field as primary key.

    private String apiRefNo;
    private String transactionStatus;
}
