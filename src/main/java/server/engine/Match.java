package server.engine;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import server.model.Player;

@RequiredArgsConstructor
@Getter
@Setter
public class Match {
    @NonNull
    private Player player1, player2;
    private Player currentPlayer;

    private char[][] board = new char[3][3];
    private boolean ready;

    private static final char EMPTY_MARK = '.';
    private static final char PLAYER1_MARK = 'X';
    private static final char PLAYER2_MARK = 'O';

    public Player getWinner() {
        if ((board[0][0] == PLAYER1_MARK && board[0][1] == PLAYER1_MARK && board[0][2] == PLAYER1_MARK) ||
                (board[1][0] == PLAYER1_MARK && board[1][1] == PLAYER1_MARK && board[1][2] == PLAYER1_MARK) ||
                (board[2][0] == PLAYER1_MARK && board[2][1] == PLAYER1_MARK && board[2][2] == PLAYER1_MARK) ||
                (board[0][0] == PLAYER1_MARK && board[1][1] == PLAYER1_MARK && board[2][2] == PLAYER1_MARK) ||
                (board[2][0] == PLAYER1_MARK && board[1][1] == PLAYER1_MARK && board[0][2] == PLAYER1_MARK))
            return player1;

        else if ((board[0][0] == PLAYER2_MARK && board[0][1] == PLAYER2_MARK && board[0][2] == PLAYER2_MARK) ||
                (board[1][0] == PLAYER2_MARK && board[1][1] == PLAYER2_MARK && board[1][2] == PLAYER2_MARK) ||
                (board[2][0] == PLAYER2_MARK && board[2][1] == PLAYER2_MARK && board[2][2] == PLAYER2_MARK) ||
                (board[0][0] == PLAYER2_MARK && board[1][1] == PLAYER2_MARK && board[2][2] == PLAYER2_MARK) ||
                (board[2][0] == PLAYER2_MARK && board[1][1] == PLAYER2_MARK && board[0][2] == PLAYER2_MARK))
            return player2;
        else
            return null;
    }

    //TODO  ruch na mapie
    public void move(int row, int column) {
        if (currentPlayer == player1) {
            board[row][column] = PLAYER1_MARK;
            currentPlayer = player2;
        } else {
            board[row][column] = PLAYER2_MARK;
            currentPlayer = player1;
        }
    }
    //TODO  zmiana gracza
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }
    //TODO init mapy
    public void initializeBoard(){
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                this.board[i][j] = EMPTY_MARK;
            }
        }
    }

    }






