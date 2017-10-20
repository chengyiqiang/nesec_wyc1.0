package view;

import org.openqa.selenium.By;

import testcase.Login;
import controller.SetApp;

public class DoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SetApp.setApp();
		Login.login_001();

	
			try {
				Thread.sleep(2000);
				SetApp.click(By.xpath("//android.widget.RadioButton[3]"));
				Thread.sleep(2000);
				SetApp.click(By.xpath("//android.widget.RadioButton[1]"));
				Thread.sleep(2000);
				SetApp.click(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.ImageView"));
				Thread.sleep(2000);
				SetApp.back();
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		

	}

}
