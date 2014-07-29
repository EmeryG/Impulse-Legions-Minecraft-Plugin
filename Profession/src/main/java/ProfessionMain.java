import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * Created by Ervin on 7/29/2014.
 */
public class ProfessionMain extends JavaPlugin {

    public static HashMap<UUID, ProfessionClasses> jobs = new HashMap<>();
    public static ArrayList<Integer> hits = new ArrayList<>();

    public static Inventory inv;

    static Plugin plugin;

    public void onEnable() {

        ProfessionListener pl = new ProfessionListener();
        Bukkit.getServer().getPluginManager().registerEvents(pl, this);

        plugin = this;

        hits.clear();

        inv = Bukkit.createInventory(null, 9, getConfig().getString("ProfessionInvName").replaceAll("&", "§"));
        inv.setItem(1, make(Material.NETHER_STAR, 1, 0, "THEIF", Arrays.asList("")));
        inv.setItem(2, make(Material.NETHER_STAR, 1, 0, "SCOUT", Arrays.asList("")));
        inv.setItem(3, make(Material.NETHER_STAR, 1, 0, "HEAVY", Arrays.asList("")));
        inv.setItem(4, make(Material.NETHER_STAR, 1, 0, "COWARD", Arrays.asList("")));
        inv.setItem(5, make(Material.NETHER_STAR, 1, 0, "BLOODLUST", Arrays.asList("")));
    }

    public static ItemStack make(Material material, int amount,
                                 int shrt, String displayName, List<String> lore) {
        ItemStack item = new ItemStack(material, amount, (short) shrt);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static void addProfession(Player player, ProfessionClasses job) {
    }
}
