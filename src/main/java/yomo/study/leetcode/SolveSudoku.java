package yomo.study.leetcode;

public class SolveSudoku {
    public static void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        helper(board);
    }

    private static boolean helper(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (  board[i][j] == '.') {


                    for (char c = '1'; c <= '9'; c++) {

                        if (isValide(board, i, j, c)) {
                            board[i][j] = c;
                            if (helper(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }

                        }

                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValide(char[][] board, int col, int row, char c) {
        for (int k = 0; k < 9; k++) {
            if (board[row][k] != '.' && board[row][k] == c) {
                return false;
            }
            if (board[k][col] != '.' && board[k][col] == c) {
                return false;
            }
            if (board[3 * (row / 3) + k / 3][3 * (col / 3) + k % 3] != '.' && board[3 * (row / 3) + k / 3][3 * (col / 3) + k % 3] == c) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {


        char[][] chars = {};
        solveSudoku(chars);
        System.out.println(chars.toString());
    }

}
