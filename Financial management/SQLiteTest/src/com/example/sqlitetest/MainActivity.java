package com.example.sqlitetest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.example.db.MyDatabaseHelper;
import com.example.db.MySQLiteOpenHelper;
import com.example.utility.MyProcessBar;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	public static final int DATE_MOTH_DAY = 1;
	public static final int DATE_DAY = 2;
	public static final int DATE_WEEK=3;
	public static final int FLOE=4;
	private Button add_expense_quickly_btn = null;
	private TextView month_tv = null;
	private TextView income_amount_tv = null;
	private TextView expense_amount_tv = null;
	private TextView budget_balance_amount_tv = null;
	private TextView date_of_month_tv = null;
	private TextView today_datestr_tv = null;
	private TextView today_expense_amount_tv = null;
	private TextView today_income_amount_tv = null;
	private TextView week_datestr_tv = null;
	private TextView week_expense_amount_tv = null;
	private TextView week_income_amount_tv = null;
	private TextView month_datestr_tv = null;
	private TextView month_expense_amount_tv = null;
	private TextView month_income_amount_tv = null;
	
	private MyProcessBar myProcessBar = null;
	
	private static final String TAG="MainActivity";
	private final String MYLOG="myLog";
	private MySQLiteOpenHelper myMySQLiteOpenHelper=null;
	private MyDatabaseHelper myMyDatabaseHelper=null;
	private String myWeekDateFirst=null;
	private String myWeekDateLast=null;
	private String myTodayDate=null;
	private String myMonthDateFirst=null;
	private String myMonthDateLast=null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }

    
    
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		myMySQLiteOpenHelper=MyDatabaseHelper._MySQLiteOpenHelper;
		myMyDatabaseHelper=new MyDatabaseHelper(this);
        this.loadingFormation();
        this.initDate();
        this.init();
	}

	private void initDate(){
		
		GregorianCalendar _GregorianCalendar=null;
		Calendar _Calendar=Calendar.getInstance();
		
		int _Year = _Calendar.get(Calendar.YEAR);
		int _Month = _Calendar.get(Calendar.MONTH);
		Log.i(TAG, "Month:"+_Month);
		int _Day = _Calendar.get(Calendar.DAY_OF_MONTH);
		int length_first= _Calendar.get(Calendar.DAY_OF_WEEK)-1;
		int length_last=7-length_first;
		
		_GregorianCalendar=new GregorianCalendar(_Year,_Month,_Day-length_first);
		myWeekDateFirst = this.formatDate(_GregorianCalendar.getTime());
		_GregorianCalendar=new GregorianCalendar(_Year,_Month,_Day+length_last);
		myWeekDateLast=this.formatDate(_GregorianCalendar.getTime());
		
		_GregorianCalendar=new GregorianCalendar(_Year,_Month,_Day);
		myTodayDate=this.formatDate(_GregorianCalendar.getTime());
		
		_GregorianCalendar = new GregorianCalendar(_Year,_Month,1);
		myMonthDateFirst=this.formatDate(_GregorianCalendar.getTime());
		
		_GregorianCalendar = new GregorianCalendar(_Year,_Month+1,1);
		_GregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
		myMonthDateLast=this.formatDate(_GregorianCalendar.getTime());
		
		month_tv.setText(String.valueOf(_Month+1));
		date_of_month_tv.setText(_Day+"");
		today_datestr_tv.setText(myTodayDate);
		week_datestr_tv.setText(myWeekDateFirst+"~"+myWeekDateLast);
		month_datestr_tv.setText(myMonthDateFirst+"~"+myMonthDateLast);
	}
	
	private String formatDate(Date pDate){
		
		SimpleDateFormat _SimpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String _Date = _SimpleDateFormat.format(pDate);
		return _Date;
	}
	
	private void init(){
		
		income_amount_tv.setText("+"+this.getSumInput());
		expense_amount_tv.setText("-"+this.getSumOutput());
		budget_balance_amount_tv.setText("0.0");
		today_expense_amount_tv.setText("-"+this.getTodayOutput());
		today_income_amount_tv.setText("+"+this.getTodayInput());
		week_expense_amount_tv.setText("-"+this.getWeekOutput());
		week_income_amount_tv.setText("+"+this.getWeekInput());
		month_expense_amount_tv.setText("-"+this.getMonthOutput());
		month_income_amount_tv.setText("+"+this.getMonthInput());
		
		
	}
	
	public void onBackPressed() {
		new AlertDialog.Builder(this).setTitle(getString(R.string.point))
		.setMessage(getString(R.string.exit_message))
		.setPositiveButton(getString(R.string.exit_ok), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				myMySQLiteOpenHelper.close();
				finish();
//        		android.os.Process.killProcess(android.os.Process.myPid());
			}
		}).setNegativeButton(getString(R.string.exit_cancle), new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
		}).show();
	}
	
	private String formatString(String pString){
		
		
		return null;
	}
	
	private String getMonthInput(){
		
		String _Sql="select inputMoney from Input where inputTime>=? and inputTime<=?";
		Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{myMonthDateFirst,myMonthDateLast});
		Double sum=0.0;
		while(_Cursor.moveToNext()){
			sum+=Double.parseDouble(_Cursor.getString(0));
		}
		return String.valueOf(sum);
	}
	
	private String getMonthOutput(){
		
		String _Sql="select outputMoney from Output where outputTime>=? and outputTime<=?";
		Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{myMonthDateFirst,myMonthDateLast});
		Double sum=0.0;
		while(_Cursor.moveToNext()){
			sum+=Double.parseDouble(_Cursor.getString(0));
		}
		return String.valueOf(sum);
	}
	
	private String getWeekInput(){
		
		String _Sql="select inputMoney from Input where inputTime>=? and inputTime<=?";
		Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{myWeekDateFirst,myWeekDateLast});
		Double sum=0.0;
		while(_Cursor.moveToNext()){
			sum+=Double.parseDouble(_Cursor.getString(0));
		}
		Log.i(TAG, "weekInput:"+sum);
		return String.valueOf(sum);
	}
	
	private String getWeekOutput(){
		
		String _Sql="select outputMoney from Output where outputTime>=? and outputTime<=?";
		Cursor _Cursor=myMyDatabaseHelper.rawQuery(_Sql, new String[]{myWeekDateFirst,myWeekDateLast});
		Double sum=0.0;
		while(_Cursor.moveToNext()){
			sum+=Double.parseDouble(_Cursor.getString(0));
		}
		return String.valueOf(sum);
	}
	
	
	private String getTodayInput(){
		
		String _Sql="select inputMoney from Input where inputTime=?";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql, new String[]{myTodayDate});
		Double sum=0.0;
		while(_Cursor.moveToNext()){
			sum+=Double.parseDouble(_Cursor.getString(0));
		}
		return String.valueOf(sum);
	}
	
	private String getTodayOutput(){
		
		String _Sql="select outputMoney from Output where outputTime=?";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql, new String[]{myTodayDate});
		Double sum=0.0;
		while(_Cursor.moveToNext()){
			sum+=Double.parseDouble(_Cursor.getString(0));
		}
		return String.valueOf(sum);
	}
	
	private String getSumOutput(){
		
		String _Sql="select outputMoney from Output";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql, null);
		Double sum=0.0;
		while(_Cursor.moveToNext()){
			sum +=Double.parseDouble(_Cursor.getString(0));
			String value=_Cursor.getString(0);
			Log.i(TAG, "Output:"+value);
		}
		return String.valueOf(sum);
	}
	
	private String getSumInput(){
		
		String _Sql="select inputMoney from Input";
		Cursor _Cursor = myMyDatabaseHelper.rawQuery(_Sql, null);
		Double sum=0.0;
		while(_Cursor.moveToNext()){
			sum+=Double.parseDouble(_Cursor.getString(0));
		}
		return String.valueOf(sum);
	}
	
	private void loadingFormation() {
		
		myProcessBar = (MyProcessBar) findViewById(R.id.budget_pb);
		
		add_expense_quickly_btn = (Button) findViewById(R.id.add_expense_quickly_btn);
		
		month_tv = (TextView) findViewById(R.id.month_tv);

		income_amount_tv = (TextView) findViewById(R.id.income_amount_tv);
		expense_amount_tv = (TextView) findViewById(R.id.expense_amount_tv);
		budget_balance_amount_tv = (TextView) findViewById(R.id.budget_balance_amount_tv);

		date_of_month_tv = (TextView) findViewById(R.id.date_of_month_tv);
		today_datestr_tv = (TextView) findViewById(R.id.today_datestr_tv);
		today_expense_amount_tv = (TextView) findViewById(R.id.today_expense_amount_tv);
		today_income_amount_tv = (TextView) findViewById(R.id.today_income_amount_tv);
		week_datestr_tv = (TextView) findViewById(R.id.week_datestr_tv);
		week_expense_amount_tv = (TextView) findViewById(R.id.week_expense_amount_tv);
		week_income_amount_tv = (TextView) findViewById(R.id.week_income_amount_tv);
		month_datestr_tv = (TextView) findViewById(R.id.month_datestr_tv);
		month_expense_amount_tv = (TextView) findViewById(R.id.month_expense_amount_tv);
		month_income_amount_tv = (TextView) findViewById(R.id.month_income_amount_tv);
		
		
		this.findViewById(R.id.today_row_rl).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent _Intent = new Intent(MainActivity.this,NavigationExpenseActivity.class);
				_Intent.putExtra(NavigationExpenseActivity.TODAY, myTodayDate);
				_Intent.putExtra(NavigationExpenseActivity.KEY,MainActivity.DATE_DAY);
				MainActivity.this.startActivity(_Intent);
			}
			
		});
	    this.findViewById(R.id.week_row_rl ).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent(MainActivity.this,NavigationExpenseActivity.class);
				_Intent.putExtra(NavigationExpenseActivity.WEEKDAYFIRST, myWeekDateFirst);
				_Intent.putExtra(NavigationExpenseActivity.WEEKDAYLAST, myWeekDateLast);
				_Intent.putExtra(NavigationExpenseActivity.KEY, MainActivity.DATE_WEEK);
				MainActivity.this.startActivity(_Intent);
				
			}
	    	
	    });
	    this.findViewById(R.id.month_row_rl).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent(MainActivity.this,NavigationExpenseActivity.class);
				_Intent.putExtra(NavigationExpenseActivity.MONTHDAYFIRST, myMonthDateFirst);
				_Intent.putExtra(NavigationExpenseActivity.MONTHDAYLAST, myMonthDateLast);
				_Intent.putExtra(NavigationExpenseActivity.KEY, MainActivity.DATE_MOTH_DAY);
				MainActivity.this.startActivity(_Intent);
				
			}
	    	
	    });
	    this.findViewById(R.id.nav_account_btn).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent(MainActivity.this,AccountActivity.class);
				MainActivity.this.startActivity(_Intent);
			}
	    	
	    });
	    findViewById(R.id.nav_budget_btn).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent  = new Intent(MainActivity.this,NavigationExpenseActivity.class);
				_Intent.putExtra(NavigationExpenseActivity.KEY, MainActivity.FLOE);
				MainActivity.this.startActivity(_Intent);
				
			}
	    	
	    });
	    add_expense_quickly_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent _Intent = new Intent(MainActivity.this,TranciationActivity.class);
				_Intent.putExtra(TranciationActivity.TYPE_FLAG, TranciationActivity.PAYOUT_MODE);
				MainActivity.this.startActivity(_Intent);
			}
	    	
	    });
	}

}
