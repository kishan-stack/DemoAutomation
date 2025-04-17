package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;;import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Task1 {
    public static void main(String[] args) throws Exception{

        WebDriver driver = new ChromeDriver();
        driver.get("https://easemytrip.com/");
        driver.manage().window().maximize();
//        Thread.sleep(2000);
//
        driver.findElement(By.xpath("//button[@class='srchBtnSe']")).click();
        TreeMap<Integer,Object> mpp = new TreeMap<>();
        List<WebElement> FlightContainer = driver.findElements(By.xpath("//div[contains(@class,'fltResult')]"));
        for(WebElement flight : FlightContainer){

            String price = flight.getDomAttribute("price");
            int numPrice = Integer.parseInt(price);
            String deptm = flight.getDomAttribute("deptm");
            String og = flight.getDomAttribute("og");
            String ds = flight.getDomAttribute("ds");
            String arrtm = flight.getDomAttribute("arrtm");
            String itemDetails =price+" "+deptm+" "+arrtm+" "+og+" "+ds+" ";
            mpp.put(
                    numPrice,itemDetails
            );

//book button = //div[contains(@class,"fltResult")]//button[@class='btn book-bt-n ng-scope']
//            book through price = //div[@price='7206']//button[@class='btn book-bt-n ng-scope']
        }
        Integer filter = 18000;
        System.out.println("Flights tickets less then "+ filter+" are");
        for (Map.Entry<Integer,Object> entry : mpp.entrySet()){
            Integer key = entry.getKey();
            Object value = entry.getValue();
            if (key<filter){

                System.out.println(key+" "+value);
            }
        }
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@stop='0']//button[@class='btn book-bt-n ng-scope']")).click();
      }
}
