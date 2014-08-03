package legions.chunks;

import legions.legion.Legion;
import legions.lib.Lib;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by WaterNode on 7/28/2014.
 */
public class LegionChunk {
    @Getter
    int x;

    @Getter
    int z;

    @Getter
    String owner;

    @Setter
    @Getter
    Boolean state = true;

    public LegionChunk(int xcoord, int zcoord, String o) {
        x = xcoord;
        z = zcoord;
        owner = o;
    }

    public String returnColor(Legion l) {
        return Lib.stanceColor(l, owner) + "O";
    }
}
