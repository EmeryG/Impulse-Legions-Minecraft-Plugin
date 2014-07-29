import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

/**
 * Created by Ervin on 7/29/2014.
 */
public class ProfessionMember {

    @Setter @Getter
    static ProfessionClasses profession;

    public ProfessionMember(ProfessionClasses prof) {
        profession = prof;
    }

    public static ProfessionClasses getProfession(Player player) {
        return profession;
    }
}
