package ct;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Test1 {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	  System.setProperty("webdriver.chrome.driver", "exe/chromedriver.exe");
	    driver = new ChromeDriver();
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void eee() {
    // Test name: 部門登録
    // Step # | name | target | value
    // 1 | open | /businessBase/index.html | 
    driver.get("http://localhost:8080/businessBase/index.html");
    // 2 | setWindowSize | 1050x660 | 
    driver.manage().window().setSize(new Dimension(1050, 660));
    // 3 | click | id=login_sub | 
    driver.findElement(By.id("login_sub")).click();
    // 4 | click | css=#left_tab2 > img | 
    driver.findElement(By.cssSelector("#left_tab2 > img")).click();
    // 5 | click | id=dleft_tab1_8_span | 
    driver.findElement(By.id("dleft_tab1_8_span")).click();
    // 6 | click | id=dleft_tab1_9_span | 
    driver.findElement(By.id("dleft_tab1_9_span")).click();
    // 7 | selectFrame | index=0 | 
    driver.switchTo().frame(0);
    // 8 | click | id=departmentId | 
    driver.findElement(By.id("departmentId")).click();
    // 9 | type | id=departmentId | 7
    driver.findElement(By.id("departmentId")).sendKeys("7");
    // 10 | click | id=departmentName | 
    driver.findElement(By.id("departmentName")).click();
    // 11 | type | id=departmentName | 人事部
    driver.findElement(By.id("departmentName")).sendKeys("人事部");
    // 12 | click | id=establishmentDay | 
    driver.findElement(By.id("establishmentDay")).click();
    // 13 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 14 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 15 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 16 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 17 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 18 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 19 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 20 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 21 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 22 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 23 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 24 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 25 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 26 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 27 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 28 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 29 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 30 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 31 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 32 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 33 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 34 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 35 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 36 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 37 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 38 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 39 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 40 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 41 | click | css=.ui-icon-circle-triangle-w | 
    driver.findElement(By.cssSelector(".ui-icon-circle-triangle-w")).click();
    // 42 | click | linkText=3 | 
    driver.findElement(By.linkText("3")).click();
    // 43 | click | id=establishmentDay | 
    driver.findElement(By.id("establishmentDay")).click();
    // 44 | click | linkText=13 | 
    driver.findElement(By.linkText("13")).click();
    // 45 | click | name=searchBtn | 
    driver.findElement(By.name("searchBtn")).click();
    // 46 | click | css=.text-center span | 
    driver.findElement(By.cssSelector(".text-center span")).click();
    // 47 | click | name=nextBtn | 
    driver.findElement(By.name("nextBtn")).click();
    // 48 | click | name=updatBtn | 
    driver.findElement(By.name("updatBtn")).click();
    // 49 | selectFrame | relative=parent | 
    driver.switchTo().defaultContent();
    // 51 | click | css=#top_close img | 
    driver.findElement(By.cssSelector("#top_close img")).click();
    // 52 | assertConfirmation | ログアウトしますか？ | 
    assertThat(driver.switchTo().alert().getText(), is("ログアウトしますか？"));
    // 53 | webdriverChooseOkOnVisibleConfirmation |  | 
    driver.switchTo().alert().accept();
  }
}
