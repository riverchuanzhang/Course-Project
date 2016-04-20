package com.example.sqlitetest;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.db.MyDatabaseHelper;
import com.example.db.MySQLiteOpenHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AccountActivity extends Activity{

	private TextView asset_amount_tv=null;
	private TextView liabilitiy_amount_tv=null;
	private ListView account_lv = null;
	private MyDatabaseHelper myMyDatabaseHelper=null;
	private MySQLiteOpenHelper myMySQLiteOpenHelper=null;
	private final String TAG="AccountActivity";
	private Button add_btn=null;
	private Button go_to_transfer_btn=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.account_activity);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		myMyDatabaseHelper = new MyDatabaseHelper(this);
		myMySQLiteOpenHelper = MyDatabaseHelper._MySQLiteOpenHelper;
		this.loadingFormation();
		this.init();
	}
	
	private void loadingFormation(){
		
		asset_amount_tv = (TextView)this.findViewById(R.id.asset_amount_tv);
		liabilitiy_amount_tv=(TextView)this.findViewById(R.id.liabilitiy_amount_tv);
		account_lv=(ListView)this.findViewById(R.id.account_lv);
		add_btn=(Button)this.findViewById(R.id.add_btn);
		go_to_transfer_btn=(Button)this.findViewById(R.id.go_to_transfer_btn);
	}
	
	private void init(){
		
		asset_amount_tv.setText(this.getSumAccount().get(0));
		liabilitiy_amount_tv.setText(this.getSumAccount().get(1));
		account_lv.setAdapter(new AccountListViewAdapter(this,this.getAccountMessage()));
		add_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent=new Intent(AccountActivity.this,AddOrEditActivity.class);
				AccountActivity.this.startActivity(_Intent);
			}
			
		});
		go_to_transfer_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent=new Intent(AccountActivity.this,TransferAccountActivity.class);
				AccountActivity.this.startActivity(_Intent);
			}
			
			
		});
		account_lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			}
			
			
		});
	}
	
	private HashMap<Integer,String> getSumAccount(){
		
		String _Sql = "select accountMoney from Account";
		Cursor _Cursor= myMyDatabaseHelper.rawQuery(_Sql, null);
		Double sum=0.0;
		Double sumDebet=0.0;
		HashMap<Integer,String> _HashMap = new HashMap<Integer,String>();
		while(_Cursor.moveToNext()){
			if(Double.parseDouble(_Cursor.getString(0))>0.0){
				Log.i(TAG,"KKKK:"+Double.parseDouble(_Cursor.getString(0)));
				sum+=Double.parseDouble(_Cursor.getString(0));
			}else{
				sumDebet+=Double.parseDouble(_Cursor.getString(0));
			}
			_HashMap.put(0, String.valueOf(sum));
			_HashMap.put(1, String.valueOf(sumDebet));
			Log.i(TAG, "sum:"+String.valueOf(sum));
			Log.i(TAG, "sumDebt:"+String.valueOf(sumDebet));
		}
		return _HashMap;
	}
	
	private ArrayList<HashMap<Integer,ArrayList<String>>> getAccountMessage(){
		
		String _Sql="select accountName, accountMoney from Account";
		Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, null);
		ArrayList<HashMap<Integer,ArrayList<String>>> _ArrayList =new ArrayList<HashMap<Integer,ArrayList<String>>>();
		int j=0;
		while(_Cursor.moveToNext()){
			HashMap<Integer,ArrayList<String>> _HashMap=new HashMap<Integer,ArrayList<String>>();
			ArrayList<String> _ArrayList2=new ArrayList<String>();
			int i=0;
			for(i=0;i<2;i++){
				_ArrayList2.add(_Cursor.getString(i));
				Log.i(TAG, "accountName:"+_Cursor.getString(0)+" "+_Cursor.getString(1));
			}
			_HashMap.put(j,_ArrayList2);
//			Log.i(TAG, "accountName:"+_Cursor.getString(0)+" "+_Cursor.getString(1));
			_ArrayList.add(_HashMap);
			j++;
		}
		return _ArrayList;
	}
	
}
