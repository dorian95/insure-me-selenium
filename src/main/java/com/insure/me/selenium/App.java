package com.insure.me.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Hello world!
 *
 */
public class App {
        public static void main(String[] args) throws InterruptedException, IOException {
                System.setProperty("webdriver.chrome.driver",
                                "/usr/local/bin/chromedriver");
                System.out.println("Chrome driver path set");

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--remote-allow-origins=*");

                WebDriver webDriver = new ChromeDriver(chromeOptions);

                String url = "http://54.201.126.224:8080/contact.html";
                System.out.println("URL set :" + url);
                webDriver.get(url);
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                webDriver.findElement(By.id("inputName"))
                                .sendKeys("Nursultan");
                Thread.sleep(1000);

                webDriver.findElement(By.id("inputNumber"))
                                .sendKeys(("8135027563"));
                Thread.sleep(1000);

                webDriver.findElement(By.id("inputMail"))
                                .sendKeys(("nursultan_irgaliyev@gmail.com"));
                Thread.sleep(1000);

                webDriver.findElement(By.id("inputMessage"))
                                .sendKeys(("Hello from Selenium test"));
                Thread.sleep(1000);

                webDriver.findElement(By.id("my-button")).click();

                Thread.sleep(10000);

                // assert that the message sent is displayed
                assertEquals(webDriver.findElement(By.id("response")).getText(), "Message Sent");
                System.out.println("Assertion Passed");

                File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
                File destFile = new File("screenshot.png");
                FileUtils.copyFile(file, destFile);
                System.out.println("Taking screenshot");
                System.out.println("Screenshot stored at " + destFile.getAbsolutePath());
                webDriver.quit();
        }
}
