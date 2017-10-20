package testcase;

import org.openqa.selenium.By;

import controller.SetApp;


public class Login {
	
	public static void login_001 (){
		try {
			Thread.sleep(3000);
			SetApp.sendKey(By.id("com.nesun.netarrangecar:id/et_user_name"), "429004199909090946");
			SetApp.sendKey(By.id("com.nesun.netarrangecar:id/et_user_pass"), "111111");
			SetApp.click(By.id("com.nesun.netarrangecar:id/btn_login"));
			Thread.sleep(2000);
			SetApp.click(By.id("com.nesun.netarrangecar:id/btn_headcheck"));
			Thread.sleep(2000);
			SetApp.click(By.id("com.nesun.netarrangecar:id/dialog_yes"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ÓÃÀýlogin_001Ö´ÐÐ³ö´í");
		}		
	}
	
}
