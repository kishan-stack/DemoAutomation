package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Set;

public class AutomationTask2 {
    WebDriver driver;
    public AutomationTask2(WebDriver d){
        this.driver = d;
    }


    public void SignIn(){
        driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("stevewhitman11@gmail.com");
        driver.findElement(By.xpath("//input[@id='continue']")).click();
//        ap_password
        driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("Amazonpass");
//        signInSubmit
        driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();


    }
    public void SearchFunctionality(String item){
        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(item);
        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scroll(0,350)");
    }

    public void SelectProductAndAddToCart(){
        driver.findElement(By.xpath("//span[text()='Video Camera Camcorder kimire Digital Camera Recorder Full HD 1080P 15FPS 24MP 3.0 Inch 270 Degree Rotation LCD 16X Digital Zoom Camcorder Camera with 2 Batteries(Black)']")).click();
        Set<String> allWindows = driver.getWindowHandles();
        System.out.println(allWindows);
        ArrayList<String>windows = new ArrayList<>();
        for (String window:allWindows){
            windows.add(window);
        }
        driver.switchTo().window(windows.get(1));
        String current = driver.getWindowHandle();
        driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
        driver.findElement(By.xpath("//a[@href='/cart?ref_=sw_gtc']")).click();

        //
//        Go to Cart
    }


    public static void main(String[] args) {
        WebDriver d = new ChromeDriver();
        AutomationTask2 at2 = new AutomationTask2(d);
        at2.driver.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fcart%2Fview.html%3Fref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
        at2.driver.manage().window().maximize();
        at2.SignIn();
        at2.SearchFunctionality("camcorder");
        at2.SelectProductAndAddToCart();

    }

}
