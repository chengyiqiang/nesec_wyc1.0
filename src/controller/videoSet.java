package controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class videoSet {

	// �����Ƶ����ʼѧϰ
	public static void watchVideo(String sectionName) {
		try {
			SetApp.getElementByTextAndBy(
					By.id("com.nesec.jxjy_phone:id/tv_child_title"),
					sectionName).click();
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�����Ƶ��ѧϰ�쳣");
		}
	}

	// ��ȡ��Ƶ���Ź����е�Ԫ��
	public static WebElement getVideoElement(By by) {
		WebElement webElement = null;
		try {
			Thread.sleep(5000);
			SetApp.touchAction.press(SetApp.width / 2, SetApp.hight / 2)
					.release().perform();
			Thread.sleep(1000);
			webElement = SetApp.driver.findElement(by);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webElement;
	}

	// ��Ƶ��ͣ��ʼ
	public static void pauseOrPlay() {
		try {
			getVideoElement(By.id("com.nesec.jxjy_phone:id/btn_play")).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��Ƶ��ͣ�򲥷��쳣");
		}
	}

	// ��ȡ��Ƶ�Ѳ���ʱ��
	public static String getVideoHasPlayedTime() {
		String videoHasPlayedTime = "��ȡ�Ѳ�ʱ��ʧ��";
		try {
			videoHasPlayedTime = getVideoElement(
					By.id("com.nesec.jxjy_phone:id/tv_has_played_time"))
					.getText();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��ȡ�Ѳ�ʱ���쳣");
		}
		return videoHasPlayedTime;
	}

	// ��ȡ��Ƶʱ��
	public static String getVideoTotalTime() {
		String videoTotalTime = "��ȡ��Ƶ��ʱ��ʧ��";
		try {
			videoTotalTime = getVideoElement(
					By.id("com.nesec.jxjy_phone:id/tv_duration_alltime"))
					.getText();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��ȡ��Ƶ��ʱ���쳣");
		}
		return videoTotalTime;
	}

	// ��Ƶ���÷��ؼ�
	public static void videoBack() {
		try {
			getVideoElement(By.id("com.nesec.jxjy_phone:id/imgbtn_back"))
					.click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�����Ƶ���÷��ؼ��쳣");
		}
	}

	// ��ȡ��Ƶ����
	public static String getVideoTitle() {
		String videoTitle = "��ȡ��Ƶ����ʧ��";
		try {
			videoTitle = getVideoElement(
					By.id("com.nesec.jxjy_phone:id/tv_video_title")).getText();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��ȡ��Ƶ�����쳣");
		}
		return videoTitle;
	}

	// �϶�������
	public static void dragSeekBar(String backOrFront) {
		try {
			Thread.sleep(5000);
			SetApp.touchAction.press(SetApp.width / 2, SetApp.hight / 2)
					.release().perform();
			Thread.sleep(1000);
			int hight = SetApp.getMidHight(SetApp.driver.findElement(By
					.id("com.nesec.jxjy_phone:id/seekbar")));
			if (backOrFront.equals("front")) {
				SetApp.driver.swipe(100, hight, 400, hight, 1500);
			} else if (backOrFront.equals("back")) {
				SetApp.driver.swipe(400, hight, 100, hight, 1500);
			} else {
				System.out.println("\"backOrFront\"��������Ϊback��front");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�϶��������쳣");
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
}
