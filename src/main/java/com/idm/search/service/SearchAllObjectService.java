package com.idm.search.service;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idm.search.model.AuthResponse;
import com.idm.search.util.CredentialsResolver;

@Service
public class SearchAllObjectService {
	

	public ResponseEntity<String> searchAllObjects() throws IOException
	{		
		RestTemplate restTemplate = new RestTemplate();	
		
	String clientID="bfb6f4083542440e8b11841a24d96d8d";
	String clientSecret="977ebfa7ce6fce23eebd5f40d2de84908d8fb2c1694808745870afb3859ab376";
	String url="https://apac-partner02.api.identitynow.com/oauth/token";
	
	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
	headers.add("Accept", MediaType.APPLICATION_JSON.toString()); //Optional in case server sends back JSON data
	    
	MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
	requestBody.add("grant_type", "client_credentials");
	requestBody.add("client_id", clientID); 
	requestBody.add("client_secret", clientSecret);
	    
	HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
	    
	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, formEntity, String.class);
	ResponseEntity<String> response1 = null;
	
		System.out.println("Rest Response ");
		System.out.println(response.getStatusCodeValue());	
		ObjectMapper mapper = new ObjectMapper();
		AuthResponse node = mapper.readValue(response.getBody(), AuthResponse.class);
		System.out.println("Access Token \n"+node.getAccess_token()); 		
 		
        String authToken1 = node.getAccess_token();
 	    HttpHeaders headers1 = new HttpHeaders();
 	    headers1.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));        
 	    headers1.add("User-Agent", "Spring's RestTemplate" );  // value can be whatever
 	    headers1.add("Authorization", "Bearer "+authToken1);
        String url_identity="https://apac-partner02.api.identitynow.com/v2/identities";

        HttpEntity<String> request = new HttpEntity<String>(headers1);
        
		response1 = restTemplate.exchange(url_identity, HttpMethod.GET, request, String.class);
		System.out.println("\nList of Identities  \n" + response1.getBody());
		
 	   return response1;
}
}
