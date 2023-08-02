package kr.ac.kopo.service;

import java.text.DecimalFormat;

public class NumberUtil {

	public String asCurrency(long num) {
		
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(num);
        
		return formattedNumber;
	}
	
	public String asCurrencyWithKorean(long num) {
		
		String sNum = Long.toString(num);
		
		StringBuilder korean = new StringBuilder();
		int digitsCnt = 0;
		
		for (int i = 0; i < sNum.length(); ++i) {
			if(i % 4 == 0) {
				
				switch (digitsCnt) {
				case 1:
					korean.append(": ��:");
					break;
				case 2:
					korean.append(": ��:");
					break;
				case 3:
					korean.append(": ��:");
					break;
				case 4:
					korean.append(": ��:");
					break;
				default:
					break;
				}
				digitsCnt ++;
			}
			
			korean.append(sNum.charAt(sNum.length()-1 - i));
		}
		korean = korean.reverse();
		
		StringBuilder result = new StringBuilder();
		String[] kArr =  korean.toString().split(":");
		
		for(int i = 0; i < kArr.length; ++i) {
			
			if(i % 2 == 0) { // Ȧ���϶� �׻� ������
				int chNum = Integer.parseInt(kArr[i]);
				String chStr = Integer.toString(chNum);
				kArr[i] = chStr;
			}	
			
			if(i != kArr.length) {		
				result.append(":"+kArr[i]);
			} 
		}
		
		
		String r = result.toString();

		r = r.replace(":0:��", "");

		r = r.replace(":0:��", "");

		r = r.replace(":0:��", "");
		
		r = r.replace(":0", "");

		r = r.replace(":", "");
		
		if(r.equals("")) {
			r = "0";
		}

		return r;
	}
}
