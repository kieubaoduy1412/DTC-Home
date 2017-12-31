package com.example.dtchome;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


import android.content.Context;


public class DataConfig {
	private Integer[] LampsDataDefault = { 0, 0, 0, 0 };
	private String PhoneDefault = "+84973033189";
	private String LampsFilename = "LampsData";
	private String PhoneFilename = "phone";
	private static Context context;	
	/*
	 * Hàm khởi tạo
	 */
	public DataConfig(Context GlobalContext){
		context = GlobalContext;
	}	
	
	/*
	 * Lấy trạng thái các đèn được lưu trong CSDL
	 */
	public Integer[] GetLampStatus(){
		if( IsFileExist(LampsFilename) )
		{
			Integer[] LampsData = LampsDataDefault;
			try {
				FileInputStream in = context.openFileInput(LampsFilename);
				BufferedReader reader = new BufferedReader(new InputStreamReader( in ));
				String data;
				while ((data = reader.readLine()) != null) {
					data = data.trim();
					if( data.length() > 0 ){
						String[] ConvertData = data.trim().split(":");
						LampsData[Integer.parseInt(ConvertData[0])] = Integer.parseInt(ConvertData[1]);
					}
				} in.close();
				return LampsData;
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			InitLampsStatusFile();
		}
		return LampsDataDefault;
	}
	
	/*
	 * Lấy trạng thái của 1 đèn
	 */
	public Integer GetLampStatus(Integer LampID){
		Integer[] AllStatus = GetLampStatus();
		return AllStatus[LampID];
	}
	
	/*
	 * Lưu trạng thái của 1 đèn
	 */
	public void SetLampStatus(Integer LampID, Integer Status){
		Integer[] AllStatus = GetLampStatus();
		AllStatus[LampID] = Status;
		
		int LampsLength = AllStatus.length;
		String Data = "";
		
		for( int i = 0; i < LampsLength; i ++ ){
			Data += i + ":" + AllStatus[i] + "\n";
		}
		
		WriteData( LampsFilename, Data );
	}
	
	/*
	 * Tạo file cấu hình mặc định nếu file không tồn tại
	 */
	private void InitLampsStatusFile(){
		String Data = "";
		int LampsLength = LampsDataDefault.length;
		
		for( int i = 0; i < LampsLength; i ++ ){
			Data += i + ":" + LampsDataDefault[i] + "\n";
		}
		WriteData( LampsFilename, Data );
	}
	
	/*
	 * Ghi dữ liệu ra file
	 */
	private void  WriteData( String Filename, String Data){
		FileOutputStream outputStream;
		try {
			outputStream = context.openFileOutput(Filename, Context.MODE_PRIVATE);
			outputStream.write(Data.getBytes());
			outputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Kiểm tra file có tồn tại không
	 */
	private boolean IsFileExist(String fname){
	    File file = context.getFileStreamPath(fname);
	    return file.exists();
	}
	
	public String getPhoneNumber(){
		if( ! IsFileExist( PhoneFilename ) ){
			return PhoneDefault;
		}
		
		String phone = "";
		try {
			FileInputStream in = context.openFileInput(PhoneFilename);
			BufferedReader reader = new BufferedReader(new InputStreamReader( in ));
			String data = "";
			
			while ((data = reader.readLine()) != null) {
				data = data.trim();
				
				if( data.length() > 0 ){
					phone += data;
				}
			} in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return phone;
	}
	
	public void storePhoneNumber(String phone){
		WriteData( PhoneFilename, phone );
	}
}
