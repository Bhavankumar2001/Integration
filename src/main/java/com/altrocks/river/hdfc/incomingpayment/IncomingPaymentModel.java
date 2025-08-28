package com.altrocks.river.hdfc.incomingpayment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;


@Entity
@Table(name="incoming_payment_table")
@Data
public class IncomingPaymentModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="incoming_id")
	private int incomingId;
	@Column(name="alert_sequence_no")
	public String alertSequenceNo;
	@Column(name="remitter_name")
	public String remitter_Name;
	@Column(name="remitter_account")
	public String remitter_Account;
	@Column(name="remitter_bank")
	public String remitter_Bank;
	@Column(name="user_reference_number")
	public String user_Reference_Number;
	@Column(name="virtual_account")
	public String virtual_Account;
	@Column(name="amount")
	public Double amount;
	@Column(name="mnemonic_code")
	public String mnemonic_Code;
	@Column(name="remitter_ifsc")
	public String remitter_IFSC;
	@Column(name="chequeNo")
	public String chequeNo;
	@Column(name="transaction_description")
	public String transaction_Description;
	@Column(name="account_number")
	public String account_Number;
	@Column(name="debit_credit")
	public String debit_Credit;
	@Column(name="transaction_date")
	public String transaction_Date;
	@Column(name="value_date")
	public String value_Date;
	
	@CreationTimestamp
	@Column(name="created_at")
	public Date createdAt;
	@Column(name="is_sap_synced")
	public boolean isSapSynced;
	@Column(name="sap_synced_on")
	public Date sap_synced_on;
	

}
