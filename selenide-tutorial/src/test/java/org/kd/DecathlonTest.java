package org.kd;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.kd.common.Lib;
import org.kd.decathlon.Article;
import org.kd.decathlon.PO_Main;
import org.kd.decathlon.PO_ProductPage;
import org.kd.decathlon.PO_SearchResults;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class DecathlonTest {
    public static void main(String[] args) {
        DecathlonTest testSuite = new DecathlonTest();

        //testSuite.testProductSearch();
        //testSuite.testProductDetails();
        testSuite.testCheckProductAvailability();
    }

    private void testProductSearch() {
        PO_Main mainPage = new PO_Main();
        mainPage.searchForProduct("itiwit");
        new Lib().delay(1000);
        PO_SearchResults searchResultsPage = new PO_SearchResults();

        List<Article> articles = searchResultsPage
                .getFoundProducts().stream()
                .map(SelenideElement::getText)
                .map(Article::new)
                .collect(Collectors.toList());

        articles.forEach(System.out::println);
    }

    private void testProductDetails() {
        String product = "itiwit";
        PO_Main mainPage = new PO_Main();
        mainPage.searchForProduct(product);
        new Lib().delay(1000);

        PO_SearchResults searchResultsPage = new PO_SearchResults();
        searchResultsPage.clickProduct(0);

        PO_ProductPage productPage = new PO_ProductPage();
        System.out.println(productPage.getProductHeader());
    }

    private void testCheckProductAvailability() {
        String product = "kamizelka itiwit";

        PO_Main mainPage = new PO_Main();
        mainPage.searchForProduct(product);
        new Lib().delay(1000);

        PO_SearchResults searchResultsPage = new PO_SearchResults();
        searchResultsPage.clickProduct(0);

        PO_ProductPage productPage = new PO_ProductPage();
        productPage.clickCheckAvailability();
        List<String> cities = productPage.enterCity("wroclaw");

        $x("//*[contains(text(), '" + cities.get(0) + "')]")
                .hover()
                .click();
        printCities(cities);

        System.out.println("Found stores: " + getAmountOfFoundStores());
        getFoundStoresNames().forEach(System.out::println);
    }

    private List<String> getFoundStoresNames(){
        ElementsCollection stores = $$x("//li/h4[not(contains(@style,'displayed:false'))]");
        return stores.texts();
    }

    private int getAmountOfFoundStores() {
        String headerContent = $("h3[class = 'store-selection__list-title js-store-result']").getText();
        return Integer
                .parseInt(headerContent
                        .replaceAll("[^0-9]", ""));
    }

    private void printCities(List<String> cities) {
        System.out.println("Cities list:");
        cities.forEach(System.out::println);
    }
}
