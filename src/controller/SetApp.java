package controller;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import appdata.AppData;
import appdata.KCXXInfo;

/**
 * @author cyq 2017年9月29日下午3:24:40
 * @Description:
 */
public class SetApp {
	public static DesiredCapabilities dcp = new DesiredCapabilities();
	private static AppiumDriver driver = null;
	private static TouchAction touchAction = null;
	// 设备高度
	public static int hight;
	// 设备宽度
	public static int width;

	public static void setApp() {
		// 设置设备名称
		dcp.setCapability("deviceName", AppData.devicesName);
		// 设置平台名称 iOS；android
		dcp.setCapability("platformName", AppData.platformName);
		// android 版本
		dcp.setCapability("platformVersion", AppData.version);
		// 包名
		dcp.setCapability("appPackage", AppData.appPackage);
		// APP主activity
		dcp.setCapability("appActivity", AppData.appActivity);

		// 可避免出现键盘输入中文出错问题
		dcp.setCapability("unicodeKeyboard", "True"); // 使用 Unicode 输入法
		dcp.setCapability("resetKeyboard", "True"); // 重置输入法到原有状态

		try {
			driver = new AppiumDriver(new URL(AppData.urlString), dcp);
			touchAction = new TouchAction(driver);

		} catch (Exception e) {
			System.out.println("appium启动失败" + e.getStackTrace());
		}

		// 获取设备的分辨率（高，宽）
		try {
			hight = driver.manage().window().getSize().height;
			width = driver.manage().window().getSize().width;
		} catch (Exception e) {
			System.out.println("获取设备的分辨率（高，宽）失败" + e.getStackTrace());
		}

	}

	// 等待10s判断一个元素是否出现
	public static boolean waitForElement(By by) throws Exception {
		boolean result = false;
		for (int i = 1; i < 10; i++) {
			try {
				driver.findElement(by);
				result = true;
				break;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		return result;
	}

	// 截图并保存
	public static void screenShot(String caseName) throws Exception {
		String name = caseName + AppData.sdf.format(new Date()) + ".jpg";
		// System.getProperty("user.dir") 获取当前项目的目录
		String path = System.getProperty("user.dir") + "/screenshot/";// 文件目录

		File image = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(image, new File(path + name));
	}

	// 获取元素的文本信息
	public static String getElementText(By by) {
		String text = "";
		try {
			text = driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("获取元素文本异常");
			e.printStackTrace();
		}
		return text;
	}

	// 获取该属性所有的元素所对应的所有文本信息
	public static List<String> getElementsNames(By by) {
		// 获取该属性的所有元素
		List<WebElement> elementlist = driver.findElements(by);
		List<String> elementsnames = new ArrayList<String>();
		// 获取所有属性的所有文本信息
		for (int i = 0; i < elementlist.size(); i++) {
			elementsnames.add(elementlist.get(i).getText());
		}
		return elementsnames;
	}

	// 移除list集合中的重复元素
	public static List removeDuolicateWithOrder(List list) {
		Set set = new HashSet();
		List newlist = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newlist.add(element);
		}
		list.clear();
		list.addAll(newlist);
		return newlist;
	}

	// 滑动屏幕
	public static boolean swipe(int startx, int starty, int endx, int endy) {
		boolean result = true;
		try {
			driver.swipe(startx, starty, endx, endy, 1000);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			System.out.println("根滑动屏幕异常");
		}
		return result;
	}

	// 滑动屏幕 direction表示方向,during表示滑动时间
	public static void swipeSection(String direction, int during) {
		switch (direction) {
		case "left":// 向左滑动
			try {
				driver.swipe(width * 3 / 4, hight / 2, width / 4, hight / 2,
						during);
			} catch (Exception e) {
				driver.swipe(width * 3 / 4, hight / 2, width / 4, hight / 2,
						during);
			}
			break;
		case "right":// 向右滑动
			try {
				driver.swipe(width / 4, hight / 2, width * 3 / 4, hight / 2,
						during);
			} catch (Exception e) {
				driver.swipe(width / 4, hight / 2, width * 3 / 4, hight / 2,
						during);
			}
			break;
		case "up":// 向上滑动
			try {
				driver.swipe(width / 2, hight * 3 / 4, width / 2, hight / 4,
						during);
			} catch (Exception e) {
				driver.swipe(width / 2, hight * 3 / 4, width / 2, hight / 4,
						during);
			}
			break;
		case "down":// 向下滑动
			try {
				driver.swipe(width / 2, hight / 4, width / 2, hight * 3 / 4,
						during);
			} catch (Exception e) {
				driver.swipe(width / 2, hight / 4, width / 2, hight * 3 / 4,
						during);
			}
			break;

		default:
			System.out.println("diretion参数必须为“left,right,up,down”");
			break;
		}
	}

	// 滑动模块 direction表示方向,during表示滑动时间
	public static void swipeModel(String direction, int during) {
		int hight = getMidHight(driver.findElement(By
				.id("com.nesec.jxjy_phone:id/indicator")));
		if (direction.equals("left")) {
			try {
				driver.swipe(500, hight, 90, hight, during);
			} catch (Exception e) {
				driver.swipe(500, hight, 90, hight, during);
			}
		} else if (direction.equals("right")) {
			try {
				driver.swipe(90, hight, 500, hight, during);
			} catch (Exception e) {
				driver.swipe(90, hight, 500, hight, during);
			}
		} else {
			System.out.println("diretion参数必须为“left,right”");
		}
	}

	// 通过By和元素text获取元素
	public static WebElement getElementByTextAndBy(By by, String elementtext) {
		WebElement webElement = null;
		try {
			Thread.sleep(3000);
			List<WebElement> elements = driver.findElements(by);
			for (int i = 0; i < elements.size(); i++) {
				if (elements.get(i).getText().equals(elementtext)) {
					webElement = elements.get(i);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("通过By和元素text获取元素异常");
		}
		return webElement;
	}

	// 通过By获取元素对象
	public static WebElement getElement(By by) {
		WebElement webElement = null;
		try {
			Thread.sleep(3000);
			webElement = driver.findElement(by);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("通过By获取元素对象异常");
		}
		return webElement;
	}

	// 点击一个元素
	public static boolean click(By by) {
		boolean result = true;
		try {
			driver.findElement(by).click();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			System.out.println("点击一个元素异常");
		}
		return result;
	}

	// 输入内容
	public static boolean sendKey(By by, String value) {
		boolean result = true;
		try {
			driver.findElement(by).sendKeys(value);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			System.out.println("输入内容异常");
		}
		return result;
	}

	// 根据坐标点击
	public static boolean clickCoordinate(int x, int y) {
		boolean result = true;
		try {
			touchAction.press(x, y).release().perform();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			System.out.println("根据坐标点击异常");
		}
		return result;
	}

	// 判断元素是否可点（isenabled）
	public static boolean isEnabled(By by) {
		boolean result = false;
		try {
			if (driver.findElement(by).isEnabled()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("判断元素是否可点（isenabled）异常");
		}
		return result;
	}

	// 获取当前所学模块
	// public static String getCurrentModelName() {
	// String currentModelName = "";
	// try {
	//
	// }
	// } catch (Exception e) {
	// System.out.println("获取当前所学模块失败");
	// e.printStackTrace();
	// }
	// System.out.println("当前所学模块为" + currentModelName);
	// return currentModelName;
	// }

	// 视频开始、过程中、结束时点击人脸验证
	public static void checkFace() {
		try {
			Thread.sleep(2000);
			driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_headcheck"))
					.click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("点击人脸验证异常");
		}
	}

	// 键盘点击返回
	public static void back() {
		try {
			Thread.sleep(1000);
			SetApp.driver.sendKeyEvent(4);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("点击回退异常");
		}
	}

	// 键盘点击home
	public static void home() {
		try {
			Thread.sleep(1000);
			SetApp.driver.sendKeyEvent(3);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("点击home异常");
		}
	}

	// 将mm:ss字符串转换为毫秒数
	public static int timeToInt(String time) {
		int result = -1;
		try {
			String[] timeString = time.split(":");
			result = (Integer.parseInt(timeString[0]) * 60 + Integer
					.parseInt(timeString[1])) * 1000;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("将mm:ss字符串转换为毫秒数异常");
		}
		return result;
	}

	// 获取元素的坐标 获取元素横向中心轴的y（hight）值
	public static int getMidHight(WebElement we) {
		int hight = -1;
		try {
			hight = we.getLocation().y + we.getSize().height / 2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hight;
	}

	// 获取当前所学课程学习的章节(方法有问题，完善中...........)
	// public static String getCurrentChapterName() {
	// String currentChapterName = "";
	// int chapter = KCXXInfo.chapter;
	// int[] section = KCXXInfo.section;
	// try {
	// // 将模块滑动到第一个章节可以显示的界面
	// for (int i = 0; i < 6; i++) {
	// swipeSection("down", 2000);
	// }
	//
	// for (int i = 0; i < chapter; i++) {
	// for (int j = 0; j < section[i]; j++) {
	// int k = 0;
	// for (int m = 0; m < i; m++) {
	// k = k + section[m];
	// }
	// if
	// (getElementByTextAndBy(By.id("com.nesec.jxjy_phone:id/tv_child_title"),AppData.childNameList.get(k
	// + j)).isEnabled()) {
	// currentChapterName = AppData.childNameList.get(k + j);
	// System.out.println(k+j);//
	// } else {
	// return currentChapterName;
	// }
	// }
	// Thread.sleep(2000);
	// getElementByTextAndBy(By.id("com.nesec.jxjy_phone:id/tv_group_title"),AppData.groupNameList.get(i)).click();
	// }
	// } catch (Exception e) {
	// System.out.println("获取当前所学章节失败");
	// e.printStackTrace();
	// }
	// System.out.println("当前所学课程学习章节为" + currentChapterName);
	// return currentChapterName;
	// }

}
