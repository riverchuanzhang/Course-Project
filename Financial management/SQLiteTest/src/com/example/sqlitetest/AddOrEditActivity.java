package com.example.sqlitetest;

import java.util.ArrayList;

import com.example.db.MyDatabaseHelper;
import com.example.db.MySQLiteOpenHelper;
import com.example.object.AccountInfo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddOrEditActivity extends Activity{

	private EditText name_et=null;
	private EditText amount_of_money_et=null;
	private Button save_btn=null;
	private Button cancel_btn=null;
	private MySQLiteOpenHelper myMySQLiteOpenHelper=null;
	private MyDatabaseHelper myMyDatabaseHelper=null;
	private AccountInfo myAccountInfo=new AccountInfo();
	private final String MYLOG="myLog";
	private final String TAG="AddOrEditActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.add_or_edit_activity);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		myMyDatabaseHelper=new MyDatabaseHelper(this);
		myMySQLiteOpenHelper=MyDatabaseHelper._MySQLiteOpenHelper;
		this.loadingFormat();
		this.init();
	}
	
	private void init(){
		
		save_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				insertAccount();
			}
			
			
		});
		cancel_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent(AddOrEditActivity.this,AccountActivity.class);
				AddOrEditActivity.this.startActivity(_Intent);
				AddOrEditActivity.this.finish();
				
			}
			
		});

	}
	
	
	
	private void insertAccount(){
		
		String _AccountName=name_et.getText().toString();
		String _AccountMoney=amount_of_money_et.getText().toString();
		Log.i(MYLOG, TAG+"_AccountName:"+_AccountName);
		Log.i(MYLOG, TAG+"_AccountName:"+_AccountMoney);
		if(_AccountMoney.equals("")||_AccountName.equals("")){
			Toast.makeText(AddOrEditActivity.this, "please input 账户名称和转账金额", Toast.LENGTH_SHORT).show();;
		}else{
			myAccountInfo.setMyAccountName(_AccountName);
			myAccountInfo.setMyAccountMoney(_AccountMoney);
			String _Sql = "select * from Account";
			Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, null);
			Log.i(MYLOG, "ddd"+_Cursor.getColumnCount()+"");
			myAccountInfo.setMyAccountNo(String.valueOf(_Cursor.getColumnCount()+1));
			myMyDatabaseHelper.insert(myMySQLiteOpenHelper.TABLES[3], myMySQLiteOpenHelper.USER_ITEMS[3], 
					new String[]{myAccountInfo.getMyAccountNo(),myAccountInfo.getMyAccountName(),
					myAccountInfo.getMyAccountMoney(),"0"});
			Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
			Log.i(MYLOG,"myMyDatabaseHelper:"+myMyDatabaseHelper.toString());
			this.textInsertAccount();
		}

		
	}
	
	private void textInsertAccount(){
		
		String _Sql="select * from Account";
		Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, null);
		ArrayList<AccountInfo> _ArrayList = new ArrayList<AccountInfo>();
		while(_Cursor.moveToNext()){
			AccountInfo _AccountInfo = new AccountInfo();
			_AccountInfo.setMyAccountNo(_Cursor.getString(0));
			Log.i(TAG, _Cursor.getString(0)+""+_Cursor.getString(1)+""+_Cursor.getString(2));
			_AccountInfo.setMyAccountName(_Cursor.getString(1));
			_AccountInfo.setMyAccountMoney(_Cursor.getColumnName(2));
			_ArrayList.add(_AccountInfo);
		}
	}
	
	
	private void loadingFormat(){
		
		name_et=(EditText)this.findViewById(R.id.name_et);
		amount_of_money_et=(EditText)this.findViewById(R.id.amount_of_money_et);
		save_btn=(Button)this.findViewById(R.id.save_btn);
		cancel_btn=(Button)this.findViewById(R.id.cancel_btn);
	}
	
}
