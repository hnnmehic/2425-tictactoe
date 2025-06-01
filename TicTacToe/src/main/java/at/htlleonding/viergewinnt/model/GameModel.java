package at.htlleonding.viergewinnt.model;

public class GameModel {
    private CurrentPlayer [][] board;
    private CurrentPlayer currentPlayer;

    public static final int SIZE = 3;

    public GameModel() {
        this.board = new CurrentPlayer[SIZE][SIZE];
        this.currentPlayer = CurrentPlayer.X;
        initializeBoard();
    }

    public GameModel(GameModel other) {
        this.board = other.board;
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(other.board[i],0,this.board[i],0,SIZE);
        }
        this.currentPlayer = other.currentPlayer;
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = null;
            }
        }
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col] == null) {
            board[row][col] = currentPlayer;
            switchPlayer();
            return true;
        }
        return false;
    }

    private void switchPlayer() {
        if (currentPlayer == CurrentPlayer.X) {
            currentPlayer = CurrentPlayer.O;
        } else {
            currentPlayer = CurrentPlayer.X;
        }
    }



    public void resetGame() {
        initializeBoard();
        currentPlayer = CurrentPlayer.X;
    }

    public CurrentPlayer checkWinner() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] != null &&
                board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0];

            if (board[0][i] != null &&
                    board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i];

            }


        if (board[0][0] != null &&
                board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0];

        if (board[0][2] != null &&
                board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2];

        return null;

    }

    public CurrentPlayer[][] getBoard() {
        return board;
    }

    public CurrentPlayer getCurrentPlayer() {
        return currentPlayer;
    }
}
