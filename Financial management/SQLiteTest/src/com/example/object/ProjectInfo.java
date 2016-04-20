package com.example.object;

public class ProjectInfo {
	
	private String myProjectNo=null;
	private String myProjectName=null;
	
	public ProjectInfo(){
		
	}
	
	public ProjectInfo(String pProjectNo,String pProjectName){
		
		this.myProjectName=pProjectName;
		this.myProjectNo=pProjectNo;
	}

	public String getMyProjectNo() {
		return myProjectNo;
	}

	public void setMyProjectNo(String myProjectNo) {
		this.myProjectNo = myProjectNo;
	}

	public String getMyProjectName() {
		return myProjectName;
	}

	public void setMyProjectName(String myProjectName) {
		this.myProjectName = myProjectName;
	}
	
}
