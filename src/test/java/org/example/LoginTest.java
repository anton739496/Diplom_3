package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class LoginTest extends BaseSeleniumTest {

    private final String browserName;

    public LoginTest(String browserName) {
        this.browserName = browserName;
    }

    @Before
    @Step("Register user")
    public void beforeEach() {
        registerUser();
        setWebDriver(browserName);
    }

    @After
    @Step("Delete user")
    public void afterEach() {
        deleteUser();
    }

    @Test
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    public void testLoginEnterAccountButton(){
        loadPage(BASE_URL);
        mainPage.clickEnterToAccountButton();
        loginPage.login(email, password);
        assertNotNull(mainPage.waitCreateOrderButtonVisible());
    }

    @Test
    @Description("Вход через кнопку «Личный кабинет»")
    public void testLoginPersonalAccountButton(){
        loadPage(BASE_URL);
        mainPage.clickPersonalAccountButton();
        loginPage.login(email, password);
        assertNotNull(mainPage.waitCreateOrderButtonVisible());
    }

    @Test
    @Description("Вход через кнопку в форме регистрации")
    public void testLoginRegistrationForm(){
        loadPage(BASE_URL + "/register");
        registrationPage.scrollToLoginButton();
        registrationPage.clickLoginButton();
        loginPage.login(email, password);
        assertNotNull(mainPage.waitCreateOrderButtonVisible());
    }

    @Test
    @Description("Вход через кнопку в форме восстановления пароля")
    public void testLoginRecoverPasswordPage(){
        loadPage(BASE_URL + "/forgot-password");
        recoverPasswordPage.scrollToLoginButton();
        recoverPasswordPage.clickLoginButton();
        loginPage.login(email, password);
        assertNotNull(mainPage.waitCreateOrderButtonVisible());
    }
}
