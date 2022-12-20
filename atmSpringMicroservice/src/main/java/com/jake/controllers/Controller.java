package com.jake.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jake.models.Account;
import com.jake.models.Safe;
import com.jake.requests.AccountRequest;
import com.jake.requests.DepositRequest;
import com.jake.services.AccountService;
import com.jake.services.SafeService;

@RestController
@RequestMapping("/atm")
public class Controller {

	@Autowired
	private AccountService accountService; 
	@Autowired
	private SafeService safeService;
	
	@Autowired
	private ObjectMapper mapper;
	
	@GetMapping()
	public String welcome() {
		return "welcome to atm";
	}
	
	@GetMapping(value = "/safe")
	public String showsafe() {
		return safeService.displaySafeContents();
	}
	
	@GetMapping(value = "/createAccount")
	public String createAccount(@RequestBody AccountRequest accountRequest) {
		Account newAccount = accountService.createAccount(mapper.convertValue(accountRequest, Account.class));
		return "Account created: " + newAccount.getAccountNumber() + ", and Signed In";
	}
	
	@GetMapping(path = "/signIn")
	public String signin(@RequestBody AccountRequest accountRequest) {
		return accountService.signIn(accountRequest.getAccountNumber(),accountRequest.getPin());
	}
	
	@GetMapping(value = "/signOut")
	public String signOut(@PathVariable int accountNumber, @PathVariable int pin) {
		return accountService.signOut();
	}
	
	@GetMapping(path = "/balance")
	public String accountDetails() {
		return accountService.showBalance();
	}
	
	@GetMapping(path = "/withdraw/{withdrawAmount}")
	public String withdraw(@PathVariable int withdrawAmount) {
		return safeService.withdraw(withdrawAmount) + ",\n New Account Balance: " + accountService.showBalance() + ",\n New Safe Balance: " + safeService.displaySafeContents();
	}
	
	@GetMapping(path = "/deposit")
	public String deposit(@RequestBody DepositRequest depositRequest) {
		return safeService.deposit(mapper.convertValue(depositRequest, Safe.class)) + ",\n New Account Balance: " + accountService.showBalance() + ",\n New Safe Balance: " + safeService.displaySafeContents();
	}
}
