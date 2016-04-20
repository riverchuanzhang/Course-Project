package com.example.db;

import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySQLiteOpenHelper extends SQLiteOpenHelper{
	
	public static final String DATABASE_NAME="FincialManager";
	public static String[] TABLES={"User","ManagerGroup","UM","Account","Business",
		"FirstInGroup","SecondInGroup","FirstOutGroup","SecondOutGroup","Project",
		"SM","SubMember","Input","Output"};
	public static String[][] USER_ITEMS ={
		{"userEmail","userPassword","userName"},
		{"managerGroupNo","managerGroupName"},
		{"managerGroupNo","userEmail"},
		{"accountNo","accountName","accountMoney"},
		{"businessNo","businessName"},
		{"firstInGroupNo","firstInGroupName"},
		{"secondInGroupNo","secondInGroupName","firstInGroupNo"},
		{"firstOutGroupNo","firstOutGroupName","firstOutBudjetMoney"},
		{"secondOutGroupNo","secondOutGroupName","firstOutGroupNo","secondOutBudjetMoney"},
		{"projectNo","projectName"},
		{"memberNo","managerGroupNo"},
		{"memberNo","memberName"},
		{"inputTime","inputMoney","accountNo","firstInGroupNo","secondInGroupNo","projectNo"},
		{"outputTime","outputMoney","accountNo","firstOutGroupNo","secondOutGroupNo","businessNo","projectNo"},
		
		};
	public static String[][] FILE_TYPE={
		{"TEXT primary key","TEXT","TEXT"},
		{"TEXT primary key","TEXT"},
		{"TEXT primary key","TEXT"},
		{"TEXT primary key","TEXT","TEXT"},
		{"TEXT primary key","TEXT"},
		{"TEXT primary key","TEXT"},
		{"TEXT primary key","TEXT","TEXT"},
		{"TEXT primary key","TEXT","TEXT"},
		{"TEXT primary key","TEXT","TEXT","TEXT"},
		{"TEXT primary key","TEXT"},
		{"TEXT primary key","TEXT"},
		{"TEXT primary key","TEXT"},
		{"TEXT","TEXT","TEXT","TEXT","TEXT","TEXT"},
		{"TEXT","TEXT","TEXT","TEXT","TEXT","TEXT","TEXT"}
	};
	
	public MySQLiteOpenHelper(Context pContext,String pDatabaseName,int pDataBaseVersion){
		super(pContext, pDatabaseName, null, pDataBaseVersion);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		int i=0;
		int j=0;
		for(i=0;i<TABLES.length;i++){
				String SQL="create table "+TABLES[i]+"(";
				for(j=0;j<USER_ITEMS[i].length;j++){
					SQL+=USER_ITEMS[i][j]+" "+FILE_TYPE[i][j]+",";
				}
				SQL = SQL.substring(0, SQL.length() - 1);
				SQL+=")";
				db.execSQL(SQL);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		int i=0;
		for(i=0;i<TABLES.length;i++){
			
			db.execSQL("DROP TABLE IF EXIT"+ TABLES[i]);
		}
		onCreate(db);
	}

}



