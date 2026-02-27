package jp.co.sss.crud.test01;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("01_ログイン機能")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {

	@LocalServerPort
	private int port;

	private WebDriver webDriver;

	/**
	 * テストメソッドを実行する前に実行されるメソッド
	 */
	@BeforeEach
	public void createDriver() {
		ChromeOptions options = new ChromeOptions();
		// 実際のブラウザ画面を見たくない場合は以下を有効にします
		// options.addArguments("--headless"); 

		webDriver = new ChromeDriver(options);
		// ヘッドレス時にウィンドウサイズを指定しないと、要素が重なって見えなくなることがあるため設定
		options.addArguments("--window-size=1920,1080");

	}

	@AfterEach
	public void quitDriver() {

		if (webDriver != null) {
			webDriver.quit();
		}
	}

	@Test
	@Order(1)
	public void 正常系_ログイン操作_画面遷移_一般権限() {

		// スクリーンショットのリスト
		ArrayList<File> tempFileList = new ArrayList<File>();
		// スクショ保存パス
		String screenshotPath = "screenshots\\01_LoginTest\\";

		// 指定したURLに遷移する
		webDriver.get("http://localhost:" + port + "/spring_crud/");

		WebElement loginIdElement = webDriver.findElement(By.name("empId"));
		loginIdElement.clear();
		loginIdElement.sendKeys("1");

		WebElement password = webDriver.findElement(By.name("empPass"));
		password.clear();
		password.sendKeys("1111");

		// スクリーンショット
		tempFileList.add(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE));

		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();
		WebElement headerMsg = webDriver.findElement(By.cssSelector("header .user_info"));

		// スクリーンショット
		tempFileList.add(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE));

		// スクリーンショット出力
		int count = 0;
		try {
			for (File file : tempFileList) {
				count++;
				Files.move(file, new File(screenshotPath + new Object() {
				}.getClass().getEnclosingMethod().getName() + "_" + count + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 検証
		assertEquals("社員管理システム", webDriver.getTitle());
		assertTrue(headerMsg.getText().contains("ようこそ"), "セッションにユーザ情報が登録されていない可能性があります");

	}

	@Test
	@Order(2)
	public void 正常系_ログイン操作_画面遷移_管理者権限() {

		// スクリーンショットのリスト
		ArrayList<File> tempFileList = new ArrayList<File>();
		// スクショ保存パス
		String screenshotPath = "screenshots\\01_LoginTest\\";

		// 指定したURLに遷移する
		webDriver.get("http://localhost:" + port + "/spring_crud/");

		WebElement loginIdElement = webDriver.findElement(By.name("empId"));
		loginIdElement.clear();
		loginIdElement.sendKeys("2");

		WebElement password = webDriver.findElement(By.name("empPass"));
		password.clear();
		password.sendKeys("2222");

		// スクリーンショット
		tempFileList.add(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE));

		webDriver.findElement(By.cssSelector("input[type='submit']")).submit();

		// スクリーンショット
		tempFileList.add(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE));

		// スクリーンショット出力
		int count = 0;
		try {
			for (File file : tempFileList) {
				count++;
				Files.move(file, new File(screenshotPath + new Object() {
				}.getClass().getEnclosingMethod().getName() + "_" + count + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement headerMsg = webDriver.findElement(By.cssSelector("header .user_info"));

		// 検証
		assertEquals("社員管理システム", webDriver.getTitle());
		assertTrue(headerMsg.getText().contains("ようこそ"), "セッションにユーザ情報が登録されていない可能性があります");

	}

}
