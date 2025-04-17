package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {
    public static void main(String[] args)throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.get("https://easemytrip.com/");
        driver.manage().window().maximize();
    }
}
