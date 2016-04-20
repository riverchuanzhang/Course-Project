package com.example.object;

public class SecondInGroupInfo {
	
	private String mySecondInGroupNo=null;
	private String mySecondInGroupName=null;
	private String myFirstInGroupNo=null;
	
	public SecondInGroupInfo(){
		
	}
	
	public SecondInGroupInfo(String pSecondInGroupNo,String pSecondInGroupName,String pFirstInGroupNo){
		
		this.myFirstInGroupNo=pFirstInGroupNo;
		this.mySecondInGroupName=pSecondInGroupName;
		this.mySecondInGroupNo=pSecondInGroupNo;
	}

	public String getMySecondInGroupNo() {
		return mySecondInGroupNo;
	}

	public void setMySecondInGroupNo(String mySecondInGroupNo) {
		this.mySecondInGroupNo = mySecondInGroupNo;
	}

	public String getMySecondInGroupName() {
		return mySecondInGroupName;
	}

	public void setMySecondInGroupName(String mySecondInGroupName) {
		this.mySecondInGroupName = mySecondInGroupName;
	}

	public String getMyFirstInGroupNo() {
		return myFirstInGroupNo;
	}

	public void setMyFirstInGroupNo(String myFirstInGroupNo) {
		this.myFirstInGroupNo = myFirstInGroupNo;
	}
	
}
