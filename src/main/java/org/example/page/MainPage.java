package org.example.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    private final By personalAccountButton = By.xpath("//a[@class='AppHeader_header__link__3D_hX' and @href='/account']");
    private final By enterToAccountButton = By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button");
    private final By createOrderButton = By.className("button_button__33qZ0");
    private final By bunTab = By.xpath("//span[text()='Булки']/..");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/..");
    private final By ingredientsTab = By.xpath("//span[text()='Начинки']/..");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step
    public void clickEnterToAccountButton() {
        driver.findElement(enterToAccountButton).click();
    }

    public WebElement waitCreateOrderButtonVisible() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
    }

    @Step
    public void clickBunTab() {
        driver.findElement(bunTab).click();
    }

    @Step
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step
    public void clickIngredientsTab() {
        driver.findElement(ingredientsTab).click();
    }

    public String getSauceTabClassValue() {
        return driver.findElement(saucesTab).getAttribute("class");
    }

    public String getIngredientsTabClassValue() {
        return driver.findElement(ingredientsTab).getAttribute("class");
    }

    public String getBunTabClassValue() {
        return driver.findElement(bunTab).getAttribute("class");
    }

}
