package at.htlleonding.viergewinnt.model;

public class GameModel {
    private char [][] board;
    private char currentPlayer;

    public static final int SIZE = 3;

    public GameModel() {
        this.board = new char[SIZE][SIZE];
        this.currentPlayer = 'X';
        initializeBoard();
    }

    public GameModel(GameModel other) {
        this.board = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(other.board[i],0,this.board[i],0,SIZE);
        }
        this.currentPlayer = other.currentPlayer;
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            switchPlayer();
            return true;
        }
        return false;
    }

    private void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(char currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void resetGame() {
        initializeBoard();
        currentPlayer = 'X';
    }

    public char checkWinner() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] != ' ' &&
                board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0];

            if (board[0][i] != ' ' &&
                    board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i];

            }


        if (board[0][0] != ' ' &&
                board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0];

        if (board[0][2] != ' ' &&
                board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2];

        return ' ';

    }
}
