package src;
import org.w3c.dom.NodeList;
import src.*;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

        List<List<Integer>> ret = September.generate(5);
        System.out.println(isPalindrome("    Aa  "));

    }

    //1103
    public int[] distributeCandies(int candies, int num_people) {
        int currCandySum = 0;
        int currIndex = 0;
        int[] res = new int[num_people];
        while(currCandySum < candies)
        {
            currIndex ++;
            if(candies - currCandySum > currIndex)
            {
                res[(currIndex - 1)% num_people] += currIndex;
            }
            else
            {
                res[(currIndex - 1)% num_people] += candies - currCandySum;
            }
            currCandySum += currIndex;
        }
        return res;
    }
    //198
    public int rob(int[] nums) {
        //特殊情况
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        //从nums[1]开始，记录当前的两种选择
        int robk2 = nums[0];
        int robk1 = Math.max(nums[0],nums[1]);
        for(int i = 2; i < nums.length; i++)
        {
            int temp = Math.max(robk2 + nums[i],robk1);
            robk2 = robk1;
            robk1 = temp;
        }
        return robk1;
    }
    //136
    public int singleNumber(int[] nums) {
        int ret = 0;
        for(int num : nums)
        {
            ret = ret ^ num;
        }
        return ret;
    }
    //125
    static public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left < right)
        {
            while(left < right && !Character.isLetterOrDigit(s.charAt(left)))
                left ++;
            while(left < right && !Character.isLetterOrDigit(s.charAt(right)))
                right --;
            if(left < right)
            {
                if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                    return false;
                left ++;
                right --;
            }
        }
        return true;
    }
    //70
    public int climbStairs(int n) {
        if(n == 1)
            return 1;
        if(n == 2)
            return 2;
        int n1 = 2;
        int n2 = 1;
        int ret = 0;
        for(int i = 3; i <= n; i++)
        {
            ret = n1 + n2;
            n2 = n1;
            n1 = ret;
        }
        return ret;
    }
    //67
    public String addBinary(String a, String b) {
        int m = a.length();
        int n = b.length();
        StringBuilder ans = new StringBuilder();
        int carry = 0;
        for(int i = 0; i < Math.max(m,n); i++)
        {
            carry = carry + (i < m? a.charAt(i) - '0' : 0);
            carry = carry + (i < n? b.charAt(i) - '0' : 0);
            ans.append(carry % 2 +'0');
            carry /= 2;
        }
        if(carry > 0)
        {
            ans.append('1');
        }
        ans = ans.reverse();
        return ans.toString();
    }
    //58
    public int lengthOfLastWord(String s) {
        //特殊情况
        if(s.length() == 0)
            return 0;
        int end = s.length() - 1;
        while(end >= 0 && s.charAt(end) == ' ')
        {
            end --;
        }
        if(end < 0)
            return 0;
        //已经找到了单词的结束位置，下面开始寻找单词的开始位置
        int start = end;
        while(start >= 0 && s.charAt(start) != ' ')
        {
            start--;
        }
        return end - start;
    }
    //28
    public int strStr(String haystack, String needle) {
        //处理特殊情况
        if(needle == "")
            return 0;
        if(haystack == "")
            return -1;

        //开始判断
        int length = needle.length();
        for(int i = 0; i < haystack.length() - length + 1; i++)
        {
            String sub = haystack.substring(i,i+length);
            if(sub.equals(needle))//这里要使用equals函数，而不能用 == 判断
                return i;
        }
        return -1;
    }
    //20
    public boolean isValid(String s) {
        //特殊情况,奇数个括号，不可能成对
        if(s.length() % 2 == 1)
            return false;
        Stack<Character> stackTemp = new Stack<>();
        for(int i = 0; i < s.length(); i++)
        {
            char curr = s.charAt(i);
            if(curr == '(' || curr == '[' || curr == '{')
            {
                stackTemp.push(curr);
            }
            else if(curr == ')')
            {
                if(!stackTemp.isEmpty() && stackTemp.peek() == '(')
                    stackTemp.pop();
                else
                    return false;
            }
            else if(curr == ']')
            {
                if(!stackTemp.isEmpty() && stackTemp.peek() == '[')
                    stackTemp.pop();
                else
                    return false;
            }
            else if(curr == '}')
            {
                if(!stackTemp.isEmpty() && stackTemp.peek() == '{')
                    stackTemp.pop();
                else
                    return false;
            }
        }
        return stackTemp.isEmpty();
    }
    //14
    public String longestCommonPrefix(String[] strs) {
        //特殊情况，没有string
        if(strs.length == 0)
        {
            return "";
        }
        //string数组有内容时
        String ret = strs[0];
        for(int i = 1; i < strs.length; i++)
        {
            ret = commenPrefix(ret,strs[i]);
            if(ret.length() == 0)
                return ret;
        }
        return ret;
    }

    public String commenPrefix(String a, String b)
    {
        int length = Math.min(a.length(),b.length());
        int i = 0;
        while(i < length && (a.charAt(i) == b.charAt(i)))
        {
            i ++;
        }
        return a.substring(0,i);
    }
}

