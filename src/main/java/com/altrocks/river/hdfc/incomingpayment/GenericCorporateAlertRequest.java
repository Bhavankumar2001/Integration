package com.altrocks.river.hdfc.incomingpayment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;

import lombok.Data;
@Data
public class GenericCorporateAlertRequest{
    @JsonProperty("Alert Sequence No") 
    public String alert_Sequence_No;
    @JsonProperty("Remitter Name") 
    public String remitter_Name;
    @JsonProperty("Remitter Account") 
    public String remitter_Account;
    @JsonProperty("Remitter Bank") 
    public String remitter_Bank;
    @JsonProperty("User Reference Number") 
    public String user_Reference_Number;
    @JsonProperty("Virtual Account") 
    public String virtual_Account;
    @JsonProperty("Amount") 
    public Double amount;
    @JsonProperty("Mnemonic Code") 
    public String mnemonic_Code;
    @JsonProperty("Remitter IFSC") 
    public String remitter_IFSC;
    @JsonProperty("ChequeNo") 
    public String chequeNo;
    @JsonProperty("Transaction Description") 
    public String transaction_Description;
    @JsonProperty("Account Number") 
    public String account_Number;
    @JsonProperty("Debit Credit") 
    public String debit_Credit;
    @JsonProperty("Transaction Date") 
    public String transaction_Date;
    @JsonProperty("Value Date") 
    public String value_Date;
    
   
    
    
   
    
   
   
   
   
  
}
