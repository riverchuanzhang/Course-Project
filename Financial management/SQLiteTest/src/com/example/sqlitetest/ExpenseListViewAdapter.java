package com.example.sqlitetest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

import com.example.object.InputInfo;
import com.example.object.OutputInfo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpenseListViewAdapter extends BaseAdapter{

	private Context myContext;
	private ArrayList<InputInfo> myArrayListIn=null;
	private ArrayList<OutputInfo> myArrayListOut= null;
	private final String TAG="ExpenseListViewAdapter";
	private final String MYLOG="myLog";
	private final String DATE="date";

	
	public ExpenseListViewAdapter(Context pContext,ArrayList<InputInfo> pArrayListIn,ArrayList<OutputInfo> pArrayListOut){
		
		myContext=pContext;
		myArrayListIn=pArrayListIn;
		myArrayListOut=pArrayListOut;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.i(MYLOG, TAG+""+myArrayListIn.size()+myArrayListOut.size());
		return myArrayListIn.size()+myArrayListOut.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if(position<myArrayListIn.size()){
			return myArrayListIn.get(position);
		}else{
			return myArrayListOut.get(position);
		}
		
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater _LayoutInflater = LayoutInflater.from(myContext);
		View rootView = _LayoutInflater.inflate(R.layout.nav_expend_listview_items, null);
		TextView category_name_tv=(TextView)rootView.findViewById(R.id.category_name_tv);
		TextView cost_tv = (TextView)rootView.findViewById(R.id.cost_tv);
		TextView output_time_tv=(TextView)rootView.findViewById(R.id.output_time_tv);
		ImageView category_icon_iv=(ImageView)rootView.findViewById(R.id.category_icon_iv);
		if(position<myArrayListIn.size()){
			category_name_tv.setText(myArrayListIn.get(position).getMySecondInGroupName());
			cost_tv.setText("+"+myArrayListIn.get(position).getMyInputMoney());
			output_time_tv.setText(myArrayListIn.get(position).getMyInputTime());
			category_icon_iv.setBackgroundResource(R.drawable.icon_qtzx);
		}else{
			category_name_tv.setText(myArrayListOut.get(position-myArrayListIn.size()).getMySecondOutGroupName());
			cost_tv.setText("-"+myArrayListOut.get(position-myArrayListIn.size()).getMyOutputMoney());
			output_time_tv.setText(myArrayListOut.get(position-myArrayListIn.size()).getMyOutputTime());
			category_icon_iv.setBackgroundResource(R.drawable.icon_rqwl_csjz);
		}
		Log.i(MYLOG, TAG+"position:"+position);
		return rootView;
	}

}
