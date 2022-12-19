package pages;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class SearchTests{
	
	
	/**
	 * Test to verify the search results using assert by search text.
	 */
	@Test(description = "LEVEL-1")
	public static void shouldVerifySearchResults() {
		
		// TODO Auto-generated method stub
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        driver.get("https://testvagrant.myshopify.com/");

        HomePage homePage = new HomePage(driver);
        String searchText = "ADIDAS";
        homePage.search(searchText);
        
        homePage.searchAssert(searchText);
        
        System.out.println("Completed Level 1 - shouldVerifySearchResults.");
   
        //Close browser..
        driver.quit();

	}
	
	 public static void main(String[] args) {
		 shouldVerifySearchResults();
	    }


}
