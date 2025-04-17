package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Task3_1 {
    public static void main(String[] args) throws Exception{
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.automationtesting.in/FileUpload.html");
        driver.manage().window().maximize();
        driver.findElement(By.id("input-4")).sendKeys("/home/jaykishan.varma@apmosys.mahape/IdeaProjects/AutomationTasksRevision/samplefile.pdf");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("FileUpload.png");
        FileUtils.copyFile(src,dest);
    }
}
