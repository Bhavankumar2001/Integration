package com.altrocks.river.hdfc.outgoingpayment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "outgoing_payments")
public class OutgoingPaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private String companyCode;
	private Date documentDate;
	private String postingDate;
	private String accountingDocumentType;
	private String accountingDocument;
	private String documentReferenceID;
	private String TransactionCurrency;
	private String amountInTransactionCurrency;
	private String bankAccount;
	private String bankNumber;
	private String paymentMethodsList;
	private String accountAssignment;
	private String accountingDocumentHeaderText;
	private String clearingJournalEntry;
	private String gLAccount;
	private String costCenter;
	private String profitCenter;
	private String clearingDate;
	private String supplier;
	private String fiscalYear;
	
	
	private String bankFlag;
	
	private String bankObjectId;
	
	private String sapFlag;
	
	private Date sapCreationDate;
	
	private Date sapUpdateDate;
	
	private String sapPostJson;
	
	private String bankErrMsg;
	
	private String sapErrMsg;

}

