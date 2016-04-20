package com.example.object;

public class ManagerGroupInfo {

	private String myManagerGroupNo=null;
	private String myManagerGroupName=null;
	
	public ManagerGroupInfo(){
		
	}
	
	public ManagerGroupInfo(String pManagerGroupNo,String pManagerGroupName){
		
		this.myManagerGroupName=pManagerGroupName;
		this.myManagerGroupNo=pManagerGroupNo;		
	}

	public String getMyManagerGroupNo() {
		return myManagerGroupNo;
	}

	public void setMyManagerGroupNo(String myManagerGroupNo) {
		this.myManagerGroupNo = myManagerGroupNo;
	}

	public String getMyManagerGroupName() {
		return myManagerGroupName;
	}

	public void setMyManagerGroupName(String myManagerGroupName) {
		this.myManagerGroupName = myManagerGroupName;
	}
	
}
