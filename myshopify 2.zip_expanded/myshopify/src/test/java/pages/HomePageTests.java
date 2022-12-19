package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTests {

    
	/**
	 * Verify page Title
	 */
	@Test(description = "LEVEL-1")
		public static void shouldVerifyTitle() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://testvagrant.myshopify.com/");
        String t = driver.getTitle();
        System.out.println(t);
      
        Assert.assertEquals(t, "mykart");
        
        System.out.println("Completed Level 1 - shouldVerifyTitle.");
        
        driver.quit();
    }

	
	/**
	 * Verify the Vision of the company.
	 */
     @Test(description = "LEVEL-1")
        public static void shouldAssertTheVisionOfTheCompany() {
    	//System.setProperty("webdriver.chrome.driver","C:\\Users\\yogan\\Pictures\\chromedriver_win32\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://testvagrant.myshopify.com/");

        HomePage homePage = new HomePage(driver);
        String missionText = homePage.getOurMissionText();
        System.out.println(missionText);
        
        Assert.assertEquals(missionText, "Shopping should be fun, convenient and affordable");
        
        System.out.println("Completed Level 1 - shouldAssertTheVisionOfTheCompany.");
        
        driver.quit();
        
    }
        
        public static void main(String[] args) {
        	shouldVerifyTitle();
        	shouldAssertTheVisionOfTheCompany();
        }
}
