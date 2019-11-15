package com.chensi.arithmetic;

/**
 * @author chensi
 * 2019-11-15 11:09
 */
public class SimpleTen {

    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * <p>
     * 你可以假设数组中无重复元素。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: [1,3,5,6], 2
     * 输出: 1
     * 示例 3:
     * <p>
     * 输入: [1,3,5,6], 7
     * 输出: 4
     * 示例 4:
     * <p>
     * 输入: [1,3,5,6], 0
     * 输出: 0
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-insert-position
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            } else if (nums[i] > target) {
                return i;
            } else {
                if (i == nums.length - 1) {
                    return nums.length;
                }
            }
        }
        return 0;
    }


    /**
     * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
     * <p>
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 1 被读作  "one 1"  ("一个一") , 即 11。
     * 11 被读作 "two 1s" ("两个一"）, 即 21。
     * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
     * <p>
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
     * <p>
     * 注意：整数顺序将表示为一个字符串。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1
     * 输出: "1"
     * 示例 2:
     * <p>
     * 输入: 4
     * 输出: "1211"
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String str = "1";
        int i = 1;
        while (i < n) {
            StringBuilder subStr = new StringBuilder();
            if (str.length() == 1) {
                subStr.append("1").append(str);
            } else {
                Character curChar = str.charAt(0);
                int start = 0;
                for (int j = 1; j < str.length(); j++) {
                    if (curChar != null && !curChar.equals(str.charAt(j))) {
                        String curSub = str.substring(start, j);
                        subStr.append(curSub.length()).append(curSub.charAt(0));
                        curChar = str.charAt(j);
                        start = j;
                    }
                }
                String curSub = str.substring(start);
                subStr.append(curSub.length()).append(curSub.charAt(0));
            }
            str = subStr.toString();
            i++;
        }
        return str;
    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶:
     *
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        int sum = nums[0];
        int cur = nums[0];
        int i = 1;
        while(i < nums.length) {
            if(sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            cur = Math.max(cur, sum);
        }
        return cur;
    }

    /**
     * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
     *
     * 如果不存在最后一个单词，请返回 0 。
     *
     * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
     *
     * 示例:
     *
     * 输入: "Hello World"
     * 输出: 5
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int lastBlank = s.lastIndexOf(" ");
        if(lastBlank >= 0) {
            if (lastBlank == s.length() - 1) {
                return lengthOfLastWord(s.substring(0, lastBlank));
            } else {
                return s.substring(lastBlank + 1).length();
            }
        }
        return s.length();
    }

    public int lengthOfLastWord2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        if (end < 0) {
            return 0;
        }
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     *
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     *
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * 示例 2:
     *
     * 输入: [4,3,2,1]
     * 输出: [4,3,2,2]
     * 解释: 输入数组表示数字 4321。
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        return null;
    }

    public static void main(String[] args) {
        SimpleTen simpleTen = new SimpleTen();
        System.out.println(simpleTen.lengthOfLastWord2("hello world"));
    }
}
