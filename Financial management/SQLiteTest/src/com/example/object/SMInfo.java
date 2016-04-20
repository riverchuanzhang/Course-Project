package com.example.object;

public class SMInfo {

	private String myMemberNo=null;
	private String myManagerGroupNo=null;
	
	public SMInfo(){
		
	}
	
	public SMInfo(String pMemberNo,String pManagerGroupNo){
		
		this.myManagerGroupNo=pManagerGroupNo;
		this.myMemberNo=pMemberNo;
	}

	public String getMyMemberNo() {
		return myMemberNo;
	}

	public void setMyMemberNo(String myMemberNo) {
		this.myMemberNo = myMemberNo;
	}

	public String getMyManagerGroupNo() {
		return myManagerGroupNo;
	}

	public void setMyManagerGroupNo(String myManagerGroupNo) {
		this.myManagerGroupNo = myManagerGroupNo;
	}
	
}
