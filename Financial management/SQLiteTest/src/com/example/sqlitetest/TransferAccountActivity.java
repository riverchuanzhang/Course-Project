package com.example.sqlitetest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ListIterator;

import com.example.db.MyDatabaseHelper;
import com.example.db.MySQLiteOpenHelper;
import com.example.object.AccountInfo;
import com.example.object.ProjectInfo;
import com.example.object.TransferAccountInfo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class TransferAccountActivity extends Activity{

	private Spinner transfer_out_account_spn=null;
	private Spinner transfer_in_account_spn=null;
	private EditText transfer_amount_out_et=null;
	private Spinner transfer_info_project_spn=null;
	private MySQLiteOpenHelper myMySQLiteOpenHelper=null;
	private MyDatabaseHelper myMyDatabaseHelper=null;
	private String myAccountOutName=null;
	private String myAccountInName=null;
	private final String TAG="TransferAccountActivity:";
	private LinearLayout transfer_amount_edit_area_ly=null;
	private final String MYLOG="myLog";
	private Button transfer_btn=null;
	private TransferAccountInfo myTransferAccount = new TransferAccountInfo();
	private TransferAccountInfo myTransferAccountInfo= new TransferAccountInfo();
	private Button transfer_info_tradetime_btn=null;
	private DatePickerDialog datePicker = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.transfer_account_activity);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		myMySQLiteOpenHelper=MyDatabaseHelper._MySQLiteOpenHelper;
		myMyDatabaseHelper=new MyDatabaseHelper(this);
		this.loadingFormat();
		this.init();
	}
	
	private void loadingFormat(){
		
		transfer_out_account_spn=(Spinner)this.findViewById(R.id.transfer_out_account_spn);
//		transfer_info_project_spn=(Spinner)this.findViewById(R.id.transfer_info_project_spn);
		transfer_amount_out_et=(EditText)this.findViewById(R.id.transfer_amount_out_et);
		transfer_in_account_spn=(Spinner)this.findViewById(R.id.transfer_in_account_spn);
		transfer_amount_edit_area_ly=(LinearLayout)this.findViewById(R.id.transfer_amount_edit_area_ly);
		transfer_btn=(Button)this.findViewById(R.id.transfer_btn);
		transfer_info_tradetime_btn=(Button)this.findViewById(R.id.transfer_info_tradetime_btn);
		
	}
	
	private void init(){
		
		transfer_out_account_spn.setAdapter(this.getAccountInfoArrayAdapter(this.getAccountOutName()));
//		transfer_info_project_spn.setAdapter(this.getProjectInfoArrayAdapter(this.getProjectName()));
		
		transfer_info_tradetime_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar _Calendar = Calendar.getInstance();
				datePicker = new DatePickerDialog(TransferAccountActivity.this, mDateSetListenerSatrt,
						_Calendar.get(Calendar.YEAR), _Calendar.get(Calendar.MONTH), _Calendar.get(Calendar.DAY_OF_MONTH));
				datePicker.show();
			}
			
		});
		
		transfer_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updataAccount();
			}
			
		});
		
		transfer_out_account_spn.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				myAccountOutName=parent.getItemAtPosition(position).toString();
				transfer_in_account_spn.setAdapter(getAccountInfoArrayAdapter(getAccountInName()));	
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
			
			
		});
		
		transfer_in_account_spn.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				myAccountInName=parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		

		
	}
	
	private void updataAccount(){
		
		String _AccountMoney=null;
		_AccountMoney = transfer_amount_out_et.getText().toString();
		Log.i(MYLOG, "_AccountMoney:"+_AccountMoney);
		myTransferAccountInfo.setMyTransferMoney(_AccountMoney);
		if(_AccountMoney.equals("")){

			Toast.makeText(TransferAccountActivity.this, "please input 转账金额", Toast.LENGTH_SHORT).show();;

		}else{
			ArrayList<String> _ArrayList = new ArrayList<String>();
			String _Sql="select accountNo from Account where accountName=?";
			Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{myAccountInName});
			Cursor _Cursor3=myMyDatabaseHelper.rawQuery(_Sql, new String[]{myAccountOutName});
			String _AccountNoOut=null;
			String _AccountNoIn=null;
			while(_Cursor.moveToNext()){
				_AccountNoOut = _Cursor.getString(0);
				Log.i(MYLOG, TAG+"_AccountNoOut:"+_AccountNoOut);
			}
			myTransferAccountInfo.setMyAccountOutNo(_AccountNoOut);
			while(_Cursor3.moveToNext()){
				_AccountNoIn = _Cursor3.getString(0);
				Log.i(MYLOG, "_AccountNoIn:"+_AccountNoIn);
			}
			myTransferAccountInfo.setMyAccountInNo(_AccountNoIn);
			String _Sql2="select accountMoney from Account where accountNo=?";
			Cursor _Cursor2=myMyDatabaseHelper.rawQuery(_Sql2, new String[]{_AccountNoOut});
			Cursor _Cursor4=myMyDatabaseHelper.rawQuery(_Sql2, new String[]{_AccountNoIn});
			String _AccountInfoMoneyOut=null;
			String _AccountInfoMoneyIn=null;
			while(_Cursor2.moveToNext()){
				_AccountInfoMoneyOut = _Cursor2.getString(0);
				Log.i(MYLOG, TAG+"_AccountInfoMoneyOut:"+_AccountInfoMoneyOut);
			}
			while(_Cursor4.moveToNext()){
				_AccountInfoMoneyIn=_Cursor4.getString(0);
				Log.i(MYLOG, TAG+"_AccountInfoMoneyIn:"+_AccountInfoMoneyIn);
			}
			Double temIn=Double.parseDouble(_AccountInfoMoneyOut)-Double.parseDouble(_AccountMoney);
			Double temOut=Double.parseDouble(_AccountInfoMoneyIn)+Double.parseDouble(_AccountMoney);
			Log.i(MYLOG, TAG+"temIn:"+temIn+"temOut"+temOut);
			myMyDatabaseHelper.updata(myMySQLiteOpenHelper.TABLES[3], new String[]{myMySQLiteOpenHelper.USER_ITEMS[3][2]}, 
					new String[]{String.valueOf(temIn)}, "accountNo=?", new String[]{_AccountNoOut});
			myMyDatabaseHelper.updata(myMySQLiteOpenHelper.TABLES[3], new String[]{myMySQLiteOpenHelper.USER_ITEMS[3][2]}, 
					new String[]{String.valueOf(temOut)}, "accountNo=?", new String[]{_AccountNoIn});
			Toast.makeText(TransferAccountActivity.this, "转账成功", Toast.LENGTH_SHORT).show();;
		}

	}
	
	private ArrayList<AccountInfo> getAccountOutName(){
		
		String _Sql="select accountName from Account";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql,null );
		ArrayList<AccountInfo> _ArrayList=new ArrayList<AccountInfo>();
		while(_Cursor.moveToNext()){
			if(_Cursor.getString(0).equals(myAccountInName)){

			}else{
				AccountInfo _AccountInfo=new AccountInfo();
				_AccountInfo.setMyAccountName(_Cursor.getString(0));
				Log.i(MYLOG, TAG+"_Cursor:"+_Cursor.getString(0));
				_ArrayList.add(_AccountInfo);
			}

		}
		return _ArrayList;
	}
	
	private ArrayList<AccountInfo> getAccountInName(){
		
		String _Sql="select accountName from Account";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql,null);
		ArrayList<AccountInfo> _ArrayList=new ArrayList<AccountInfo>();
		while(_Cursor.moveToNext()){
			if(_Cursor.getString(0).equals(myAccountOutName)){

			}else{
				AccountInfo _AccountInfo = new AccountInfo();
				_AccountInfo.setMyAccountName(_Cursor.getString(0));
				_ArrayList.add(_AccountInfo);
			}
		}
		return _ArrayList;
	}
	
	private ArrayList<ProjectInfo> getProjectName(){
		
		String _Sql="select projectName from Project";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql, null);
		ArrayList<ProjectInfo> _ArrayList = new ArrayList<ProjectInfo>();
		while(_Cursor.moveToNext()){
			ProjectInfo _ProjectInfo = new ProjectInfo();
			_ProjectInfo.setMyProjectName(_Cursor.getString(0));
			_ArrayList.add(_ProjectInfo);
		}
		return _ArrayList;
	}
	
	private ArrayAdapter<String> getProjectInfoArrayAdapter(ArrayList<ProjectInfo> pArrayList){
		
		ArrayList<String> _ArrayList=new ArrayList<String>();
		Iterator<ProjectInfo> _ListIterator= pArrayList.iterator();
		while(_ListIterator.hasNext()){
			_ArrayList.add(_ListIterator.next().getMyProjectName());
		}
		ArrayAdapter<String> _ArrayAdapter=new ArrayAdapter<String>(TransferAccountActivity.this,
				android.R.layout.simple_spinner_item,_ArrayList);
		_ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		return _ArrayAdapter;
	}
	
	private ArrayAdapter<String> getAccountInfoArrayAdapter(ArrayList<AccountInfo> pArrayList){
		
		ArrayList<String> _ArrayList=new ArrayList<String>();
		Iterator<AccountInfo> _ListIterator= pArrayList.iterator();
		while(_ListIterator.hasNext()){
			_ArrayList.add(_ListIterator.next().getMyAccountName());
		}
		ArrayAdapter<String> _ArrayAdapter=new ArrayAdapter<String>(TransferAccountActivity.this,
				android.R.layout.simple_spinner_item,_ArrayList);
		_ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		return _ArrayAdapter;
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListenerSatrt = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			}
	};
	
}
