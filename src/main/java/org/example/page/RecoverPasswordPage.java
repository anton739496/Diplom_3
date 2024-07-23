package org.example.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RecoverPasswordPage extends BasePage {

    private final By loginButton = By.className("Auth_link__1fOlj");

    public RecoverPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step
    public void scrollToLoginButton() {
        WebElement element = driver.findElement(loginButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
