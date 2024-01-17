package server.engine;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import server.model.Player;

import static server.io.ConsolePrinter.printWinInfo;


@Getter
@Setter
public class Match {
    @NonNull
    private Player player1, player2;
    @NonNull
    private int id;
    private Player currentPlayer;
    private boolean isDraw = false;


    private char[][] board = new char[3][3];
    private boolean ready;

    private static final char EMPTY_MARK = '.';
    private static final char PLAYER1_MARK = 'X';
    private static final char PLAYER2_MARK = 'O';

    public Match(@NonNull Player player1, @NonNull Player player2, @NonNull int id) {
        this.player1 = player1;
        this.player2 = player2;
        this.id = id;
        initializeMatch();
    }

    public void getWinner() {
        if ((board[0][0] == PLAYER1_MARK && board[0][1] == PLAYER1_MARK && board[0][2] == PLAYER1_MARK) || (board[1][0] == PLAYER1_MARK && board[1][1] == PLAYER1_MARK && board[1][2] == PLAYER1_MARK) || (board[2][0] == PLAYER1_MARK && board[2][1] == PLAYER1_MARK && board[2][2] == PLAYER1_MARK) || (board[0][0] == PLAYER1_MARK && board[1][1] == PLAYER1_MARK && board[2][2] == PLAYER1_MARK) || (board[2][0] == PLAYER1_MARK && board[1][1] == PLAYER1_MARK && board[0][2] == PLAYER1_MARK)) {
            player1.addWin();
            player1.setWinner(true);

        } else if ((board[0][0] == PLAYER2_MARK && board[0][1] == PLAYER2_MARK && board[0][2] == PLAYER2_MARK) || (board[1][0] == PLAYER2_MARK && board[1][1] == PLAYER2_MARK && board[1][2] == PLAYER2_MARK) || (board[2][0] == PLAYER2_MARK && board[2][1] == PLAYER2_MARK && board[2][2] == PLAYER2_MARK) || (board[0][0] == PLAYER2_MARK && board[1][1] == PLAYER2_MARK && board[2][2] == PLAYER2_MARK) || (board[2][0] == PLAYER2_MARK && board[1][1] == PLAYER2_MARK && board[0][2] == PLAYER2_MARK)) {
            player2.addWin();
            player2.setWinner(true);

        } else if (true) {

            isDraw = true;
        }

    }

    private void move(int row, int column) {
        if (currentPlayer == player1) {
            board[row][column] = PLAYER1_MARK;
            currentPlayer = player2;
        } else {
            board[row][column] = PLAYER2_MARK;
            currentPlayer = player1;
        }
    }

    public void makeMove(int row, int column, int playerId) {
        setCurrentPlayer(playerId);
        if (isValidMove(row, column)) {
            move(row, column);
        }
        checkWinner();
        if (player1.isWinner() == false || player2.isWinner() == false || isDraw == false) {
            getCurrentGameState();
            notifyNextPlayer();
        }
    }

    private void notifyNextPlayer() {
//
//        String nextPlayer = (currentPlayer == 'X') ? "Player1" : "Player2";
//        players.get(nextPlayer).notify("Your turn.");
    }

    private Match getCurrentGameState() {
        return this;
    }

    private void checkWinner() {
        getWinner();
        if (player1.isWinner()) {
            printWinInfo(player1, player2);
        } else if (player2.isWinner()) {
            printWinInfo(player2, player1);
        }
    }

    private boolean isValidMove(int row, int column) {
        if (board[row][column] == EMPTY_MARK) return true;
        else return false;
    }

    private void setCurrentPlayer(int playerId) {
        if (player1.getId() == playerId) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
    }

    private void initializeMatch() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = EMPTY_MARK;
            }
        }
        player1.setWinner(false);
        player2.setWinner(false);
    }

}






