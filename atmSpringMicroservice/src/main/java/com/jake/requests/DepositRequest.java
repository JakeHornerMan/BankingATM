package com.jake.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest {
	public int id;
	public int fiftys;
	public int twentys;
	public int tens;
	public int fives;
}
