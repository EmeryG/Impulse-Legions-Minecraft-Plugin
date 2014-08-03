package legions.lib;

import legions.LegionsMain;
import legions.chunks.LegionChunk;
import legions.legion.Legion;
import legions.legion.LegionMember;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by WaterNode on 7/29/2014.
 */
public class Lib {

    public static void giveLegionInfo(Player p, Legion infoLegion, Legion playersLegion) {
        p.sendMessage(Config.getMessage("InfoFormat.Legion", stanceColor(playersLegion, infoLegion) + infoLegion.getName()));

        p.sendMessage(Config.getMessage("InfoFormat.Level", "" + infoLegion.getLevel()));

        p.sendMessage(Config.getMessage("InfoFormat.PlayersHeader"));
        for(LegionMember member : infoLegion.getMembers()) {
            p.sendMessage(Config.getMessage("InfoFormat.PlayersList", member.getName()));
        }

        p.sendMessage(Config.getMessage("InfoFormat.EnemiesHeader"));
        for(String enemy : infoLegion.getEnemies()) {
            p.sendMessage(Config.getMessage("InfoFormat.EnemiesList", stanceColor(playersLegion, enemy) + enemy));
        }

        p.sendMessage(Config.getMessage("InfoFormat.AlliesHeader"));
        for(String ally : infoLegion.getAllies()) {
            p.sendMessage(Config.getMessage("InfoFormat.AlliesList", stanceColor(playersLegion, ally) + ally));
        }
    }

    public static void genMap(Player p, Legion legion) {
        p.sendMessage(ChatColor.BLACK + "        North        ");
        for(int z = p.getLocation().getChunk().getZ() - 8;
            z <= p.getLocation().getChunk().getZ()+8;
            z++) {

            String row = ChatColor.BLACK + "W ";

            for (int x = -4;
                 x <= p.getLocation().getChunk().getX() + 4;
                 x++) {


                LegionChunk c = LegionsMain.getChunkManager().getChunk(x, z);
                if (x == p.getLocation().getChunk().getX() && z == p.getLocation().getChunk().getZ()) {
                    double rotation = (p.getLocation().getYaw() - 90) % 360;

                    if (rotation < 0) {
                        rotation += 360.0;
                    }

                    if ((rotation >= 0 && rotation < 46) || (rotation >= 316 && rotation <= 360)) {
                        row += ChatColor.LIGHT_PURPLE + "^";
                    } else if (rotation >= 226 && rotation < 316) {
                        row += ChatColor.LIGHT_PURPLE + "<";
                    } else if (rotation >= 136 && rotation < 226) {
                        row += ChatColor.LIGHT_PURPLE + "V";
                    } else {
                        row += ChatColor.LIGHT_PURPLE + ">";
                    }
                } else if (c != null) {
                    row += c.returnColor(legion);
                } else {
                    row += ChatColor.BLACK + "X";
                }
            }
            row += ChatColor.BLACK + "W";

            p.sendMessage(row);
        }
        p.sendMessage(ChatColor.BLACK + "        South        ");
    }

    public static String stanceColor(Legion one, Legion two) {
        try {
            if (one.getAllies().contains(two.getName())) {
                return ChatColor.GREEN + "O";
            } else if (one.getEnemies().contains(two.getName())) {
                return ChatColor.RED + "O";
            } else {
                return ChatColor.GRAY + "O";
            }
        } catch(NullPointerException e) {
            return ChatColor.GRAY + "O";
        }
    }

    public static String stanceColor(Legion one, String two) {
        try {
            if (one.getAllies().contains(two)) {
                return ChatColor.GREEN + "";
            } else if (one.getEnemies().contains(two)) {
                return ChatColor.RED + "";
            } else {
                return ChatColor.GRAY + "";
            }
        } catch(NullPointerException e) {
            return ChatColor.GRAY + "";
        }
    }
}
