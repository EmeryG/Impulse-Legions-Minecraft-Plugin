package legions.legion;

import lombok.Getter;

/**
 * Created by WaterNode on 7/28/2014.
 */
public enum Rank {
    GENERAL("general"),
    COMMANDER("commander"),
    NORMAL("normal");

    @Getter
    private String value;

    Rank(String value) {
        this.value = value;
    }
}
