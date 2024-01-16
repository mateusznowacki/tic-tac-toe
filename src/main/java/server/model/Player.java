package server.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Player {
    private final int id;
    private final String name;
    private int matchId;


}
