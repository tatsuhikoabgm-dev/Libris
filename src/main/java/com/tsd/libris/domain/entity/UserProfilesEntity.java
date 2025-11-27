package com.tsd.libris.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfilesEntity {
	
	private Long userId;
	private String lastName;
	private String firstName;
	private String lastNameKana;
	private String firstNameKana;
	private String postalCode;
	private String prefecture;
	private String city;
	private String town;
	private String addressNumber;
	private String building;
	private LocalDate birthday;
	private String phoneNumber;
	private String email;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
