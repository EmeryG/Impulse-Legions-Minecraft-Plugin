import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ervin on 7/29/2014.
 */
public class ProfessionMain extends JavaPlugin {

    @Getter
    static ProfessionMain main;

    public void onEnable() {

        ProfessionListener pl = new ProfessionListener();
        Bukkit.getServer().getPluginManager().registerEvents(pl, this);

        main = this;
    }
}
