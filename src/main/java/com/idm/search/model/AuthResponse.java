package com.idm.search.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
	private String access_token;
	private String token_type; 
	private String expires_in; 
	private String scope;
	private String tenant_id;
	private String pod;
	private String strong_auth_supported;
	private String org;
	private String identity_id;
	private String user_name;
	private String strong_auth;
	private String jti;
	
}
