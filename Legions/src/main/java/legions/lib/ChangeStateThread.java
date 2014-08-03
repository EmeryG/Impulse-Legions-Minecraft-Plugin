package legions.lib;

import legions.chunks.LegionChunk;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by WaterNode on 8/3/2014.
 */
public class ChangeStateThread extends BukkitRunnable {
    LegionChunk chunk;

    public ChangeStateThread(LegionChunk c) {
        chunk = c;
    }

    public void run() {
        chunk.setState(true);
    }
}
