package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;
import java.util.TreeMap;

public class AutomationTask2 {
    public static void main(String[] args) throws Exception{
        WebDriver driver = new ChromeDriver();
        driver.get("https://amazon.in");
        driver.manage().window().maximize();
        WebElement input = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        input.sendKeys("toys");
        input.sendKeys(Keys.ENTER);
        JavascriptExecutor jsx = (JavascriptExecutor)driver;
        jsx.executeScript("window.scroll(0,250)");

        WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(8));
        WebElement item1 = wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-3-announce']")));
        item1.click();
        Thread.sleep(2000);
        WebElement item2 = wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-4-announce']")));
        item2.click();
//        WebElement item3 = wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-5-announce']")));
//        item3.click();
        Thread.sleep(2000);
        WebElement item4 = wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-6-announce']")));
        item4.click();
        Thread.sleep(2000);
        WebElement cart = wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/gp/cart/view.html?ref_=nav_cart']")));
        cart.click();
        TreeMap<Integer,WebElement> mpp = new TreeMap<>();

        for (int posx = 1; posx <4; posx++) {
            try {

                // Get the price using the current posx
                String price = driver.findElement(By.xpath("//div[@data-csa-c-posx='" + posx + "']")).getAttribute("data-price");
                System.out.println("Price for posx=" + posx + ": " + price);

                // Wait for 2 seconds (optional, adjust or remove based on your needs)
                Thread.sleep(2000);
                Integer intPRice = Integer.parseInt(price);

                // Get the item name using the current posx
                WebElement itemName = driver.findElement(By.xpath("//div[@data-csa-c-posx='" + posx + "']//span[@class='a-icon a-icon-small-trash']"));
                System.out.println("Item Name for posx=" + posx + ": " + itemName);
                mpp.put(intPRice,itemName);

            } catch (Exception e) {
                System.out.println("Error retrieving data for posx=" + posx + ": " + e.getMessage());
            }
        }
        Integer mainELem = mpp.firstKey();
//        String ElemText = mainELem.replace("…", "");
//        System.out.println(ElemText);
//            for(mpp.forEach(0);)
        mpp.forEach((key, value) -> {
//            System.out.println("Key: " + key + ", Value: " + value);
            if(key != mainELem)
            {
                try{
                    value.click();
                    Thread.sleep(2000);

                }catch (Exception e)
                {
                    System.out.println(e);
                }

            }
        });
///div[@data-asin='B0D5YCYS1G']//span[@class='a-icon a-icon-small-trash']
//        //div[@data-csa-c-posx='1']//span[@class='a-truncate-cut']
    //        System.out.println(driver.findElement(By.xpath("//div[@class='sc-badge-price-to-pay sc-apex-cart-price-to-pay']//span[@class='a-offscreen']")).getText());
    }
}
