import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.material.Door;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ervin on 7/29/2014.
 */
public class ProfessionListener implements Listener {

    ArrayList<Integer> hits = new ArrayList<>();

    @EventHandler
    public void onPIE(PlayerInteractEvent event) {
        if (event instanceof Player) {
            Player p = event.getPlayer();
            if (p.getItemInHand().getType() == Material.EMERALD
                    && event.getAction() == Action.LEFT_CLICK_AIR
                    && event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (ProfessionMember.getProfession(p).equals(ProfessionClasses.THEIF)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 5, 0));
                }
            } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (ProfessionMember.getProfession(p).equals(ProfessionClasses.THEIF)) {
                    if (event.getClickedBlock().getType() == Material.WOOD_DOOR
                            && event.getClickedBlock().getType() == Material.TRAP_DOOR) {
                        Door d = (Door) event.getClickedBlock().getState().getData();
                        if (!d.isOpen()) {
                            d.setOpen(true);
                        }
                        event.setCancelled(true);
                    } else if (event.getClickedBlock().getType() == Material.IRON_DOOR) {
                        Random r = new Random();
                        if (r.nextInt(9) == 3) {
                            Door d = (Door) event.getClickedBlock().getState().getData();
                            if (!d.isOpen()) {
                                d.setOpen(true);
                            }
                            event.setCancelled(true);
                        }
                    }
                }
            } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (ProfessionMember.getProfession(p).equals(ProfessionClasses.HEAVY)) {
                    if (!(event.getClickedBlock().getType() == Material.REDSTONE)) {
                        for (int i = 0; i > 5; i++) {
                            hits.add(1);
                        }
                        if (hits.equals(5)) {
                            Block b = event.getClickedBlock();
                            b.breakNaturally();
                            b.setType(Material.AIR);
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

    @EventHandler
    public void onEDEE(EntityDamageEvent event) {
        if (event instanceof Player) {
            Player p = ((Player) event).getPlayer();
            if (ProfessionMember.getProfession(p).equals(ProfessionClasses.HEAVY)) {
                Double dmg = event.getDamage() * 0.1;
                event.setDamage(dmg);
            }
        }
    }
}