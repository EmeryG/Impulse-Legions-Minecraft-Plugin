import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by WaterNode on 7/28/2014.
 */
public class Legion implements Listener {
    FileConfiguration config;

    @Getter
    String name;

    @Getter
    @Setter
    int level;

    @Getter
    int xp;

    ArrayList<LegionMember> members = new ArrayList<LegionMember>();
    ArrayList<String> enemies = new ArrayList<String>();
    ArrayList<String> allies = new ArrayList<String>();

    public Legion(String n, ArrayList<LegionMember> ms, FileConfiguration c) {
        n = name;
        members = ms;
        config = c;
    }

    public boolean onCommand(Player player, String[] args) {
        switch (args[0].toLowerCase()) {
            case "invite":
            case "inv":
                if (getMember(name).getRank() != Rank.NORMAL) {
                    if(LegionManager.PendingInvites.containsKey(player.getName())) {
                        LegionManager.PendingInvites.remove(player.getName());
                        LegionManager.PendingInvites.put(player.getName(), args[1]);

                        try {
                            Bukkit.getPlayer(args[1]).sendMessage(Config.getMessage("InviteFormat", name, player.getName()));
                        } catch(NullPointerException e) {
                            player.sendMessage(Config.getMessage("PlayerNotOnline", args[1]));
                            LegionManager.PendingInvites.remove(player.getName());
                            return false;
                        }
                    }
                } else {
                    player.sendMessage(Config.getMessage("NeedHigherRank"));
                    return false;
                }
                break;
            case "general":
                if (getMember(player.getName()).getRank() == Rank.GENERAL) {
                    try {
                        getMember(player.getName()).setRank(Rank.COMMANDER);
                        getMember(args[1]).setRank(Rank.GENERAL);
                        saveLegion();
                        broadcastMessage(Config.getMessage("NewGeneral", args[1]));
                    } catch(NullPointerException e) {
                        player.sendMessage(Config.getMessage("NotInLegion", args[1]));
                        return false;
                    }
                } else {
                    player.sendMessage(Config.getMessage("NeedGeneral"));
                    return false;
                }
                break;
            case "commander":
                if (getMember(name).getRank() == Rank.GENERAL) {
                    try {
                        if(getMember(args[1]).getRank() == Rank.COMMANDER) {
                            getMember(args[1]).setRank(Rank.NORMAL);
                            broadcastMessage(Config.getMessage("DemotedCommander", args[1]));
                            saveLegion();
                        } else {
                            getMember(args[1]).setRank(Rank.COMMANDER);
                            broadcastMessage(Config.getMessage("NewCommander", args[1]));
                            saveLegion();
                        }
                    } catch(NullPointerException e) {
                        player.sendMessage(Config.getMessage("NotInLegion", args[1]));
                        return false;
                    }
                } else {
                    player.sendMessage(Config.getMessage("NeedGeneral"));
                    return false;
                }
                break;
            case "claim":
                if (getMember(name).getRank() != Rank.NORMAL) {
                    if(LegionManager.claimedAmount(name) < members.size()*2) {
                        // Claim
                    } else {
                        player.sendMessage(Config.getMessage("NotEnoughSpace"));
                    }
                } else {
                    player.sendMessage(Config.getMessage("NeedHigherRank"));
                    return false;
                }
                break;
            case "unclaim":
                if (getMember(name).getRank() != Rank.NORMAL) {

                } else {
                    player.sendMessage(Config.getMessage("NeedHigherRank"));
                    return false;
                }
                break;
            case "attack":
                if (getMember(name).getRank() != Rank.NORMAL) {

                } else {
                    player.sendMessage(Config.getMessage("NeedHigherRank"));
                    return false;
                }
                break;
            case "ally":
                if (getMember(name).getRank() != Rank.NORMAL) {

                } else {
                    player.sendMessage(Config.getMessage("NeedHigherRank"));
                    return false;
                }
                break;
            case "neutral":
                if (getMember(name).getRank() != Rank.NORMAL) {

                } else {
                    player.sendMessage(Config.getMessage("NeedHigherRank"));
                    return false;
                }
                break;
            case "enemy":
                if (getMember(name).getRank() != Rank.NORMAL) {

                } else {
                    player.sendMessage(Config.getMessage("NeedHigherRank"));
                    return false;
                }
                break;
            default:
                player.sendMessage(Config.getMessage("CmdNotFound"));
                return false;
        }
        return true;
    }

    public void broadcastMessage(String m) {
        for (LegionMember lm : members) {
            try {
                Bukkit.getPlayer(lm.getName()).sendMessage(m);
            } catch (NullPointerException e) {

            }
        }
    }

    public LegionMember getMember(String name) {
        for (LegionMember lm : members) {
            if (lm.getName().equalsIgnoreCase(name)) {
                return lm;
            }
        }
        return null;
    }

    public void addMember(String name) {
        members.add(new LegionMember(Rank.NORMAL, name));
        broadcastMessage(Config.getMessage("NewMember", name));
        saveLegion();
    }

    public void saveLegion() {
        ArrayList<String> players = new ArrayList<String>();

        for(LegionMember member : members) {
            players.add(member.getName());
        }
        config.set("players", members);

        for(LegionMember member : members) {
            config.set("players." + member.getName(), member.getRank().getValue());
        }

        config.set("level", level);
        config.set("xp", xp);

        config.set("allies", allies);
        config.set("enemies", enemies);
        try {
            config.save(new File(LegionsMain.getMain().getDataFolder() + "/legions/" + name.toLowerCase() + ".yml"));
        } catch (IOException e) {
            LegionsMain.getMain().getLogger().info("IOError when saving config: " + e.getMessage());
        }

        config = Config.getConfig("/legions/" + name.toLowerCase() + ".yml");
    }
}
