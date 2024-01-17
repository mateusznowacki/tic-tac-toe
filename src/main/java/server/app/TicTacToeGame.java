package server.app;

import server.engine.Match;
import server.model.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
//todo dodwanie gracza do meczu

public class TicTacToeGame extends UnicastRemoteObject implements TicTacToeService {
    private List<Player> players;
    private List<Match> matches;
    private int playerId = 0;
    public TicTacToeGame() throws RemoteException {
        this.players = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public void runGameLoop(Match match){
        while (match.isRunning()){
           //
//
//
//


            if(!match.isRunning()){
                break;
            }
        }
    }

    @Override
    public void registerPlayer(String name) throws RemoteException {
        players.add(new Player(players.size()+1, name));
    }

    @Override
    public void deletePlayer(int id) throws RemoteException {
        players.removeIf(player -> player.getId() == id);
    }

    @Override
    public void makeMove(int matchId,int playerId, int row, int column) throws RemoteException {
       matches.get(matchId).makeMove(row,column, playerId);
    }

    @Override
    public void createMatch(Player player1, Player player2) throws RemoteException {
        Match match = new Match(player1,player2,matches.size()+1);
        matches.add(match);

    }

    @Override
    public void deleteMatch(int matchId) throws RemoteException {
        matches.removeIf(match -> match.getMatchId() == matchId);
    }

    @Override
    public Match getMatch(int matchId) throws RemoteException {
       return matches.get(matchId);
    }
}
