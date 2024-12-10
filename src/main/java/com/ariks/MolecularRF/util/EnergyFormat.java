package com.ariks.MolecularRF.util;

public class EnergyFormat {
    private static final String[] SUFFIXES = {"", "K", "M", "B", "T", "Q", "QQ"};
    public static String formatNumber(long number) {
        if (number < 1000) {
            return String.valueOf(number);
        }
        int magnitude = (int) (Math.log10(number) / 3);
        double scaledNumber = number / Math.pow(1000, magnitude);
        return String.format("%.1f %s", scaledNumber, SUFFIXES[magnitude]);
    }
}