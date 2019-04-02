package selenium;

import java.nio.charset.Charset;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
 
import org.junit.*;
 
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
 
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
 
import com.csvreader.CsvReader;
 
public class test {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String id = null;
  private String pwd = null;
  private String gitUrl = null;
 
  @Before
  public void setUp() throws Exception {
	String driverPath = System.getProperty("user.dir") + "/src/geckodriver.exe";
	System.setProperty("webdriver.gecko.driver", driverPath);
    driver = new FirefoxDriver();
    baseUrl = "http://121.193.130.195:8800";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
 
  @Test
  public void testGit() throws Exception {
      CsvReader cin =  new CsvReader("C:/Users/’≈”¿º—/Desktop/»Ìº˛≤‚ ‘/input.csv", ',',Charset.forName("GBK"));
      cin.readHeaders();
      while(cin.readRecord()){
        id = cin.get(1);
        if (id.length() < 10) {
        	continue;
        }
        pwd = id.substring(4, 10);
        gitUrl = cin.get(3);
        driver.get(baseUrl + "/");
        driver.findElement(By.name("id")).clear();
        driver.findElement(By.name("id")).sendKeys(id);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(pwd);
        driver.findElement(By.id("btn_login")).click();
        String gitUrls = null;
        gitUrls = driver.findElement(By.id("student-git")).getText();
        
        if (!gitUrl.equals(gitUrls)){
            System.out.println(id);
            System.out.println(gitUrl);
            System.out.println(gitUrls);
            continue;
             
        }
        driver.findElement(By.id("btn_logout")).click();
        assertEquals(gitUrl,gitUrls);
    }
  }
 
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
 
  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
 
  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
 
  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
