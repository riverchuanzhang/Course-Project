package com.example.object;

public class OutputInfo {

	private String myOutputTime=null;
	private String myOutputMoney=null;
	private String myAccountNo=null;
	private String myFirstOutGroupNo=null;
	private String mySecondOutGroupNo=null;
	private String myBusinessNo=null;
	private String myProjectNo=null;
	private String myAccountName=null;
	private String myFirstOutGroupName=null;
	private String mySecondOutGroupName=null;
	private String myBusinessName=null;
	private String myProjectName=null;
	private int myImag=0;
	
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

	public String getMyFirstOutGroupName() {
		return myFirstOutGroupName;
	}

	public void setMyFirstOutGroupName(String myFirstOutGroupName) {
		this.myFirstOutGroupName = myFirstOutGroupName;
	}

	public String getMySecondOutGroupName() {
		return mySecondOutGroupName;
	}

	public void setMySecondOutGroupName(String mySecondOutGroupName) {
		this.mySecondOutGroupName = mySecondOutGroupName;
	}

	public String getMyBusinessName() {
		return myBusinessName;
	}

	public void setMyBusinessName(String myBusinessName) {
		this.myBusinessName = myBusinessName;
	}

	public String getMyProjectName() {
		return myProjectName;
	}

	public void setMyProjectName(String myProjectName) {
		this.myProjectName = myProjectName;
	}

	public OutputInfo(){
		
	}
	
	public OutputInfo(String pOutputTime,String pOutputMoney,String pAcountNo,
			String pFirstOutGroupNo,String pSecondOutGroupNo,String pBusinessNo,String pProjectNo){
		this.myOutputTime=pOutputTime;
		this.myOutputMoney=pOutputMoney;
		this.myAccountNo=pAcountNo;
		this.myFirstOutGroupNo=pFirstOutGroupNo;
		this.mySecondOutGroupNo=pSecondOutGroupNo;
		this.myBusinessNo=pBusinessNo;
		this.myProjectNo=pProjectNo;
	}

	public String getMyOutputTime() {
		return myOutputTime;
	}

	public void setMyOutputTime(String myOutputTime) {
		this.myOutputTime = myOutputTime;
	}

	public String getMyOutputMoney() {
		return myOutputMoney;
	}

	public void setMyOutputMoney(String myOutputMoney) {
		this.myOutputMoney = myOutputMoney;
	}

	public String getMyAccountNo() {
		return myAccountNo;
	}

	public void setMyAccountNo(String myAccountNo) {
		this.myAccountNo = myAccountNo;
	}

	public String getMyFirstOutGroupNo() {
		return myFirstOutGroupNo;
	}

	public void setMyFirstOutGroupNo(String myFirstOutGroupNo) {
		this.myFirstOutGroupNo = myFirstOutGroupNo;
	}

	public String getMySecondOutGroupNo() {
		return mySecondOutGroupNo;
	}

	public void setMySecondOutGroupNo(String mySecondOutGroupNo) {
		this.mySecondOutGroupNo = mySecondOutGroupNo;
	}

	public String getMyBusinessNo() {
		return myBusinessNo;
	}

	public void setMyBusinessNo(String myBusinessNo) {
		this.myBusinessNo = myBusinessNo;
	}

	public String getMyProjectNo() {
		return myProjectNo;
	}

	public void setMyProjectNo(String myProjectNo) {
		this.myProjectNo = myProjectNo;
	}
	
}
