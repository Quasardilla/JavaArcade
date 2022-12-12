package Intro.ALGore_Rhythm_Challenges;

import java.util.Arrays;

public class Algorithms {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(parseXY("(30,20)")));
        System.out.println(Arrays.toString(parseXY("(30, 20)")));

        System.out.println(reverseString("reverse"));
    }

    /** Parses an ordered pair of a value
     * 
     * @param str
     *      The ordered pair with format (x, y) or (x,y)
     * 
     * @return Returns an array of both values x and y,
     *         x at index of 0 and y at index of 1
     * 
     */

    public static int[] parseXY(String str) {
        return str.contains(" ") ? new int[] {Integer.parseInt(str.substring(1, str.indexOf(','))), Integer.parseInt(str.substring(str.indexOf(',') + 2, str.length() - 1))} :  new int[] {Integer.parseInt(str.substring(1, str.indexOf(','))), Integer.parseInt(str.substring(str.indexOf(',') + 1, str.length() - 1))};
    }

    /** Reverses the characters of a string
     * 
     * @param str
     *      The input string that is to be reversed
     * 
     * @return
     *      The reversed string
     * 
     * @since
     *      18
     */
    public static String reverseString(String str) {
        String str2 = "";
        for(int i = str.length() - 1; i >= 0; i--)
            str2 += str.charAt(i);

        return str2;
    }
}
