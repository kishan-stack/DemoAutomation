package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;

public class Task3_1 {
    public static void main(String[] args) throws Exception{

        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.automationtesting.in/FileUpload.html");
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,250)");
        driver.findElement(By.id("input-4")).sendKeys("/home/jaykishan.varma@apmosys.mahape/IdeaProjects/AutomationTasksRevision/samplefile.pdf");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("FileUpload.png");
        FileUtils.copyFile(src,dest);

        Thread.sleep(2000);

        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//a[text()='More']"));
        WebElement download = driver.findElement(By.xpath("//a[text()='File Download']"));

        action.moveToElement(element).perform();
        action.moveToElement(download).perform();
        download.click();

        js.executeScript("window.scroll(0,250)");
        driver.findElement(By.xpath("//a[@type='button']")).click();
        String downloadPath = "/home/jaykishan.varma@apmosys.mahape/IdeaProjects/AutomationTasksRevision";
        String expectedFileName = "samplefile.pdf"; 
        File downloadedFile = new File(downloadPath + "\\" + expectedFileName);
        if (downloadedFile.exists()) {
            long fileSize = downloadedFile.length();

            if (fileSize > 0) {
                System.out.println("Download successful. File size: " + fileSize + " bytes");
            } else {
                System.out.println("File downloaded but it's empty!");
            }
        } else {
            System.out.println("File not found after download attempt.");

    }
}
