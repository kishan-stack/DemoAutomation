package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.TreeMap;

public class AutomationTask2 {
    public static void main(String[] args) throws Exception{
        WebDriver driver = new ChromeDriver();

        TakesScreenshot ts = (TakesScreenshot) driver;
        driver.get("https://amazon.in");
        driver.manage().window().maximize();
        WebElement input = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        input.sendKeys("bottles");
        input.sendKeys(Keys.ENTER);
        JavascriptExecutor jsx = (JavascriptExecutor)driver;
        jsx.executeScript("window.scroll(0,250)");

        WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(8));
        Wait<WebDriver> FluWt = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))        // Maximum wait time
                .pollingEvery(Duration.ofMillis(500))       // Polling interval
                .ignoring(NoSuchElementException.class);

        WebElement item = FluWt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-3-announce']")));
        item.click();
//        Thread.sleep(2000);

        WebElement item2 = FluWt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-5-announce']")));
        item2.click();
//        Thread.sleep(2000);

        WebElement item3 = FluWt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-7-announce']")));
        item3.click();
//        Thread.sleep(2000);

        WebElement item4 = FluWt.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='a-autoid-9-announce']")));
        item4.click();
        Thread.sleep(2000);


        WebElement cart = wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='nav-cart']")));
        cart.click();

//        Thread.sleep(2000);
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File("cartBeforeDeletion.png");
        FileHandler.copy(srcFile, destFile);


        TreeMap<Integer,String> mpp = new TreeMap<>();

        for (int posx = 1; posx <5; posx++) {
            try {


                String price = driver.findElement(By.xpath("//div[@data-csa-c-posx='" + posx + "']")).getAttribute("data-price");
                System.out.println("Price for item=" + posx + ": " + price);


//                Thread.sleep(2000);
                Integer intPRice = Integer.parseInt(price);


//                String ItemButton = driver.findElement(By.xpath("//div[@data-csa-c-posx='" + posx + "']//button[@data-a-selector='decrement']")).getText();
//                System.out.println("Button for posx =" + posx + ": " + ItemButton);
                mpp.put(intPRice,"//div[@data-csa-c-posx='" + posx + "']//button[@data-a-selector='decrement']");

            } catch (Exception e) {
                System.out.println("Error retrieving data for item=" + posx + ": " + e.getMessage());
            }
        }
        Integer lowestPrice = mpp.firstKey();
        String mainELem = mpp.firstEntry().getValue();
        String ElemText = mainELem.replace("…", "");
//        System.out.println(ElemText);

        for (String xpath : mpp.values()){
            if (!xpath.equals(ElemText)){
                try {
                    WebElement button = driver.findElement(By.xpath(xpath));
                    button.click();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File("cartAfterDeletion.png");

        FileHandler.copy(source, destination);

        driver.navigate().refresh();
        try {
            WebElement stockElement = driver.findElement(By.xpath("//*[contains(text(), 'In stock')]"));
            String stockText = stockElement.getText();


            System.out.println("last item in cart is "+stockText+"having price "+lowestPrice);
//            System.out.println("The item is in stock!");
        } catch (Exception e) {

            System.out.println(e.getMessage());

        }


        System.out.println("Screen Shot saved as cartBeforeDeletion.png");

        System.out.println("Screen Shot saved as cartAfterDeletion.png");

//        driver.navigate().refresh();


//        //div[@data-csa-c-posx='1']//span[@class='a-truncate-cut']
        //        System.out.println(driver.findElement(By.xpath("//div[@class='sc-badge-price-to-pay sc-apex-cart-price-to-pay']//span[@class='a-offscreen']")).getText());
    }
}
