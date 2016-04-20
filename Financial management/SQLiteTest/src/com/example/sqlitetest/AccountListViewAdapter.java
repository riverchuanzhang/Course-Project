package com.example.sqlitetest;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AccountListViewAdapter extends BaseAdapter{

	private ArrayList<HashMap<Integer,ArrayList<String>>> myArrayList=null;
	
	private Context myContext;
	
	public AccountListViewAdapter(Context pContext,ArrayList<HashMap<Integer,ArrayList<String>>> pArrayList){
		
		this.myContext=pContext;
		this.myArrayList=pArrayList;
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myArrayList.size();
	}
	
	@Override
	public HashMap<Integer,ArrayList<String>> getItem(int position) {
		// TODO Auto-generated method stub
		return myArrayList.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater _LayoutInflater =LayoutInflater.from(myContext);
		View rootView=_LayoutInflater.inflate(R.layout.simple_list_item_adapter, null);
		TextView second_level_account_group_name_tv = (TextView)rootView.findViewById(R.id.second_level_account_group_name_tv);
		TextView account_balance_tv=(TextView)rootView.findViewById(R.id.account_balance_tv);
		second_level_account_group_name_tv.setText(myArrayList.get(position).get(position).get(0));
		account_balance_tv.setText(myArrayList.get(position).get(position).get(1));
		return rootView;
	}
	
}
