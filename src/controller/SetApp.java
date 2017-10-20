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
 * @author cyq 2017��9��29������3:24:40
 * @Description:
 */
public class SetApp {
	public static DesiredCapabilities dcp = new DesiredCapabilities();
	private static AppiumDriver driver = null;
	private static TouchAction touchAction = null;
	// �豸�߶�
	public static int hight;
	// �豸���
	public static int width;

	public static void setApp() {
		// �����豸����
		dcp.setCapability("deviceName", AppData.devicesName);
		// ����ƽ̨���� iOS��android
		dcp.setCapability("platformName", AppData.platformName);
		// android �汾
		dcp.setCapability("platformVersion", AppData.version);
		// ����
		dcp.setCapability("appPackage", AppData.appPackage);
		// APP��activity
		dcp.setCapability("appActivity", AppData.appActivity);

		// �ɱ�����ּ����������ĳ�������
		dcp.setCapability("unicodeKeyboard", "True"); // ʹ�� Unicode ���뷨
		dcp.setCapability("resetKeyboard", "True"); // �������뷨��ԭ��״̬

		try {
			driver = new AppiumDriver(new URL(AppData.urlString), dcp);
			touchAction = new TouchAction(driver);

		} catch (Exception e) {
			System.out.println("appium����ʧ��" + e.getStackTrace());
		}

		// ��ȡ�豸�ķֱ��ʣ��ߣ���
		try {
			hight = driver.manage().window().getSize().height;
			width = driver.manage().window().getSize().width;
		} catch (Exception e) {
			System.out.println("��ȡ�豸�ķֱ��ʣ��ߣ���ʧ��" + e.getStackTrace());
		}

	}

	// �ȴ�10s�ж�һ��Ԫ���Ƿ����
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

	// ��ͼ������
	public static void screenShot(String caseName) throws Exception {
		String name = caseName + AppData.sdf.format(new Date()) + ".jpg";
		// System.getProperty("user.dir") ��ȡ��ǰ��Ŀ��Ŀ¼
		String path = System.getProperty("user.dir") + "/screenshot/";// �ļ�Ŀ¼

		File image = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(image, new File(path + name));
	}

	// ��ȡԪ�ص��ı���Ϣ
	public static String getElementText(By by) {
		String text = "";
		try {
			text = driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("��ȡԪ���ı��쳣");
			e.printStackTrace();
		}
		return text;
	}

	// ��ȡ���������е�Ԫ������Ӧ�������ı���Ϣ
	public static List<String> getElementsNames(By by) {
		// ��ȡ�����Ե�����Ԫ��
		List<WebElement> elementlist = driver.findElements(by);
		List<String> elementsnames = new ArrayList<String>();
		// ��ȡ�������Ե������ı���Ϣ
		for (int i = 0; i < elementlist.size(); i++) {
			elementsnames.add(elementlist.get(i).getText());
		}
		return elementsnames;
	}

	// �Ƴ�list�����е��ظ�Ԫ��
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

	// ������Ļ
	public static boolean swipe(int startx, int starty, int endx, int endy) {
		boolean result = true;
		try {
			driver.swipe(startx, starty, endx, endy, 1000);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			System.out.println("��������Ļ�쳣");
		}
		return result;
	}

	// ������Ļ direction��ʾ����,during��ʾ����ʱ��
	public static void swipeSection(String direction, int during) {
		switch (direction) {
		case "left":// ���󻬶�
			try {
				driver.swipe(width * 3 / 4, hight / 2, width / 4, hight / 2,
						during);
			} catch (Exception e) {
				driver.swipe(width * 3 / 4, hight / 2, width / 4, hight / 2,
						during);
			}
			break;
		case "right":// ���һ���
			try {
				driver.swipe(width / 4, hight / 2, width * 3 / 4, hight / 2,
						during);
			} catch (Exception e) {
				driver.swipe(width / 4, hight / 2, width * 3 / 4, hight / 2,
						during);
			}
			break;
		case "up":// ���ϻ���
			try {
				driver.swipe(width / 2, hight * 3 / 4, width / 2, hight / 4,
						during);
			} catch (Exception e) {
				driver.swipe(width / 2, hight * 3 / 4, width / 2, hight / 4,
						during);
			}
			break;
		case "down":// ���»���
			try {
				driver.swipe(width / 2, hight / 4, width / 2, hight * 3 / 4,
						during);
			} catch (Exception e) {
				driver.swipe(width / 2, hight / 4, width / 2, hight * 3 / 4,
						during);
			}
			break;

		default:
			System.out.println("diretion��������Ϊ��left,right,up,down��");
			break;
		}
	}

	// ����ģ�� direction��ʾ����,during��ʾ����ʱ��
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
			System.out.println("diretion��������Ϊ��left,right��");
		}
	}

	// ͨ��By��Ԫ��text��ȡԪ��
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
			System.out.println("ͨ��By��Ԫ��text��ȡԪ���쳣");
		}
		return webElement;
	}

	// ͨ��By��ȡԪ�ض���
	public static WebElement getElement(By by) {
		WebElement webElement = null;
		try {
			Thread.sleep(3000);
			webElement = driver.findElement(by);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ͨ��By��ȡԪ�ض����쳣");
		}
		return webElement;
	}

	// ���һ��Ԫ��
	public static boolean click(By by) {
		boolean result = true;
		try {
			driver.findElement(by).click();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			System.out.println("���һ��Ԫ���쳣");
		}
		return result;
	}

	// ��������
	public static boolean sendKey(By by, String value) {
		boolean result = true;
		try {
			driver.findElement(by).sendKeys(value);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			System.out.println("���������쳣");
		}
		return result;
	}

	// ����������
	public static boolean clickCoordinate(int x, int y) {
		boolean result = true;
		try {
			touchAction.press(x, y).release().perform();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
			System.out.println("�����������쳣");
		}
		return result;
	}

	// �ж�Ԫ���Ƿ�ɵ㣨isenabled��
	public static boolean isEnabled(By by) {
		boolean result = false;
		try {
			if (driver.findElement(by).isEnabled()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�ж�Ԫ���Ƿ�ɵ㣨isenabled���쳣");
		}
		return result;
	}

	// ��ȡ��ǰ��ѧģ��
	// public static String getCurrentModelName() {
	// String currentModelName = "";
	// try {
	//
	// }
	// } catch (Exception e) {
	// System.out.println("��ȡ��ǰ��ѧģ��ʧ��");
	// e.printStackTrace();
	// }
	// System.out.println("��ǰ��ѧģ��Ϊ" + currentModelName);
	// return currentModelName;
	// }

	// ��Ƶ��ʼ�������С�����ʱ���������֤
	public static void checkFace() {
		try {
			Thread.sleep(2000);
			driver.findElement(By.id("com.nesec.jxjy_phone:id/btn_headcheck"))
					.click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���������֤�쳣");
		}
	}

	// ���̵������
	public static void back() {
		try {
			Thread.sleep(1000);
			SetApp.driver.sendKeyEvent(4);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��������쳣");
		}
	}

	// ���̵��home
	public static void home() {
		try {
			Thread.sleep(1000);
			SetApp.driver.sendKeyEvent(3);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���home�쳣");
		}
	}

	// ��mm:ss�ַ���ת��Ϊ������
	public static int timeToInt(String time) {
		int result = -1;
		try {
			String[] timeString = time.split(":");
			result = (Integer.parseInt(timeString[0]) * 60 + Integer
					.parseInt(timeString[1])) * 1000;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��mm:ss�ַ���ת��Ϊ�������쳣");
		}
		return result;
	}

	// ��ȡԪ�ص����� ��ȡԪ�غ����������y��hight��ֵ
	public static int getMidHight(WebElement we) {
		int hight = -1;
		try {
			hight = we.getLocation().y + we.getSize().height / 2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hight;
	}

	// ��ȡ��ǰ��ѧ�γ�ѧϰ���½�(���������⣬������...........)
	// public static String getCurrentChapterName() {
	// String currentChapterName = "";
	// int chapter = KCXXInfo.chapter;
	// int[] section = KCXXInfo.section;
	// try {
	// // ��ģ�黬������һ���½ڿ�����ʾ�Ľ���
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
	// System.out.println("��ȡ��ǰ��ѧ�½�ʧ��");
	// e.printStackTrace();
	// }
	// System.out.println("��ǰ��ѧ�γ�ѧϰ�½�Ϊ" + currentChapterName);
	// return currentChapterName;
	// }

}
