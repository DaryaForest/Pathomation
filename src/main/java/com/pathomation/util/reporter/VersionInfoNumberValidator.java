package com.pathomation.util.reporter;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionInfoNumberValidator {

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String JSON_VERSION_INFO_ADRESS =
            "^\"\\d\\.\\d\\.\\d\\.\\d\\d\\d\"$";
    private static final String XML_VERSION_INFO_ADRESS =
            "^\\d\\.\\d\\.\\d\\.\\d\\d\\d$";

/*    public VersionInfoNumberValidator(){
        pattern = Pattern.compile(JSON_VERSION_INFO_ADRESS);
    }*/

    /**
     * Validate version info address with regular expression
     * @param versionInfo  versionInfo number for validation in A.B.C.D format
     * @return true valid versionInfo address, false invalid versionInfo address
     */


    public static boolean validateJSON(final String versionInfo){
        pattern = Pattern.compile(JSON_VERSION_INFO_ADRESS);
        matcher = pattern.matcher(versionInfo);
        return matcher.matches();
    }
    public static boolean validateXMLVersionInfo(final String versionInfo){
        pattern = Pattern.compile(XML_VERSION_INFO_ADRESS);
        matcher = pattern.matcher(versionInfo);
        return matcher.matches();
    }



}
