package com.example.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.db.MyDatabaseHelper;
import com.example.db.MySQLiteOpenHelper;
import com.example.object.InputInfo;
import com.example.sqlitetest.R;

public class SecondInGroupDao {
	
	private ArrayList<InputInfo> myArrayList=null;
	private Context myContext;
	private MySQLiteOpenHelper myMySQLiteOpenHelper = MyDatabaseHelper._MySQLiteOpenHelper;
	private MyDatabaseHelper myMyDatabaseHelper = null;
	private final String MYLOG="myLog";
	private final String TAG="SecondInGroupDao:";
	
	public SecondInGroupDao(Context pContext){
		
		myMyDatabaseHelper = new MyDatabaseHelper(myContext);
		this.myContext=pContext;
	}

	public ArrayList<InputInfo> getMyArrayList(String pStartTime) {
		
		myArrayList=new ArrayList<InputInfo>();
		InputDao _InputDao = new InputDao(myContext);
		String _Sql1="select secondInGroupNo,inputTime,inputMoney from Input where inputTime=?";
		String _Sql2="select secondInGroupName from SecondInGroup where secondInGroupNo=?";
		Cursor _Cursor=_InputDao.getQueryCursor(_Sql1, new String[]{pStartTime});
		while(_Cursor.moveToNext()){
			InputInfo _InputInfo = new InputInfo();
			Cursor _Cursor2 = this.getQueryCursor(_Sql2, new String[]{_Cursor.getString(0)});
			Log.i(MYLOG, TAG+"secondInGroupNo:"+_Cursor.getString(0)); 
			Log.i(MYLOG, TAG+"inputTime:"+_Cursor.getString(1)); 
			Log.i(MYLOG, TAG+"secondInGroupMoney:"+_Cursor.getString(2)); 
			while(_Cursor2.moveToNext()){
				_InputInfo.setMySecondInGroupName(_Cursor2.getString(0));
				Log.i(MYLOG, TAG+"secondInGroupName:"+_Cursor2.getString(0)); 
			}
			_InputInfo.setMyInputTime(_Cursor.getString(1));
			_InputInfo.setMyInputMoney(_Cursor.getString(2));
//			_InputInfo.setMyImag(R.drawable.icon_qtzx);
			myArrayList.add(_InputInfo);
		}
		return myArrayList;
	}
	
	public ArrayList<InputInfo> getMyArrayList(){
		myArrayList=new ArrayList<InputInfo>();
		InputDao _InputDao = new InputDao(myContext);
		String _Sql1="select secondInGroupNo,inputTime,inputMoney from Input";
		String _Sql2="select secondInGroupName from SecondInGroup where secondInGroupNo=?";
		Cursor _Cursor=_InputDao.getQueryCursor(_Sql1, null);
		while(_Cursor.moveToNext()){
			InputInfo _InputInfo = new InputInfo();
			Cursor _Cursor2 = this.getQueryCursor(_Sql2, new String[]{_Cursor.getString(0)});
			Log.i(MYLOG, TAG+"secondInGroupNo:"+_Cursor.getString(0)); 
			Log.i(MYLOG, TAG+"inputTime:"+_Cursor.getString(1)); 
			Log.i(MYLOG, TAG+"secondInGroupMoney:"+_Cursor.getString(2)); 
			while(_Cursor2.moveToNext()){
				_InputInfo.setMySecondInGroupName(_Cursor2.getString(0));
				Log.i(MYLOG, TAG+"secondInGroupName:"+_Cursor2.getString(0)); 
			}
			_InputInfo.setMyInputTime(_Cursor.getString(1));
			_InputInfo.setMyInputMoney(_Cursor.getString(2));
//			_InputInfo.setMyImag(R.drawable.icon_qtzx);
			myArrayList.add(_InputInfo);
		}
		return myArrayList;
	}
	
	public ArrayList<InputInfo> getMyArrayList(String pStartTime,String pEndTime){
		
		myArrayList=new ArrayList<InputInfo>();
		InputDao _InputDao = new InputDao(myContext);
		String _Sql1="select secondInGroupNo,inputTime,inputMoney from Input where inputTime>=? and inputTime<=?";
		String _Sql2="select secondInGroupName from SecondInGroup where secondInGroupNo=?";
		Cursor _Cursor=_InputDao.getQueryCursor(_Sql1, new String[]{pStartTime,pEndTime});
		while(_Cursor.moveToNext()){
			InputInfo _InputInfo = new InputInfo();
			Cursor _Cursor2 = this.getQueryCursor(_Sql2, new String[]{_Cursor.getString(0)});
			Log.i(MYLOG, TAG+"secondInGroupNo:"+_Cursor.getString(0)); 
			Log.i(MYLOG, TAG+"inputTime:"+_Cursor.getString(1)); 
			Log.i(MYLOG, TAG+"secondInGroupMoney:"+_Cursor.getString(2)); 
			while(_Cursor2.moveToNext()){
				_InputInfo.setMySecondInGroupName(_Cursor2.getString(0));
				Log.i(MYLOG, TAG+"secondInGroupName:"+_Cursor2.getString(0)); 
			}
			_InputInfo.setMyInputTime(_Cursor.getString(1));
			_InputInfo.setMyInputMoney(_Cursor.getString(2));
//			_InputInfo.setMyImag(R.drawable.icon_qtzx);
			myArrayList.add(_InputInfo);
		}
		return myArrayList;
	}
	
	public Cursor getQueryCursor(String pSql,String[] pSectionArgs){
		
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(pSql, pSectionArgs);
		return _Cursor;
	}
	
	
}
