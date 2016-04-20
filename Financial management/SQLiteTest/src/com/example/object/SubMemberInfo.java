package com.example.object;

public class SubMemberInfo {

	private String myMemberNo=null;
	private String myMemberName=null;
	
	public SubMemberInfo(){
		
	}
	
	public SubMemberInfo(String pMemberNo,String pMemberName){
		
		this.myMemberName=pMemberName;
		this.myMemberNo=pMemberNo;
	}

	public String getMyMemberNo() {
		return myMemberNo;
	}

	public void setMyMemberNo(String myMemberNo) {
		this.myMemberNo = myMemberNo;
	}

	public String getMyMemberName() {
		return myMemberName;
	}

	public void setMyMemberName(String myMemberName) {
		this.myMemberName = myMemberName;
	}
	
}
