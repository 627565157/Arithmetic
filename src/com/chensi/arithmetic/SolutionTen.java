package com.chensi.arithmetic;

/**
 * @author chensi
 * 2019-08-19 09:30
 */
public class SolutionTen {

    public static void main(String[] args) {
        int[] num1 = new int[]{1, 2};
        int[] num2 = new int[]{3, 4};
        SolutionTen solutionTen = new SolutionTen();
        System.out.println(solutionTen.findMedianSortedArrays(num1, num2));
    }


    /**
     * 解法1
     * 寻找两个有序数组的中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArraysOne(int[] nums1, int[] nums2) {
        int[] nums3 = new int[nums1.length + nums2.length];
        int num1Index = 0;
        int num2Index = 0;
        for (int i = 0; i < nums3.length; i++) {
            if (num1Index < nums1.length && num2Index < nums2.length) {
                if (nums1[num1Index] < nums2[num2Index]) {
                    nums3[i] = nums1[num1Index];
                    num1Index++;
                } else {
                    nums3[i] = nums2[num2Index];
                    num2Index++;
                }
            } else if (num1Index >= nums1.length) {
                nums3[i] = nums2[num2Index];
                num2Index++;
            } else if (num2Index >= nums2.length) {
                nums3[i] = nums1[num1Index];
                num1Index++;
            }
        }
        return getArrMiddle(nums3);
    }

    private double getArrMiddle(int[] arr) {
        if (arr.length % 2 == 0) {
            int middile = arr.length / 2;
            return (double) (arr[middile] + arr[middile - 1]) / 2;
        } else {
            int middle = (arr.length / 2);
            return arr[middle];
        }
    }

    /**
     * 解法2
     * 寻找两个有序数组的中位数
     * 只遍历， 不创建新数组
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArraysRow(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int num1Index = 0;
        int num2Index = 0;
        int left = -1;
        int right = -1;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (num1Index < m && (num2Index >= n || nums1[num1Index] <= nums2[num2Index])) {
                right = nums1[num1Index++];
            } else {
                right = nums2[num2Index++];
            }
        }
        if (len % 2 == 1) {
            return right;
        } else {
            return (left + right) / 2.0;
        }
    }

    /**
     * 解法3 寻找两个有序数组的中位数
     *
     * 利用查找第k个小的元素，每次舍弃小于第k/2个元素的元素
     * @param nums1
     * @param nums2
     * @return
     */

    public double findMedianSortedArraysThree(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        return (getKth(nums1, 0, m - 1, nums2, 0, n - 1, left) + getKth(nums1, 0, m - 1, nums2, 0, n - 1, right)) / 2.0;
    }

    /**
     * 空间复杂度：虽然我们用到了递归，但是可以看到这个递归属于尾递归，所以编译器不需要不停地堆栈，所以空间复杂度为 O(1)
     * 找到第K个小的值
     * @param nums1
     * @param start1
     * @param end1
     * @param nums2
     * @param start2
     * @param end2
     * @param k
     * @return
     */
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        } else if (len2 == 0) {
            return nums1[start1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        int i = start1 + Math.min(k / 2, len1) - 1;
        int j = start2 + Math.min(k / 2, len2) - 1;
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    /**
     * 解法4 寻找两个有序数组的中位数
     * i + j = m - i  + n - j  , 也就是 j = ( m + n ) / 2 - i
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if(m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int iMin = 0;
        int iMax = m;
        while(iMin <= iMax) {
            int i = (iMin + iMax) /2;
            int j = (m + n + 1) / 2 - i;
            if(j != 0 && i != m && nums2[j - 1] > nums1[i]) {
                iMin = i + 1;
            } else if(i != 0 && j != n && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;
            } else {
                int maxLeft;
                if(i == 0) {
                    maxLeft = nums2[j - 1];
                } else if(j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight;
                if(i == m) {
                    minRight = nums2[j];
                } else if(j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }


}
