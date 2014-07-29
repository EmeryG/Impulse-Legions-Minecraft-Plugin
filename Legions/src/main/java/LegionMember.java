import lombok.Getter;
import lombok.Setter;

/**
 * Created by WaterNode on 7/28/2014.
 */
public class LegionMember {

    @Setter @Getter
    Rank rank;

    @Getter
    String name;

    public LegionMember(Rank r, String n) {
        rank = r;
        name = n;
    }
}
