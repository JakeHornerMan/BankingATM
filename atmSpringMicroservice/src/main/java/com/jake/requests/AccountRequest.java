package com.jake.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

	private int id;
	private int accountNumber;
	private int pin;
	private Double balance;
	private Double overdraft;
	
}
