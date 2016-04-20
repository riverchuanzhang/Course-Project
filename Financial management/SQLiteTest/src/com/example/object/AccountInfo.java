package com.example.object;

public class AccountInfo {

	private String myAccountNo=null;
	private String myAccountName=null;
	private String myAccountMoney=null;
	
	public AccountInfo(){
		
	}
	
	public AccountInfo(String pAccountNo,String pAccountName){
		this.myAccountName=pAccountName;
		this.myAccountNo=pAccountNo;
	}

	public String getMyAccountNo() {
		return myAccountNo;
	}

	public void setMyAccountNo(String myAccountNo) {
		this.myAccountNo = myAccountNo;
	}

	public String getMyAccountName() {
		return myAccountName;
	}

	public void setMyAccountName(String myAccountName) {
		this.myAccountName = myAccountName;
	}

	public String getMyAccountMoney() {
		return myAccountMoney;
	}

	public void setMyAccountMoney(String myAccountMoney) {
		this.myAccountMoney = myAccountMoney;
	}
	
	
}
