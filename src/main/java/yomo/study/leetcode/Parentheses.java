package yomo.study.leetcode;

import java.util.Stack;

/**
 * <p>Title:Parentheses
 * <p>Description:
 * <p>Modified History:
 *
 * @author HH
 * @date 2019/9/4 21:14
 */
public class Parentheses {
    public static  boolean isValid(String s) {
        Stack<Character> objects = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);

            Character pop = objects.pop();
            if (!pop.equals(c)) {
                objects.push(c);
            }
        }
        return objects.size() > 0;
    }


    public static void main(String[] args) {
        isValid("{}{}{}}{()()(");
    }
}