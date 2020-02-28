package com.birdea.testcode.solution;

import com.birdea.testcode.util.L;

public class Ch1Solution {
    public static void main(String[] args) {
        char[] c = {'h','e','l','l','o'};
        char[] d = {'H','a','n','n','a','h'};
        char[] s = {'A',' ','m','a','n',',',' ','a',' ','p','l','a','n',',',' ','a',' ','c','a','n','a','l',':',' ','P','a','n','a','m','a'};

        new Ch1Solution().reverseString(s);
    }

    public void reverseString(char[] s) {
        //helper(0, s);
        /*helper(s, 0, s.length-1);
        L.msg("reverseString:" + s);
        for (char i : s) {
            L.msg("reversed:" + i);
        }*/
        int left = 0, right = s.length-1;
        while (left < right) {
            char tmp  = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }
    public void helper(int index, char[] s) {
        if (index < 0 || index > s.length / 2) {
            return;
        }
        char srcValue = s[index];
        int dstIndex = s.length - 1 - index;
        L.msg("srcValue:"+srcValue+", dstIndex:"+dstIndex+", dstValue:"+s[dstIndex]);
        s[index] = s[dstIndex];
        s[dstIndex] = srcValue;
        //
        index = index + 1;
        helper(index, s);
    }
    public void helper(char[] s, int left, int right) {
        if (left >= right) {
            return;
        }
        char tmp  = s[left];
        s[left++] = s[right];
        s[right--] = tmp;
        helper (s, left, right);
    }

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i=0; i<s.length(); i++) {
            if (i < k) {
                char tmp = chars[i];
                chars[i] = chars[k-i];
                chars[k-i] = tmp;
            }
            if (i >= 2*k) {

            }
        }
        return null;
    }
}
