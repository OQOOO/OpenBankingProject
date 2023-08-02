package kr.ac.kopo.vo;

public class HistoryVO {
	
	private int trNum;
	private String AcNumber;
	private String trAcNumber;
	private String trName;
	private long amount;
	private String trTime;
	private String currency;
	private String currencyKR;
	
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyKR() {
		return currencyKR;
	}

	public void setCurrencyKR(String currencyKR) {
		this.currencyKR = currencyKR;
	}

	
	
	@Override
	public String toString() {
		return "HistoryVO [trNum=" + trNum + ", AcNumber=" + AcNumber + ", trAcNumber=" + trAcNumber + ", trName="
				+ trName + ", amount=" + amount + ", trTime=" + trTime + "]";
	}

	public int getTrNum() {
		return trNum;
	}

	public void setTrNum(int trNum) {
		this.trNum = trNum;
	}

	public String getAcNumber() {
		return AcNumber;
	}

	public void setAcNumber(String acNumber) {
		AcNumber = acNumber;
	}

	public String getTrAcNumber() {
		return trAcNumber;
	}

	public void setTrAcNumber(String trAcNumber) {
		this.trAcNumber = trAcNumber;
	}

	public String getTrName() {
		return trName;
	}

	public void setTrName(String trName) {
		this.trName = trName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getTrTime() {
		return trTime;
	}

	public void setTrTime(String trTime) {
		this.trTime = trTime;
	}
	
	
}
