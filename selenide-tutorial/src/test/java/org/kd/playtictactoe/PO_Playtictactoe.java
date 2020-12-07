package org.kd.playtictactoe;

import com.codeborne.selenide.ElementsCollection;
import org.kd.common.BasePage;

import static com.codeborne.selenide.Selenide.*;

public class PO_Playtictactoe extends BasePage {

    public PO_Playtictactoe() {
        super("https://playtictactoe.org/");
        open(url);
    }

    public ElementsCollection getCells() {
        return $$x("//div[contains(@class, 'square')]");
    }

    public ElementsCollection getEmptyCells() {
        return $$x("//div[contains(@class, 'square')]/div[@class = '']");
    }

    public boolean isGameOver() {
        boolean gameOver = $$("div[class='restart']").isEmpty();
        if (gameOver) System.out.println("game over");
        else System.out.println("game is running");
        return gameOver;
    }

    @Override
    public void navigate() {
        System.err.println("No URL");
    }
}
