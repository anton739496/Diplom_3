package org.example.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage extends BasePage {

    private final By constructorButton = By.xpath("//a[@href='/' and @class='AppHeader_header__link__3D_hX']");
    private final By stellarBurgersLogo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/' ]");
    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Логин']/following-sibling::input");
    private final By logoutButton = By.className("Account_button__14Yp3");

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickStellarBurgersLogo() {
        driver.findElement(stellarBurgersLogo).click();
    }

    @Step
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public String getNameFieldValue() {
        return driver.findElement(nameField).getAttribute("value");
    }

    public String getEmailFieldValue() {
        return driver.findElement(emailField).getAttribute("value");
    }
}
