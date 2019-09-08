package yomo.study.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * <p>Title:Parentheses
 * <p>Description:
 * <p>Modified History:
 * 遇到开始的括号就直接放入栈中，遇到结束的就直接弹出栈中的括号，如果不相等则返回false
 * 如果弹出来的为空  则第一个就是反括号，返回false结束
 *
 * @author HH
 * @date 2019/9/4 21:14
 */
public class Parentheses {


    public static boolean isValid(String s) {
        HashMap<Character, Character> mappings;
        mappings = new HashMap<Character, Character>();
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');
        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (mappings.containsKey(c)) {

                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }


    public static List<String> generateParenthesis(int n) {
        // 开始用分支 递归 回溯 必须要有一个递归函数
        List<String> res = new ArrayList<>();
        gen(0, 0, n, "", res);
        return res;
    }

    private static void  gen(int left, int right, int n, String cur, List<String> res) {
        if (left == n && right == n) {
            res.add(cur);
            return ;
        }
        if (left < n) {
            gen(left + 1, right, n, cur + "(", res);
        }
        if (left > right && right < n) {
            gen(left, right + 1, n, cur + ")", res);

        }

    }


    public static void main(String[] args) {
        System.out.println(isValid("{{{}}}"));
        System.out.println(generateParenthesis(5));

    }
}