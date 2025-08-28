package com.altrocks.river.hdfc.incomingpayment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altrocks.river.common.GenericCorporateAlertResponse;
import com.altrocks.river.common.ResponseDTO;

@RestController
@RequestMapping("/altrocks/incoming")
public class IncomingPaymentController {

	@Autowired
	private IncomingPaymentRepository incomingPaymentRepository;


//	@PostMapping("/payment/notification")
//	public ResponseDTO incomingNotification(@RequestBody IncomingDto dto) {
//		ResponseDTO responseDTO=new ResponseDTO();
//		List<GenericCorporateAlertResponse> responses=new ArrayList<>(); 
//		try {
//			List<GenericCorporateAlertRequest> alertRequest = dto.getGenericCorporateAlertRequest();
//			
////			List<Long> SeqList = alertRequest.stream().map(GenericCorporateAlertRequest :: getAlert_Sequence_No).collect(Collectors.toList());
//////			incomingPaymentRepository.exis
////			if(!incomingPaymentRepository.existsByAlertSequenceNoIn(SeqList)) {
////				
////			}else {
////				
////			}
//			
//			for (GenericCorporateAlertRequest response : alertRequest) {
//				GenericCorporateAlertResponse alertResponse=new GenericCorporateAlertResponse();
//				try {
////					List<IncomingPaymentModel> list=incomingPaymentRepository.findByAlertSequenceNo(response.getAlert_Sequence_No());
////					if(!incomingPaymentRepository.existsByAlert_Sequence_No(response.getAlert_Sequence_No())) {
////						
////					}
//					if (incomingPaymentRepository.findByAlertSequenceNo(response.getAlert_Sequence_No()).isEmpty()) {
//						DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//						DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//						LocalDateTime transactionDate = LocalDateTime.parse(response.getTransaction_Date(), dateTimeFormatter);
//						LocalDate valueDate = LocalDate.parse(response.getValue_Date(), dateFormatter);
//						IncomingPaymentModel model=new IncomingPaymentModel();
//						model.setAlertSequenceNo(response.getAlert_Sequence_No());
//						model.setRemitter_Name(response.getRemitter_Name());
//						model.setRemitter_Account(response.getRemitter_Account());
//						model.setRemitter_Bank(response.getRemitter_Bank());
//						model.setUser_Reference_Number(response.getUser_Reference_Number());
//						model.setVirtual_Account(response.getVirtual_Account());
//						model.setAmount(response.getAmount());
//						model.setMnemonic_Code(response.getMnemonic_Code());
//						model.setRemitter_IFSC(response.getRemitter_IFSC());
//						model.setChequeNo(response.getChequeNo());
//						model.setTransaction_Description(response.getTransaction_Description());
//						model.setAccount_Number(response.getAccount_Number());
//						model.setDebit_Credit(response.getDebit_Credit());
//						model.setTransaction_Date(response.getTransaction_Date());
//						model.setValue_Date(response.getValue_Date());
//						model.setCreatedAt(new Date());
//						incomingPaymentRepository.save(model);
//						
//						alertResponse.setDomainReferenceNo(response.getAlert_Sequence_No());
//						alertResponse.setErrorMessage("0");
//						alertResponse.setErrorCode("Success");
//						responses.add(alertResponse);
//					} else {
//						alertResponse.setDomainReferenceNo(response.getAlert_Sequence_No());
//						alertResponse.setErrorMessage("0");
//						alertResponse.setErrorCode("Duplicate");
//						responses.add(alertResponse);
//					}
//				} catch (Exception e) {
//					alertResponse.setDomainReferenceNo(response.getAlert_Sequence_No());
//					alertResponse.setErrorMessage("1");
//					alertResponse.setErrorCode("Technical Reject");
//					responses.add(alertResponse);
//				}
////				responseDTO.setGenericCorporateAlertResponse(responses);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
////			GenericCorporateAlertResponse alertResponse=new GenericCorporateAlertResponse();
////			alertResponse.setDomainReferenceNo(response.getAlert_Sequence_No());
////			alertResponse.setErrorMessage("1");
////			alertResponse.setErrorCode("Technical Reject");
////			responses.add(alertResponse);
//		}
//
//		return responseDTO;
//	}

	@PostMapping("/payment/notification")
	public ResponseDTO incomingNotification1(@RequestBody IncomingDto dto) {
		ResponseDTO responseDTO=new ResponseDTO();
		GenericCorporateAlertResponse alertResponse=new GenericCorporateAlertResponse();
		try {
			List<GenericCorporateAlertRequest> alertRequest = dto.getGenericCorporateAlertRequest();
			if (alertRequest.size()==1) {
				GenericCorporateAlertRequest response=alertRequest.get(0);
				if (response.getAlert_Sequence_No()==null ||  response.getAlert_Sequence_No().equals("")) {
					alertResponse.setDomainReferenceNo(dto.getGenericCorporateAlertRequest().get(0).getAlert_Sequence_No());
					alertResponse.setErrorMessage("1");
					alertResponse.setErrorCode("Alert Sequence Should Not Empty or Null");
				} else {
					if (incomingPaymentRepository.findByAlertSequenceNo(response.getAlert_Sequence_No()).isEmpty()) {
						IncomingPaymentModel model=new IncomingPaymentModel();
						model.setAlertSequenceNo(response.getAlert_Sequence_No());
						model.setRemitter_Name(response.getRemitter_Name());
						model.setRemitter_Account(response.getRemitter_Account());
						model.setRemitter_Bank(response.getRemitter_Bank());
						model.setUser_Reference_Number(response.getUser_Reference_Number());
						model.setVirtual_Account(response.getVirtual_Account());
						model.setAmount(response.getAmount());
						model.setMnemonic_Code(response.getMnemonic_Code());
						model.setRemitter_IFSC(response.getRemitter_IFSC());
						model.setChequeNo(response.getChequeNo());
						model.setTransaction_Description(response.getTransaction_Description());
						model.setAccount_Number(response.getAccount_Number());
						model.setDebit_Credit(response.getDebit_Credit());
						model.setTransaction_Date(response.getTransaction_Date());
						model.setValue_Date(response.getValue_Date());
						model.setCreatedAt(new Date());
						incomingPaymentRepository.save(model);
						
						alertResponse.setDomainReferenceNo(response.getAlert_Sequence_No());
						alertResponse.setErrorMessage("0");
						alertResponse.setErrorCode("Success");
					} else {
						alertResponse.setDomainReferenceNo(response.getAlert_Sequence_No());
						alertResponse.setErrorMessage("0");
						alertResponse.setErrorCode("Duplicate");
					}
				}

			} else {
				alertResponse.setDomainReferenceNo(dto.getGenericCorporateAlertRequest().get(0).getAlert_Sequence_No());
				alertResponse.setErrorMessage("1");
				alertResponse.setErrorCode("More than one OBJECT Present");
			}
		} catch (Exception e) {
			alertResponse.setDomainReferenceNo(dto.getGenericCorporateAlertRequest().get(0).getAlert_Sequence_No());
			alertResponse.setErrorMessage("1");
			alertResponse.setErrorCode(e.getLocalizedMessage());
		}
		responseDTO.setGenericCorporateAlertResponse(alertResponse);
		return responseDTO;
		
	}
	
}
