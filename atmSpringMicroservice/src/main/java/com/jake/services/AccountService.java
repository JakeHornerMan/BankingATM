package com.jake.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jake.models.Account;
import com.jake.repos.AccountRepository;
import com.jake.requests.AccountRequest;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;
	
	private Account signedInAccount = null;
	
//	public void createAccount(int accNum, int pin, double balance, double overdraft) {
//		Account newAccount = new Account();
//		newAccount.setAccountNumber(accNum);
//		newAccount.setPin(pin);
//		newAccount.setBalance(balance);
//		newAccount.setOverdraft(overdraft);
//		
//		signedInAccount = accountRepo.save(newAccount);
//	}
	
	public Account createAccount(Account account) {
		signedInAccount = accountRepo.save(account);
		return signedInAccount;
	}
	
	public String signIn(int accountNumber, int pin) {
		Account account = new Account();
		account = accountRepo.getByAccountNumber(accountNumber);
		if(account.getAccountNumber() == accountNumber && account.getPin() == pin) {
			signedInAccount = account;
			return "Signed in as : " + accountNumber;
		}
		return "invalid account number or pin";
	}
	
	public String signOut() {
		signedInAccount = null;
		return "Account signed out";
	}
	
	public boolean isSignedIn() {
		if(signedInAccount != null) {
			return true;
		}
		return false;
	}
	
	public String showBalance() {
		if(isSignedIn()) {
			return signedInAccount.showBalance();
		}
		else {
			return "No Account has been signed in.";
		}
	}
	
	public boolean isCorrectFunds(int ans){
		if(isSignedIn()) {
			double amount = signedInAccount.getBalance() + signedInAccount.getOverdraft();
			if(ans <= amount) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public void withdraw(int withdraw) {
		if(signedInAccount.getBalance() < withdraw) {
			int roundDown = signedInAccount.getBalance().intValue();
			withdraw = withdraw - roundDown;
			signedInAccount.setBalance(0.0);
			signedInAccount.setOverdraft(signedInAccount.getOverdraft()-withdraw);
		}
		else {
			signedInAccount.setBalance(signedInAccount.getBalance() - withdraw);
		}
	}

	public void deposit(int amount) {
		signedInAccount.setBalance(signedInAccount.getBalance() + amount);
	}

	
}
