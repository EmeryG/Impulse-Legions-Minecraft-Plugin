import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Created by WaterNode on 7/28/2014.
 */
public class LegionsMain extends JavaPlugin {


    @Getter
    static LegionsMain main;

    public void onEnable() {
        main = this;
    }
}
