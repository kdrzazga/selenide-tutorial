package org.kd.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Lib {
    public Lib() {
    }

    public void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String convertDate(Date dateToConvert) {
        return new SimpleDateFormat("dd.MM.yyyy")
                .format(dateToConvert);
    }
}