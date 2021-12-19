package stylepatrick.miningtelegramnotifier.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class CurrentStatsData {

    private Long time;
    private Long lastSeen;
    private Long reportedHashrate;
    private Long currentHashrate;
    private Long validShares;
    private Long invalidShares;
    private Long activeWorkers;
    private Long averageHashrate;
    private Long unpaid;
    private Double coinsPerMin;
    private Double usdPerMin;
    private Double btcPerMin;

    public CurrentStatsData() {
    }

}
