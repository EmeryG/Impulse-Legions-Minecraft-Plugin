import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * Created by Ervin on 7/29/2014.
 */
public class ProfessionListener implements Listener {

    @EventHandler
    public void onPIE(PlayerInteractEvent event) {
        if (event instanceof Player) {
            Player p = event.getPlayer();
            if (p.getItemInHand().getType() == Material.EMERALD
                    && event.getAction() == Action.LEFT_CLICK_AIR
                    && event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (ProfessionMember.getProfession(p) == ProfessionClasses.THEIF) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 5, 0));
                }
            } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (ProfessionMember.getProfession(p) == ProfessionClasses.THEIF) {
                    if (event.getClickedBlock().getType() == Material.WOOD_DOOR
                            && event.getClickedBlock().getType() == Material.TRAP_DOOR) {
                        event.setCancelled(true);
                        event.getClickedBlock().setType(Material.WOODEN_DOOR);
                    } else if(event.getClickedBlock().getType() == Material.IRON_DOOR) {
                        Random r = new Random(10);
                        if(r.equals("3")) {
                            event.setCancelled(true);
                            event.getClickedBlock().setType(Material.IRON_DOOR_BLOCK);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPJE(PlayerJoinEvent event) {
        if (event instanceof Player) {
            Player p = event.getPlayer();
            if (ProfessionMember.getProfession(p) == ProfessionClasses.SCOUT) {
                p.addPotionEffect(new InfinitePotionEffect(PotionEffectType.SPEED, 0));
            }
        }
    }
}