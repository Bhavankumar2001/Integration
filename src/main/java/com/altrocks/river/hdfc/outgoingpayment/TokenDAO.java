package com.altrocks.river.hdfc.outgoingpayment;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TokenDAO {
	  @JsonProperty("access_token")
	private String access_token;

}
