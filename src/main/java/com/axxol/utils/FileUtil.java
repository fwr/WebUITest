package com.axxol.utils;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    //删除指定文件夹下所有文件
//param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                flag = true;
            }
        }
        return flag;
    }

    /**
     *创建文件（包括它的目录）
     * @param path  文件所在的目录，如/sct/s/a
     * @param name  文件名"/a.txt"
     */
    public static void createFile(String path,String name){
        createFile(path);
        File file=new File(path+name);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     *创建文件夹
     */
    public static void createFile(String path){
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }
}
