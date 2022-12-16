import org.testng.Assert;
import org.testng.ITestContext;

import org.testng.annotations.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.io.File;


public class AppTest {

  private WebDriver driver;

  @BeforeTest(alwaysRun = true)
  public void setupBeforeTest(ITestContext context) {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    driver = new ChromeDriver(options);
  }

  @AfterTest(alwaysRun = true)
  public void setupAfterTest(ITestContext context) {
    if (driver != null) {
        // Just as a helper to troubleshoot the test failure, take a screenshot
        takeScreenshot(context.getName());
        driver.quit();
    }
  }

  private void takeScreenshot(String filename) {
    try {
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("build/test-results/test/" + filename + ".png"));
    } catch (Exception e) {
        e.printStackTrace();
    }
  }

  private WebElement getSearchInput() {
    return driver.findElement(By.name("q"));
  }

  @Test
  public void gotoGoogle() {
    driver.get("https://www.google.com");
  }

  @Parameters("expectedUrl")
  @Test
  public void expectUrl(String expectedUrl) {    
    Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
  }
  
  @Parameters("keyword")
  @Test
  public void enterSearch(String keyword) {
    getSearchInput().sendKeys(keyword);
  }

  @Test
  public void tapLucky() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnI")));
    element.click();
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
    getSearchInput().sendKeys(Keys.ENTER);
  }
}
