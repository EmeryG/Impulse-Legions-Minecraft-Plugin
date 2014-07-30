import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WaterNode on 7/28/2014.
 */
public class Config {

    public static FileConfiguration getConfig(String filepath) {
        File file = new File(LegionsMain.getMain().getDataFolder() + filepath);

        if(file.exists()) {
            return YamlConfiguration.loadConfiguration(file);
        } else {
            return null;
        }
    }

    public static String getMessage(String path) {
        return LegionsMain.getMain().getConfig().getString(path).replace("&", "§");
    }

    public static String getMessage(String path, String var) {
        String message = LegionsMain.getMain().getConfig().getString(path).replace("&", "§");
        return message.replace("%v", var);
    }

    public static String getMessage(String path, String var, String var2) {
        String message = LegionsMain.getMain().getConfig().getString(path).replace("&", "§");
        message = message.replace("%v", var);
        return message.replace("%v2", var2);
    }

    public static Legion createLegion(Player p, String legionName) {
        ArrayList<LegionMember> members = new ArrayList<LegionMember>();
        members.add(new LegionMember(Rank.GENERAL, p.getName()));

        FileConfiguration config = Config.getConfig("/legions/" + legionName.toLowerCase() + ".yml");
        if(config == null) {
            try {
                File f = new File(LegionsMain.getMain().getDataFolder() + "/" + legionName.toLowerCase() + ".yml");

                if(f.createNewFile()) {
                    throw new IOException("File is already created, there is an unidentified issue.");
                }

                config = Config.getConfig("/legions/" + legionName.toLowerCase() + ".yml");

                List<String> players = config.getStringList("players");
                players.add(p.getName());

                config.set("players", players);
                config.set("players." + p.getName(), "general");

                config.set("level", 1);
                config.set("xp", 0);

                config.set("allies", new ArrayList<String>());
                config.set("enemies", new ArrayList<String>());

                config.save(LegionsMain.getMain().getDataFolder() + "/" + legionName.toLowerCase() + ".yml");

            } catch (IOException e) {
                LegionsMain.getMain().getLogger().info("IOError while making a new Legion: " + e.getMessage());
            }
        } else {
            p.sendMessage(getMessage("AlreadyCreated"));
            return null;
        }

        return new Legion(legionName, members, config);
    }
}
