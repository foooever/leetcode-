Leetcode刷题笔记
139 单词拆分
判断非空字符串能否被拆分成给定字典中的单词组成
打表 对每一个当前的字符串位置i进行切分，判断是否可以组成前后两个部分都能由单词构成
optimization
//1 不用一直分到第一个元素，只要分到单词表中最长元素的位置
//2 可以只考察dp[i]为1的位置，使用之前的结果

974 Subarray Sums Divisible by K
    solution perfect
    input:数组A，K
    output:可被整除序列数
    1.将数值依次累加至数组中
    2.将数组元素取余K对相同余数的计数
    3.具有相同余数的相减即能被K整除

978 Longest Turbulent Subarray
	input:数组
	output:最长的序列满足constrain
	solution1:打表dp 将每一位的最长值进行计算，所得结果即为所求
	solution2:INTEGER.compare()对相邻的元素比较结果分别记为1，0，-1
	只有满足两个记号相乘为-1才为满足题目的要求
	profect idea 利用标号-1，0，1进行标记

3 Longest Substring Without Repeating Characters
    input:字符串
    output:最大无重复字串长度
    solution1:表记录ASCII码的值首次出现的角标，使用一个标记位时刻更新重复的位置
    solution2:sliding window 将字符存入set中当出现重复字符的瞬间，从标记位开始删除set中的元素直至不再重复

4 Median of Two Sorted Arrays
    input:两个有序数组
    output:输出中位数
    solution:得到总长度的中点，从A,B取数，直至到达中点，每次记录两个值为了even和odd情况的中位数 Complexity O(m+n)
    perfect solution:将A,B分为left & right部分，然后滑动角标使得A_left + B_left的left_part的长度等于right_part（+1）；
                     并且满足一定的条件（B[j-1]<A[i] && A[i-1] <= B[j]）  