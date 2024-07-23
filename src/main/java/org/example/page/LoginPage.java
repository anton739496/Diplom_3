package org.example.page;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By registrationButton = By.xpath("//a[@class='Auth_link__1fOlj' and @href='/register']");
    private final By emailField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By loginButton = By.className("button_button__33qZ0");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @SneakyThrows
    @Step
    public void login(String email, String password) {
        Thread.sleep(1000);
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
}
