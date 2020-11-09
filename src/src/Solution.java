package src;
import org.w3c.dom.NodeList;
import src.*;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

        List<List<Integer>> ret = September.generate(5);
        System.out.println(ret);

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

