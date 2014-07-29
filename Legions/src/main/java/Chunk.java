import lombok.Getter;
import org.bukkit.ChatColor;

/**
 * Created by WaterNode on 7/28/2014.
 */
public class Chunk {
    @Getter
    int x;

    @Getter
    int z;

    @Getter
    String owner;

    public Chunk(int xcoord, int zcoord) {
        x = xcoord;
        z = zcoord;
    }

    public String returnColor(Legion l) {
        return Lib.stanceColor(l, owner) + "O";
    }
}
