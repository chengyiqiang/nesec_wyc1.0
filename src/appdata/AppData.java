package appdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


//客户端数据集合
public class AppData {
	
	//数据信息
	//设备名称
	public static String deviceName = "faf6d064";
//	public static String devicesName = "QDY4C17523003538";
	//设备操作平台
	public static String platformName = "Android";
//	public static String platformVersion = "";
	//apk包名
	public static String appPackage = "com.nesun.netarrangecar";
	//apk主activity
	public static String appActivity = "com.nesun.netarrangecar.activity.WelcomeActivity";
	//设备安卓版本
	public static String version = "4.4.4";
//	public static String version = "7.0";
	public static String urlString = "http://127.0.0.1:4723/wd/hub";

	//设置日期格式
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	//初始行和列
	public static int row = 1;
	public static int column = 0;
	
	//用例路径
	public static String path = "D:\\Result\\长沙网约车";

	
}
