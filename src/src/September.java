package src;

import java.util.*;

public class September {
    //509
    public int fib(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        return fib(N - 1) + fib(N - 2);
    }

    //485
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
                res = Math.max(res, count);
            } else {
                count = 0;
            }
        }
        return res;
    }

    //448
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = Math.abs(nums[i]) - 1;
            if (nums[temp] > 0) {
                nums[temp] = -1 * nums[temp];
            }
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] > 0) {
                res.add(j + 1);
            }
        }
        return res;
    }

    //414
    public int thirdMax(int[] nums) {
        long max = Long.MIN_VALUE;
        long mid = max;
        long third = max;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                third = mid;
                mid = max;
                max = nums[i];
            } else if (nums[i] > mid && nums[i] < max) {
                third = mid;
                mid = nums[i];
            } else if (nums[i] > third && nums[i] < mid) {
                third = nums[i];
            }
        }
        if (third != Long.MIN_VALUE) {
            return new Long(third).intValue();
        }
        return new Long(max).intValue();
    }

    //283
    public void moveZeroes(int[] nums) {
        int lastNonZeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroIndex] = nums[i];
                lastNonZeroIndex++;
            }
        }
        for (int j = lastNonZeroIndex; j < nums.length; j++) {
            nums[j] = 0;
        }
    }

    //268
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ (i ^ nums[i]);
        }
        return res;
    }

    //219
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //前n - k
        k = Math.min(k, nums.length);
        for (int i = 0; i < nums.length - k; i++) {
            for (int n = 1; n <= k; n++) {
                if (nums[i] == nums[i + n]) {
                    return true;
                }
            }
        }
        //最后k个元素
        if (k >= 2) {
            for (int j = nums.length - k; j < nums.length - 1; j++) {
                for (int m = j + 1; m < nums.length; m++) {
                    if (nums[j] == nums[m]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //217
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    //169
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    //167
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            res[0] = i;
            for (int j = i; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    res[1] = j;
                }
            }
        }
        res[0]++;
        res[1]++;
        return res;
    }

    //122 买卖股票II
    public int maxProfit(int[] prices) {
        int profit = 0;
        int i = 0;
        while (i < prices.length - 1) {
            //寻找买入时机
            while (i < prices.length - 1 && prices[i + 1] < prices[i]) {
                i++;
            }
            int low = prices[i];
            //寻找卖出时机
            while (i < prices.length - 1 && prices[i + 1] >= prices[i]) {
                i++;
            }
            int high = prices[i];
            profit += high - low;
        }
        return profit;
    }


    //121 买卖股票I
    public int maxProfit1(int[] prices) {
        int minBeforeCurr = Integer.MAX_VALUE;
        int[] profits = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minBeforeCurr) {
                minBeforeCurr = prices[i];
            }
            profits[i] = prices[i] - minBeforeCurr;
        }
        int maxProfit = 0;
        for (int profit : profits) {
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }

//    //167
//    public int[] twoSum(int[] numbers, int target) {
//        for (int i = 0; i < numbers.length; i++) {
//            for (int j =)
//        }
//    }

    //119杨辉三角II
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new LinkedList<>();
        if (rowIndex == 0) {
            return null;
        }
        //先创建第一行
        res.add(new LinkedList<>());
        res.get(0).add(1);
        for (int i = 1; i < rowIndex; i++) {
            //记录上一行
            List<Integer> preRow = res.get(i - 1);
            //创建本行的第一个元素
            res.get(i).add(1);
            for (int j = 1; j < i; j++) {
                res.get(i).add(preRow.get(j - 1) + preRow.get(j));
            }
            //创建本行的最后一个元素
            res.get(i).add(1);
        }
        return res.get(rowIndex - 1);
    }

    //118杨辉三角
    public static List<List<Integer>> generate(int numRows) {
        int i = 1;
        List<List<Integer>> res = new LinkedList<>();
        while (i <= numRows) {
            List<Integer> temp = new LinkedList<>();
            for (int size = 1; size <= i; size++) {
                if (size == 1 || size == i) {
                    temp.add(1);
                } else {
                    temp.add(res.get(i - 2).get(size - 2) + res.get(i - 2).get(size - 1));
                }
            }
            res.add(temp);
            i++;
        }
        return res;
    }

    //66 数组加一
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = (digits[i] + 1) % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    //112
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        } else if (root.right == null && root.left == null) {
            if (root.val == sum)
                return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    //111
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null) {
            return minDepth(root.right) + 1;
        } else if (root.right == null) {
            return minDepth(root.left) + 1;
        } else {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }

    //110
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int dif = binTreeHeight(root.left) - binTreeHeight(root.right);
        if (dif <= 1 && dif >= -1 && isBalanced(root.left) && isBalanced(root.right)) {
            return true;
        }
        return false;
    }

    int binTreeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(binTreeHeight(root.left), binTreeHeight(root.right)) + 1;
    }

    //108
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, right);
        return root;
    }

    //107
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Stack<List<Integer>> res0 = new Stack<List<Integer>>();
        List<List<Integer>> res1 = new LinkedList<List<Integer>>();
        if (root == null) {
            return res1;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new LinkedList<Integer>();
            while (size > 0) {
                TreeNode u = queue.poll();
                if (u != null) {
                    temp.add(u.val);
                    if (u.left != null)
                        queue.offer(u.left);
                    if (u.right != null)
                        queue.offer(u.right);
                }
                size--;
            }
            res0.push(temp);
        }
        while (!res0.isEmpty()) {
            res1.add(res0.pop());
        }
        return res1;
    }

    //104 BFS
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                TreeNode temp = q.poll();
                if (temp.left != null) {
                    q.offer(temp.left);
                }
                if (temp.right != null) {
                    q.offer(temp.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    //104 递归法
//    public int maxDepth(TreeNode root) {
//        if(root == null)
//        {
//            return 0;
//        }
//        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
//    }

    //101 迭代法
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode u = q.poll();
            TreeNode v = q.poll();
            if (u == null && v == null) {
                continue;
            } else if (u == null ^ v == null) {
                return false;
            } else if (u.val != v.val) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);
            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }

    //101 递归法
//    public boolean isSymmetric(TreeNode root) {
//        return check(root,root);
//    }
//    public boolean check(TreeNode u, TreeNode v)
//    {
//        if(u == null && v == null)
//        {
//            return true;
//        }
//        else if(u == null || v == null)
//        {
//            return false;
//        }
//        else if(u.val != v.val)
//        {
//            return false;
//        }
//        return check(u.left,v.right) && check(u.right,v.left);
//    }


    //100 BFS
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }

        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode head1 = queue1.poll();
            TreeNode head2 = queue2.poll();

            TreeNode left1 = head1.left;
            TreeNode left2 = head2.left;
            TreeNode right1 = head1.right;
            TreeNode right2 = head2.right;
            if (head1.val != head2.val) {
                return false;
            } else if (left1 == null ^ left2 == null) {
                return false;
            } else if (right1 == null ^ right2 == null) {
                return false;
            } else {
                if (left1 != null) {
                    queue1.offer(left1);
                }
                if (left2 != null) {
                    queue2.offer(left2);
                }
                if (right1 != null) {
                    queue1.offer(right1);
                }
                if (right2 != null) {
                    queue2.offer(right2);
                }
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }

    //100 DFS
//public boolean isSameTree(TreeNode p, TreeNode q) {
//   if(p == null && q == null)
//   {
//       return true;
//   }
//   else if(p == null || q == null)
//   {
//       return false;
//   }
//   else if(p.val != q.val)
//   {
//       return false;
//   }
//
//   return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
//}

    //88
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p3 = m + n - 1;
        while (p2 >= 0) {
            if (p1 >= 0 && nums1[p1] >= nums2[p2]) {
                nums1[p3] = nums1[p1];
                p1--;
            } else {
                nums1[p3] = nums2[p2];
                p2--;
            }
            p3--;
        }
    }

    //83
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    //69
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int res = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if ((long) mid * mid <= x) {
                left = mid + 1;
                res = mid;//防止下一次循环被较大的mid冲刷
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

//    //67
//    public String addBinary(String a, String b) {
//
//    }

    //53
    public static int maxSubArray(int[] nums) {
        int[] maxSubIndex = new int[nums.length];
        maxSubIndex[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxSubIndex[i] = Math.max(maxSubIndex[i - 1] + nums[i], nums[i]);
        }
        //返回f数组最大值
        int result = nums[0];
        for (int j = 0; j < nums.length; j++) {
            result = Math.max(result, maxSubIndex[j]);
        }
        return result;
    }

    //35
    public static int searchInsert(int[] nums, int target) {
        // if(nums[nums.length - 1] < target)
        // {
        //     return nums.length;
        // }
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

