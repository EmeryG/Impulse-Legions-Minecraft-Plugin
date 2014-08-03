package legions.chunks;

import legions.legion.Legion;
import org.bukkit.Chunk;
import org.bukkit.event.Listener;

import java.util.ArrayList;

/**
 * Created by WaterNode on 7/31/2014.
 */
public class ChunkManager implements Listener {
    ArrayList<LegionChunk> claimedChunks = new ArrayList<LegionChunk>();

    public int claimedAmount(String legionName) {
        int i = 0;
        for(LegionChunk c : claimedChunks) {
            if(c.getOwner().equalsIgnoreCase(legionName)) {
                i++;
            }
        }
        return i;
    }

    public LegionChunk getChunk(int x, int z) {
        for(LegionChunk c : claimedChunks) {
            if(c.getX() == x && c.getZ() == z) {
                return c;
            }
        }
        return null;
    }

    public boolean claimLand(String legion, Chunk chunk) {
        if(getChunk(chunk.getX(), chunk.getZ()) == null) {
            claimedChunks.add(new LegionChunk(chunk.getX(), chunk.getZ(), legion));
            return true;
        } else {
            return false;
        }
    }

    public String unclaimLand(String legion, Chunk chunk) {
        LegionChunk removeChunk = getChunk(chunk.getX(), chunk.getZ());
        if(removeChunk != null) {
            if(removeChunk.getOwner() == legion) {
                claimedChunks.remove(removeChunk);
                return "ChunkUnclaimed";
            }
            return "NotYours";
        } else {
            return "FakeChunk";
        }
    }
}
