package rb.test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HashmapConsumerTest {
	List<HashMap<String,String>> listOfData = null;
	HashMap<String, String> hashmap = null;
	WebDriver driver = null;

	@BeforeSuite
	public void beforeSuite() {
		
		
	}

	@AfterTest
	public void afterSuite() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
	@BeforeTest
	public void launchURL() {
		// initialize driver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://practicetestautomation.com/practice-test-login/");
	}

	@Test(dataProvider = "csvDataProvider", dataProviderClass = HashmapDataProvider.class)
	public void testHashMapDataProvider(HashMap<String,String> hashmap) throws InterruptedException {
		System.out.println(hashmap);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(hashmap.get("username"));
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(hashmap.get("password"));
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		Thread.sleep(2000);
		driver.get("https://practicetestautomation.com/practice-test-login/");
	}
}
