package com.example.sqlitetest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class BudgetActivity extends Activity{

	private ListView budget_category_lv=null;
	private TextView budget_amount_tv=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.budget_activity);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	private void loadingFormat(){
		
		budget_category_lv=(ListView)this.findViewById(R.id.budget_category_lv);
		budget_amount_tv=(TextView)this.findViewById(R.id.budget_amount_tv);
	}
	
	private void init(){
		
		
	}
}
