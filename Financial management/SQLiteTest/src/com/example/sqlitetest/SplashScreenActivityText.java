package com.example.sqlitetest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import com.example.db.DataInfo;
import com.example.db.MyDatabaseHelper;
import com.example.db.MySQLiteOpenHelper;
import com.example.object.UserInfo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ListView;

public class SplashScreenActivityText extends Activity {
	
	public static MyDatabaseHelper myMyDatabaseHelper=null;
	ListView myListView=null;
	Button myButton=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.splash_screen_activity_text2);
		myListView=(ListView)this.findViewById(R.id.test_listview);
		myButton=(Button)this.findViewById(R.id.test_Button);
		this.initDatabase();
		this.testInsert();
		this.testUpdata();
		this.testDelete();
		
	}

	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//activity jump
		Intent _Intent=new Intent(SplashScreenActivityText.this,MainActivity.class);
		startActivity(_Intent);
		this.finish();
		return super.onTouchEvent(event);
	}
	
	private boolean initDatabase(){
		
		Resources _Res = this.getResources();
//		String[] _Array = _Res.getStringArray(R.id)
		myMyDatabaseHelper=new MyDatabaseHelper(this);
		myMyDatabaseHelper.createDatabase("fincialManager5", 1);
		return true;
	}
	
	private boolean initData(){
		ArrayList<UserInfo> _UserInfoItems = new ArrayList<UserInfo>();
		int i=0;
		while(i<3){
			UserInfo _UserInfo=new UserInfo("001","001","zhangdachuan");//·Åµ½array-list
			
		}
		return true;
	}
	
	private boolean testInsert(){
		
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
					new String[]{_HashMap.get(0),_HashMap.get(1)});
		}
		_ListIterator=new DataInfo().getSecondOutGroupInfo().listIterator();
		while(_ListIterator.hasNext()){
			HashMap<Integer,String> _HashMap=_ListIterator.next();
			myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[8],MySQLiteOpenHelper.USER_ITEMS[8], 
					new String[]{_HashMap.get(0),_HashMap.get(1),_HashMap.get(2)});
		}
		_ListIterator=new DataInfo().getAccountInfo().listIterator();
		while(_ListIterator.hasNext()){
			HashMap<Integer,String> _HashMap=_ListIterator.next();
			myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[3],MySQLiteOpenHelper.USER_ITEMS[3], 
					new String[]{_HashMap.get(0),_HashMap.get(1)});
		}
		
				
//		myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[0],MySQLiteOpenHelper.USER_ITEMS[0], new String[] {"001","001","zhangdachuan"});
//		myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[0], MySQLiteOpenHelper.USER_ITEMS[0], new String[]{"002","002","zhongdakang"});
		String sql="select * from User";
		ArrayList<ArrayList<String>> _Items=new ArrayList<ArrayList<String>>();
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(sql, null);
		_Cursor.moveToFirst();
		Log.i("TAG", _Cursor.getString(0));
		Log.i("TAG", _Cursor.getString(1));
		Log.i("TAG", _Cursor.getString(2));
		_Cursor.moveToPrevious();
		while(_Cursor.moveToNext()){
			int i=0;
			ArrayList<String> _Items_child=new ArrayList<String>();
			while(i<=2){
				_Items_child.add(_Cursor.getString(i));
				i++;
			}
			_Items.add(_Items_child);
		}
		myListView.setAdapter(new TestAdapter(this,_Items));
		return true;
	}
	
	private boolean testUpdata(){
		
		myMyDatabaseHelper.updata(MySQLiteOpenHelper.TABLES[0], new String[]{MySQLiteOpenHelper.USER_ITEMS[0][1],MySQLiteOpenHelper.USER_ITEMS[0][2]}, new String[]{"000133","zhengzelin"},
				MySQLiteOpenHelper.USER_ITEMS[0][0]+"==?", new String[]{"001"});
		return true;
	}
	
	private boolean testDelete(){
		
		myMyDatabaseHelper.delete(MySQLiteOpenHelper.TABLES[0],MySQLiteOpenHelper.USER_ITEMS[0][0]+"==?",new String[]{"001"});
		return true;
	}
	
	private boolean test(){
		this.testDelete();
		return true;
	}
	
}
