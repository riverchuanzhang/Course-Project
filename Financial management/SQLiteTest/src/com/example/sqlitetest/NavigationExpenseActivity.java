package com.example.sqlitetest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import com.example.dao.InputDao;
import com.example.dao.OutputDao;
import com.example.dao.SecondInGroupDao;
import com.example.dao.SecondOutGroupDao;
import com.example.object.InputInfo;
import com.example.object.OutputInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class NavigationExpenseActivity extends Activity{

	private TextView time_interval_tv=null;
	private Button pre_btn=null;
	private Button next_btn=null;
	private TextView  income_amount_tv=null;
	private TextView payout_amount_tv=null;
	private ListView expense_lv=null;
	private TextView listview_loading_tv=null;
	public static final String TODAY="TodayTime";
	public static final String WEEKDAYFIRST="WeekDayFirst";
	public static final String WEEKDAYLAST="WeekDayLast";
	public static final String MONTHDAYFIRST="MonthDayFirst";
	public static final String MONTHDAYLAST="MonthDayLast";
	private String myTodayTime=null;
	private String myWeekDayFirst=null;
	private String myWeekDayLast=null;
	private String myMonthDayFirst=null;
	private String myMonthDayLast=null;
	private ArrayList<OutputInfo> myArrayList=null;
	private int flag=0;
	public static final String KEY="Time";
	public final String TAG="NavigationExpenseActivity";
	public final String MYLOG="myLog";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.nav_expend_activity);
		Intent _Intent = this.getIntent();
		myTodayTime=_Intent.getStringExtra(this.TODAY);
		myWeekDayFirst=_Intent.getStringExtra(this.WEEKDAYFIRST);
		myWeekDayLast=_Intent.getStringExtra(this.WEEKDAYLAST);
		myMonthDayFirst=_Intent.getStringExtra(this.MONTHDAYFIRST);
		myMonthDayLast=_Intent.getStringExtra(this.MONTHDAYLAST);
		flag=_Intent.getIntExtra(KEY, 0);
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.loadingFromat();
		this.init();
	}
	
	private void loadingFromat(){
		
		time_interval_tv=(TextView)this.findViewById(R.id.time_interval_tv);
		pre_btn=(Button)this.findViewById(R.id.pre_btn);
		next_btn=(Button)this.findViewById(R.id.next_btn);
		income_amount_tv=(TextView)this.findViewById(R.id.income_amount_tv);
		payout_amount_tv=(TextView)this.findViewById(R.id.payout_amount_tv);
		expense_lv=(ListView)this.findViewById(R.id.expense_lv);
		listview_loading_tv=(TextView)this.findViewById(R.id.listview_loading_tv);
		expense_lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void init(){
		SecondOutGroupDao _SecondOutGroupDao=new SecondOutGroupDao(this);
		SecondInGroupDao _SecondInGroupDao = new SecondInGroupDao(this);
		ArrayList<InputInfo> _ArrayListIn = null;
		ArrayList<OutputInfo> _ArrayListOut = null;
		switch(flag){
			case MainActivity.DATE_DAY:{
				_ArrayListIn = _SecondInGroupDao.getMyArrayList(myTodayTime);
				_ArrayListOut =_SecondOutGroupDao.getMyArrayList(myTodayTime);
				expense_lv.setAdapter(new ExpenseListViewAdapter(this,_SecondInGroupDao.getMyArrayList(myTodayTime),
						_SecondOutGroupDao.getMyArrayList(myTodayTime)));
				time_interval_tv.setText(myTodayTime);
				income_amount_tv.setText(new InputDao(this).getInputMoneySum(_ArrayListIn));
				payout_amount_tv.setText(new OutputDao(this).getOutputMoneySum(_ArrayListOut));
				break;
			}
			case MainActivity.DATE_WEEK:{
				_ArrayListIn = _SecondInGroupDao.getMyArrayList(myWeekDayFirst,myWeekDayLast);
				_ArrayListOut =_SecondOutGroupDao.getMyArrayList(myWeekDayFirst,myWeekDayLast);
				expense_lv.setAdapter(new ExpenseListViewAdapter(this,_ArrayListIn,_ArrayListOut));
				time_interval_tv.setText(myWeekDayFirst+"~"+myWeekDayLast);
				income_amount_tv.setText(new InputDao(this).getInputMoneySum(_ArrayListIn));
				payout_amount_tv.setText(new OutputDao(this).getOutputMoneySum(_ArrayListOut));
				expense_lv.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						
					}
					
				});
				break;
			}
			case MainActivity.DATE_MOTH_DAY:{
				_ArrayListIn = _SecondInGroupDao.getMyArrayList(myMonthDayFirst,myMonthDayLast);
				_ArrayListOut =_SecondOutGroupDao.getMyArrayList(myMonthDayFirst,myMonthDayLast);
				expense_lv.setAdapter(new ExpenseListViewAdapter(this,_ArrayListIn,_ArrayListOut));
				time_interval_tv.setText(myMonthDayFirst+"~"+myMonthDayLast);
				income_amount_tv.setText(new InputDao(this).getInputMoneySum(_ArrayListIn));
				payout_amount_tv.setText(new OutputDao(this).getOutputMoneySum(_ArrayListOut));
				break;
			}
			case MainActivity.FLOE:{
				_ArrayListIn = _SecondInGroupDao.getMyArrayList();
				_ArrayListOut = _SecondOutGroupDao.getMyArrayList();
				expense_lv.setAdapter(new ExpenseListViewAdapter(this,_ArrayListIn,_ArrayListOut));
				time_interval_tv.setText("Á÷Ë®");
				income_amount_tv.setText(new InputDao(this).getInputMoneySum(_ArrayListIn));
				payout_amount_tv.setText(new OutputDao(this).getOutputMoneySum(_ArrayListOut));
				break;
			}
			default:break;
		}
		
		
	}
	
	

}
