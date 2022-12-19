package com.jake.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jake.models.Safe;
import com.jake.repos.SafeRepository;

@Service
public class SafeService {

	@Autowired
	private SafeRepository safeRepo;
	@Autowired
	private AccountService accountService;
	
	private Safe atmSafe;
	
	public void setAtmSafe(){
		atmSafe = safeRepo.findById(1).get();
	}
	
	public String displaySafeContents() {
		setAtmSafe();
		atmSafe = safeRepo.findById(1).get();
		String ans = atmSafe.toString();
		String ans2 = atmSafe.displayTotalSafe();
		
		return ans + "\n" +ans2;
	}

	public boolean IsSufficiantFundsInSafe(double withdrawAmount) {
		if(withdrawAmount <= atmSafe.TotalInSafe()) {
			return true;
		}
		else {
			return false;
		}
	}

	public String withdraw(int withdrawAmount) {
		setAtmSafe();
		if(accountService.isCorrectFunds(withdrawAmount) && IsSufficiantFundsInSafe(withdrawAmount)) {
			accountService.withdraw(withdrawAmount);
			return calulateNotes(withdrawAmount);
		}
		else {
			if(!accountService.isCorrectFunds(withdrawAmount)) {
				return "Account Not signed in.";
			}
			else if(!IsSufficiantFundsInSafe(withdrawAmount)) {
				return "Insufficient funds in safe.";
			}
			else {
				return "Please sign in to an account.";
			}
		}
	}
	
	public String calulateNotes(int ans) {
		
		//50
		int fiftys = ans/50;
		if(fiftys <= atmSafe.getFiftys()) {			
			ans = ans - (fiftys*50);
			if(ans == 0) {
				removeFiftys(fiftys);
				return "You are now recieving [fiftys= " +fiftys + "]";
			}
		}
		else {
			while(fiftys > atmSafe.getFiftys()) {
				fiftys --;
			}
			ans = ans - (fiftys*50);
		}
		removeFiftys(fiftys);
		
		
		//20
		int twentys = ans/20;
		if(twentys <= atmSafe.getTwentys()) {			
			ans = ans - (twentys*20);
			if(ans == 0) {
				removeTwentys(twentys);
				return "You are now recieving [fiftys= " +fiftys + ", twentys= " +twentys + "]";
			}
		}
		else {
			while(twentys > atmSafe.getTwentys()) {
				twentys --;
			}
			ans = ans - (twentys*20);
		}
		removeTwentys(twentys);
		
		
		//10
		int tens = ans/10;
		if(tens <= atmSafe.getTens()) {			
			ans = ans - (tens*10);
			if(ans == 0) {
				removeTens(tens);
				return "You are now recieving [fiftys= " +fiftys + ", twentys= " +twentys + ", tens=" + tens +"]";
			}
		}
		else {
			while(tens > atmSafe.getTens()) {
				tens --;
			}
			ans = ans - (tens*10);
		}
		removeTens(tens);
		
		
		//5
		int fives = ans/5;
		removeFives(fives);
		return "You are now recieving [fiftys= " +fiftys + ", twentys= " +twentys + ", tens=" + tens + ", fives=" + fives + "]";
		
	}

	private void removeFiftys(int fiftys) {
		int ans = atmSafe.getFiftys() - fiftys;
		atmSafe.setFiftys(ans);
	}
	
	private void removeTwentys(int twentys) {
		int ans = atmSafe.getTwentys() - twentys;
		atmSafe.setTwentys(ans);
	}
	
	private void removeTens(int tens) {
		int ans = atmSafe.getTens() - tens;
		atmSafe.setTens(ans);
	}
	
	private void removeFives(int fives) {
		int ans = atmSafe.getFives() - fives;
		atmSafe.setFives(ans);
	}
	
	public int CalculatingHowManyNotes(int ans, int nomination) {
		return ans;
	}
}
