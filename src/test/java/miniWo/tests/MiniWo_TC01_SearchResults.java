package miniWo.tests;

import org.testng.annotations.Test;

import junit.framework.Assert;
import miniWo.utils.CommonActions;
import miniWo.utils.Loggers;
import miniWo.utils.WebdriverActions;

import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;

public class MiniWo_TC01_SearchResults {
	WebDriver driver;

	@Test
	public void SearchResults() throws Throwable {
		Loggers.startTestCase("MiniWo_TC01_SearchResults");

		Loggers.info("1. Enter location value in the text field");
		driver.findElement(By.cssSelector("#autocomplete2")).sendKeys("Berlin");

		Loggers.info("2. Click on the Search button");
		driver.findElement(By.cssSelector(".btn.btn-primary.hidden-xs")).click();

		Loggers.info("3. Get the value of the search result");
		String Value1 = driver.findElement(By.cssSelector("#numberres")).getText();
		CommonActions CA = new CommonActions();
		int ResultNUM1 = CA.getsubstring(Value1, "(", "T");

		Loggers.info("4. Assert that result count is greater than zero");
		boolean result1 = CA.CheckResultValue(ResultNUM1);
		Assert.assertEquals(true, result1);

		Loggers.info("5. Apply the age filter");
		Select dropdown = new Select(driver.findElement(By.cssSelector("select.form-control")));
		dropdown.selectByVisibleText("6 - 10 Jahre");

		Loggers.info("6. Get the value of the search result");
		String Value2 = driver.findElement(By.cssSelector("#numberres")).getText();
		int ResultNUM2 = CA.getsubstring(Value2, "(", "T");

		Loggers.info("7. Assert that result count is greater than zero");
		boolean result2 = CA.CheckResultValue(ResultNUM2);
		Assert.assertEquals(true, result2);

		Loggers.info("8. Apply the sort by surname filter");
		driver.findElement(By.cssSelector("#sortbya2")).click();

		Loggers.info("9. Get the value of the search result");
		String Value3 = driver.findElement(By.cssSelector("#numberres")).getText();
		int ResultNUM3 = CA.getsubstring(Value3, "(", "T");

		Loggers.info("10. Assert that result count is greater than zero");
		boolean result3 = CA.CheckResultValue(ResultNUM3);
		Assert.assertEquals(true, result3);

		Loggers.info("11. Apply the distance filter");
		WebdriverActions WA = new WebdriverActions();
		WebElement slider = driver.findElement(By.cssSelector("span.ui-slider-handle"));
		WA.sliderMoveRight(driver, slider);

		Loggers.info("12. Get the value of the search result");
		String Value4 = driver.findElement(By.cssSelector("#numberres")).getText();
		int ResultNUM4 = CA.getsubstring(Value4, "(", "T");

		Loggers.info("13. Assert that result count is greater than zero");
		boolean result4 = CA.CheckResultValue(ResultNUM4);
		Assert.assertEquals(true, result4);

		Loggers.info("14. Get the text of first result");
		String linktextonResults = driver.findElement(By.xpath("(//div[@class='col-sm-6']//a)[1]")).getText();

		Loggers.info("15. Click on the first result");
		WebElement Link = driver.findElement(By.xpath("(//div[@class='col-sm-6']//a)[1]"));
		WA.scrollUp(driver, Link);
		WA.clickElement(driver, Link);

		Loggers.info("16. Switch to new window and get the text of the result opened in new window");
		String parentWindow = WA.switchNewWindow(driver);
		WA.wait3(driver, 3000);
		WebElement Link2 = driver.findElement(By.xpath("//h1[@itemprop='name']"));
		String linktextonDetails = Link2.getText();

		Loggers.info("17. Compare the linktext of result page and details page. ");
		WA.switchParentWindow(driver, parentWindow);
		Assert.assertEquals(linktextonResults, linktextonDetails);

	}

	@BeforeMethod
	public void getUrl() {
		driver = new FirefoxDriver();
		driver.get("https://miniwo.de/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void CloseBrowser() {
		driver.quit();
	}

}
