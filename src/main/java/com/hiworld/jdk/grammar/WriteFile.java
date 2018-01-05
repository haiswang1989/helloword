package com.hiworld.jdk.grammar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.alibaba.fastjson.JSONObject;

public class WriteFile {

    public static void main(String[] args) throws IOException {
        
        File file = new File("F:\\test.log");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        
        String str = "wanghaisheng";
        int i=0;
        long t1 = System.currentTimeMillis();
        while(i++!=10000000) {
            JSONObject.toJSONString(str).getBytes();
            bw.write(str);
            bw.newLine();
        }
        long t2 = System.currentTimeMillis();
        System.out.println("use : " + (t2-t1));
        
    }

}
