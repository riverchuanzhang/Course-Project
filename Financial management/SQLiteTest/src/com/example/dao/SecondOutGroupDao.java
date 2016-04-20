package com.example.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.db.MyDatabaseHelper;
import com.example.db.MySQLiteOpenHelper;
import com.example.object.OutputInfo;
import com.example.object.SecondOutGroupInfo;
import com.example.sqlitetest.R;

public class SecondOutGroupDao {

	private ArrayList<OutputInfo> myArrayList=null;
	private Context myContext;
	private MyDatabaseHelper myMyDatabaseHelper=null;
	private MySQLiteOpenHelper mySQLiteOpenHelper = MyDatabaseHelper._MySQLiteOpenHelper;
	private final String TAG="SecondOutGroupDao:";
	private final String MYLOG="myLog";
	
	public SecondOutGroupDao(Context pContext){
		
		this.myContext=pContext;
		myMyDatabaseHelper=new MyDatabaseHelper(myContext);
	}

	public ArrayList<OutputInfo> getMyArrayList(String pStartTime) {
		
		myArrayList= new ArrayList<OutputInfo>();
		OutputDao _OutputDao = new OutputDao(myContext);
		SecondOutGroupDao _SecondOutGroupDao = new SecondOutGroupDao(myContext);
		String _SqlOutput1="select secondOutGroupNo, outputTime,outputMoney from Output where outputTime=?";
		String _SqlSecontOutGroup="select secondOutGroupName from SecondOutGroup where secondOutGroupNo=?";
		Cursor _Cursor2 = _OutputDao.getQueryCursor(_SqlOutput1, new String[]{pStartTime});
		while(_Cursor2.moveToNext()){
			OutputInfo _OutputInfo = new OutputInfo();
			Cursor _Cursor1=_SecondOutGroupDao.getQueryCursor(_SqlSecontOutGroup, new String[]{_Cursor2.getString(0)});
			Log.i(MYLOG, TAG+"secondOutGroupNo:"+_Cursor2.getString(0));
			Log.i(MYLOG, TAG+"outPutTime:"+_Cursor2.getString(1));
			Log.i(MYLOG, TAG+"outPutMoney:"+_Cursor2.getString(2));
			while(_Cursor1.moveToNext()){
				_OutputInfo.setMySecondOutGroupName(_Cursor1.getString(0));
				Log.i(MYLOG, TAG+"secondOutGroupName:"+_Cursor1.getString(0));
			}
			_OutputInfo.setMyOutputTime(_Cursor2.getString(1));
			_OutputInfo.setMyOutputMoney(_Cursor2.getString(2));
			_OutputInfo.setMyImag(R.drawable.icon_rqwl_csjz);
			myArrayList.add(_OutputInfo);
		}
		return myArrayList;
	}
	
	public ArrayList<OutputInfo> getMyArrayList(){
		
		myArrayList= new ArrayList<OutputInfo>();
		OutputDao _OutputDao = new OutputDao(myContext);
		SecondOutGroupDao _SecondOutGroupDao = new SecondOutGroupDao(myContext);
		String _SqlOutput1="select secondOutGroupNo, outputTime,outputMoney from Output";
		String _SqlSecontOutGroup="select secondOutGroupName from SecondOutGroup where secondOutGroupNo=?";
		Cursor _Cursor2 = _OutputDao.getQueryCursor(_SqlOutput1, null);
		while(_Cursor2.moveToNext()){
			OutputInfo _OutputInfo = new OutputInfo();
			Cursor _Cursor1=_SecondOutGroupDao.getQueryCursor(_SqlSecontOutGroup, new String[]{_Cursor2.getString(0)});
			Log.i(MYLOG, TAG+"secondOutGroupNo:"+_Cursor2.getString(0));
			Log.i(MYLOG, TAG+"outPutTime:"+_Cursor2.getString(1));
			Log.i(MYLOG, TAG+"outPutMoney:"+_Cursor2.getString(2));
			while(_Cursor1.moveToNext()){
				_OutputInfo.setMySecondOutGroupName(_Cursor1.getString(0));
				Log.i(MYLOG, TAG+"secondOutGroupName:"+_Cursor1.getString(0));
			}
			_OutputInfo.setMyOutputTime(_Cursor2.getString(1));
			_OutputInfo.setMyOutputMoney(_Cursor2.getString(2));
			_OutputInfo.setMyImag(R.drawable.icon_rqwl_csjz);
			myArrayList.add(_OutputInfo);
		}
		return myArrayList;
	}
	
	public ArrayList<OutputInfo> getMyArrayList(String pStartTime,String pEndTime) {
		
		myArrayList= new ArrayList<OutputInfo>();
		OutputDao _OutputDao = new OutputDao(myContext);
		SecondOutGroupDao _SecondOutGroupDao = new SecondOutGroupDao(myContext);
		String _SqlOutput1="select secondOutGroupNo, outputTime,outputMoney from Output where outputTime>=? and outputTime<=?";
		String _SqlSecontOutGroup="select secondOutGroupName from SecondOutGroup where secondOutGroupNo=?";
		Cursor _Cursor2 = _OutputDao.getQueryCursor(_SqlOutput1, new String[]{pStartTime,pEndTime});
		while(_Cursor2.moveToNext()){
			OutputInfo _OutputInfo = new OutputInfo();
			Cursor _Cursor1=_SecondOutGroupDao.getQueryCursor(_SqlSecontOutGroup, new String[]{_Cursor2.getString(0)});
			Log.i(MYLOG, TAG+"secondOutGroupNo:"+_Cursor2.getString(0));
			Log.i(MYLOG, TAG+"outPutTime:"+_Cursor2.getString(1));
			Log.i(MYLOG, TAG+"outPutMoney:"+_Cursor2.getString(2));
			while(_Cursor1.moveToNext()){
				_OutputInfo.setMySecondOutGroupName(_Cursor1.getString(0));
				Log.i(MYLOG, TAG+"secondOutGroupName:"+_Cursor1.getString(0));
			}
			_OutputInfo.setMyOutputTime(_Cursor2.getString(1));
			_OutputInfo.setMyOutputMoney(_Cursor2.getString(2));
			_OutputInfo.setMyImag(R.drawable.icon_rqwl_csjz);
			myArrayList.add(_OutputInfo);
		}
		return myArrayList;
	}
	
	public Cursor getQueryCursor(String pSql,String[] pSectionArgs){
		
		Cursor _Cursor=myMyDatabaseHelper.rawQuery(pSql, pSectionArgs);
		return _Cursor;
	}


	
	
}
