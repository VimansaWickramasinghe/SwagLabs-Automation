package demoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class First_Automated_Test {
    WebDriver driver;

    //use before class bcz this should execute before first test method
    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/v1/");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void Login_both_correct(){
    //Valid username and password
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL,"https://www.saucedemo.com/v1/inventory.html");
    }
    @Test
    public void Login_invalid_username(){
        //Invalid username and password
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("Vima_user");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL,"https://www.saucedemo.com/v1/");
    }

    @Test
    public void Login_invalid_password(){
        //Invalid username and password
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("secret_sauce123");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL,"https://www.saucedemo.com/v1/");
    }

    @Test
    public void Login_both_invalid(){
        //Invalid username and password
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("standard_uservima");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("secret_sauce123");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL,"https://www.saucedemo.com/v1/");
    }

    @Test
    public void Login_both_empty(){
        //Invalid username and password
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL,"https://www.saucedemo.com/v1/");
    }


}
