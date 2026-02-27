package jp.co.sss.crud.test00;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
@DisplayName("00_index表示")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexTest {

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
	public void 正常系_ログイン画面表示_タイトル() {

		// スクリーンショットのリスト
		ArrayList<File> tempFileList = new ArrayList<File>();
		// スクショ保存パス
		String screenshotPath = "screenshots\\00_IndexTest\\";

		// 指定したURLに遷移する
		webDriver.get("http://localhost:" + port + "/spring_crud/");

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

		WebElement title = webDriver.findElement(By.cssSelector("header .content .title"));

		// 検証
		assertEquals("社員管理システム", webDriver.getTitle());
		assertEquals("社員管理システム", title.getText());

	}

}
