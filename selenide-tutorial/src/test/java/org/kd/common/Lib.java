package org.kd.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Lib {

    public String convertDate(Date dateToConvert) {
        return new SimpleDateFormat("dd.MM.yyyy")
                .format(dateToConvert);
    }
}