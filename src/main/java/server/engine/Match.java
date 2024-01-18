package server.engine;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import server.model.Player;



import static server.io.ConsolePrinter.printMatchDraw;
import static server.io.ConsolePrinter.printWinInfo;


@Getter
@Setter
public class Match {
    private Player player1, player2;
    private int matchId;
    private Player currentPlayer;
    private boolean isDraw = false;
    private boolean isRunning = true;
    int playerNumber = 0;


    private char[][] board = new char[3][3];
    private boolean ready;

    private static final char EMPTY_MARK = '.';
    private static final char PLAYER1_MARK = 'X';
    private static final char PLAYER2_MARK = 'O';

    public Match(int matchId) {
        this.matchId = matchId;
        initializeMatch();

    }
    public void addPlayer(Player player){
        if(playerNumber == 0){
            player1 = player;
            playerNumber++;
        }else if(playerNumber == 1){
            player2 = player;
            playerNumber++;
        }
    }




    public void getWinner() {
        if ((board[0][0] == PLAYER1_MARK && board[0][1] == PLAYER1_MARK && board[0][2] == PLAYER1_MARK) ||
                (board[1][0] == PLAYER1_MARK && board[1][1] == PLAYER1_MARK && board[1][2] == PLAYER1_MARK) ||
                (board[2][0] == PLAYER1_MARK && board[2][1] == PLAYER1_MARK && board[2][2] == PLAYER1_MARK) ||
                (board[0][0] == PLAYER1_MARK && board[1][1] == PLAYER1_MARK && board[2][2] == PLAYER1_MARK) ||
                (board[2][0] == PLAYER1_MARK && board[1][1] == PLAYER1_MARK && board[0][2] == PLAYER1_MARK)) {
            isRunning = true;
            player1.addWin();
            player2.addLoss();
            player1.setWinner(true);
            setCurrentGameState();

        } else if ((board[0][0] == PLAYER2_MARK && board[0][1] == PLAYER2_MARK && board[0][2] == PLAYER2_MARK) ||
                (board[1][0] == PLAYER2_MARK && board[1][1] == PLAYER2_MARK && board[1][2] == PLAYER2_MARK) ||
                (board[2][0] == PLAYER2_MARK && board[2][1] == PLAYER2_MARK && board[2][2] == PLAYER2_MARK) ||
                (board[0][0] == PLAYER2_MARK && board[1][1] == PLAYER2_MARK && board[2][2] == PLAYER2_MARK) ||
                (board[2][0] == PLAYER2_MARK && board[1][1] == PLAYER2_MARK && board[0][2] == PLAYER2_MARK)) {
            isRunning = true;
            player2.addWin();
            player1.addLoss();
            player2.setWinner(true);
            setCurrentGameState();

        } else if (player1.isWinner() == false && player2.isWinner() == false && isDraw() == true) {
            isRunning = true;
            isDraw = true;
            player1.addDraw();
            player2.addDraw();
            setCurrentGameState();
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
            notifyNextPlayer();
        } else if (isDraw == true) {
            printMatchDraw();
        }
    }

    private void notifyNextPlayer() {
        //TODO: implementacja powiadomienia gracza
        System.out.println("Next turn.");
    }

    private void setCurrentGameState() {

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
    }

    private boolean isDraw() {
        //sprawdzam puste pola
        int emptyFields = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == EMPTY_MARK) {
                    emptyFields++;
                }
            }
        }
        if (emptyFields == 0) {
            return true;
        } else {
            return false;
        }
    }
}






