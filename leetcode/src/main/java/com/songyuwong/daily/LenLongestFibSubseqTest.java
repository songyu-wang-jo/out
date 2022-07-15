package com.songyuwong.daily;

import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

/**
 * <p>
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 * <p>
 * n >= 3
 * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 * <p>
 * （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 * <p>
 * 示例 1：
 * <p>
 * 输入: arr = [1,2,3,4,5,6,7,8]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
 * <p>
 * 示例 2：
 * <p>
 * 输入: arr = [1,3,7,11,12,14,18]
 * <p>
 * 输出: 3
 * <p>
 * 解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
 * <p>
 * 提示：
 * <p>
 * 3 <= arr.length <= 1000
 * <p>
 * 1 <= arr[i] < arr[i + 1] <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence">https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 *
 * @author SongYu
 * @since 2022/7/9
 */
public class LenLongestFibSubseqTest {

    /**
     * <p>
     * 斐波那契数列的连续子列也是斐波那契数列
     * <p>
     * 对于一个 int 数组求最长的斐波那契数列的长度（约束条件只有数组中的数的顺序不可发生变化）
     * <p>
     * 遍历所有一个方向上的两个数的组合并求解该组合在数组相反方向上有没有数能构成最基本的斐波那契数列
     * <p>
     * 将遍历并计算得到的结果保存起来
     * <p>
     * 然后进行整合将最短的数列搭配拼接求得最长的斐波那契数列长度
     *
     * @param arr 原数组
     * @return 最长的斐波那契数列的长度
     */
    public int lenLongestFibSubseq(int[] arr) {
        // 对无效的输入过滤
        if (Objects.isNull(arr) || arr.length < 3) {
            return 0;
        } else {
            HashMap<Integer, Integer> finder = new HashMap<>();
            // 保留每一种组合的数列状态的数组，每组状态初始化时默认为长度 0
            int[][] dp = new int[arr.length][arr.length];
            int ans = 0;
            // 遍历数组中的每一个数
            finder.put(arr[0], 0);
            for (int i = 1; i < arr.length; i++) {
                finder.put(arr[i], i); // 保存相反方向上的数据
                // 遍历这个数按顺序往后搭配组成的两个数进行组合计算, 因为给出的数列时递增的 又满足 斐波那契 则 arr[i] * 2 > arr[j]
                for (int j = i + 1; j < arr.length && arr[i] * 2 > arr[j]; j++) {
                    // 计算该搭配作为最后两位数时需要的第一位数在数组中是否存在
                    // 此处需要判断数组中是否存在对应的值用 hash 的方式进行判断,获取对应的符合要求的下标
                    Integer matchedIndex = finder.getOrDefault(arr[j] - arr[i], -1);
                    // 匹配到了值并且方向时反向的
                    if (matchedIndex > -1 && matchedIndex < i) {
                        // 存在新的符合要求的最短队列，从状态数组中取出前两个位置上的状态加 1，大于三时说明前两个索引上的数已经有状态，
                        // 设置当前两位上的状态为前两位加一，否则设置符合条件时的初始值 3 并更新答案
                        dp[i][j] = Math.max(dp[matchedIndex][i] + 1, 3);
                        ans = Math.max(ans, dp[i][j]);
                    }
                }
            }
            return ans;
        }
    }

    @Test
    public void test() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(lenLongestFibSubseq(arr));
    }

}
