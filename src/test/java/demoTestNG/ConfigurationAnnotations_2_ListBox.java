package demoTestNG;

import org.testng.annotations.*;

@Test
public class ConfigurationAnnotations_2_ListBox {

    public void checkout(){
        System.out.println("checkout");
    }

    public void payment(){
        System.out.println("payment");
    }
    @BeforeMethod
    //preconditions before a test case
    public void precondition(){
        System.out.println("preconditions2");
    }

    @AfterMethod
    //post conditions after a test case
    public void postcondition(){
        System.out.println("post-condition2");
    }
    @BeforeClass
    //preconditions before a test case
    public void login(){
        System.out.println("login to site2");
    }

    @AfterClass
    //post conditions after a test case
    public void logout(){
        System.out.println("logout of site2");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("before test");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("after test");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("before suite");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("after suite");
    }

}
