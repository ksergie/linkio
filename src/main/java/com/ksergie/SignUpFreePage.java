package com.ksergie;

// The test user: user tester_linkio + "yyyyMMddHHmmss"
// test email: tester_linkioyyyyMMddHHmmss@linkio.com
// Please, delete it from your databases


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static jdk.nashorn.internal.objects.NativeString.trim;
import static org.assertj.core.api.Assertions.*;

public class SignUpFreePage {

    private WebDriver driver;

    public SignUpFreePage(WebDriver driver) {
        this.driver = driver;
    }

    static private String url = "https://app.linkio.com/users/sign_up?_ga=2.31143743.1291449009.1529912819-1784825895.1529912819";
    private String userName;
    private By fieldEmail = By.xpath("//input[@name='email']");
    private By fieldName = By.xpath("//input[@name='name']");
    private By fieldPassword = By.xpath("(//input[@type='password'])[1]");
    private By fieldPasswordConfirm = By.xpath("(//input[@type='password'])[2]");
    private By checkbox = By.xpath("//input[@type='checkbox']");
    private By buttonSignUp = By.xpath("//span[text()='Sign Up']");
    private By pageTitle = By.xpath("//div[@class='signup-component__text']");

    private SignUpFreePage typeEmail(String email){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(fieldEmail).sendKeys(email);
        return this;
    }

    private SignUpFreePage typeName(String name){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(fieldName).sendKeys(name);
        return this;
    }

    private SignUpFreePage typePassword(String passwd){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(fieldPassword).sendKeys(passwd);
        return this;
    }

    private SignUpFreePage typePasswordConfirm(String passwdConfirm){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(fieldPasswordConfirm).sendKeys(passwdConfirm);
        return this;
    }

    private SignUpFreePage tickCheckBox(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(checkbox).click();
        return this;
    }

    private SignUpFreePage clickSignUpButton(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(buttonSignUp).click();
        return this;
    }

    private void register(String email, String name, String passwd, String passwdConfirm){
        driver.get(url);
        typeEmail(email);
        typeName(name);
        typePassword(passwd);
        typePasswordConfirm(passwdConfirm);
        tickCheckBox();
        clickSignUpButton();
    }

    private String getUserName(){
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date today = Calendar.getInstance().getTime();
        String dat = df.format(today);
        return userName = "tester_linkio" + dat;
    }

    private String getTitle() {
        return trim((new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText());
    }

    public void registerWithCorrectData(){
        register(getUserName() + "@linkio.com", userName, "20Linkio_18", "20Linkio_18");
        assertThat(getTitle()).isEqualTo("A message with confirmation link has been sent to your email address. Please follow the link to activate your account.");
    }
}
