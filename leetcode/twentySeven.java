package leetcode;
import java.util.*;
public class twentySeven {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        if (wordBreak(s, wordDict))
        {
            System.out.println("111");
        }
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
    //
}
