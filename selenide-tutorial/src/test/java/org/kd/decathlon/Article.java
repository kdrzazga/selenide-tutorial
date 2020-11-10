package org.kd.decathlon;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class Article {
    private final boolean promotion;
    private final boolean zeroInterest;
    private final Float price;
    private final String manufacturer;
    private final String description;

    public Article(String tagContent) {
        String[] fields = tagContent.split("\n");

        promotion = "KONIEC SERII".equals(fields[0]);
        zeroInterest = "RATY 0%".equals(fields[0]);

        String priceString = Arrays.stream(fields)
                .filter(fieldContent -> fieldContent.matches("\\d+,\\d+\\sz."))
                .findFirst()
                .orElse("")
                .replaceAll("\\sz.", "")
                .replace(",", ".");

        price = priceString.length() > 0 ? Float.parseFloat(priceString) : 0f;

        manufacturer = Arrays.stream(fields).skip(fields.length - 2).findFirst().orElse("");
        description = Arrays.stream(fields).skip(fields.length - 1).findFirst().orElse("");

    }

    @Override
    public String toString() {
        return "Article{" +
                "promotion=" + promotion +
                ", zeroInterest=" + zeroInterest +
                ", price='" + price + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
