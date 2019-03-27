import java.util.*;
import leetcode.*;
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    //LeetCode job
    //91 解码方式
    public static int numDecodings(String s) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        int[] dp = new int[len + 1];
        if (ch[0] == '0') return 0;
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < len; i++) {
            int la = ch[i] - '0';
            int sla = ch[i - 1] - '0';
            if (i == 1) {
                if (la == 0) {
                    if (sla < 3) dp[2] = 1;
                    else return 0;
                } else {
                    if (sla * 10 + la <= 26) dp[2] = 2;
                    else dp[2] = 1;
                }
            } else {
                if (la == 0) {
                    if (sla == 0) return 0;
                    else {
                        if (sla * 10 + la <= 26) {
                            dp[i + 1] = dp[i - 1];
                        } else return 0;
                    }
                } else {
                    dp[i + 1] = dp[i];
                    if (sla * 10 + la <= 26 && sla != 0) {
                        dp[i + 1] += dp[i - 1];
                    }
                }
            }
        }
        return dp[len];
    }

    //321 摆动序列
    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int i = 1;
        int j = 2;
        for (; i < nums.length; ) {
            int temp = nums[i];
            int k = j;
            for (; k < nums.length; k++) {
                if (nums[k] > temp) {
                    j = k;
                    nums[i] = nums[j];
                    nums[j] = temp;
                    break;
                }
            }
            i += 2;
            System.out.println(Arrays.toString(nums));
        }
    }

    //115 不同子序列
    public static int numDistinct(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        int count = 0;
        int[] flag = new int[len2];
        int i = 0;
        int j = 0;
        while (i <= len1 && j <= len2) {
            if (j == len2 && sc[i - 1] == tc[j - 1]) {
                count++;
                i = flag[j - 1];
                j = j - 1;
                for (int k = i - 1; k < len2; k++) {
                    flag[k] = 0;
                }
            } else {
                if (sc[i] == tc[j]) {
                    flag[j] = i;
                    i++;
                } else {
                    i++;
                }
            }
        }
        return count;
    }

    //1
    public static int[] twoSum(int[] nums, int target) {
        int[] re = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] + nums[j] == target) {
                    re[0] = i;
                    re[1] = j;
                    break;
                }
        }
        return re;
    }

    //15 three sum
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> li = new ArrayList<List<Integer>>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
            if (nums[i] == 0) count++;
        }
        for (int i = 2; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                int te = (0 - nums[i]) - nums[j];
                if (map.containsKey(te) && map.get(te) != j && map.get(te) != i) {
                    int[] arr = {nums[i], nums[j], te};
                    Arrays.sort(arr);
                    int min = Math.min(nums[j], Math.min(te, nums[i]));
                    int max = Math.max(nums[j], Math.max(te, nums[i]));
                    int mid = arr[1];
                    //System.out.println("our:" + min + " " + mid + " " +max);
                    List<Integer> ar = Arrays.asList(min, mid, max);
                    //System.out.println(ar);
                    if (!li.contains(ar)) {
                        li.add(ar);
                    }
                }
            }
            if (count > 2) {
                List<Integer> ar = Arrays.asList(0, 0, 0);
                li.add(ar);
            }
        }
        return li;
    }

    //9
    public static boolean isPalindrome(int x) {
        /*String s = "";
        boolean flag = true;
        while(x > 0)
        {
            s += x % 10;
            x /= 10;
        }
        int lo = 0;
        int hi = s.length() - 1;
        while(lo < hi)
        {
            if(s.charAt(lo) != s.charAt(hi))
            {
                flag = false;
                break;
            }
        }
        return flag;*/
        String reverseNumber = new StringBuilder(String.valueOf(x)).reverse().toString();
        String s = String.valueOf(x);
        return reverseNumber.equals(s);
    }

    //4 mid num
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int[] bucket = new int[Math.max(nums1[nums1.length - 1], nums2[nums2.length - 1]) + 1];
        for (int i = 0; i < nums1.length; i++) {
            bucket[nums1[i]]++;
        }
        for (int j = 0; j < nums2.length; j++) {
            bucket[nums2[j]]++;
        }
        if ((nums1.length + nums2.length) % 2 == 0) {
            int mid = (nums1.length + nums2.length) / 2 + 1;
            int count = 0;
            int i = 0;
            for (; i < bucket.length; i++) {
                if (count < mid - 1) {
                    count += bucket[i];
                } else
                    break;
            }
            if (bucket[i - 1] > 1) return (double) (i - 1);
            else {
                int j = i;
                while (bucket[j] == 0) {
                    j++;
                }
                return (double) (i - 1 + j) / 2;
            }
        } else {
            int mid = (nums1.length + nums2.length) / 2 + 1;
            int count = 0;
            int i = 0;
            for (; i < bucket.length; i++) {
                if (count < mid) {
                    count += bucket[i];
                } else
                    break;
            }
            return (double) (i - 1);
        }
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> li = new ArrayList<Integer>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            if (nums1[i] > nums2[j]) {
                li.add(nums1[i++]);
            } else {
                li.add(nums2[j++]);
            }
            System.out.println(1);
        }
        while (i < len1) {
            li.add(nums1[i++]);
            System.out.println(1);
        }
        while (j < len2) {
            li.add(nums2[j++]);
        }
        System.out.println(11);
        Collections.sort(li);
        if ((len1 + len2) % 2 == 1) {
            return li.get((len1 + len2) / 2);
        } else {
            return (li.get((len1 + len2) / 2) + li.get((len1 + len2) / 2 - 1)) / 2;
        }
    }

    //72 编辑距离--动规打表
    public static int minDistance(String word1, String word2) {
        char[] sr1 = word1.toCharArray();
        char[] sr2 = word2.toCharArray();
        int l1 = sr1.length;
        int l2 = sr2.length;
        int[][] dp = new int[l1 + 1][l2 + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= l1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= l2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (sr1[i - 1] == sr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[l1][l2];
    }

    //2 two sum
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode re = new ListNode(0);
        int count = 0;
        while (l1.next != null && l2.next != null) {
            int sum = l1.val + l2.val + count;
            int cc = sum % 10;
            count = sum / 10;
            re.val = cc;
            re = re.next;
        }
        while (l1.next != null) {
            int sum = l1.val + count;
            int cc = sum % 10;
            count = sum / 10;
            re.val = cc;
            re = re.next;
        }
        while (l2.next != null) {
            int sum = l2.val + count;
            int cc = sum % 10;
            count = sum / 10;
            re.val = cc;
            re = re.next;
        }
        return re;
    }

    //3 最长无重复字串
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0;
        int i = 0;
        int j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));//连续
            }
        }
        return ans;
    }

    //7 整数反转
    public static int reverse(int x) {
        int re = 0;
        while(x != 0)
        {
            int aa = x % 10;
            x /= 10;
            if (re > Integer.MAX_VALUE / 10 || (re == Integer.MAX_VALUE / 10 && aa > 7))
            {
                return 0;
            }
            if (re < Integer.MIN_VALUE / 10 || (re == Integer.MIN_VALUE / 10 && aa < -8))
            {
                return 0;
            }
            re = re * 10 + aa;
        }
        return re;
    }

    //6 Z形变换
    public static String convert(String s, int numRows) {
        int le = s.length();
        String re;
        int count = 0;
        while(count < le)
        {
            int i = 0;
            for(; i < le; i++)
            {

            }
        }
        return "";
    }

    //8 z字符串转换整数
    public static int myAtoi(String str) {
        int i = 0;
        String re = "";
        if (str.length() == 0) {return 0;}
        while(str.charAt(i) == ' ')
        {
            i++;
        }
        System.out.println(i);
        char cc = str.charAt(i);
        System.out.println(cc + "你好");
        if (cc == '+' || cc == '-' || cc >= 48 && cc <= 57)
        {
            i++;
            for(; i < str.length(); i++)
            {
                char ch = str.charAt(i);
                if (ch >= 48 && ch <= 57)
                {
                    re += ch;
                }
            }
            System.out.println(re);
            if (cc == '-')
            {
                if (re.length() == 0) {return 0;}
                else {
                    if (-Double.parseDouble(re) < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    } else {
                        return (int) -Double.parseDouble(re);
                    }
                }
            }
            else
            {
                if (cc != '+')
                {
                    re = cc + re;
                    //System.out.println(re + "你好");
                }
                if (re.length() == 0) {return 0;}
                else {
                    if (Double.parseDouble(re) > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    } else {
                        return Integer.parseInt(re);
                    }
                }
            }
        }
        return 0;
    }

    //11 盛水最多的容器 官方题解双指针
    public static int maxArea(int[] height) {
        int lo = 0;
        int hi = height.length - 1;
        int maxarea = Math.min(height[lo], height[hi]) * (hi - lo);
        while(lo < hi)
        {
            if (height[lo] > height[hi])
            {
                hi--;
            }
            else
            {
                lo++;
            }
            int ar = Math.min(height[lo], height[hi]) * (hi - lo);
            maxarea = Math.max(maxarea, ar);
        }
        return maxarea;
    }

    //12 整数转罗马数字
    public static String intToRoman(int num) {
        char[] ch = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] nn = {1, 5, 10, 50, 100, 500, 1000};
        String str = "";
        int tt = num / 1000;
        while(tt-- > 0)
        {
            str += 'M';
        }
        num %= 1000;
        int i = nn.length - 2;
        while(i >= 0 && num > 0)
        {
            if (num / 100 == 9 || num / 100 == 4)
            {
                str += num / 100 == 9 ? "CM" : "CD";
                num -= num / 100 == 9 ? 900 : 400;
                i -= 2;
            }
            else if (num / 10 == 9 || num / 10 == 4)
            {
                str += num / 10 == 9 ? "XC" : "XL";
                num -= num / 10 == 9 ? 90 : 40;
                i -= 2;
            }
            else if (num == 9 || num == 4)
            {
                str += num == 9 ? "IX" : "IV";
                num -= num == 9 ? 9 : 4;
            }
            else {
                int c_n = num / nn[i];
                int r_n = num % nn[i];
                while (c_n-- > 0) {
                    str += ch[i];
                }
                num = r_n;
                i--;
            }
        }
        return str;
    }

    //13 罗马数字转整数
    public static int romanToInt(String s) {
        HashMap<Character, Integer> hmap = new HashMap<>();
        char[] ch = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] nu = {1, 5, 10, 50, 100, 500, 1000};
        for(int i = 0; i < ch.length; i++)
        {
            hmap.put(ch[i], nu[i]);
        }
        int sum = 0;
        for(int i = 0; i <s.length() - 1; i++)
        {
            if (hmap.get(s.charAt(i)) < hmap.get(s.charAt(i + 1)))
            {
                sum -= hmap.get(s.charAt(i));
            }
            else
            {
                sum += hmap.get(s.charAt(i));
            }
        }
        sum += hmap.get(s.charAt(s.length() - 1));
        return sum;
    }

    //14 最长公共前缀
    public static String longestCommonPrefix(String[] strs) {
        String str = "";
        int count = 0;
        int i = 0;
        while(count == 0)
        {
            if (strs.length == 0) break;
            int num = 0;
            char ch = ' ';
            for(int j = 0; j <strs.length; j++)
            {
                if (i == strs[j].length())
                {
                    count++;
                    break;
                }
                ch = strs[0].charAt(i);
                if (strs[j].charAt(i) == ch)
                {
                    num++;
                }
            }
            if (num == strs.length)
            {
                str += ch;
            }
            else
            {
                break;
            }
            i++;
        }
        return str;
    }

    //85 最大矩形
    public static int maximalRectangle(char[][] matrix) {
        int raw = matrix.length;
        int col = matrix[0].length;
        if (raw == 0 || col == 0) return 0;
        int max = 0;
        for(int k = 0; k < raw; k++) {
            int[] dp = new int[col + 1];
            dp[0] = 0;
            for(int t = 0; t < raw - k; t++)
            {
            for (int i = 1; i <= col; i++) {

                    int sum = 0;
                    for(int j = 0; j <= t; j++)
                    {
                        sum += matrix[k + j][i - 1] - '0';
                    }
                    if (sum == t + 1) {
                        dp[i] = dp[i - 1] + sum;
                    }
                    else
                    {
                        dp[i] = 0;
                    }
                    max = Math.max(max, dp[i]);
                }
            }
        }

        return max;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}
