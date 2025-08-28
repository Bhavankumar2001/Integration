package com.altrocks.river.hdfc.outgoingpayment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Data
@JsonIgnoreProperties("type")
public class OutgoingPaymentContentDAO {
	@JsonProperty(value="m:properties")
	private OutgoingPaymentPropertiesDAO properties;

}
