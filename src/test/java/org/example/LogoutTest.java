package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LogoutTest extends BaseSeleniumTest {

    private final String browserName;

    public LogoutTest(String browserName) {
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
    @Description("Проверка выхода по кнопке «Выйти» в личном кабинете.")
    public void testClickLogoutButton() throws Exception {
        loadPage(BASE_URL + "/login");
        loginPage.login(email, password);
        mainPage.clickPersonalAccountButton();
        personalAccountPage.clickLogoutButton();
        Thread.sleep(1000);
        assertEquals(BASE_URL + "/login", getCurrentUrl());
    }
}
