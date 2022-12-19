package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.List;
import org.openqa.selenium.Keys;

public class HomePage {

    WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[@id=\"shopify-section-footer\"]/footer/div[1]/div[1]/div[3]/div/p")
    private WebElement ourMissionText;

    @FindBy(how = How.CSS, using = ".icon-search")
    public WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//a[@id='cart-icon-bubble']")
    WebElement cartButton;

    @FindBy(how = How.ID, using = "Search-In-Modal")
    public WebElement searchTextBox;
    
    @FindBy(how = How.XPATH, using = "//button[@class='search__button field__button']")
    public WebElement findButton;



    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public String getOurMissionText() {
        return ourMissionText.getText().trim();
    }

    public void search(String text) {
        searchButton.click();
        searchTextBox.sendKeys(text);
       	try {
    			Thread.sleep(3000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
    
    public void returnSearch () {
    	findButton.click();
    }
    
    public void viewCart() {
    	cartButton.click();
    }

    public void addProductsToCart(int productsCount)  {
        List<WebElement> products = driver.findElements(By.cssSelector(".product-grid .grid__item"));
        int count = products.size(); 
        System.out.println("Total products avaiable is :" + count);
       
        for (int i = 0; i < productsCount; i++) {

        	products.get(i).click();
            new ProductPage(driver).addToCart();
            try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            driver.navigate().back(); 

        }
        
    }
     public void searchAssert (String searchText) {
         //Get the values from the Search suggestion/result, save them in list.
         List<WebElement> p = driver.findElements(By.xpath("//ul[@id='predictive-search-results-list']/li/a/div/h3"));
         int resultCount = p.size(); 
         System.out.println(resultCount);
        
         //iterate through list
         for( WebElement i: p){
         	String resultText = i.getText();
               System.out.println(resultText);
               Assert.assertTrue(resultText.contains(searchText),"Failure message");  //Returns FALSE - if the case is not matched.
         }
     }
     
    }

