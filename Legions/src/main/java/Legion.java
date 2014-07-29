import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by WaterNode on 7/28/2014.
 */
public class Legion {
    FileConfiguration config;

    @Getter
    String name;

    @Getter @Setter
    int level;

    @Getter
    int xp;

    ArrayList<LegionMember> members = new ArrayList<LegionMember>();
    ArrayList<String> enemies = new ArrayList<String>();
    ArrayList<String> allies = new ArrayList<String>();

    public Legion(String n, ArrayList<LegionMember> ms, FileConfiguration c)  {
        n = name;
        members = ms;
        config = c;
    }

    public boolean onCommand(Player player, String[] args) {
        switch(args[0].toLowerCase()) {
            case "invite": case "inv":
                if(getMember(name).getRank() != Rank.NORMAL) {

                } else {
                    player.sendMessage(Config.getMessage("needhigherrank"));
                }
                break;
            case "general":
                if(getMember(name).getRank() == Rank.GENERAL) {

                } else {
                    player.sendMessage(Config.getMessage("needgeneral"));
                }
                break;
            case "commander":
                if(getMember(name).getRank() == Rank.GENERAL) {

                } else {
                    player.sendMessage(Config.getMessage("needgeneral"));
                }
                break;
            case "claim":
                if(getMember(name).getRank() != Rank.NORMAL) {

                } else {
                    player.sendMessage(Config.getMessage("needhigherrank"));
                }
                break;
            case "unclaim":
                if(getMember(name).getRank() != Rank.NORMAL) {

                } else {
                    player.sendMessage(Config.getMessage("needhigherrank"));
                }
                break;
            case "attack":
                if(getMember(name).getRank() != Rank.NORMAL) {

                } else {
                    player.sendMessage(Config.getMessage("needhigherrank"));
                }
                break;
            case "ally":
                if(getMember(name).getRank() != Rank.NORMAL) {

                } else {
                    player.sendMessage(Config.getMessage("needhigherrank"));
                }
                break;
            case "neutal":
                if(getMember(name).getRank() != Rank.NORMAL) {

                } else {
                    player.sendMessage(Config.getMessage("needhigherrank"));
                }
                break;
            case "enemy":
                if(getMember(name).getRank() != Rank.NORMAL) {

                } else {
                    player.sendMessage(Config.getMessage("needhigherrank"));
                }
                break;
            default:
                player.sendMessage(Config.getMessage("CmdNotFound"));
                return false;
        }
        return true;
    }

    public LegionMember getMember(String name) {
        for(LegionMember lm : members) {
            if(lm.getName().equalsIgnoreCase(name)) {
                return lm;
            }
        }
        return null;
    }
}
