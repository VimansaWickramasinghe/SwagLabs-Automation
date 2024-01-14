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

public class CheckOutPage {
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
        //login steps
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL,"https://www.saucedemo.com/v1/inventory.html");

        //add item to cart
        driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[3]/button")).click();

        //click on cart icon
        driver.findElement(By.cssSelector("#shopping_cart_container > a > svg")).click();

        //click to checkout
        driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]")).click();
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void CheckOutPage() throws InterruptedException, IOException {

        //fill checkout details
        driver.findElement(By.xpath("//*[@id=\"first-name\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys("vimansa");
        driver.findElement(By.xpath("//*[@id=\"last-name\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys("vidu");
        driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys("81000");
        driver.findElement(By.xpath("//*[@class=\"btn_primary cart_button\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]")).click();
        Thread.sleep(2000);
        String orderCompleteText = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).getText();
        Assert.assertEquals(orderCompleteText,"THANK YOU FOR YOUR ORDER");

        //File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(screenshotFile, new File(".//screenshot/screen.png"));
    }

    @Test
    public void CheckOutPageEmptyDetails() throws InterruptedException, IOException {

        //fill checkout details
        String CheckoutPageText = driver.findElement(By.xpath("//*[@id=\"contents_wrapper\"]/div[2]")).getText();
        Assert.assertEquals(CheckoutPageText, "Checkout: Your Information");
        driver.findElement(By.xpath("//*[@class=\"btn_primary cart_button\"]")).click();

         String ErrorMessage = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/h3")).getText();
        Assert.assertEquals(ErrorMessage,"Error: First Name is required");

        //Take screenshots
        //File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(screenshotFile, new File(".//screenshot/screen.png"));
    }
}
