package org.kd;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.junit.Test;
import org.kd.common.Lib;
import org.kd.common.TestDataFactory;
import org.kd.seleniumeasy.PO_DualListBox;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SeleniumEasyTest {
    //no PageObjectPattern
    public static final String SELENIUM_EASY = "https://www.seleniumeasy.com/test";

    public SeleniumEasyTest() {
        Configuration.startMaximized = true;
    }

    public static void main(String[] args) {
        SeleniumEasyTest test = new SeleniumEasyTest();
        test.testFooterNavigation();
        test.testMenuNavigation();
        test.testDualListBox();
        test.testDynamicDataLoading();
        test.testGenerateFileToDownload();
        test.testJsAlertBox();
    }

    public void testJsAlertBox() {
        open(SELENIUM_EASY + "/javascript-alert-box-demo.html");
        $(byText("Click for Prompt Box")).click();

        getWebDriver()
                .switchTo()
                .alert()
                .sendKeys("Deflation steps before hiperinflation.");

        getWebDriver()
                .switchTo()
                .alert()
                .accept();

        String message = $("#prompt-demo")
                .waitUntil(visible, 10 * 1000)
                .getText();

        System.out.println(message);
        tearDown();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("{empty}")
    @Description("Simple Happy-Path test for page https://www.seleniumeasy.com/test/dynamic-data-loading-demo.html#")
    @Test
    public void testGenerateFileToDownload() {
        open(SELENIUM_EASY + "/generate-file-to-download-demo.html");
        String longText = new TestDataFactory().createLongMultilineText();

        $("#textbox")
                .sendKeys(longText);
        $("#create").click();
        $("#link-to-download")
                .waitUntil(visible, 5000)
                .click();

        System.out.print("Downloaded file content does ");
        if (!readDownloaderFile().equals(longText)) System.out.print("NOT ");
        System.out.println("match");
        tearDown();
    }

    @Step
    private String readDownloaderFile() {
        File dir = new Lib()
                .getLastModified(System.getProperty("user.dir") + "\\build\\downloads");
        StringBuilder sb = new StringBuilder();

        if (dir.listFiles() == null) return "";

        Arrays.stream(Objects.requireNonNull(dir.listFiles())).findFirst().ifPresent(
                dlFile -> {
                    try {
                        List<String> lines = Files.readAllLines(dlFile.toPath());
                        String downloadedFileContent = String.join("\n", lines.toArray(new CharSequence[0]));
                        sb.append(downloadedFileContent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        return sb.toString();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("{empty}")
    @Description("Simple Happy-Path test for page https://www.seleniumeasy.com/test/dynamic-data-loading-demo.html#")
    @Test
    public void testDynamicDataLoading() {
        open(SELENIUM_EASY + "/dynamic-data-loading-demo.html");
        $("#save").click();
        SelenideElement loading = $("#loading");
        System.out.println(loading
                .waitUntil(not(text("loading...")), 20 * 1000)
                .text());

        tearDown();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("{empty}")
    @Description("Test cooperation between both boxes in dual list box")
    @Test
    public void testDualListBox() {
        PO_DualListBox po_dualListBox = new PO_DualListBox();

        Map<String, List<String>> testDataMap = new HashMap<>();
        testDataMap.put("d", Arrays.asList("bootstrap-duallist", "Dapibus ac facilisis in"));
        testDataMap.put("ac ", Arrays.asList("Dapibus ac facilisis in", "Porta ac consectetur ac"));
        testDataMap.put("us ", Arrays.asList("Dapibus ac facilisis in", "Morbi leo risus"));

        po_dualListBox.navigate();

        testDataMap.forEach(
                (typedValue, expectedValue) -> {
                    po_dualListBox.typeInSearchDualList(typedValue);

                    po_dualListBox.getStatementList()
                            .stream()
                            .map(SelenideElement::getText)
                            .filter(text -> !text.isEmpty())
                            .forEach(elementText -> assertTrue(elementText, expectedValue));
                }
        );

        tearDown();
    }

    private void assertTrue(String elementText, List<String> values) {
        if (!values.contains(elementText)) System.err.println(elementText + " - error");
        else System.out.println(elementText + " - OK");
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Epic("E1")
    @Story("{empty}")
    @Test
    public void testMenuNavigation() {
        PO_DualListBox po_dualListBox = new PO_DualListBox();
        po_dualListBox.navigate();
        po_dualListBox.clickMenuOption("Table");
       // sleep(1000);
        po_dualListBox.clickMenuOption("Table Pagination");
    }

    @Test
    public void testFooterNavigation() {
        PO_DualListBox po_dualListBox = new PO_DualListBox();
        po_dualListBox.navigate();
        po_dualListBox.clickTutorialsMenuOption("Selenium Tutorials");
    }

    private void tearDown() {
        closeWebDriver();
    }
}
