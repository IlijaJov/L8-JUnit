package l8;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

	public abstract class BasicTest {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions actions;
	protected String baseUrl = "https://www.l8test.com/";
	protected WebElement inputField = driver.findElement(By.xpath("//*[@id=\"form1\"]/input"));
	
	protected static String getRandomText(int n) {
		String TextString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(10);

		for (int i = 0; i < 10; i++) {
			int index = (int) (TextString.length() * Math.random());
			sb.append(TextString.charAt(index));
		}
		return sb.toString();
	}
	
	protected static String chicagoTimeMs() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		df.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
		long millis = date.getTime();
		
		String time = Long.toString(millis);
		return time;
	}
	
	@Rule 
	public TestName testName = new TestName();
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30, 100);	
	}
	
	@After
	public void cleanup() throws IOException, InterruptedException {
		 Result result = JUnitCore.runClasses(BasicTest.class);
		
		 if (result.getFailureCount() > 0) {
				File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scr, new File("./screenshots/" + 
									testName.getMethodName() + ".jpg"));		
		} 
		Thread.sleep(1000);
		driver.quit();
	}
	
}
