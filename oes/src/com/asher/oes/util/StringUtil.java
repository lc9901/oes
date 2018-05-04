package com.asher.oes.util;


public class StringUtil {

    public static boolean isNull(String data) {
        return data == null || data.equals("");
    }

    public static String[] split(String strSource, String strDiv) {
        int arynum = 0, intIdx = 0, intIdex = 0;
        int div_length = strDiv.length();
        if (strSource.compareTo("") != 0) {
            if (strSource.indexOf(strDiv) != -1) {
                intIdx = strSource.indexOf(strDiv);
                for (int intCount = 1; ; intCount++) {
                    if (strSource.indexOf(strDiv, intIdx + div_length) != -1) {
                        intIdx = strSource.indexOf(strDiv, intIdx + div_length);
                        arynum = intCount;
                    }
                    else {
                        arynum += 2;
                        break;
                    }
                }
            }
            else {
                arynum = 1;
            }
        }
        else {
            arynum = 0;
        }
        intIdx = 0;
        intIdex = 0;
        String[] returnStr = new String[arynum];

        if (strSource.compareTo("") != 0) {
            if (strSource.indexOf(strDiv) != -1) {
                intIdx = strSource.indexOf(strDiv);
                returnStr[0] = strSource.substring(0, intIdx);
                for (int intCount = 1; ; intCount++) {
                    if (strSource.indexOf(strDiv, intIdx + div_length) != -1) {
                        intIdex = strSource.indexOf(strDiv, intIdx + div_length);
                        returnStr[intCount] = strSource.substring(intIdx + div_length,
                                intIdex);
                        intIdx = strSource.indexOf(strDiv, intIdx + div_length);
                    }
                    else {
                        returnStr[intCount] = strSource.substring(intIdx + div_length,
                            strSource.length());
                        break;
                    }
                }
            }
            else {
                returnStr[0] = strSource.substring(0, strSource.length());
                return returnStr;
            }
        }
        else {
            return returnStr;
        }
        return returnStr;
    }
}