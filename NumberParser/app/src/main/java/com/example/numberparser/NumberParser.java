package com.example.numberparser;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import java.util.Map;

public class NumberParser {

    private final String Plus_sign = "+";
    private Map<String, Integer> callingCodes;
    private Map<String, String> prefixes;

    public NumberParser(Map<String, Integer> callingCodes, Map<String, String> prefixes) {
        this.callingCodes = callingCodes;
        this.prefixes = prefixes;
    }

    public String parse(String dialledNumber, String userNumber) {

        //If diallednumber or user'snumber empty return 0
        if (StringUtils.isEmpty(dialledNumber) || StringUtils.isEmpty(userNumber)) {
            return null;
        }

       //If dialledNumber in international format returns dialledNumber
        for (Map.Entry<String, Integer> entry : callingCodes.entrySet()) {
            if (dialledNumber.startsWith(Plus_sign+entry.getValue())) {
                return dialledNumber;
            }
        }

        //Get country calling code from userNumber
        String userCountryCode = getCountryCode(userNumber);

        //Get country code
        String userPrefix = getPrefix(userCountryCode);

        //Format diallednumber
        if (dialledNumber.startsWith(userPrefix)) {
            String newDialledNumber = dialledNumber.substring(userPrefix.length());
            Log.d("Dialled number","MSG: "+Plus_sign+userCountryCode+newDialledNumber);
            return Plus_sign+userCountryCode+newDialledNumber;
        }
        return dialledNumber;
    }

    //Get country calling code/international code from userNumber
    private String getCountryCode(String number) {
        for (Map.Entry<String, Integer> entry : callingCodes.entrySet()) {
            Integer countryCode = entry.getValue();
            if (number.startsWith(Plus_sign+countryCode)) {
                Log.d("getInternationalCode","MSG: "+countryCode+"");
                return countryCode+"";
            }
        }
        return null;
    }

    //Get country prefix code (GB/US) from country calling code
    private String getPrefix(String internationalPrefix) {
        for (Map.Entry<String, Integer> entry : callingCodes.entrySet()) {
            Integer code = entry.getValue();
            if (Integer.valueOf(internationalPrefix).equals(code)) {
                String countryCode = entry.getKey();
                Log.d("getPrefix","MSG: "+countryCode);
                return prefixes.get(countryCode);
            }
        }
        return null;
    }
}
