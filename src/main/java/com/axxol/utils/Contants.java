package com.axxol.utils;

public class Contants {
/*    //模拟器三星GalaxyS7
    public class android{
        public static final String platformName="Android";
        public static final String deviceName="8AD4C17308002700";
        public static final String platformVersion="6.0";
    }*/
    //华为nova
//    public class android{
//        public static final String platformName="Android";
//        public static final String deviceName="8AD4C17308002700";
//        public static final String platformVersion="6.0";
//    }
/*    //vivo XA20
    public class android{
        public static final String platformName="Android";
        public static final String deviceName="KWG5T16328001562";
        public static final String platformVersion="6.0";
    }*/
/*    //华为mate9
    public class android{
        public static final String platformName="Android";
        public static final String deviceName="3HX0217321011138";
        public static final String platformVersion="7.0";
    }*/
//小米5x
    public class android{
        public static final String platformName="Android";
        public static final String deviceName="8c3bdef20804";
        public static final String platformVersion="8.1.0";
    }
    //浏览器
    public class browser{
        public static final String browser_name="";
    }
    public static void main(String arg[]){
        /*String path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(path);
        String apppath=path+"student_release_3.2.4.apk";
        System.out.println(apppath);*/
        String rootPath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
        //  .substring(1)是为了去掉最前的"/",如果linux环境去掉"/"就不能正确获取路径
        rootPath = rootPath.substring(1);
        rootPath = rootPath.substring(0,rootPath.indexOf("target"));

        System.out.println(rootPath);
    }
    private static String getProjectRootPath(){
        String rootPath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
        rootPath = rootPath.substring(0,rootPath.indexOf("target"));
        //  .substring(1)是为了去掉最前的"/",如果linux环境去掉"/"就不能正确获取路径
        rootPath = rootPath.substring(1);
        return rootPath;
    }

    //学生端
//    public static class app_info{
//        public static final String apppath=getProjectRootPath()+"target/classes/student_release_3.2.4.apk";
//        public static final String appPackage="gaosi.com.learn";
//        public static final String appActivity="gaosi.com.learn.studentapp.loading.SplashingActivity";
//    }

/*    //教师端、校长端
    public class app_info{
        public static final String appPackage="com.gaosi.teacherapp";
        public static final String appActivity="com.gaosi.teacherapp.loading.SplashingActivity";
    }*/
  //91好课
    public class app_info{
        public static final String appPackage="com.haoke91.a91edu";
        public static final String appActivity="com.haoke91.a91edu.ui.WelcomeActivity";
    }

    public class appium_server{
        public static final String url="http://127.0.0.1:4723/wd/hub";
    }
}
