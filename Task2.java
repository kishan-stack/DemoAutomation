package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {
    public static void main(String[] args)throws Exception {
         WebDriver driver = new ChromeDriver();
        driver.get("https://vinothqaacademy.com/iframe/");
        driver.manage().window().maximize();
        driver.switchTo().frame("employeetable");
        driver.findElement(By.id("nameInput")).sendKeys("hello");
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.switchTo().frame("popuppage");
        Thread.sleep(2000);
        WebElement alertbox = driver.findElement(By.name("alertbox"));
        Thread.sleep(2000);
        alertbox.click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        driver.switchTo().frame("registeruser");
        Thread.sleep(2000);
        driver.findElement(By.id("vfb-5")).sendKeys("hello");


    }
}
