package demoTestNG;

import org.testng.annotations.*;

public class ConfigurationAnnotations_1_DatePickers {
    @Test
    public void addtocart1(){
        System.out.println("add to cart item1");
    }
    @Test
    public void addtocart2(){
        System.out.println("add to cart item2");
    }
    @BeforeMethod
    //preconditions before a test case
    public void precondition(){
        System.out.println("preconditions");
    }

    @AfterMethod
    //post conditions after a test case
    public void postcondition(){
        System.out.println("post-condition");
    }

    @BeforeClass
    //preconditions before a test case
    public void login(){
        System.out.println("login to site");
    }

    @AfterClass
    //post conditions after a test case
    public void logout(){
        System.out.println("logout of site");
    }

}
