package com.example.dao;

import java.util.ArrayList;
import java.util.ListIterator;

import android.content.Context;
import android.database.Cursor;

import com.example.db.MyDatabaseHelper;
import com.example.db.MySQLiteOpenHelper;
import com.example.object.InputInfo;
import com.example.object.OutputInfo;

public class OutputDao {

	private MyDatabaseHelper myMyDatabaseHelper=null;
	private MySQLiteOpenHelper myMySQLiteOpenHelper = MyDatabaseHelper._MySQLiteOpenHelper;
	private Context myContext;
	
	public OutputDao(Context pContext){
		
		this.myContext=pContext;
		this.myMyDatabaseHelper=new MyDatabaseHelper(myContext);
	}
	
	public Cursor getQueryCursor(String pSql,String[] pSectionArgs){
		
		Cursor _Cursor=myMyDatabaseHelper.rawQuery(pSql, pSectionArgs);
		return _Cursor;

	}
	
	public ArrayList<String> getOutputInfo(){
		
		return null;
	}
	
	public String getOutputMoneySum(ArrayList<OutputInfo> pArrayListIn){
		
		ListIterator<OutputInfo> _ListIteratorIn=pArrayListIn.listIterator();
		Double sumOut = 0.0;
		while(_ListIteratorIn.hasNext()){
			sumOut += Double.parseDouble(_ListIteratorIn.next().getMyOutputMoney());
		}

		return String.valueOf(sumOut);
	}
}
