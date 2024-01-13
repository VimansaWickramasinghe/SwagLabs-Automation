package demoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventeryPage {
    WebDriver driver;

    @BeforeClass
    public void driversetup(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/v1/");
    }


    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void sort(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL,"https://www.saucedemo.com/v1/inventory.html");
        WebElement d_down = driver.findElement(By.cssSelector("#inventory_filter_container > select"));
        Select select = new Select(d_down);
        select.selectByValue("za");
        try {
            Thread.sleep(2000);
            String sort_text = driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[2]")).getText();
            Assert.assertEquals(sort_text, "Name (Z to A)");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        select.selectByIndex(0);
        try {
            Thread.sleep(2000);
            String sort_text = driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[1]")).getText();
            Assert.assertEquals(sort_text, "Name (A to Z)");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        select.selectByVisibleText("Price (high to low)");
        try {
            Thread.sleep(2000);
            String sort_text = driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[3]")).getText();
            Assert.assertEquals(sort_text, "Price (low to high)");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        select.selectByVisibleText("Price (high to low)");
        try {
            Thread.sleep(2000);
            String sort_text = driver.findElement(By.xpath("//*[@id=\"inventory_filter_container\"]/select/option[4]")).getText();
            Assert.assertEquals(sort_text, "Price (high to low)");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).click();
        String removeBTN = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).getText();

        Assert.assertEquals(removeBTN, "REMOVE");
        String Cart_Number = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
        Assert.assertEquals(Cart_Number, "1");
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).click();
        String addToCartBTN = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).getText();
        Assert.assertEquals(addToCartBTN, "ADD TO CART");



    }


}