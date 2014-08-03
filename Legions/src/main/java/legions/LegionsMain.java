package legions;

import legions.chunks.ChunkManager;
import legions.legion.LegionManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by WaterNode on 7/28/2014.
 */
public class LegionsMain extends JavaPlugin {
    @Getter
    static ChunkManager chunkManager;

    @Getter
    static LegionManager legionManager;

    @Getter
    static LegionsMain main;

    public void onEnable() {
        main = this;
    }
}
