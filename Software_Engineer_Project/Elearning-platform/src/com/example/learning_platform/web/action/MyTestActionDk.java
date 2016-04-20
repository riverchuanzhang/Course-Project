package com.example.learning_platform.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class MyTestActionDk extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4845587985491095726L;
	@Override
	public String execute() throws Exception {
		System.out.println("TestAction234");
		return "test444";
	}

}
