package package_for_websitemonitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import package_for_websitemonitor.model.User;
import package_for_websitemonitor.model.WebsiteSubscription;

public class WebsMonitor {
    private List<WebsiteSubscription> subscriptions;
    private NotificationService notificationService;
    // private Random random = new Random(); // For simulating changes

    public WebsMonitor(NotificationService notificationService) {
        this.subscriptions = new ArrayList<>();
        this.notificationService = notificationService;
    }

    public void registerWebsite(User user, String url, String frequency) {
        WebsiteSubscription subscription = new WebsiteSubscription(user, url, frequency);
        subscriptions.add(subscription);
    }

    public void modifySubscription(User user, String url, String newFrequency) {
        for (WebsiteSubscription sub : subscriptions) {
            if (sub.getUser().equals(user) && sub.getUrl().equals(url)) {
                sub.setFrequency(newFrequency);
                break;
            }
        }
    }

    public void cancelSubscription(User user, String url) {
        subscriptions.removeIf(sub -> sub.getUser().equals(user) && sub.getUrl().equals(url));
    }

    public void startMonitoring() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkWebsitesForUpdates();
            }
        }, 0, 24 * 60 * 60 * 1000); // Check daily
    }

    private void checkWebsitesForUpdates() {
        for (WebsiteSubscription sub : subscriptions) {
            String currentHash = simulateWebsiteFetch(sub.getUrl());

            if (sub.getLastHash() == null) {
                sub.setLastHash(currentHash);
            } else if (!sub.getLastHash().equals(currentHash)) {
                notificationService.sendNotification((User) sub.getUser(),"Website content has changed!", sub.getUrl());
                sub.setLastHash(currentHash);
            }
            sub.setLastChecked(new Date().toString());
        }
    }

    private String simulateWebsiteFetch(String url) {
        return "simulated-hash-" + System.currentTimeMillis() % 1000;
    }

}