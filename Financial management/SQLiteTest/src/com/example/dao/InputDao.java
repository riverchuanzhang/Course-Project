package com.example.dao;

import java.util.ArrayList;
import java.util.ListIterator;

import com.example.db.MyDatabaseHelper;
import com.example.db.MySQLiteOpenHelper;
import com.example.object.InputInfo;
import com.example.object.OutputInfo;

import android.content.Context;
import android.database.Cursor;

public class InputDao {

	private Context myContext;
	private MySQLiteOpenHelper myMySQLiteOpenHelper=MyDatabaseHelper._MySQLiteOpenHelper;
	private MyDatabaseHelper myMyDatabaseHelper=null;
	
	public InputDao(Context pContext){
		
		this.myContext=pContext;
		myMyDatabaseHelper = new MyDatabaseHelper(myContext);
	}
	
	public Cursor getQueryCursor(String pSql,String[] pSectionArgs){
		
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(pSql, pSectionArgs);
		return _Cursor;
	}
	
	public String getInputMoneySum(ArrayList<InputInfo> pArrayListIn){
		
		ListIterator<InputInfo> _ListIteratorOut=pArrayListIn.listIterator();
		Double sumIn=0.0;
		while(_ListIteratorOut.hasNext()){
			sumIn += Double.parseDouble(_ListIteratorOut.next().getMyInputMoney());
		}
		return String.valueOf(sumIn);
		
	}
	
	
}
