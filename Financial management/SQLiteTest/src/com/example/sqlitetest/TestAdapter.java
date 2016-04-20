package com.example.sqlitetest;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TestAdapter extends BaseAdapter{
	
	private Context myContext;
	private ArrayList<ArrayList<String>> myItems=null;
	public TestAdapter(Context pContext,ArrayList<ArrayList<String>> pItems){
		this.myContext=pContext;
		this.myItems=pItems;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return myItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater _LayoutInflater=(LayoutInflater)LayoutInflater.from(myContext);
		View rootView = _LayoutInflater.inflate(R.layout.text_adapter,null);
		TextView _UserEmailTextView = (TextView)rootView.findViewById(R.id.text_user_email);
		TextView _UserPasswordTextView = (TextView)rootView.findViewById(R.id.text_user_password);
		TextView _UserNameTextView = (TextView)rootView.findViewById(R.id.text_user_name);
		_UserEmailTextView.setText(myItems.get(position).get(0));
		_UserPasswordTextView.setText(myItems.get(position).get(1));
		_UserNameTextView.setText(myItems.get(position).get(2));
		return rootView;
	}

	
}
