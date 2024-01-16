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

    private static final char EMPTY = '.';
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
//TODO  zmiana gracza
//TODO init mapy



    }






