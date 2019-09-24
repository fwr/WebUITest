package com.axxol.utils;

import java.io.*;
import java.util.Properties;

public class ReadProperties {
    public static Properties readConfig(){
        Properties pps = new Properties();
        String PATH="/config.properties";
        try {
            InputStream in=ReadProperties.class.getResourceAsStream(PATH);
            pps.load(new InputStreamReader(in, "utf-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pps;
    }

    public static String getConfig(String key){
        //考虑命令行的方式的读取
        Properties properties=readConfig();
        String value=properties.getProperty(key,"").trim();
        return value;
    }

    /**
     * 按行读取文件,返回整个文件的内容，如json文件
     */
    public static String ReadFile(String filename) {
        String filepath = System.getProperty("user.dir")+"/src/test/resources/"+filename;
        File f=new File(filepath);
        FileInputStream fin= null;
        try {
            fin = new FileInputStream(f);
            byte[] bs=new byte[1024];
            int count=0;
            StringBuffer sb=new StringBuffer();
            while((count=fin.read(bs))>0)
            {
                String str=new String(bs,0,count);	//反复定义新变量：每一次都 重新定义新变量，接收新读取的数据
                sb.append(str);
            }
            fin.close();
            System.out.println(sb.toString());
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
