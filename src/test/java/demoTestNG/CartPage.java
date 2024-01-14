package demoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class CartPage {

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
    public void cart() throws InterruptedException, IOException {
        //login
    driver.findElement(By.xpath("//*[@id=\"user-name\"]")).click();
    driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
    driver.findElement(By.xpath("//*[@id=\"password\"]")).click();
    driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
    driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    String URL = driver.getCurrentUrl();
    Assert.assertEquals(URL,"https://www.saucedemo.com/v1/inventory.html");

    //add items to cart

    driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).click();
    String ProductName = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/a/div")).getText();

    //go to cart page
    driver.findElement(By.cssSelector("#shopping_cart_container > a > svg")).click();
    String CartName = driver.findElement(By.xpath("//*[@id=\"contents_wrapper\"]/div[2]")).getText();
    Assert.assertEquals(CartName,"Your Cart");
    String CartProductName = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
    Assert.assertEquals(ProductName,CartProductName);

     driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]")).click();



    File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(screenshotFile, new File(".//screenshot/screen.png"));




    }



}
