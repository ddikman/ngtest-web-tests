import org.testng.Assert;
import org.testng.ITestContext;

import org.testng.annotations.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.io.File;


public class AppTest {

  private WebDriver driver;

  @BeforeSuite(alwaysRun = true)
  public void setupBeforeSuite(ITestContext context) {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    driver = new ChromeDriver(options);
  }

  private void takeScreenshot(String filename) {
    try {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("build/test-results/test/" + filename + ".png"));
    } catch (Exception e) {
        e.printStackTrace();
    }
  }

  @AfterSuite(alwaysRun = true)
  public void setupAfterSuite(ITestContext context) {
    if (driver != null) {
        // Just as a helper to troubleshoot the test failure, take a screenshot
        takeScreenshot(context.getName());
        driver.quit();
    }
  }

  @Test
  public void gotoGoogle() {
    driver.get("https://www.google.com");
  }

  @Parameters("url")
  @Test
  public void navigateTo(String url) {
    driver.navigate().to(url);
  }
  
  @Parameters("keyword")
  @Test
  public void enterSearch(String keyword) {
    WebElement element = driver.findElement(By.name("q"));
    element.sendKeys(keyword);
    element.sendKeys("\n");
  }

  @Test
  public void tapSearch() {
    driver.findElement(By.name("btnI")).click();
  }

  @Parameters("expectedTitle")
  @Test
  public void theFirstMatchIs(String expectedTitle) {
    WebElement element = driver.findElement(By.cssSelector("#search div h3"));
    Assert.assertEquals(element.getText(), expectedTitle);
  }

  @Parameters("keyword")
  @Test
  public void searchFor(String keyword) {
    enterSearch(keyword);
  }
}
