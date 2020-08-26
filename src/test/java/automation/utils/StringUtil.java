package automation.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private StringUtil(){
        //
    }
    /**
     * This method is for judge the string null or not null
     *
     * @author    Gary Ye Feng
     * @param    str    the String
     * @return    true if string is null, otherwise false
     */
    public static boolean isNull(String str) {
        return str == null;
    }

    /**
     * This method is for judge the string is empty or not
     *
     * @author    Gary Ye Feng
     * @param    str        the String
     * @return    true if string equals to ''; otherwise false
     */
    public static boolean isEmpty(String str) {
        return "".equals(str);
    }

    /**
     * This method is for judge null or empty
     *
     * @author    Gary Ye Feng
     * @param    str        the String
     * @return    true if string equals to '' or null; otherwise false
     */
    public static boolean isNullOrEmpty(String str) {
        return isNull(str) || isEmpty(str);
    }

    /**
     * This method is for get the matcher string
     *
     * @author    Gary Ye Feng
     * @param    source    the full String
     * @param    rex        regular expression
     * @return    get the matcher string if match; otherwise null
     */
    public static String getMatch(String source, String rex) {
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            return source.substring(matcher.start(), matcher.end());
        } else {
            return null;
        }
    }

    /**
     * get the match group
     *
     * @author    Gary Ye Feng
     * @param    source    the full String
     * @param     rex        regular expression
     * @param     groupIndex    the index of group
     * @return    get the matcher string if match; otherwise null
     */
    public static String getMatchGroup(String source, String rex, int groupIndex) {
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            return matcher.group(groupIndex);
        } else {
            return null;
        }
    }

    /**
     * This method is for replaceAll the string
     *
     * @author     Gary Ye Feng
     * @param     source    the full String
     * @param    rex        regular expression
     * @param     replaceBy the string replace by
     */
    public static void replaceAll(String source, String rex, String replaceBy) {
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            matcher.replaceAll(replaceBy);
        }
    }

    /**
     * This method is for judge is match
     * @author    Gary Ye Feng
     * @param     source    the full String
     * @param     rex        regular expression
     * @return    match return true, otherwise return false
     */
    public static boolean isMatch(String source, String rex) {
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(source);
        return matcher.matches();
    }

    /**
     * This method get the left String of the boundary
     *
     * @author    Gary Ye Feng
     * @param    strFull    the full string
     * @param    strRight    the boundary string
     * @return    the left string of the boundary
     */
    public static String getLeftString(String strFull, String strRight) {
        String re_val = "";
        if (strRight==null || strFull==null) {
            return null;
        } else if (strFull.contains(strRight)) {
            re_val = strFull.substring(0, strFull.indexOf(strRight));
        }
        return re_val;
    }

    public static String getLeftStringFromLast(String strFull, String strRight) {
        String re_val = "";
        if (strRight==null || strFull==null) {
            return null;
        } else if (strFull.contains(strRight)) {
            re_val = strFull.substring(0, strFull.lastIndexOf(strRight));
        }
        return re_val;
    }

    /**
     * This method get the right String of the boundary
     *
     * @author    Gary Ye Feng
     * @param    strFull    the full string
     * @param    strLeft    the boundary string
     * @return    the right string of the boundary
     */
    public static String getRightString(String strFull, String strLeft) {
        String re_val = "";
        if (strLeft==null || strFull==null) {
            return null;
        } else if (strFull.contains(strLeft)) {
            int strLeft_len = strLeft.length();
            int start_pos = strFull.indexOf(strLeft);
            re_val = strFull.substring(start_pos+strLeft_len);
        }
        return re_val;
    }

    public static String getRightStringFromLast(String strFull, String strLeft) {
        String re_val = "";
        if (strLeft==null || strFull==null) {
            return null;
        } else if (strFull.contains(strLeft)) {
            int strLeft_len = strLeft.length();
            int start_pos = strFull.lastIndexOf(strLeft);
            re_val = strFull.substring(start_pos+strLeft_len);
        }
        return re_val;
    }

    /**
     * This method get the mid String between two boundaries
     *
     * @author    Gary Ye Feng
     * @param    strFull    the full string
     * @param    strLeft    the left boundary string
     * @param    strRight    the right boundary string
     * @return    the mid String between two boundaries if found, otherwise return ""
     */
    public static String getMidString(String strFull, String strLeft, String strRight) {
        String re_val = "";
        if (strLeft==null && strRight==null) {
            re_val = strFull;
        } else if (strLeft==null) {
            re_val = getLeftString(strFull, strRight);
        } else if (strRight==null) {
            re_val = getRightString(strFull, strLeft);
        } else if (strFull.contains(strLeft) && strFull.contains(strRight)) {
            re_val = getRightString(strFull, strLeft);
            re_val = getLeftString(re_val, strRight);
        }
        return re_val;
    }


    public static String getOuterMidString(String strFull, String strLeft, String strRight) {
        String re_val = "";
        if (strLeft==null && strRight==null) {
            re_val = strFull;
        } else if (strLeft==null) {
            re_val = getLeftStringFromLast(strFull, strRight);
        } else if (strRight==null) {
            re_val = getRightStringFromLast(strFull, strLeft);
        } else if (strFull.contains(strLeft) && strFull.contains(strRight)) {
            re_val = getRightString(strFull, strLeft);
            re_val = getLeftStringFromLast(re_val, strRight);
        }
        return re_val;
    }


}
