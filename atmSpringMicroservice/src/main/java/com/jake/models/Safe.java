package com.jake.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Safe implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	public int fiftys;
	public int twentys;
	public int tens;
	public int fives;

	@Override
	public String toString() {
		return "Safe [fiftys=" + fiftys + ", twentys="
				+ twentys + ", tens=" + tens + ", fives=" + fives + "]";
	}
	
	public String displayTotalSafe() {
		return "Safe holds = €"+ TotalInSafe();
	}
	
	public int TotalInSafe() {
		int ans = (fiftys*50) + (twentys*20) + (tens*10) + (fives*5);
		return ans;
	}
}
