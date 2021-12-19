package stylepatrick.miningtelegramnotifier.service;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import stylepatrick.miningtelegramnotifier.config.AppConfig;
import stylepatrick.miningtelegramnotifier.model.CurrentStats;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

@Service
public class TelegramNotifier {

    private final AppConfig appConfig;

    public TelegramNotifier(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    private CurrentStats currentStats;

    @Scheduled(fixedRateString = "${notifier.pollingInterval}")
    public void getCurrentStatsFromMiningApi() {
        CurrentStats previousStats = new CurrentStats();
        if (currentStats == null) {
            currentStats = requestStatsOnMiningApi();
        } else {
            previousStats = currentStats;
            currentStats = requestStatsOnMiningApi();
        }
        if (previousStats.getStatus() != null && !previousStats.getStatus().equals("ERROR")) {
            Long difference =  previousStats.getData().getReportedHashrate() - (previousStats.getData().getReportedHashrate() / 100) * appConfig.getHashrateGap();
            if (currentStats.getData().getReportedHashrate() < difference) {
                sendTelegramNotification(currentStats, "Hashrate decreasing!");
            } else if (difference == 0 && currentStats.getData().getReportedHashrate() != 0) {
                sendTelegramNotification(currentStats, "Offline!");
            }
        }
    }

    private CurrentStats requestStatsOnMiningApi() {
        RestTemplate restTemplate = new RestTemplate();
        String url = appConfig.getPoolApi() + "/miner/" + appConfig.getMiningAddress() + "/currentStats";
        ResponseEntity<CurrentStats> response = restTemplate.getForEntity(url, CurrentStats.class);
        return response.getBody();
    }

    private void sendTelegramNotification(CurrentStats currentStats, String title) {
        Date date = new Date();
        date.setTime(currentStats.getData().getLastSeen() * 1000);
        String urlString = appConfig.getTelegramApi() + "/bot%s/sendMessage?chat_id=%s&text=%s&parse_mode=HTML";
        String text = "<b>" + title + "</b> \n" +
                "<b>Last Seen: </b>" + date.getTime() + "\n" +
                "<b>Active Workers: </b>" + currentStats.getData().getActiveWorkers() + "\n" +
                "<b>Reported Hashrate: </b>" + currentStats.getData().getReportedHashrate();
        urlString = String.format(urlString, appConfig.getTelegramToken(), appConfig.getTelegramChatId(), URLEncoder.encode(text));

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
