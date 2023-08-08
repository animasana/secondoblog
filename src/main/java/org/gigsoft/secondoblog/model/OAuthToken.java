package org.gigsoft.secondoblog.model;

public record OAuthToken(
	String access_token,
	String token_type,
	String refresh_token,
	Long expires_in,
	String scope,
	Long refresh_token_expires_in) {
}
