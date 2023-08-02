package kr.ac.kopo.vo;

public class BankAccountVO {
	// 하나라도 바꾸면 매우 치명적인 문제가 생길 수 있음. 추가하기만 할것
	private String bankCode;
	private String acNumber;
	private String userId;
	private String acPassword;
	private String acName;
	private long balance;
	private int rate;
	private String acOpenDate;
	private String acEndDate;
	private String transferType;
	private long regularTransfer;
	private String userMemo;
	private int state;
	private String transferAcNumber;
	private String currency;
	private String currencyKR;
	
	
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAcNumber() {
		return acNumber;
	}
	public void setAcNumber(String acNumber) {
		this.acNumber = acNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAcPassword() {
		return acPassword;
	}
	public void setAcPassword(String acPassword) {
		this.acPassword = acPassword;
	}
	
	public String getAcName() {
		return acName;
	}
	public void setAcName(String acName) {
		this.acName = acName;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getAcOpenDate() {
		return acOpenDate;
	}
	public void setAcOpenDate(String acOpenDate) {
		this.acOpenDate = acOpenDate;
	}
	public String getAcEndDate() {
		return acEndDate;
	}
	public void setAcEndDate(String acEndDate) {
		this.acEndDate = acEndDate;
	}
	public String getTransferType() {
		return transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	public long getRegularTransfer() {
		return regularTransfer;
	}
	public void setRegularTransfer(long regularTransfer) {
		this.regularTransfer = regularTransfer;
	}
	public String getUserMemo() {
		return userMemo;
	}
	public void setUserMemo(String userMemo) {
		this.userMemo = userMemo;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
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
	public String getTransferAcNumber() {
		return transferAcNumber;
	}
	public void setTransferAcNumber(String transferAcNumber) {
		this.transferAcNumber = transferAcNumber;
	}
	
	@Override
	public String toString() {
		return "BankAccountVO [bankCode=" + bankCode + ", acNumber=" + acNumber + ", userId=" + userId + ", acPassword="
				+ acPassword + ", acName=" + acName + ", balance=" + balance + ", rate=" + rate + ", acOpenDate="
				+ acOpenDate + ", acEndDate=" + acEndDate + ", transferType=" + transferType + ", userMemo=" + userMemo
				+ ", state=" + state + "]";
	}
	
	
}
