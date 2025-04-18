package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class Task1V2 {
    public static void main(String[] args) throws Exception{
//        https://www.investing.com/
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.investing.com/");
        driver.manage().window().maximize();

        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0,1050)");


        TreeMap<Double, String> mpp = new TreeMap<>();

        List<WebElement> stockContainer = driver.findElements(By.xpath("//tr[@class='datatable-v2_row__hkEus dynamic-table-v2_row__ILVMx']"));
        for (int i=0;i<10;i++) {

            WebElement changeTd = stockContainer.get(i).findElement(By.xpath(".//td[contains(text(),'%')]"));

            String changeText = changeTd.getText().trim(); // e.g., "+0.74%"

            if (changeText.startsWith("+")) {
                double change = Double.parseDouble(changeText.replace("+", "").replace("%", ""));

                String stockTitle = stockContainer.get(i).findElement(By.xpath(".//a")).getAttribute("title");
                jsx.executeScript("arguments[0].style.border='2px solid lime'; arguments[0].style.background='rgba(144, 238, 144, 0.7)';", stockContainer.get(i));
                System.out.println("Stock: " + stockTitle + " " + changeText);
                mpp.put(change, stockTitle);
            }


        }


        double topChange = mpp.lastKey();
        String topStock = mpp.get(topChange);
        System.out.println("Most positive stock: " + topStock + " with +" + topChange + "%");

        WebElement topStockElement = driver.findElement(By.xpath("//a[@title='" + topStock + "']"));

        jsx.executeScript(
                "arguments[0].style.border='2px solid lime'; arguments[0].style.background='rgba(144,238,144,0.3)';arguments[0].style.fontWeight='bold';",
                topStockElement
        );
        Thread.sleep(3000);
        topStockElement.click();
        Thread.sleep(3000);
        jsx.executeScript("window.scrollBy(0,200)");
        String h1 = driver.findElement(By.xpath("//h1")).getText();
        System.out.println(h1);
        System.out.println(topStock);
        if (h1.startsWith(topStock)) {
            System.out.println("Validated");
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String time = date.format(new Date());

        File destFile = new File("Table"+time+".png");
        FileHandler.copy(srcFile, destFile);


    }
}
