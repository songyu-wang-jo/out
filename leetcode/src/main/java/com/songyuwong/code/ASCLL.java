package com.songyuwong.code;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/25
 */
public class ASCLL {

    public static void main(String[] args) {
        String s = "wangsongyu";
        char[] chars = s.toCharArray();
        int pingyin = 0;
        for (char aChar : chars) {
            pingyin += (int) aChar;
        }
        String ss = "王送雨";
        chars = ss.toCharArray();
        int hanzi = 0;
        for (char aChar : chars) {
            hanzi += (int) aChar;
        }
        System.out.println(pingyin);
        System.out.println(hanzi);
        System.out.println((pingyin + hanzi) ^ 19980527);
    }

}
