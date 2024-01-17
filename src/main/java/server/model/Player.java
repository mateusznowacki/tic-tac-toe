package server.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Player {
    @NonNull
    private final int id;
    @NonNull
    private final String name;
    private int matchId;
    private int wins=0;
    private int losses=0;
    private int draws=0;
    private boolean isWinner=false;

    public void addWin(){
        wins++;
    }
    public void addLoss(){
        losses++;
    }
    public void addDraw(){
        draws++;
    }
}
