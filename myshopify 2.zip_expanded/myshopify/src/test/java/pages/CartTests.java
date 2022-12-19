package pages;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


public class CartTests {
	
	/**
	 * Test to clear the cart
	 */
    @Test(description = "LEVEL-2")
    public static void shouldClearCart() {
    	WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        //Navigate to URL.
        driver.get("https://testvagrant.myshopify.com/");
        
        //Search for a product.
        HomePage homePage = new HomePage(driver);
        String searchText = "ADIDAS";
        homePage.search(searchText);
        homePage.returnSearch();
        
        //Add product to the cart.
        homePage.addProductsToCart(1);
        homePage.viewCart();
        
        //Clear the cart
        CartPage cartPage = new CartPage(driver);
        cartPage.clearCart();
        
        //Verify that cart is clear, get the cart message and assert. 
        String msg = cartPage.cartMessage();
        Assert.assertEquals(msg, "Your cart is empty");
        
        System.out.println("Completed Level 2 - shouldClearCart.");
        driver.quit();
    }

    /**
	 * Test to add items to the cart.
	 */
    @Test(description = "LEVEL-2")
    public static void shouldAddItemCart() {
    	WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        
        driver.get("https://testvagrant.myshopify.com/");
        
        //Search for a product.
        HomePage homePage = new HomePage(driver);
        String searchText = "ADIDAS";
        homePage.search(searchText);
        
        homePage.returnSearch();
        
        //Add product to the cart.
        homePage.addProductsToCart(3);
       
        //Go to view cart.
        homePage.viewCart();
        
        //Get cart count from the view cart page.
        int cartCount = new CartPage(driver).getCartCount();
        Assert.assertEquals(cartCount, 3);
        System.out.println("Actual vs Expected cart count is met!");
        
        System.out.println("Completed Level 2 - shouldAddItemCart.");
        //Close browser.
        driver.quit();

    }

    /**
	 * Test to price increase on increasing the quantity.
	 */
    @Test(description = "LEVEL-3")
    public static void shouldIncreaseTotalPriceOnIncreasingQuantity() {
    	  WebDriverManager.chromedriver().setup();
          WebDriver driver = new ChromeDriver();

          //Navigate to URL.
          driver.get("https://testvagrant.myshopify.com/");
          
          //Search for a product.
          HomePage homePage = new HomePage(driver);
          String searchText = "ADIDAS";
          homePage.search(searchText);
          homePage.returnSearch();
          
          //Add product to the cart.
          homePage.addProductsToCart(1);
          homePage.viewCart();
          
          CartPage cartPage = new CartPage(driver);
          cartPage.addQuantity(2);

          System.out.println("Completed Level 3 - shouldIncreaseTotalPriceOnIncreasingQuantity.");

          driver.quit();
    }
    
    public static void main(String[] args) {
    	shouldClearCart();
    	shouldAddItemCart();
    	shouldIncreaseTotalPriceOnIncreasingQuantity();
    }


}
