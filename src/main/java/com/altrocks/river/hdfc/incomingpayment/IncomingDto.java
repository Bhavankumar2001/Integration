package com.altrocks.river.hdfc.incomingpayment;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class IncomingDto {
	
	@JsonProperty("GenericCorporateAlertRequest") 
    public List<GenericCorporateAlertRequest> genericCorporateAlertRequest;

}
