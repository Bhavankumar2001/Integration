package com.altrocks.river.hdfc.outgoingpayment;



import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutgoingPaymentPropertiesDAO {
	
	@JsonProperty("d:ID")
	private String id;
	@JsonProperty("d:SourceLedger")
	private String sourceLedger;
	@JsonProperty("d:CompanyCode")
	private String companyCode;
	@JsonProperty("d:FiscalYear")
	private String fiscalYear;
	@JsonProperty("d:AccountingDocument")
	private String accountingDocument;
	@JsonProperty("d:LedgerGLLineItem")
	private String ledgerGLLineItem;
	@JsonProperty("d:Ledger")
	private String ledger;
	@JsonProperty("d:SupplierName")
	private String supplierName;
	@JsonProperty("d:BankCountryKey")
	private String bankCountryKey;
	@JsonProperty("d:BankName")
	private String bankName;
	@JsonProperty("d:IBAN")
	private String iban;
	@JsonProperty("d:BankNumber")
	private String bankNumber;
	@JsonProperty("d:SWIFTCode")
	private String sWIFTCode;
	@JsonProperty("d:BankAccount")
	private String bankAccount;
	@JsonProperty("d:PaymentMethodsList")
	private String paymentMethodsList;
	@JsonProperty("d:DocumentReferenceID")
	private String documentReferenceID;
	@JsonProperty("d:AccountingDocumentHeaderText")
	private String accountingDocumentHeaderText;
	@JsonProperty("d:DocumentItemText")
	private String documentItemText;
	@JsonProperty("d:AccountingDocumentType")
	private String accountingDocumentType;
	@JsonProperty("d:DocumentDate")
	private Date documentDate;
	@JsonProperty("d:PostingDate")
	private String postingDate;
	@JsonProperty("d:FiscalPeriod")
	private String fiscalPeriod;
	@JsonProperty("d:TransactionCurrency")
	private String transactionCurrency;
	@JsonProperty("d:IsReversal")
	private Boolean isReversal;
	@JsonProperty("d:IsReversed")
	private Boolean isReversed;
	@JsonProperty("d:AmountInTransactionCurrency")
	private String amountInTransactionCurrency;
	@JsonProperty("d:AmountInBalanceTransacCrcy")
	private String amountInBalanceTransacCrcy;
	@JsonProperty("d:AccountAssignment")
	private String accountAssignment;
	@JsonProperty("d:FinancialTransactionType")
	private String financialTransactionType;
	@JsonProperty("d:GLAccount")
	private String gLAccount;
	@JsonProperty("d:CostCenter")
	private String costCenter;
	@JsonProperty("d:ProfitCenter")
	private String profitCenter;
	@JsonProperty("d:PostingKey")
	private String postingKey;
//	@JsonProperty("d:ClearingDate")
//	private String clearingDate;
	@JsonProperty("d:ClearingJournalEntry")
	private String clearingJournalEntry;
	@JsonProperty("d:ValueDate")
	private Object valueDate;
	@JsonProperty("d:Customer")
	private String customer;
	@JsonProperty("d:Supplier")
	private String supplier;
	@JsonProperty("d:DebitCreditCode")
	private String debitCreditCode;
	@JsonProperty("d:BalanceTransactionCurrency")
	private String balanceTransactionCurrency;
	@JsonProperty("d:ChartOfAccounts")
	private String chartOfAccounts;
	@JsonProperty("d:ControllingArea")
	private String controllingArea;
	@JsonProperty("d:ClearingJournalEntryFiscalYear")
	private String clearingJournalEntryFiscalYear;
	@JsonProperty("d:BankInternalID")
	private String bankInternalID;
	@JsonProperty("d:BankCountry")
	private String bankCountry;
	@JsonProperty("d:HouseBank_1")
	private String houseBank1;
	@JsonProperty("d:BankControlKey")
	private String bankControlKey;
	@JsonProperty("d:ChargeAccountBank")
	private String chargeAccountBank;
	@JsonProperty("d:HouseBankAccount")
	private String houseBankAccount;
	@JsonProperty("d:HouseBank")
	private String houseBank;
	@JsonProperty("d:HouseBankChargeAccount")
	private String houseBankChargeAccount;

}
