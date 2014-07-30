import lombok.Getter;
import org.bukkit.ChatColor;

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

    public LegionChunk(int xcoord, int zcoord, String o) {
        x = xcoord;
        z = zcoord;
        owner = o;
    }

    public String returnColor(Legion l) {
        return Lib.stanceColor(l, owner) + "O";
    }
}
