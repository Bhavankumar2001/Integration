package com.altrocks.river.hdfc.outgoingpayment;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Data
@JsonIgnoreProperties({"link","id","title","category","updated"})
public class OutgoingPaymentDAO {
	
	@JsonProperty(value="content")
	private OutgoingPaymentContentDAO content;

	

}
