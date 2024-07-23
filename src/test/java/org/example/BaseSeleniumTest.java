package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.page.*;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public abstract class BaseSeleniumTest {
    protected static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    protected static final String CREATE_NEW_USER_JSON = "src/test/resources/new_user.json";
    protected static final String LOGIN_VALID_USER_JSON = "src/test/resources/existing_user.json";
    protected static final String name = "chich_chong1";
    protected static final String email = "smoke1@high.ru";
    protected static final String password = "password1";

    private WebDriver driver;

    protected MainPage mainPage;
    protected RegistrationPage registrationPage;
    protected LoginPage loginPage;
    protected RecoverPasswordPage recoverPasswordPage;
    protected PersonalAccountPage personalAccountPage;

    protected String accessToken;

    @BeforeClass
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
        RestAssured.baseURI = BASE_URL;
    }

    @Parameterized.Parameters
    public static Object[][] browsers() {
        return new Object[][]{
                {"chrome"},
                {"yandex"}
        };
    }

    public void setWebDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                initialize();
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver","src/test/resources/yandexdriver.exe");
                initialize();
                break;
            default:
                throw new InvalidArgumentException(browserName);
        }
    }

    @Step("Init browser")
    public void initialize() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        recoverPasswordPage = new RecoverPasswordPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
    }

    @After
    @Step("Quit browser")
    public void tearDown() {
        driver.quit();
    }

    protected void loadPage(String url) {
        driver.get(url);
    }


    protected Response registerUser() {
        File json = new File(CREATE_NEW_USER_JSON);

        return given().contentType("application/json")
                .body(json)
                .when()
                .post("/api/auth/register")
                .then()
                .log()
                .body()
                .extract()
                .response();
    }

    protected Response login() {
        File json = new File(LOGIN_VALID_USER_JSON);

        return given().contentType("application/json")
                .body(json)
                .when()
                .post("/api/auth/login");
    }

    protected boolean deleteUser() {
        accessToken = getToken();
        Response response = given().auth().oauth2(accessToken)
                .when()
                .delete("/api/auth/user");

        return response.statusCode() == 202;
    }

    protected String getToken() {
        var response = login();
        if (response.statusCode() == 200) {
            return extractToken(response);
        }

        return "";
    }

    protected String extractToken(Response response) {
        String temp = response.path("accessToken");
        return temp.substring(7);
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
