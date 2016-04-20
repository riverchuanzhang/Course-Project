package com.example.object;

public class TransferAccountInfo {

	private String myAccountOutNo;
	private String myAccountInNo;
	private String myTransferMoney;
	private String myProjectNo;
	private String myTransferTime;
	
	public String getMyTransferTime() {
		return myTransferTime;
	}

	public void setMyTransferTime(String myTransferTime) {
		this.myTransferTime = myTransferTime;
	}

	public TransferAccountInfo(){
		
	}

	public String getMyAccountOutNo() {
		return myAccountOutNo;
	}

	public void setMyAccountOutNo(String myAccountOutNo) {
		this.myAccountOutNo = myAccountOutNo;
	}

	public String getMyAccountInNo() {
		return myAccountInNo;
	}

	public void setMyAccountInNo(String myAccountInNo) {
		this.myAccountInNo = myAccountInNo;
	}

	public String getMyTransferMoney() {
		return myTransferMoney;
	}

	public void setMyTransferMoney(String myTransferMoney) {
		this.myTransferMoney = myTransferMoney;
	}

	public String getMyProjectNo() {
		return myProjectNo;
	}

	public void setMyProjectNo(String myProjectNo) {
		this.myProjectNo = myProjectNo;
	}
	
}
