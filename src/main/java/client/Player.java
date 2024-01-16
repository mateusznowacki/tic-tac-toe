package client;

import lombok.*;

@RequiredArgsConstructor
@Getter @Setter
public class Player {
    @NonNull
    private int id;
    @NonNull
    private String name;
    private int matchId;



}
