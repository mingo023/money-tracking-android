package com.cs50.finalprojectcs50.utils;

import java.text.NumberFormat;

public class StringConverts {
    public static String formatCurrency(long money) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(money);
    }
}
