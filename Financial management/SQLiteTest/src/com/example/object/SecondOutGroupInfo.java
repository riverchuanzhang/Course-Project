package com.example.object;

public class SecondOutGroupInfo {

	private String mySecondOutGroupNo=null;
	private String mySecondOutGroupName=null;
	private String myFirstOutGroupNo=null;
	
	public SecondOutGroupInfo(){
		
	}
	
	public SecondOutGroupInfo(String pSecondOutGroupNo,String pSecondOutGroupName,String pFirstOutGroupNo){
		
		this.mySecondOutGroupNo=pSecondOutGroupNo;
		this.mySecondOutGroupName=pSecondOutGroupName;
		this.myFirstOutGroupNo=pFirstOutGroupNo;
	}

	public String getMySecondOutGroupNo() {
		return mySecondOutGroupNo;
	}

	public void setMySecondOutGroupNo(String mySecondOutGroupNo) {
		this.mySecondOutGroupNo = mySecondOutGroupNo;
	}

	public String getMySecondOutGroupName() {
		return mySecondOutGroupName;
	}

	public void setMySecondOutGroupName(String mySecondOutGroupName) {
		this.mySecondOutGroupName = mySecondOutGroupName;
	}

	public String getMyFirstOutGroupNo() {
		return myFirstOutGroupNo;
	}

	public void setMyFirstOutGroupNo(String myFirstOutGroupNo) {
		this.myFirstOutGroupNo = myFirstOutGroupNo;
	}
	
}
