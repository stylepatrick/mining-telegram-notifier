package stylepatrick.miningtelegramnotifier.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class CurrentStats {

    private String status;
    private CurrentStatsData data;

    public CurrentStats() {
    }
}
