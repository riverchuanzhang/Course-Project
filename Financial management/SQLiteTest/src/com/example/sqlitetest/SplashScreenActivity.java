package com.example.sqlitetest;

import java.util.HashMap;
import java.util.ListIterator;
import com.example.db.DataInfo;
import com.example.db.MyDatabaseHelper;
import com.example.db.MySQLiteOpenHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity{

	public static MyDatabaseHelper myMyDatabaseHelper=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.splash_screen_activity);
		this.initDatabase();
		this.initData();
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run(){
				Intent intent = new Intent (SplashScreenActivity.this,MainActivity.class);			
				startActivity(intent);			
				SplashScreenActivity.this.finish();
			}
		}, 700);
		
	}

	private boolean initDatabase(){
		
		myMyDatabaseHelper=new MyDatabaseHelper(this);
		myMyDatabaseHelper.createDatabase("FincialManager16", 1);
		return true;
	}
	
	private boolean initData(){
		
		ListIterator<HashMap<Integer,String>> _ListIterator = new DataInfo().getUserInfo().listIterator();
		while(_ListIterator.hasNext()){
			HashMap<Integer,String> _HashMap=_ListIterator.next();
			myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[0],MySQLiteOpenHelper.USER_ITEMS[0], 
					new String[]{_HashMap.get(0),_HashMap.get(1),_HashMap.get(2)});
		}
		_ListIterator=new DataInfo().getBusinessInfo().listIterator();
		while(_ListIterator.hasNext()){
			HashMap<Integer,String> _HashMap=_ListIterator.next();
			myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[4],MySQLiteOpenHelper.USER_ITEMS[4], 
					new String[]{_HashMap.get(0),_HashMap.get(1)});
		}
		_ListIterator=new DataInfo().getFirstOutGroupInfo().listIterator();
		while(_ListIterator.hasNext()){
			HashMap<Integer,String> _HashMap=_ListIterator.next();
			myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[7],MySQLiteOpenHelper.USER_ITEMS[7], 
					new String[]{_HashMap.get(0),_HashMap.get(1),_HashMap.get(2)});
		}
		_ListIterator=new DataInfo().getSecondOutGroupInfo().listIterator();
		while(_ListIterator.hasNext()){
			HashMap<Integer,String> _HashMap=_ListIterator.next();
			myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[8],MySQLiteOpenHelper.USER_ITEMS[8], 
					new String[]{_HashMap.get(0),_HashMap.get(1),_HashMap.get(2),_HashMap.get(3)});
		}
		_ListIterator=new DataInfo().getAccountInfo().listIterator();
		while(_ListIterator.hasNext()){
			HashMap<Integer,String> _HashMap=_ListIterator.next();
			myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[3],MySQLiteOpenHelper.USER_ITEMS[3], 
					new String[]{_HashMap.get(0),_HashMap.get(1),_HashMap.get(2)});
		}
		_ListIterator=new DataInfo().getProjectInfo().listIterator();
		while(_ListIterator.hasNext()){
			HashMap<Integer,String> _HashMap=_ListIterator.next();
			myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[9],MySQLiteOpenHelper.USER_ITEMS[9], 
					new String[]{_HashMap.get(0),_HashMap.get(1)});
		}
		_ListIterator=new DataInfo().getFirstInGroupInfo().listIterator();
		while(_ListIterator.hasNext()){
			HashMap<Integer,String> _HashMap=_ListIterator.next();
			myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[5],MySQLiteOpenHelper.USER_ITEMS[5], 
					new String[]{_HashMap.get(0),_HashMap.get(1)});
		}
		_ListIterator=new DataInfo().getSecondInGroupInfo().listIterator();
		while(_ListIterator.hasNext()){
			HashMap<Integer,String> _HashMap=_ListIterator.next();
			myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[6],MySQLiteOpenHelper.USER_ITEMS[6], 
					new String[]{_HashMap.get(0),_HashMap.get(1),_HashMap.get(2)});
		}
		return true;
	}
}
