package com.example.db;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

import com.example.object.BusinessInfo;

public class DataInfo {
	
	public DataInfo(){
		
	}
	private static final String TAG="DataInfo";
	private String[][] myUserInfo={
			{"001","001","zhangdachuan"},
			{"002","002","zhangrongdong"},
			{"003","003","zhengzelin"}
	};
	private String[][] myBusinessInfo={
			{"1","饭堂"},
			{"2","银行"},
			{"3","商场"},
			{"4","超市"},
			{"5","公交"},
			{"6","其他"},
	};
	private String[][] myFirstOutGroupInfo={
			{"1","其他杂项","0"},
			{"2","金融保险","0"},
			{"3","医疗保险","0"},
			{"4","人情往来","0"},
			{"5","学习进修","0"},
			{"6","休闲娱乐","0"},
			{"7","交通通讯","0"},
			{"8","行车交通","0"},
			{"9","居家物业","0"},
			{"10","食品酒水","0"},
			{"11","衣服饰品","0"}
	};
	private String[][] mySecondOutGroupInfo={
			{"1","烂帐损失","1","0"},
			{"2","意外丢失","1","0"},
			{"3","其他支出","1","0"},
			{"4","赔偿罚款","2","0"},
			{"5","信息支出","2","0"},
			{"6","消费税收","2","0"},
	};
	
	private String[][] myAccountInfo={
			{"1","银行卡","0"},
			{"2","饭卡","0"},
			{"3","其他","0"}
	};
	
	private String[][] myProjectInfo={
			{"1","腐败"},
			{"2","旅游"},
			{"3","装修"},
			{"4","公司报销"},
			{"5","出差"},
			{"6","其他"}
	};
	
	private final String[][] myFirstInGroupInfo={
			{"1","其他输入"},
			{"2","职业输入"},
	} ;
	
	private final String[][] mySecondInGroupInfo={
			{"1","经营所得","1"},
			{"2","意外来钱","1"},
			{"3","中奖收入","1"},
			{"4","兼职收入","2"},
			{"5","奖金收入","2"},
			{"6","加班收入","2"},
	};
	
	public ArrayList<HashMap<Integer,String>> getBusinessInfo(){
		ArrayList<HashMap<Integer,String>> myBusinessInfoArrayList=new ArrayList<HashMap<Integer,String>>();
		for(int i=0;i<myBusinessInfo.length;i++){
			HashMap<Integer,String> _HashMap=new HashMap<Integer,String>();
			for(int j=0;j<myBusinessInfo[i].length;j++){
				_HashMap.put(j, myBusinessInfo[i][j]);
				Log.i(TAG, myBusinessInfo[i][j]);
			}
			myBusinessInfoArrayList.add(_HashMap);
		}
		return myBusinessInfoArrayList;
	}
	
	public ArrayList<HashMap<Integer,String>> getUserInfo(){
		ArrayList<HashMap<Integer,String>> myUserInfoArrayList=new ArrayList<HashMap<Integer,String>>();
		for(int i=0;i<myUserInfo.length;i++){
			HashMap<Integer,String> _HashMap=new HashMap<Integer,String>();
			for(int j=0;j<myUserInfo[i].length;j++){
				_HashMap.put(j, myUserInfo[i][j]);
				Log.i(TAG, myUserInfo[i][j]);
			}
			myUserInfoArrayList.add(_HashMap);
		}
		return myUserInfoArrayList;
	}
	public ArrayList<HashMap<Integer,String>> getFirstOutGroupInfo(){
		ArrayList<HashMap<Integer,String>> myFirstOutGroupInfoArrayList=new ArrayList<HashMap<Integer,String>>();
		for(int i=0;i<myFirstOutGroupInfo.length;i++){
			HashMap<Integer,String> _HahsMap = new HashMap<Integer,String>();
			for(int j=0;j<myFirstOutGroupInfo[i].length;j++){
				_HahsMap.put(j, myFirstOutGroupInfo[i][j]);
				Log.i(TAG, myFirstOutGroupInfo[i][j]);
			}
			myFirstOutGroupInfoArrayList.add(_HahsMap);
		}
		return myFirstOutGroupInfoArrayList;
	}
	public ArrayList<HashMap<Integer,String>> getSecondOutGroupInfo(){
		ArrayList<HashMap<Integer,String>> mySecondOutGroupInfoArrayList=new ArrayList<HashMap<Integer,String>>();
		for(int i=0;i<mySecondOutGroupInfo.length;i++){
			HashMap<Integer,String> _HashMap=new HashMap<Integer,String>();
			for(int j=0;j<mySecondOutGroupInfo[i].length;j++){
				_HashMap.put(j, mySecondOutGroupInfo[i][j]);
				Log.i(TAG, mySecondOutGroupInfo[i][j]);
			}
			mySecondOutGroupInfoArrayList.add(_HashMap);
		}
		return mySecondOutGroupInfoArrayList;
		
	}
	public ArrayList<HashMap<Integer,String>> getAccountInfo(){
		ArrayList<HashMap<Integer,String>> myAccountInfoArrayList=new ArrayList<HashMap<Integer,String>>();
		for(int i=0;i<myAccountInfo.length;i++){
			HashMap<Integer,String> _HashMap=new HashMap<Integer,String>();
			for(int j=0;j<myAccountInfo[i].length;j++){
				_HashMap.put(j, myAccountInfo[i][j]);
				Log.i(TAG, myAccountInfo[i][j]);
			}
			myAccountInfoArrayList.add(_HashMap);
		}
		return myAccountInfoArrayList;
		
	}
	public ArrayList<HashMap<Integer,String>> getProjectInfo(){
		ArrayList<HashMap<Integer,String>> myProjectInfoArrayList = new ArrayList<HashMap<Integer,String>>();
		for(int i=0;i<myProjectInfo.length;i++){
			HashMap<Integer,String> _HashMap = new HashMap<Integer,String>();
			for(int j=0;j<myProjectInfo[i].length;j++){
				_HashMap.put(j, myProjectInfo[i][j]);
				Log.i(TAG, myProjectInfo[i][j]);
			}
			myProjectInfoArrayList.add(_HashMap);
		}
		return myProjectInfoArrayList;
	}
	public ArrayList<HashMap<Integer,String>> getFirstInGroupInfo(){
		
		ArrayList<HashMap<Integer,String>> myFirstInGroupInfoArrayList = new ArrayList<HashMap<Integer,String>>();
		for(int i=0;i<myFirstInGroupInfo.length;i++){
			HashMap<Integer,String> _HashMap = new HashMap<Integer,String>();
			for(int j=0;j<myFirstInGroupInfo[i].length;j++){
				_HashMap.put(j, myFirstInGroupInfo[i][j]);
				Log.i(TAG, myFirstInGroupInfo[i][j]);
			}
			myFirstInGroupInfoArrayList.add(_HashMap);
		}
		return myFirstInGroupInfoArrayList;
	}
	public ArrayList<HashMap<Integer,String>> getSecondInGroupInfo(){
		ArrayList<HashMap<Integer,String>> mySecondInGroupInfoArrayList = new ArrayList<HashMap<Integer,String>>();
		for(int i=0;i<mySecondInGroupInfo.length;i++){
			HashMap<Integer,String> _HashMap = new HashMap<Integer,String>();
			for(int j=0;j<mySecondInGroupInfo[i].length;j++){
				_HashMap.put(j, mySecondInGroupInfo[i][j]);
				Log.i(TAG, mySecondInGroupInfo[i][j]);
			}
			mySecondInGroupInfoArrayList.add(_HashMap);
		}
		return mySecondInGroupInfoArrayList;
	}
}
