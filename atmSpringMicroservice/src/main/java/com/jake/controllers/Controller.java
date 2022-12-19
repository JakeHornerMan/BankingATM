package com.jake.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jake.services.AccountService;
import com.jake.services.SafeService;

@RestController
@RequestMapping("/atm")
public class Controller {

	@Autowired
	private AccountService accountService; 
	@Autowired
	private SafeService safeService;
	
	@GetMapping()
	public String welcome() {
		return "welcome to atm";
	}
	
	@GetMapping(value = "/safe")
	public String showsafe() {
		return safeService.displaySafeContents();
	}
	
	@GetMapping(value = "/createAccount/{accountNumber}/{pin}/{balance}/{overdraft}")
	public String createAccount(@PathVariable int accountNumber, @PathVariable int pin, @PathVariable double balance, @PathVariable double overdraft) {
		accountService.createAccount(accountNumber,pin,balance,overdraft);
		return "Account created: " + accountNumber + ", and Signed In";
	}
	
	@GetMapping(path = "/signIn/{accountNumber}/{pin}")
	public String signin(@PathVariable int accountNumber, @PathVariable int pin) {
		return accountService.signIn(accountNumber,pin);
	}
	
	@GetMapping(path = "/signOut")
	public String signOut(@PathVariable int accountNumber, @PathVariable int pin) {
		return accountService.signOut();
	}
	
	@GetMapping(path = "/balance")
	public String accountDetails() {
		return accountService.showBalance();
	}
	
	@GetMapping(path = "/withdraw/{withdrawAmount}")
	public String withdraw(@PathVariable int withdrawAmount) {
		return safeService.withdraw(withdrawAmount) + "\n, New Account Balance: " + accountService.showBalance() + ", New Safe Balance: " + safeService.displaySafeContents();
	}
	
	@GetMapping(path = "/deposit/{fiftys}/{twentys}/{tens}/{fives}")
	public String deposit(@PathVariable int fiftys, @PathVariable int twentys, @PathVariable int tens, @PathVariable int fives) {
		return safeService.deposit(fiftys, twentys, tens, fives) + ", New Account Balance: " + accountService.showBalance() + ", New Safe Balance: " + safeService.displaySafeContents();
	}
}
