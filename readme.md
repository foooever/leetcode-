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