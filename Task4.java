package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task4 {
    public static void main(String[] args)throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.automationtesting.in/FileDownload.html");
        driver.manage().window().maximize();

    }
}
