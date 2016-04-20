package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabaseHelper {
	
	private Context myContext;
	public static MySQLiteOpenHelper _MySQLiteOpenHelper;
	
	public MyDatabaseHelper(Context pContext){
		this.myContext=pContext;
	}
	
	public boolean createDatabase(String pDatabaseName,int pDatabaseVersion){
		
		_MySQLiteOpenHelper = new MySQLiteOpenHelper(myContext,pDatabaseName,pDatabaseVersion);
		return true;
	}
	
	public boolean insert(String pTable,String[] field,String[] values){
		
		SQLiteDatabase _Db = _MySQLiteOpenHelper.getWritableDatabase();
		ContentValues _ContentValues=new ContentValues();
		int i=0;
		for(i=0;i<field.length;i++){
			_ContentValues.put(field[i], values[i]);
		}
		_Db.insert(pTable, null, _ContentValues);
		return true;
	}
	
	public Cursor rawQuery(String pSql,String[] pSectionArgs){
		
		SQLiteDatabase _Db = _MySQLiteOpenHelper.getReadableDatabase();
		Cursor _Cursor = _Db.rawQuery(pSql, pSectionArgs); 
		return _Cursor;
	}
	
	public boolean updata(String pTable,String[] pUpdataFiled,String[] pUpdataValues,String pWhere,String[] pWhereValues){
		SQLiteDatabase _Db = _MySQLiteOpenHelper.getWritableDatabase();
		ContentValues _ContentValues=new ContentValues();
		int i=0;
		for(i=0;i<pUpdataFiled.length;i++){
			_ContentValues.put(pUpdataFiled[i], pUpdataValues[i]);
		}
		_Db.update(pTable, _ContentValues, pWhere, pWhereValues);
		return true;
	}
	
	public boolean delete(String pTable,String where,String[] whereValues){
		
		SQLiteDatabase _Db = _MySQLiteOpenHelper.getWritableDatabase();
		_Db.delete(pTable, where, whereValues);
		_Db.close();
		return true;
	}
	
}
