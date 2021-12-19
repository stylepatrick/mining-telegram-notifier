package stylepatrick.miningtelegramnotifier.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "notifier")
@Getter
@Setter
public class AppConfig {

    private String poolApi;
    private Integer hashrateGap;
    private Integer pollingInterval;
    private String miningAddress;
    private String telegramApi;
    private String telegramToken;
    private String telegramChatId;

}
