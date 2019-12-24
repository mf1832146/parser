package com.tangze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonReader {

    static ArrayList<String[]> read(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        String data = "";
        ArrayList res = new ArrayList();

        while(sc.hasNextLine()){
            String line = sc.nextLine().trim();
            if(line.length() != 0){
                JsonObject json = (JsonObject)(new Gson()).fromJson(line, JsonObject.class);
                String code = json.get("code").getAsString();
                String nl = json.get("comment").getAsString();
                res.add(new String[]{code, nl});
            }
        }
        return res;
    }

}
