package kr.ac.kopo.service;

import java.util.Random;

import kr.ac.kopo.dao.SavingAccountDAO;

public class MakeAccountNumber {
	
	public String make() {
		Random rd = new Random();
		boolean isNewAccount = false;
		String accountNumber = null;
		String BANK_CD = "37";
		
		while (!isNewAccount){
			
			
			long randNum = rd.nextLong() % 10000000000000L;
			
			
			
			if (randNum < 0) {
				randNum *= -1;
			}
			
			accountNumber = BANK_CD + String.format("%013d", randNum);
			
			
			
			isNewAccount = new SavingAccountDAO().accountNumberCheck(accountNumber);
		}
		
		return accountNumber;
	}
}
