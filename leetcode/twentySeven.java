package leetcode;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
public class twentySeven {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] n1 = {3};
        int[] n2 = {1, 2};
        System.out.println(findMedianSortedArrays(n1, n2));
    }
    //139单词拆分 判断非空字符串能否被拆分成给定字典中的单词组成
    public static boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        for(int i = 1; i <= len; i++)
        {
            for(int k = i; k >= 1; k--)
            {
                String sub = s.substring(k-1, i);
                if (wordDict.contains(sub) && dp[k - 1] == 1)
                {
                    dp[i] = 1;
                    break;
                }
                dp[i] = 0;
            }
        }
        return dp[len] == 1 ? true : false;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head  = new ListNode(0);
        ListNode re = head;
        int flag = 0;
        while(l1 != null && l2 != null)
        {
            int sum = l1.val + l2.val + flag;
            head.val = sum % 10;
            flag = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
            ListNode p = new ListNode(0);
            head.next = p;
        }
        while(l1 != null)
        {
            int sum = l1.val + flag;
            head.val = sum % 10;
            flag = sum / 10;
            l1 = l1.next;
            ListNode p = new ListNode(0);
            head.next = p;
        }
        while(l2 != null)
        {
            int sum = l2.val + flag;
            head.val = sum % 10;
            flag = sum / 10;
            l2 = l2.next;
            ListNode p = new ListNode(0);
            head.next = p;
        }
        return re;
    }

    //974 Subarray Sums Divisible by K
    //solution perfect
    public static int subarraysDivByK(int[] A, int K) {
        int len = A.length;
        int[] dp = new int[len + 1];
        int[] count = new int[K];
        dp[0] = 0;
        int re = 0;
        for(int i = 1; i <= len; i++)
        {
            dp[i] = dp[i - 1] + A[i - 1];
        }
        for(int i = 0; i < dp.length; i++)
        {
            count[(dp[i] % K + K) % K]++;
        }
        for(int i = 0; i < count.length; i++)
        {
            re += (count[i] * (count[i] - 1)) / 2;
        }
        return re;
    }

    //978 Longest Turbulent Subarray
    public static int maxTurbulenceSize(int[] A) {
        int le = A.length;
        int[] dp = new int[le];
        int max = 0;
        dp[0] = 1;
        for(int i = 1; i < le; ++i)
        {
            if (i % 2 != 0)
            {
                if (A[i - 1] < A[i])
                {
                    dp[i] = dp[i - 1] + 1;
                }
                else
                {
                    dp[i] = 1;
                }
            }
            else
            {
                if (A[i - 1] > A[i])
                {
                    dp[i] = dp[i - 1] + 1;
                }
                else
                {
                    dp[i] = 1;
                }
            }
            max = Math.max(max, dp[i]);
        }
        for(int i = 1; i < le; ++i)
        {
            if (i % 2 != 0)
            {
                if (A[i - 1] > A[i])
                {
                    dp[i] = dp[i - 1] + 1;
                }
                else
                {
                    dp[i] = 1;
                }
            }
            else
            {
                if (A[i - 1] < A[i])
                {
                    dp[i] = dp[i - 1] + 1;
                }
                else
                {
                    dp[i] = 1;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    //3 Longest Substring Without Repeating Characters
    public static int lengthOfLongestSubstring(String s) {
        int le = s.length();
        int[] flag = new int[128];
        int max = 0;
        for(int i = 0, j = 0; i < le; i++)
        {
            j = Math.max(flag[s.charAt(i)], j);
            max = Math.max(max, i - j + 1);
            flag[s.charAt(i)] = i + 1;
        }
        return max;
    }

    //4 Median of Two Sorted Arrays
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int len = (l1 + l2)/2 + 1;
        int i = 0, j = 0;
        int mid = 0;
        int b_mid = 0;
        for(int k = 0; k < len; k++)
        {
            if (i < l1 && j < l2)
            {
                if (nums1[i] > nums2[j])
                {
                    b_mid = mid;
                    mid = nums2[j++];
                }
                else
                {
                    b_mid = mid;
                    mid = nums1[i++];
                }
            }
            else if (i < l1)
            {
                b_mid = mid;
                mid = nums1[i++];
            }
            else if (j < l2)
            {
                b_mid = mid;
                mid = nums2[j++];
            }
            System.out.println(b_mid+" "+mid+" "+i+" "+j);
        }
        if ((l1 + l2) % 2 == 1)
        {
            return mid;
        }
        else
        {
            return (b_mid + mid) * 1.0 / 2;
        }
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x;}
}