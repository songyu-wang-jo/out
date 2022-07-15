package com.songyuwong.bits;

import org.junit.Test;

/**
 * <p>
 * 位运算测试
 * </p>
 *
 * @author SongYu
 * @since 2022/7/14
 */
public class BitsComputeTest {

    /**
     * （1）任意一个变量与自身异或运算结果为0，例如A^A=0;
     * （2）任意一个变量与0异或运算结果为变量本身，例如A^0=A;
     * （3）异或运算具有结合律：(A ^ B) ^ C=A ^ (B ^ C);
     * （4）异或运算具有交换律：A ^ B = B ^ A;
     */
    private void testOr() {
        int a = 3;
        int b = 4;
        a = a ^ b; // a = (a ^ b)
        b = b ^ a; // b = (b ^ a) = b ^ (a ^ b) = b ^ b ^ a = a
        a = a ^ b; // a = a ^ b = a ^ (b ^ a) = a ^ a ^ b = b
        System.out.println(a);
        System.out.println(b);
    }


    @Test
    public void test() {
        // 1
        testOr();

        // 2
        int wH = "wongsongyu".hashCode();
        printEr(wH);
        printEr(8);
        int com = wH ^ (wH >> 16);
        printEr(com);
        int num = 8 & com;
        printEr(num);
        System.out.println(num);
    }

    private void printEr(int num) {
        System.out.print("B => ");
        // int 4 字节 一个字节 8 位 一共三十二位 从最地位开始打印
        for (int i = 31; i >= 0; i--) {
            // & 1 清除其他位上的数
            System.out.print((num & 1 << i) == 0 ? "0" : "1");
        }
        System.out.print("\n");
    }

}
