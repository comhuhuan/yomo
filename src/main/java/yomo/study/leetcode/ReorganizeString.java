package yomo.study.leetcode;

/**
 * <p>Title:ReorganizeString
 * <p>Description:重构字符串
 * <p>Modified History:
 * 1.超过一半的字符数不能排序
 * 2.
 *
 * @author HH
 * @date 2019/9/9 9:07
 */
public class ReorganizeString {

    public String reorganizeString(String S) {
        int length = S.length();
        if (length == 1) {
            return S;
        }
        int max_length = 0;
        int[] arr = new int[26];

        char[] chars = S.toCharArray();
        // 这个方法统计次数为O（N） 排序的算法为O（n.logn）
        for (char c : chars) {
            // 先将字符串中的每个字符按照26个字母的排序进行放置形成数组，出现次数作为数组的元素。
            if (max_length < ++arr[c - 'a']) {
                max_length = arr[c - 'a'];
            }
        }
        if (max_length > (length + 1) / 2) {
            return " ";
        }
        char[] res = new char[S.length()];
        int even = 0;
        int odd = 1;
        for (int i = 0; i < 26; i++) {
            while (arr[i] > 0 && arr[i] < (length / 2 + 1) && odd < length) {
                res[odd] = (char) (i + 'a');
                arr[i]--;
                odd += 2;
            }
            while (arr[i] > 0) {
                res[even] = (char) (i + 'a');
                arr[i]--;
                even += 2;
            }
        }

        return new String(res);
    }
}