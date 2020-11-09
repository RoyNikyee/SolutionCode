package src;

import java.util.*;

public class October {
    //13
    public int romanToInt(String s) {
        s = s.replace("IV","a");
        s = s.replace("IX","b");
        s = s.replace("XL","c");
        s = s.replace("XC","d");
        s = s.replace("CD","e");
        s = s.replace("CM","f");

        int res = 0;
        for(int i = 0; i < s.length(); i++)
        {
            res = res + valueAtChar(s.charAt(i));
        }
        return res;
    }
    int valueAtChar(char x)
    {
        switch(x)
        {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            case 'a': return 4;
            case 'b': return 9;
            case 'c': return 40;
            case 'd': return 90;
            case 'e': return 400;
            case 'f': return 900;
            default: return 0;
        }
    }
    //09
    public boolean isPalindrome(int x) {
        //先处理特殊情况（一定不是回文数）：1.负数 2.0结尾的数（0除外）
        if(x < 0 || (x != 0 && x % 10 == 0))
            return false;
        int resLastPart = 0;
        while(resLastPart < x)//保证反转到一半
        {
            resLastPart = resLastPart * 10 + x % 10;
            x /= 10;
        }
        return (x == resLastPart || x == resLastPart/10);
    }
    //05
    public String longestPalindrome(String s) {
        if(s.length() < 2)
        {
            return s;
        }
        int leftIndex = 0;
        int rightIndex = 0;
        for(int i = 0; i < s.length(); i++)
        {
            int oddCenterExpendLength = maxExpendLength(s,i,i);
            int dualCenterExpendLength = maxExpendLength(s,i,i+1);
            int len = Math.max(oddCenterExpendLength,dualCenterExpendLength);
            if(len > rightIndex - leftIndex)
            {
                //这里要充分考虑奇数和偶数，与向下取整的规律
                leftIndex = i - (len - 1)/2;
                rightIndex = i + len/2;
            }
        }
        return s.substring(leftIndex,rightIndex + 1);
    }
    public int maxExpendLength(String s, int left, int right)
    {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))
        {
            left --;
            right ++;
        }
        return right - left - 1;
    }
    //03
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        int right = -1;//将right设置为哪个index之后就好了。-1之后，指向的是0
        int ans = 0;
        Set<Character> occur = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (i > 0) {
                occur.remove(s.charAt(i - 1));
            }
            while (right + 1 < s.length() && !occur.contains(s.charAt(right + 1))) {
                occur.add(s.charAt(right + 1));
                right++;
            }
            ans = Math.max(ans, right - i + 1);
        }
        return ans;
    }

    //面试题02.01
    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> valueSet = new HashSet<>();
        ListNode curr = head;
        ListNode prev = new ListNode(-1);//前驱节点
        prev.next = curr;
        //末尾元素之前的元素，保存值和去重
        while (curr != null) {
            if (!valueSet.contains(curr.val)) {
                valueSet.add(curr.val);
                prev = prev.next;
            } else {
                prev.next = curr.next;
            }
            curr = curr.next;
        }
        return head;
    }

    //剑指offer22
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    //剑指offer18
    public ListNode deleteNode(ListNode head, int val) {
        ListNode curr = head;
        //中间元素是删除目标
        while (curr != null && curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        //末尾元素是删除目标
        if (curr.val == val) {
            curr = null;
        }
        //开头元素是删除目标
        if (head.val == val) {
            head = head.next;
        }
        return head;
    }

    //剑指offer06
    public int[] reversePrint(ListNode head) {
        //考虑特殊情况
        if (head == null) {
            return new int[0];
        }
        //先将链表反转，并记录链表的大小
        ListNode pre = null;
        ListNode curr = head;
        int size = 0;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextTemp;
            size++;
        }
        curr = pre;
        //创建一个数组用来保存结果
        //并且遍历反转候的链表，将遍历到的值保存在数组中
        int[] ret = new int[size];
        int currIndex = 0;
        while (curr != null) {
            ret[currIndex] = curr.val;
            currIndex++;
            curr = curr.next;
        }
        return ret;
    }

    //1290
    public int getDecimalValue(ListNode head) {
        ListNode curr = head;
        int ret = 0;
        while (curr != null) {
            ret = ret * 2 + curr.val;
            curr = curr.next;
        }
        return ret;
    }

    //876
    public ListNode middleNode(ListNode head) {
        //优雅的双指针
        ListNode low = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
        }
        return low;
    }

    //    public ListNode middleNode(ListNode head) {
//        //记录中间是第几个数
//        int allNum = 0;
//        ListNode curr = head;
//        while(curr != null)
//        {
//            curr = curr.next;
//            allNum ++;
//        }
//        curr = head;
//        for(int i = 0; i < allNum/2; i++)
//        {
//            curr = curr.next;
//        }
//        return curr;
//    }
    //237
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    //234
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode curr = head;
        //构建stack
        Stack<Integer> itemStack = new Stack<>();
        while (curr != null) {
            itemStack.push(curr.val);
            curr = curr.next;
        }
        curr = head;
        //从head开始，与出栈元素比较
        while (!itemStack.isEmpty()) {
            if (curr.val != itemStack.pop())
                return false;
            else
                curr = curr.next;
        }
        return true;
    }

    //206
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    //206
//    public ListNode reverseList(ListNode head) {
//        //不需要反转的情况
//        if(head == null || head.next == null)
//            return head;
//        //建立int的栈，按list顺序存储
//        Stack<Integer> itemStack = new Stack<>();
//        while(head != null)
//        {
//            itemStack.push(head.val);
//            head = head.next;
//        }
//        //按照stack的弹出顺序，重构node
//        ListNode reverse = new ListNode(itemStack.pop());
//        ListNode ans = reverse;
//        while(!itemStack.isEmpty())
//        {
//            ListNode tempNode = new ListNode(itemStack.pop());
//            reverse.next = tempNode;
//            reverse = reverse.next;
//        }
//        return ans;
//    }
    //203
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode ret = head;
        while (head != null && head.next != null) {
            if (head.next.val == val)
                head.next = head.next.next;
            else
                head = head.next;
        }
        return ret;
    }

    //160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //特殊情况
        if (headA == null || headB == null)
            return null;
        //首尾遍历项
        ListNode pA = headA;
        ListNode pB = headB;
        //利用 A+B = B+A
        while (pA != pB) {
            pA = pA.next;
            pB = pB.next;

            if (pA == null && pB == null)
                return null;//先判断是否可以终止，也就是A+B 和 B+A遍历完后没有相遇，此时可以终止
            if (pA == null)
                pA = headB;
            if (pB == null)
                pB = headA;
        }
        return pA;
    }

    //160
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        Set<ListNode> visitedListA = new HashSet<>();
//        while(headA != null)
//        {
//            visitedListA.add(headA);
//            headA = headA.next;
//        }
//        while(headB != null)
//        {
//            if(visitedListA.contains(headB))
//                return headB;
//            headB = headB.next;
//        }
//        return null;
//    }
    //141
    public boolean hasCycle(ListNode head) {
        ListNode low = head;
        ListNode fast = low;
        while (low != null && fast != null) {
            low = low.next;
            if (fast.next != null)
                fast = fast.next.next;
            else
                return false;
            if (low == fast)
                return true;
        }
        return false;
    }

    //141
//    public boolean hasCycle(ListNode head) {
//        Set<ListNode> visited = new HashSet<>();
//        while(head != null)
//        {
//            if(visited.add(head) == false)
//                return true;
//            head = head.next;
//        }
//        return false;
//    }
    //83
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val)
                curr.next = curr.next.next;
            curr = curr.next;
        }
        return head;
    }

    //21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prehead;
    }

    //724
    public int pivotIndex(int[] nums) {
        //极其特殊的情况
        if (nums.length == 0)
            return -1;
        //初始位置的左右两侧和
        int leftSum = 0;
        int rightSum = 0;
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            rightSum += nums[i];
        }
        //左加右减，直至相等或者最后一个数
        while (leftSum != rightSum && index < nums.length - 1) {
            index++;
            leftSum += nums[index - 1];
            rightSum -= nums[index];
        }
        //判断停止的位置是否正确
        return leftSum == rightSum ? index : -1;
    }

    //717
    public boolean isOneBitCharacter(int[] bits) {
        //不管最后一位是0还是1，只要前length - 1位可以组成唯一的字符串就可以。
        int stop = 0;
        while (stop < bits.length - 1) {
            if (bits[stop] == 1)
                stop += 2;
            else
                stop++;
        }
        return stop == bits.length - 1;
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
