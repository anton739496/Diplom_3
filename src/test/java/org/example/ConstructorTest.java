package org.example;

import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ConstructorTest extends BaseSeleniumTest {

    private static final String TAB_CLASS_VALUE = "current";

    private final String browserName;

    public ConstructorTest(String browserName) {
        this.browserName = browserName;
    }

    @Before
    public void setUp() {
        setWebDriver(browserName);
    }

    @Test
    @Description("Клик по разделу «Булки» работает")
    public void testClickBunTab() {
        loadPage(BASE_URL);
        mainPage.clickIngredientsTab();
        mainPage.clickBunTab();
        assertTrue(mainPage.getBunTabClassValue().contains(TAB_CLASS_VALUE));
    }

    @Test
    @Description("Клик по разделу «Соусы» работает")
    public void testClickSauceTab() {
        loadPage(BASE_URL);
        mainPage.clickSaucesTab();
        assertTrue(mainPage.getSauceTabClassValue().contains(TAB_CLASS_VALUE));
    }

    @Test
    @Description("Клик по разделу «Начинки» работает")
    public void testClickIngredientsTab() {
        loadPage(BASE_URL);
        mainPage.clickIngredientsTab();
        assertTrue(mainPage.getIngredientsTabClassValue().contains(TAB_CLASS_VALUE));
    }
}
