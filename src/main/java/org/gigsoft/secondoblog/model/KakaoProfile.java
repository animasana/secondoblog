package org.gigsoft.secondoblog.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class KakaoProfile {
	public Long id;
	
	@JsonProperty("connected_at")
	public String connectedAt;
	
	public Properties properties;
	
	@JsonProperty("kakao_account")
	public KakaoAccount kakaoAccount;
	
	@Data	
	public class Properties {
		public String nickname;
		
		@JsonProperty("profile_image")
		public String profileImage;
		
		@JsonProperty("thumbnail_image")
		public String thumbnailImage;
	}
	
	@Data
	public class KakaoAccount {
		@JsonProperty("profile_nickname_needs_agreement")
		public Boolean profileNicknameNeedsAgreement;
		
		public Profile profile;
		
		@JsonProperty("has_email")
		public Boolean hasEmail;
		
		@JsonProperty("email_needs_agreement")
		public Boolean emailNeedsAgreement;
		
		@JsonProperty("is_email_valid")
		public Boolean isEmailValid;
		
		@JsonProperty("is_email_verified")
		public Boolean isEmailVerified;
		
		public String email;
		
		@Data
		public class Profile {
			public String nickname;
			
			@JsonProperty("thumbnail_image_url")
			public String thumbnailImageUrl;
			
			@JsonProperty("profile_image_url")
			public String profileImageUrl;			
		}
	}
}








