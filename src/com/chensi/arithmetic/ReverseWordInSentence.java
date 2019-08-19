package com.chensi.arithmetic;

/**
 * Reverse each word in a sentence
 * @Author: chensi
 * @Date: 2019/6/11 10:08
 * @Version 1.0
 */
public class ReverseWordInSentence {

    public static void main(String[] args) {

    }

    public static String reverseWord(String str) {
        if(isBlank(str)) {
            return str;
        }
        char[] subStr = new char[10];
        int startIndex = 0;
        int endIndex = 0;
        for(int i = 0; i < str.length(); i++) {
            if(Character.isSpaceChar(str.charAt(i)) || (endIndex - startIndex) == subStr.length) {
                for(int j = endIndex; j >= startIndex; j--) {
                }
                startIndex = endIndex;
            }
            endIndex++;
        }
        return null;
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
}
