package org.kd;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.kd.decathlon.Article;
import org.kd.decathlon.PO_Main;
import org.kd.decathlon.PO_ProductPage;
import org.kd.decathlon.PO_SearchResults;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DecathlonTest {
    public static void main(String[] args) {
        DecathlonTest testSuite = new DecathlonTest();

        testSuite.testProductSearch();
        testSuite.testProductDetails();
        testSuite.testCheckProductAvailability();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("{empty}")
    @Description("")
    @Test
    private void testProductSearch() {
        PO_Main mainPage = new PO_Main();
        mainPage.searchForProduct("itiwit");

        sleep(1000);

        PO_SearchResults searchResultsPage = new PO_SearchResults();
        List<Article> articles = searchResultsPage
                .getFoundProducts().stream()
                .map(SelenideElement::getText)
                .map(Article::new)
                .collect(Collectors.toList());

        tearDown();
        articles.forEach(System.out::println);
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("{empty}")
    @Description("")
    @Test
    private void testProductDetails() {
        String product = "itiwit";
        PO_Main mainPage = new PO_Main();
        mainPage.searchForProduct(product);

        sleep(1000);

        PO_SearchResults searchResultsPage = new PO_SearchResults();
        searchResultsPage.clickProduct(0);

        PO_ProductPage productPage = new PO_ProductPage();
        System.out.println(productPage.getProductHeader());
        tearDown();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("{empty}")
    @Description("")
    @Test
    private void testCheckProductAvailability() {
        String product = "kamizelka itiwit";
        PO_Main mainPage = new PO_Main();
        mainPage.searchForProduct(product);
        sleep(1000);

        PO_SearchResults searchResultsPage = new PO_SearchResults();
        searchResultsPage.clickProduct(0);

        PO_ProductPage productPage = new PO_ProductPage();
        productPage.clickCheckAvailability();
        List<String> cities = productPage.enterCity("wroclaw");

        $(withText(cities.get(0)))
                .hover()
                .click();

        printCities(cities);

        System.out.println("Found stores: " + getAmountOfStoresFound());
        getFoundStoresNames().forEach(System.out::println);
        tearDown();

    }

    private List<String> getFoundStoresNames() {
        return $(By.tagName("li"))
                .findAll(By.tagName("h4"))
                .filter(Condition.visible)
                .texts();
    }

    private int getAmountOfStoresFound() {
        sleep(500);
        String headerContent = $("h3[class = 'store-selection__list-title js-store-result']").getText();
        return Integer
                .parseInt(headerContent
                        .replaceAll("[^0-9]", ""));
    }

    private void printCities(List<String> cities) {
        System.out.println("Cities list:");
        cities.forEach(System.out::println);
    }

    private void tearDown() {
        closeWebDriver();
    }

}
