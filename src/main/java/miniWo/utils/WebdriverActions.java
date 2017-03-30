package miniWo.utils;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebdriverActions {
		
	public void sliderMoveRight(WebDriver driver,WebElement slider){
    
    int width=slider.getSize().getWidth();
    Actions move = new Actions(driver);
    move.clickAndHold(slider);
    move.moveByOffset(50, 0).build().perform();
    move.release(slider).perform();
	}
	
	public String switchNewWindow(WebDriver driver){		
	String winHandleBefore = driver.getWindowHandle();
	for(String winHandle : driver.getWindowHandles()){
	    driver.switchTo().window(winHandle);
	}	
	return winHandleBefore;
	}
	
	public void switchParentWindow(WebDriver driver,String parentWindow){
	driver.switchTo().window(parentWindow);
	}
	
	public static void waitForVisibility(WebDriver driver, WebElement element) throws Error{
    	WebDriverWait wait = new WebDriverWait(driver, 20);
    	wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public static void fluentwait(WebDriver driver,WebElement element) throws Error{
    new WebDriverWait(driver, 60).pollingEvery(1,TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(element));
 	}

	public void clickElement(WebDriver driver,WebElement element) throws Error{
	Actions actions = new Actions(driver);
	actions.moveToElement(element).click().perform();
	}
	
	public void viewElement(WebDriver driver,WebElement element) throws Error{
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	jse.executeScript("arguments[0].scrollIntoView()", element);
	}
	
	public void scrollUp(WebDriver driver,WebElement element) throws Error{
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	jse.executeScript("scroll(250, 0)"); // if the element is on top.	
	}
	
	public void scrollDown(WebDriver driver,WebElement element) throws Error{	
	JavascriptExecutor jsedown = (JavascriptExecutor)driver;
	jsedown.executeScript("scroll(0, 250)"); // if the element is on bottom.
	}
	
	public static void wait3(WebDriver driver, int time) throws InterruptedException{
		Thread.sleep(time);
		}
	
	public static void selectOptionWithIndexFromAutoComplete(int indexToSelect,WebElement autoOptions,WebDriver driver,List<WebElement> autolist) {
			
			try {
				WebdriverActions.waitForVisibility(driver, autoOptions);
			        if(indexToSelect<=autolist.size()) {
			        	System.out.println("Trying to select based on index: "+indexToSelect);
			        	autolist.get(indexToSelect).click();
			        }
			} 		
			catch (NoSuchElementException e) {
				System.out.println(e.getStackTrace());
			}
			catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}

	public void selectOptionWithTextFromAutoComplete(String textToSelect,WebElement autoOptions,WebDriver driver,List<WebElement> autolist) {
		
		try {
			WebdriverActions.waitForVisibility(driver, autoOptions);
			for(WebElement option : autolist){
		        if(option.getText().equals(textToSelect)) {
		        	System.out.println("Trying to select: "+textToSelect);
		            option.click();
		            break;
		        }
			}
		} 		
		catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		}
		catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
}