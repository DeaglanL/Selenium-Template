package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverConfig {


    public WebDriver getChromeDriver(){
        ChromeOptions cO = new ChromeOptions();
        cO.addArguments("--start-maximized");

        return new ChromeDriver(cO);
    }



}
