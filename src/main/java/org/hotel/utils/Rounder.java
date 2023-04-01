package org.hotel.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rounder {
    public static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(decimalPlace, RoundingMode.HALF_UP).doubleValue();
    }
}
