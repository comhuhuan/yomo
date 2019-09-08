package yomo.study.leetcode;



public class Trie {
    private TreeNode root;

    public Trie() {
        // 构造根节点
        this.root = new TreeNode();
        this.root.var = ' ';

    }


    /**
     * 1.把根节点变量复制过来
     * 2.遍历单词
     * 3.看看当前级别这个之母是否已经存在如果不存在 新建并初始化
     * 4.如果存在  遍历下一个节点并把当前存在的节点设置为当前节点
     * 5.最后完成的时候把是否单词的flag置为true
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null) {
            return;
        }
        TreeNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) {
                ws.children[c - 'a'] = new TreeNode(c);
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    /**
     *
     * @param word
     * @return
     */
    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        TreeNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) {
                return false;
            }
            ws = ws.children[c - 'a'];

        }
        return ws.isWord;
    }

    /**
     *
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        TreeNode ws = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (ws.children[c - 'a'] == null) {
                return false;
            }
            ws = ws.children[c - 'a'];

        }
        return true;
    }


    class TreeNode {
        public char var;
        public boolean isWord;
        public TreeNode[] children = new TreeNode[26];

        public TreeNode(char var) {
            TreeNode treeNode = new TreeNode();
            treeNode.var = var;
        }

        public TreeNode() {
        }
    }

    public static void main(String[] args) {

        Trie trie = new Trie();

        trie.insert("apple");
        trie.search("apple");   // 返回 true
        trie.search("app");     // 返回 false
        trie.startsWith("app"); // 返回 true
        trie.insert("app");
        trie.search("app");     // 返回 true
    }


}
