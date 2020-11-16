package org.kd;

import org.kd.playtictactoe.PO_Playtictactoe;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;

public class PlaytictactoeTest {

    public static void main(String[] args) {
        PO_Playtictactoe page = new PO_Playtictactoe();
        while (!page.getEmptyCells().isEmpty()) {
            if (page.isGameOver()) break;
            page.getEmptyCells().get(0).parent().click();
            sleep(150);
        }

        sleep(750);
        closeWebDriver();
    }
}
