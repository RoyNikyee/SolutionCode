package src;
import src.*;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

        List<List<Integer>> ret = September.generate(5);
        System.out.println(ret);

    }

    //697
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            right.put(nums[i], i);
            if (!left.containsKey(nums[i]))
                left.put(nums[i], i);
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }
        int degree = Collections.max(count.values());
        int ans = Integer.MAX_VALUE;

        for (int x : count.keySet()) {
            if (count.get(x) == degree) {
                ans = Math.min(ans, right.get(x) - left.get(x) + 1);
            }
        }
        return ans;
    }

    //674
    public int findLengthOfLCIS(int[] nums) {
        //考虑极端特殊的情况
        if (nums.length == 0)
            return 0;
        int maxLength = 1;
        int length = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] <= nums[i]) {
                length = 1;
            } else {
                length++;
                maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }

    //665
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1])
                count++;

            if (count > 1)
                return false;
        }
        return true;
    }

    //661
    public int[][] imageSmoother(int[][] M) {
        int H = M.length;
        int W = M[0].length;
        int[][] ans = new int[H][W];

        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                //计算ans[h][w]
                int count = 0;
                for (int nh = h - 1; nh <= h + 1; nh++) {
                    for (int nw = w - 1; nw <= w + 1; nw++) {
                        if (nh >= 0 && nh < H && nw >= 0 && nw < W) {
                            ans[h][w] += M[nh][nw];
                            count++;
                        }
                    }
                }
                ans[h][w] /= count;
            }
        }
        return ans;
    }

    //643
    public double findMaxAverage(int[] nums, int k) {
        //min不要搞成max
        k = Math.min(k, nums.length);
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        //这个res变量很重要
        double res = sum;
        for (int j = 0; j < nums.length - k; j++) {
            sum = sum - nums[j] + nums[j + k];
            res = Math.max(res, sum);
        }
        return res / k;
    }

    //628
    public int maximumProduct(int[] nums) {
        int first = Integer.MIN_VALUE;
        int second = first;
        int third = first;
        int last = Integer.MAX_VALUE;
        int lastButNotLeast = last;
        for (int n : nums) {
            if (n >= first) {
                third = second;
                second = first;
                first = n;
            } else if (n >= second) {
                third = second;
                second = n;
            } else if (n >= third) {
                third = n;
            }

            if (n <= last) {
                lastButNotLeast = last;
                last = n;
            } else if (n <= lastButNotLeast) {
                lastButNotLeast = n;
            }
        }
        return Math.max(first * second * third, last * lastButNotLeast * first);
    }

    //605
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int plantNum = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                if (i == 0 || flowerbed[i - 1] == 0) {
                    if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                        flowerbed[i] = 1;
                        plantNum++;
                        if (plantNum == n) {
                            return true;
                        }
                    }
                }
            }
        }
        if (plantNum < n) {
            return false;
        }
        return true;
    }

}
