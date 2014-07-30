import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Door;
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
                if (ProfessionMember.getProfession(p).equals(null)) {
                    p.openInventory(ProfessionMain.inv);
                }
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
                    if (!(event.getClickedBlock().getType() == Material.REDSTONE
                            || event.getClickedBlock().getType() == Material.REDSTONE_WIRE
                            || event.getClickedBlock().getType() == Material.REDSTONE_TORCH_ON
                            || event.getClickedBlock().getType() == Material.REDSTONE_TORCH_OFF
                            || event.getClickedBlock().getType() == Material.REDSTONE_COMPARATOR_ON
                            || event.getClickedBlock().getType() == Material.REDSTONE_COMPARATOR_OFF
                            || event.getClickedBlock().getType() == Material.REDSTONE_COMPARATOR
                            || event.getClickedBlock().getType() == Material.CHEST
                            || event.getClickedBlock().getType() == Material.ENDER_CHEST
                            || event.getClickedBlock().getType() == Material.TRAPPED_CHEST)) {
                        for (int i = 0; i > 5; i++) {
                            ProfessionMain.hits.add(1);
                        }
                        if (ProfessionMain.hits.equals(5)) {
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
            } else if (ProfessionMember.getProfession(p).equals(ProfessionClasses.BLOODLUST)) {
                if (p.getItemInHand().equals(Material.DIAMOND_AXE)
                        || p.getItemInHand().equals(Material.STONE_AXE)
                        || p.getItemInHand().equals(Material.WOOD_AXE)
                        || p.getItemInHand().equals(Material.IRON_AXE)) {
                    Double dmg = event.getDamage() * 0.25;
                    p.setHealth(p.getHealth() + dmg);
                }
            } else if (ProfessionMember.getProfession(p).equals(ProfessionClasses.COWARD)) {
                Random r = new Random();
                if (r.nextInt(9) == 6) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5, 1));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 5, 1));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5, 1));
                }
            }
        }
    }

    @EventHandler
    public void onICE(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player p = (Player) event.getWhoClicked();
            ItemStack clicked = event.getCurrentItem();
            event.setCancelled(true);
            if (event.getInventory().getName().equals(ProfessionMain.plugin.getConfig().getString("ProfessionInvName"))) {
                if (clicked.getType() == Material.NETHER_STAR) {
                    if (clicked.getItemMeta().getDisplayName() == "THEIF") {
                        if (ProfessionMember.getProfession(p).equals(null)) {
                            ProfessionMain.removeProfession(p.getUniqueId(), ProfessionClasses.THEIF);
                        } else {
                            ProfessionMain.addProfession(p.getUniqueId(), ProfessionClasses.THEIF);
                        }
                    } else if (clicked.getItemMeta().getDisplayName() == "SCOUT") {
                        if (ProfessionMember.getProfession(p).equals(null)) {
                            ProfessionMain.removeProfession(p.getUniqueId(), ProfessionClasses.SCOUT);
                        } else {
                            ProfessionMain.addProfession(p.getUniqueId(), ProfessionClasses.SCOUT);
                        }
                    } else if (clicked.getItemMeta().getDisplayName() == "HEAVY") {
                        if (ProfessionMember.getProfession(p).equals(null)) {
                            ProfessionMain.removeProfession(p.getUniqueId(), ProfessionClasses.HEAVY);
                        } else {
                            ProfessionMain.addProfession(p.getUniqueId(), ProfessionClasses.HEAVY);
                        }
                    } else if (clicked.getItemMeta().getDisplayName() == "COWARD") {
                        if (ProfessionMember.getProfession(p).equals(null)) {
                            ProfessionMain.removeProfession(p.getUniqueId(), ProfessionClasses.COWARD);
                        } else {
                            ProfessionMain.addProfession(p.getUniqueId(), ProfessionClasses.COWARD);
                        }
                    } else if (clicked.getItemMeta().getDisplayName() == "BLOODLUST") {
                        if (ProfessionMember.getProfession(p).equals(null)) {
                            ProfessionMain.removeProfession(p.getUniqueId(), ProfessionClasses.BLOODLUST);
                        } else {
                            ProfessionMain.addProfession(p.getUniqueId(), ProfessionClasses.BLOODLUST);
                        }
                    }
                }
            }
        }
    }
}