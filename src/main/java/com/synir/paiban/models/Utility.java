package com.synir.paiban.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;

public class Utility {
	public String getTimestamp() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
	public String gen_uuid(String username,String timestamp) {
		return timestamp.replaceAll("\\s","")+username;
	}
	public void savetoData(JSONArray array,String uuid) throws IOException {
//		
        BufferedWriter bw = new BufferedWriter(new FileWriter("/usr/test/schedule/"+uuid+".txt"));
        for(int i = 0; i < array.length();i++) {
        	bw.write(array.getString(i)+";");
        }
        bw.flush();
        bw.close();
	}
	public String[] loadData(String uuid) throws IOException{
		String res = "";
		BufferedReader br = null;
        FileReader reader = null;
        reader = new FileReader("/usr/test/schedule/"+uuid+".txt");
        br = new BufferedReader(reader);
        String s = null;
        while ((s = br.readLine()) != null) {
           res += s;
        }
        br.close();
        return res.split(";");
		
	}
	public void removefromData(String filename) {
		
	}
}
