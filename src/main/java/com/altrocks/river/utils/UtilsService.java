package com.altrocks.river.utils;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class UtilsService {
	@Value("${app.api.port}")
	public String port;

	@Value("${app.api.username}")
	public String username;

	@Value("${app.api.password}")
	public String password;

	@Value("${app.integration.scope}")
	public String appIntegrationScopeId;
	
	@Value("${app.integration.clientId}")
	public String appIntegrationClientId;
	
	@Value("${app.integration.clientSecret}")
	public String appIntegrationClientSecret;
	
	@Value("${app.integration.grantType}")
	public String appIntegrationGrantType;
	
	@Value("${app.integration.apiKey}")
	public String appIntegrationapiKey;
 
	@Value("${app.integration.loginId}")
	public String appIntegrationloginId;


	@Description(value = "API Authentication")
	public HttpResponse<String> ApiCall(String apiUrl) throws UnirestException {
		HttpResponse<String> response = Unirest.get(apiUrl).basicAuth(username, password).asString();
		return response;
	}

	@Description(value = "Convert XML to Json Format !!")
	public JsonNode XmlToJsonConversionTestContent(String data) throws JsonMappingException, JsonProcessingException {
		JSONObject json = XML.toJSONObject(data);
		String jsonString = json.toString(4);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(jsonString);
		JsonNode entryNodeArray = rootNode.path("feed").path("entry"); // .path("feed")
		return entryNodeArray;
	}

}
