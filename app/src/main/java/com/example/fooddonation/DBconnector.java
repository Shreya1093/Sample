package com.example.fooddonation;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBconnector extends SQLiteOpenHelper {

	public DBconnector(Context context) {
		super(context, "donate", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE IF NOT EXISTS reg(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT,email TEXT,phone TEXT,address text,role text)";
		db.execSQL(sql);
		String sql3 = "CREATE TABLE IF NOT EXISTS donatefood(id INTEGER PRIMARY KEY AUTOINCREMENT,username text, quantity TEXT,type text, time TEXT,address text,volunteer text,orphanage text)";
		db.execSQL(sql3);
		String sql4 = "CREATE TABLE IF NOT EXISTS foodcount(id INTEGER PRIMARY KEY AUTOINCREMENT, donor TEXT,volunteer TEXT,orphanage text,count TEXT,address text)";
		db.execSQL(sql4);
		String sql5 = "CREATE TABLE IF NOT EXISTS request(id INTEGER PRIMARY KEY AUTOINCREMENT,username text, quantity TEXT,type text, time TEXT,address text,orphanage text,qrequest text,status text)";
		db.execSQL(sql5);
		
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		db.execSQL("DROP TABLE reg IF EXITS");
		db.execSQL("DROP TABLE donatefood IF EXITS");
		db.execSQL("DROP TABLE foodcount IF EXITS");
		db.execSQL("DROP TABLE request IF EXITS");
		
		onCreate(db);
	}
	
	public void insert_reg(HashMap<String, String> map){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("username",  map.get("username"));
		cv.put("password",map.get("password"));
		cv.put("email",map.get("email"));
		
		cv.put("phone",map.get("phone"));
		cv.put("address",map.get("address"));
		cv.put("role",map.get("role"));
		
		sb.insert("reg", null, cv);
	}
	
	public void insert_request(HashMap<String, String> map){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("username",  map.get("username"));
		cv.put("quantity",map.get("quantity"));
		cv.put("type",map.get("type"));
		cv.put("time",  map.get("time"));
		cv.put("address",map.get("address"));
		
		cv.put("orphanage", map.get("orphanage"));
		cv.put("qrequest", map.get("qrequest"));
		cv.put("status", map.get("status"));
		
		sb.insert("request", null, cv);
	}
	
	
	public void insert_donatefood(HashMap<String, String> map){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("username",  map.get("username"));
		cv.put("quantity",map.get("quantity"));
		cv.put("type",map.get("type"));
		cv.put("time",  map.get("time"));
		cv.put("address",map.get("address"));
		cv.put("volunteer", map.get("volunteer"));
		cv.put("orphanage", map.get("orphanage"));
		
		sb.insert("donatefood", null, cv);
	}
	
	public void insert_foodcount(HashMap<String, String> map){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("donor",  map.get("donor"));
		cv.put("volunteer",map.get("volunteer"));
		cv.put("orphanage",map.get("orphanage"));
		cv.put("count",map.get("count"));
		cv.put("address",map.get("address"));
		
		
		sb.insert("foodcount", null, cv);
	}
	
	public void acceptDrug(String value){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("status","yes"); //These Fields should be your String values of actual column names

sb.update("medicine", cv, "drug='"+value+"'", null);
	
	}
	
public void deleteUser(String value){
	SQLiteDatabase sb = this.getWritableDatabase();

	sb.execSQL("delete from reg where username='"+value+"'");
	

}

public void deleteConsultant(String value){
	SQLiteDatabase sb = this.getWritableDatabase();

	sb.execSQL("delete from consultant where username='"+value+"'");
	

}
public void deleteDrug(String value){
	SQLiteDatabase sb = this.getWritableDatabase();

	sb.execSQL("delete from medicine where drug='"+value+"'");
	

}

}
