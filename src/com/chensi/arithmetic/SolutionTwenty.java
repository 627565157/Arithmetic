package com.chensi.arithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensi
 * 2019-08-22 10:20
 */
public class SolutionTwenty {

    public static void main(String[] args) {
        SolutionTwenty solutionTwenty = new SolutionTwenty();
        String[] arr = new String[] {"dog","racecar","car"};
        System.out.println(solutionTwenty.longestCommonPrefix(arr));
    }

    /**
     * 盛最多水的容器
     * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * <p>
     * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * <p>
     * <p>
     * <p>
     * 示例:
     * <p>
     * 输入: [1,8,6,2,5,4,8,3,7]
     * 输出: 49
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        int startIndex = 0;
        int endIndex = height.length - 1;
        long area = 0;
        while (endIndex > startIndex) {
            int width = endIndex - startIndex;
            int h = Math.min(height[startIndex], height[endIndex]);
            area = Math.max(area, width * h);
            if (height[startIndex] > height[endIndex]) {
                endIndex--;
            } else {
                startIndex++;
            }
        }
        if (area >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int) area;
        }
    }

    /**
     * 整数转罗马数字
     *
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     *     I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     *     X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     *     C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     *
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     *
     * 示例 1:
     *
     * 输入: 3
     * 输出: "III"
     *
     * 示例 2:
     *
     * 输入: 4
     * 输出: "IV"
     *
     * 示例 3:
     *
     * 输入: 9
     * 输出: "IX"
     *
     * 示例 4:
     *
     * 输入: 58
     * 输出: "LVIII"
     * 解释: L = 50, V = 5, III = 3.
     *
     * 示例 5:
     *
     * 输入: 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");
        int numUnit = 1000;
        for(int i = 0; i <= 3; i++) {
            if(num / numUnit > 0) {
                int curNum = num / numUnit;
                if(curNum == 4 || curNum == 5 || curNum == 9) {
                    stringBuilder.append(map.get(curNum * numUnit));
                } else {
                    if(curNum < 5) {
                        for(int j = 0; j < curNum; j++) {
                            stringBuilder.append(map.get(numUnit));
                        }
                    } else {
                        stringBuilder.append(map.get(5*numUnit));
                        for(int j = 0; j < curNum - 5; j++) {
                            stringBuilder.append(map.get(numUnit));
                        }
                    }
                }
                num = num - curNum * numUnit;
            }
            numUnit = numUnit / 10;
        }
        return stringBuilder.toString();
    }

    /**
     * 罗马数字转整数
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);
        int result = 0;
        int index = 0;
        while(index < s.length()) {
            String curChar = String.valueOf(s.charAt(index));
            if(index + 1 < s.length()) {
                if(map.containsKey(curChar + s.charAt(index + 1))) {
                    result += map.get(curChar + s.charAt(index + 1));
                    index += 2;
                } else {
                    result += map.get(curChar);
                    index++;
                }
            } else {
                result += map.get(curChar);
                index ++;
            }
        }
        return result;
    }

    /**
     * 最长公共前缀
     *
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * 示例 1:
     *
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     *
     * 示例 2:
     *
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     *
     * 说明:
     *
     * 所有输入只包含小写字母 a-z 。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0 || strs[0].length() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while(true) {
            if(index > strs[0].length() - 1) {
                return stringBuilder.toString();
            }
            char curChar = strs[0].charAt(index);
            for(int i = 1; i < strs.length; i++) {
                if(index > strs[i].length() - 1) {
                    return stringBuilder.toString();
                }
                if(curChar != strs[i].charAt(index)) {
                    return stringBuilder.toString();
                }
            }
            stringBuilder.append(curChar);
            index++;
        }
    }

}
