package leetcode;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
public class twentySeven {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] A = {5};
        System.out.println(subarraysDivByK(A, 9));
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
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x;}
}