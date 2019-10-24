public class Driver {
    public static void main(String[] args) {
        int[] board1 = {2, 8, 3,
                        1, 6, 4,
                        7, 0, 5};

        int[] board2 = {7, 6, 5,
                        8, 0, 4,
                        1, 2, 3};
        BFS.search(board1);
        //BFS.search(board2);
        //DFS.search(board1);
        //DFS.search(board2);
    }
}
