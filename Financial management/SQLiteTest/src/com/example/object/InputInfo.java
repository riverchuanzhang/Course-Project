package com.example.object;

public class InputInfo {

	private String myInputTime=null;
	private String myInputMoney=null;
	private String myAccountNo=null;
	private String myFirstInGroupNo=null;
	private String mySecondInGroupNo=null;
	private String myProjectNo=null;
	private String myAccountName=null;
	private String myFirstInGroupName=null;
	private String mySecondInGroupName=null;
	private String myProjectName=null;
	private int myImag = 0;
	
	public int getMyImag() {
		return myImag;
	}

	public void setMyImag(int myImag) {
		this.myImag = myImag;
	}

	public String getMyAccountName() {
		return myAccountName;
	}

	public void setMyAccountName(String myAccountName) {
		this.myAccountName = myAccountName;
	}

	public String getMyFirstInGroupName() {
		return myFirstInGroupName;
	}

	public void setMyFirstInGroupName(String myFirstInGroupName) {
		this.myFirstInGroupName = myFirstInGroupName;
	}

	public String getMySecondInGroupName() {
		return mySecondInGroupName;
	}

	public void setMySecondInGroupName(String mySecondInGroupName) {
		this.mySecondInGroupName = mySecondInGroupName;
	}

	public String getMyProjectName() {
		return myProjectName;
	}

	public void setMyProjectName(String myProjectName) {
		this.myProjectName = myProjectName;
	}

	public InputInfo(){
		
	}
	
	public InputInfo(String pInputTime,String pInputMoney,String pAccountNo,
			String pFirstInGroupNo,String pSecondInGroupNo,String pProjectNo){
		
		this.myInputTime=pInputTime;
		this.myInputMoney=pInputMoney;
		this.myAccountNo=pAccountNo;
		this.myFirstInGroupNo=pFirstInGroupNo;
		this.mySecondInGroupNo=pSecondInGroupNo;
		this.myProjectNo=pProjectNo;
		
	}

	public String getMyInputTime() {
		return myInputTime;
	}

	public void setMyInputTime(String myInputTime) {
		this.myInputTime = myInputTime;
	}

	public String getMyInputMoney() {
		return myInputMoney;
	}

	public void setMyInputMoney(String myInputMoney) {
		this.myInputMoney = myInputMoney;
	}

	public String getMyAccountNo() {
		return myAccountNo;
	}

	public void setMyAccountNo(String myAccountNo) {
		this.myAccountNo = myAccountNo;
	}

	public String getMyFirstInGroupNo() {
		return myFirstInGroupNo;
	}

	public void setMyFirstInGroupNo(String myFirstInGroupNo) {
		this.myFirstInGroupNo = myFirstInGroupNo;
	}

	public String getMySecondInGroupNo() {
		return mySecondInGroupNo;
	}

	public void setMySecondInGroupNo(String mySecondInGroupNo) {
		this.mySecondInGroupNo = mySecondInGroupNo;
	}

	public String getMyProjectNo() {
		return myProjectNo;
	}

	public void setMyProjectNo(String myProjectNo) {
		this.myProjectNo = myProjectNo;
	}
	
}
