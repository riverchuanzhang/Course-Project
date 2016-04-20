package com.example.sqlitetest;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.db.MyDatabaseHelper;
import com.example.db.MySQLiteOpenHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TranciationActivity extends Activity{

	private static final String TAG="TranciationActivity";
	public static final int INCOME_MODE = 0;
	public static final int PAYOUT_MODE = 1;
	public static final int EDIT_MODE = 2;
	private int type = PAYOUT_MODE;//操作类型0收入、1支出、2编辑
	private Calendar calendar = Calendar.getInstance();
	private DatePickerDialog datePicker = null;
	private RadioButton rb1=null;
	private FrameLayout corporation_fl = null;
	private Button cost_btn = null;
	private String  value="0";
	private Spinner first_level_category_spn = null;
	private Spinner sub_category_spn = null;
	private Spinner account_spn = null;
	private Spinner corporation_spn = null;
	private Button trade_time_btn = null;
	private Spinner project_spn = null;
	private Button memo_btn = null;
	private Button save_btn = null;
	private Button cancel_btn = null;
	private MyDatabaseHelper myMyDatabaseHelper=null;
	private MySQLiteOpenHelper myMySQLiteOpenHelper=null;
	private HashMap<String,String> myHashMap=new HashMap<String,String>();
	private final String FIRST_OUT_GROUP_NO="firstOutGroupNo";
	private final String SECOND_OUT_GROUP_NO="secondOutGroupNo";
	private final String ACCOUNT_NO="accountNo";
	private final String BUSINESS_NO="businessNo";
	private final String PROJECT_NO="projectNo";
	private final String OUTPUT_TIME="outputTime";
	private final String OUTPUT_MONEY="outputMoney";
	private final String[] OUTPUT_KEY={OUTPUT_TIME,OUTPUT_MONEY,ACCOUNT_NO,FIRST_OUT_GROUP_NO,SECOND_OUT_GROUP_NO,
			BUSINESS_NO,PROJECT_NO,};
	public static final String TYPE_FLAG="type";
	private final String FIRST_IN_GROUP_NO="firstInGroupNo";
	private final String SECOND_IN_GROUP_NO="secondInGroupNo";
	private final String INPUT_TIME="inputTime";
	private final String INPUT_MONEY="inputMoney";
	private final String[] INPUT_KEY={INPUT_TIME,INPUT_MONEY,ACCOUNT_NO,FIRST_IN_GROUP_NO,SECOND_IN_GROUP_NO,PROJECT_NO};
	private String myMoney=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.tranciation_activity);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.type=this.getIntent().getIntExtra(TranciationActivity.TYPE_FLAG, TranciationActivity.PAYOUT_MODE);
		myMySQLiteOpenHelper=MyDatabaseHelper._MySQLiteOpenHelper;
		myMyDatabaseHelper=new MyDatabaseHelper(this);
		this.loadingFormation();
		this.initData(type);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK && requestCode == 0) { 
			type=data.getIntExtra(TranciationActivity.TYPE_FLAG, 0);
			Log.i(TAG, "type:"+type);
			value=data.getStringExtra("value");
			Log.i(TAG, "value:"+Integer.parseInt(value));
			Log.i(TAG, "valueString:"+value);
			myMoney=DecimalFormat.getCurrencyInstance().format(Double.parseDouble(value));
			cost_btn.setText(myMoney);
			switch(type){
				case PAYOUT_MODE:{
					myHashMap.put(OUTPUT_MONEY,value);
					Log.i(TAG, "dddd"+myHashMap.get(OUTPUT_MONEY));
					break;
				}
				case INCOME_MODE:{
					myHashMap.put(INPUT_MONEY, value);
					break;
				}
			}
		} 
	}

	private void loadingFormation(){
		
		rb1 = (RadioButton) findViewById(R.id.payout_tab_rb);
		rb1.setChecked(true);
		
		cost_btn=(Button)findViewById(R.id.cost_btn);
		
		corporation_fl=(FrameLayout)findViewById(R.id.corporation_fl);
		
		first_level_category_spn = (Spinner) findViewById(R.id.first_level_category_spn);
		
		sub_category_spn = (Spinner) findViewById(R.id.sub_category_spn);
	
		account_spn = (Spinner) findViewById(R.id.account_spn);
		
		corporation_spn = (Spinner) findViewById(R.id.corporation_spn);
		
		trade_time_btn = (Button) findViewById(R.id.trade_time_btn);
		
		project_spn = (Spinner) findViewById(R.id.project_spn);
		
		memo_btn = (Button) findViewById(R.id.memo_btn);
	
		save_btn = (Button) findViewById(R.id.save_btn);
		
		cancel_btn = (Button) findViewById(R.id.cancel_btn);
		
		cancel_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TranciationActivity.this.finish();
			}
			
		});
		
		rb1.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if(rb1.isChecked()){
					type = PAYOUT_MODE;
					corporation_fl.setVisibility(View.VISIBLE);
					myHashMap.clear();
				}else{
					type=INCOME_MODE;
					corporation_fl.setVisibility(View.GONE);
					myHashMap.clear();
				}
				initData(type);
			}
			
		});
//		rb2.setOnCheckedChangeListener(new OnCheckedChangeListener(){
//
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView,
//					boolean isChecked) {
//				// TODO Auto-generated method stub
//				if(rb2.isChecked()){
//					type = INCOME_MODE;
//					corporation_fl.setVisibility(View.GONE);
//				}else{
//					type=PAYOUT_MODE;
//					corporation_fl.setVisibility(View.VISIBLE);
//				}
//				initData(type);
//				myHashMap.clear();
//			}
//			
//		});
		cost_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent(TranciationActivity.this,KeyPad.class);
				_Intent.putExtra("value", value);
				_Intent.putExtra(TranciationActivity.TYPE_FLAG, type);
				TranciationActivity.this.startActivityForResult(_Intent, 0);
			}
			
			
		});
		
		
		
		first_level_category_spn.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch(type){
					case PAYOUT_MODE:{
						String _firstOutGroupName=parent.getItemAtPosition(position).toString();
						Log.i(TAG, _firstOutGroupName);
						String _Sql = "select firstOutGroupNo from FirstOutGroup where firstOutGroupName=?";
						Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{_firstOutGroupName});
						if(_Cursor.moveToFirst()){
							myHashMap.put(FIRST_OUT_GROUP_NO, _Cursor.getString(0));
							Log.i(TAG, myHashMap.get(FIRST_OUT_GROUP_NO));
						}
						break;
						
					}
					case INCOME_MODE:{
						String _firstInGroupName=parent.getItemAtPosition(position).toString();
						Log.i(TAG, _firstInGroupName);
						String _Sql = "select firstInGroupNo from FirstInGroup where firstInGroupName=?";
						Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{_firstInGroupName});
						if(_Cursor.moveToFirst()){
							myHashMap.put(FIRST_IN_GROUP_NO, _Cursor.getString(0));
							Log.i(TAG, myHashMap.get(FIRST_IN_GROUP_NO));
						}
						break;
					}
					case EDIT_MODE:{
						break;
					}
					default:break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		sub_category_spn.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch(type){
					case PAYOUT_MODE:{
						String _secondOutGroupName=parent.getItemAtPosition(position).toString();
						String _Sql="select secondOutGroupNo from SecondOutGroup where secondOutGroupName=?";
						Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{_secondOutGroupName});
						if(_Cursor.moveToFirst()){
							myHashMap.put(SECOND_OUT_GROUP_NO,_Cursor.getString(0));
						}
						break;
					}
					case INCOME_MODE:{
						String _secondInGroupName=parent.getItemAtPosition(position).toString();
						String _Sql="select secondInGroupNo from SecondInGroup where secondInGroupName=?";
						Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{_secondInGroupName});
						if(_Cursor.moveToFirst()){
							myHashMap.put(SECOND_IN_GROUP_NO,_Cursor.getString(0));
						}
						break;
					}
					case EDIT_MODE:{
						break;
					}
					default:break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		account_spn.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				switch(type){
					case PAYOUT_MODE:{
						String _accountName=parent.getItemAtPosition(position).toString();
						String _Sql="select accountNo from Account where accountName=?";
						Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{_accountName});
						if(_Cursor.moveToFirst()){
							myHashMap.put(ACCOUNT_NO, _Cursor.getString(0));
						}
						break;
					}
					case INCOME_MODE:{
						String _accountName=parent.getItemAtPosition(position).toString();
						String _Sql="select accountNo from Account where accountName=?";
						Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{_accountName});
						if(_Cursor.moveToFirst()){
							myHashMap.put(ACCOUNT_NO, _Cursor.getString(0));
						}
						break;
					}
					case EDIT_MODE:{
						break;
					}
					default:break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		project_spn.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				switch(type){
					case PAYOUT_MODE:{
						String _ProjectName=parent.getItemAtPosition(position).toString();
						String _Sql="select projectNo from Project where projectName=?";
						Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{_ProjectName});
						if(_Cursor.moveToFirst()){
							myHashMap.put(PROJECT_NO, _Cursor.getString(0));
						}
						break;
					}
					case INCOME_MODE:{
						String _ProjectName=parent.getItemAtPosition(position).toString();
						String _Sql="select projectNo from Project where projectName=?";
						Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{_ProjectName});
						if(_Cursor.moveToFirst()){
							myHashMap.put(PROJECT_NO, _Cursor.getString(0));
						}
						break;
					}
					case EDIT_MODE:{
						break;
					}
					default:break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		corporation_spn.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch(type){
					case PAYOUT_MODE:{
						String _BusinessName=parent.getItemAtPosition(position).toString();
						String _Sql="select businessNo from Business where businessName=?";
						Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{_BusinessName});
						if(_Cursor.moveToFirst()){
							myHashMap.put(BUSINESS_NO,_Cursor.getString(0));
						}
						break;
					}
					case INCOME_MODE:{
						break;
					}
					case EDIT_MODE:{
						break;
					}
					default:break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		trade_time_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				datePicker = new DatePickerDialog(TranciationActivity.this, mDateSetListenerSatrt,
						calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
				datePicker.show();
			}
			
		});
		memo_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	
			}
			
		});
		save_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(type){
					case PAYOUT_MODE:{
						if(myMoney!=null){
							myHashMap.put(OUTPUT_MONEY, value);
						}else{
							Toast.makeText(TranciationActivity.this, "please input "+OUTPUT_MONEY, Toast.LENGTH_SHORT).show();
						}
						
						HashMap<Integer,String> _HashMap = new HashMap<Integer,String>();
						Log.i(TAG, myHashMap.size()+" ");
						for(int i=0;i<OUTPUT_KEY.length;i++){
							if(myHashMap.containsKey(OUTPUT_KEY[i])){
								_HashMap.put(i, myHashMap.get(OUTPUT_KEY[i]));
								Log.i(TAG, "myHashMap:"+myHashMap.get(OUTPUT_KEY[i]));
								Log.i(TAG, "_HashMap:"+_HashMap.get(i));
							}else{
								Toast.makeText(TranciationActivity.this, "please input "+OUTPUT_KEY[i], Toast.LENGTH_SHORT).show();;
								break;
							}
						}
						if(_HashMap.size()==OUTPUT_KEY.length){
							myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[13], MySQLiteOpenHelper.USER_ITEMS[13],
									new String[]{_HashMap.get(0),_HashMap.get(1),_HashMap.get(2),_HashMap.get(3),_HashMap.get(4),
									_HashMap.get(5),_HashMap.get(6)});
							String _Sql="select * from Output";
							Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, null);
							while(_Cursor.moveToNext()){
								for(int i=0;i<OUTPUT_KEY.length;i++){
									Log.i(TAG, "_Cursor:"+_Cursor.getString(i));
								}
							}
							String _Sql2="select accountMoney from Account where accountNo=?";
							Cursor _Cursor2 = myMyDatabaseHelper.rawQuery(_Sql2, new String[]{_HashMap.get(2)});
							Double _NewAccountMoney=0.0;
							while(_Cursor2.moveToNext()){
								_NewAccountMoney=Double.parseDouble(_Cursor2.getString(0))-Double.parseDouble(_HashMap.get(1));
								Log.i(TAG, "原始："+Double.parseDouble(_Cursor2.getString(0)));
								Log.i(TAG, "键入："+
								Double.parseDouble(_HashMap.get(1)));
								Log.i(TAG, "_NewAccountMoney:"+String.valueOf(_NewAccountMoney));
							}
							myMyDatabaseHelper.updata(MySQLiteOpenHelper.TABLES[3], 
									new String[]{MySQLiteOpenHelper.USER_ITEMS[3][2]}, 
									new String[]{String.valueOf(_NewAccountMoney)},"accountNo=?", new String[]{_HashMap.get(2)});
							
							Toast.makeText(TranciationActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
						}
						
						break;
					}
					case INCOME_MODE:{
						if(myMoney!=null){
							myHashMap.put(INPUT_MONEY, value);
						}else{
							Toast.makeText(TranciationActivity.this, "please input "+INPUT_MONEY, Toast.LENGTH_SHORT).show();
						}
						HashMap<Integer,String> _HashMap = new HashMap<Integer,String>();
						Log.i(TAG, "Input:"+myHashMap.size()+" ");
						for(int i=0;i<INPUT_KEY.length;i++){
							if(myHashMap.containsKey(INPUT_KEY[i])){
								_HashMap.put(i, myHashMap.get(INPUT_KEY[i]));
								Log.i(TAG, "Input:"+myHashMap.get(INPUT_KEY[i]));
							}else{
								Toast.makeText(TranciationActivity.this, "please input "+INPUT_KEY[i], Toast.LENGTH_SHORT).show();;
								break;
							}
						}
						_HashMap.remove(BUSINESS_NO);
						if(_HashMap.size()==INPUT_KEY.length){
							myMyDatabaseHelper.insert(MySQLiteOpenHelper.TABLES[12], MySQLiteOpenHelper.USER_ITEMS[12],
									new String[]{_HashMap.get(0),_HashMap.get(1),_HashMap.get(2),_HashMap.get(3),_HashMap.get(4),
									_HashMap.get(5)});
							String _Sql="select * from Input";
							Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, null);
							while(_Cursor.moveToNext()){
								for(int i=0;i<INPUT_KEY.length;i++){
									Log.i(TAG, "Input:"+_Cursor.getString(i));
								}
							}
							String _Sql2="select accountMoney from Account where accountNo=?";
							Cursor _Cursor2 = myMyDatabaseHelper.rawQuery(_Sql2, new String[]{_HashMap.get(2)});
							Double _NewAccountMoney=0.0;
							while(_Cursor2.moveToNext()){
								_NewAccountMoney=Double.parseDouble(_Cursor2.getString(0))+Double.parseDouble(_HashMap.get(1));
								Log.i(TAG, "原始："+Double.parseDouble(_Cursor2.getString(0)));
								Log.i(TAG, "键入："+
								Double.parseDouble(_HashMap.get(1)));
								Log.i(TAG, "_NewAccountMoney:"+String.valueOf(_NewAccountMoney));
							}
							myMyDatabaseHelper.updata(MySQLiteOpenHelper.TABLES[3], 
									new String[]{MySQLiteOpenHelper.USER_ITEMS[3][2]}, 
									new String[]{String.valueOf(_NewAccountMoney)},"accountNo=?", new String[]{_HashMap.get(2)});
							Toast.makeText(TranciationActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
						}
						
						break;
					}
					case EDIT_MODE:{
						break;
					}
					default:break;
				}

			}
			
		});
	}
	
	private boolean initData(int pType){
		
		switch(pType){
			case PAYOUT_MODE:{
				first_level_category_spn.setAdapter(this.getArrayAdapter(this.getFirstOutputGroupName()));
				sub_category_spn.setAdapter(this.getArrayAdapter(this.getSecondOutputGroupName()));
				account_spn.setAdapter(this.getArrayAdapter(this.getAccountName()));
				corporation_spn.setAdapter(this.getArrayAdapter(this.getBusinesstName()));
				project_spn.setAdapter(this.getArrayAdapter(this.getProjectName()));
				trade_time_btn.setText(this.formatDate(Calendar.getInstance().getTime()));
				break;
			}
			case INCOME_MODE:{
				first_level_category_spn.setAdapter(this.getArrayAdapter(this.getFirstInGroupName()));
				sub_category_spn.setAdapter(this.getArrayAdapter(this.getSecondInGroupName()));
				account_spn.setAdapter(this.getArrayAdapter(this.getAccountName()));
				project_spn.setAdapter(this.getArrayAdapter(this.getProjectName()));
				trade_time_btn.setText(this.formatDate(Calendar.getInstance().getTime()));
				break;
			}
			case EDIT_MODE:{
				break;
			}
		}

		return true;
	}
	
	private ArrayList<String> getFirstOutputGroupName(){
		
		String _Sql = "select firstOutGroupName from FirstOutGroup";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql, null);
		ArrayList<String> _ArrayList= new ArrayList<String>();
		while(_Cursor.moveToNext()){
			_ArrayList.add(_Cursor.getString(0));
		}
		return _ArrayList;
	}
	
	private ArrayList<String> getSecondOutputGroupName(){
		
		String _Sql = "select secondOutGroupName from SecondOutGroup";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql, null);
		ArrayList<String> _ArrayList = new ArrayList<String>();
		while(_Cursor.moveToNext()){
			_ArrayList.add(_Cursor.getString(0));
		}
		return _ArrayList;
	}
	
	private ArrayList<String> getAccountName(){
		
		String _Sql="select accountName from Account";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql, null);
		ArrayList<String> _ArrayList = new ArrayList<String>();
		while(_Cursor.moveToNext()){
			_ArrayList.add(_Cursor.getString(0));
		}
		return _ArrayList;
	}
	
	
	private ArrayList<String> getBusinesstName(){
		
		String _Sql="select businessName from Business";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql, null);
		ArrayList<String> _ArrayList = new ArrayList<String>();
		while(_Cursor.moveToNext()){
			_ArrayList.add(_Cursor.getString(0));
		}
		return _ArrayList;
	}
	
	private ArrayList<String> getProjectName(){
		
		String _Sql="select projectName from Project";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql, null);
		ArrayList<String> _ArrayList = new ArrayList<String>();
		while(_Cursor.moveToNext()){
			_ArrayList.add(_Cursor.getString(0));
		}
		return _ArrayList;
	}
	
	private ArrayList<String> getFirstInGroupName(){
		
		String _Sql="select firstInGroupName from FirstInGroup";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql, null);
		ArrayList<String> _ArrayList = new ArrayList<String>();
		while(_Cursor.moveToNext()){
			_ArrayList.add(_Cursor.getString(0));
		}
		return _ArrayList;
	}
	
	private ArrayList<String> getSecondInGroupName(){
		
		String _Sql="select secondInGroupName from SecondInGroup";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql, null);
		ArrayList<String> _ArrayList = new ArrayList<String>();
		while(_Cursor.moveToNext()){
			_ArrayList.add(_Cursor.getString(0));
		}
		return _ArrayList;
	}
	
	
	
	private ArrayAdapter<String> getArrayAdapter(ArrayList<String> pArrayList){
		
		ArrayAdapter<String> _ArrayAdapter=new ArrayAdapter<String>(TranciationActivity.this,
				android.R.layout.simple_spinner_item,pArrayList);
		_ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		return _ArrayAdapter;
	}
	
	private String formatDate(Date pData){
		
		
		SimpleDateFormat _SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意"yyyy-MM-dd"不要写成"yyyy-mm-dd"
		String _Date=_SimpleDateFormat.format(pData);
		return _Date;
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListenerSatrt = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			Calendar _Calendar = Calendar.getInstance();
			_Calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			_Calendar.set(Calendar.MONTH, monthOfYear);
			_Calendar.set(Calendar.YEAR, year);
			String _Time=TranciationActivity.this.formatDate(_Calendar.getTime());
			trade_time_btn.setText(_Time);
			switch(type){
				case PAYOUT_MODE:{
					myHashMap.put(OUTPUT_TIME, _Time);
					break;
				}
				case INCOME_MODE:{
					myHashMap.put(INPUT_TIME, _Time);
					break;
				} 
				default:break;
			}
			myHashMap.put(OUTPUT_TIME, _Time);
		}
	};
}
