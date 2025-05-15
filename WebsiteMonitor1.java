

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// Main class to demonstrate the system
public class WebsiteMonitor1 {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        WebsMonitor monitor = new WebsMonitor(notificationService);

        // Example usage
        User user1 = new User("user1@example.com", "email");
        monitor.registerWebsite(user1, "https://youtube.com", "daily");

        User user2 = new User("user2@example.com", "sms");
        monitor.registerWebsite(user2, "https://google.com", "weekly");

        monitor.startMonitoring();
    }
}

// User class
class User {
    private String contactInfo;
    private String preferredChannel;

    public User(String contactInfo, String preferredChannel) {
        this.contactInfo = contactInfo;
        this.preferredChannel = preferredChannel;
    }

    // Getters and setters
    public String getContactInfo() { return contactInfo; }
    public String getPreferredChannel() { return preferredChannel; }
    public void setPreferredChannel(String channel) { this.preferredChannel = channel; }
}

// WebsiteSubscription class
class WebsiteSubscription {
    private User user;
    private String url;
    private String frequency;
    private String lastChecked;
    private String lastHash;

    public WebsiteSubscription(User user, String url, String frequency) {
        this.user = user;
        this.url = url;
        this.frequency = frequency;
    }

    // Getters and setters
    public User getUser() { return user; }
    public String getUrl() { return url; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public String getLastChecked() { return lastChecked; }
    public void setLastChecked(String lastChecked) { this.lastChecked = lastChecked; }
    public String getLastHash() { return lastHash; }
    public void setLastHash(String lastHash) { this.lastHash = lastHash; }
}

// NotificationService class
class NotificationService {
    public void sendNotification(User user, String message, String websiteUrl) {
        String channel = user.getPreferredChannel();
        String contact = user.getContactInfo();

        // In a real implementation, this would actually send notifications
        System.out.println("Sending " + channel + " notification to " + contact +
                         ": " + message + " for website " + websiteUrl);
    }
}

// WebsiteMonitor class
class WebsMonitor {
    private List<WebsiteSubscription> subscriptions;
    private NotificationService notificationService;

    public WebsMonitor(NotificationService notificationService) {
        this.subscriptions = new ArrayList<>();
        this.notificationService = notificationService;
    }

    public void registerWebsite(User user, String url, String frequency) {
        WebsiteSubscription subscription = new WebsiteSubscription(user, url, frequency);
        subscriptions.add(subscription);
        // Print success message
        System.out.println("Successfully subscribed " + user.getContactInfo() +
                         " to " + url + " with " + frequency + " monitoring");
    }

    public void modifySubscription(User user, String url, String newFrequency) {
        for (WebsiteSubscription sub : subscriptions) {
            if (sub.getUser().equals(user) && sub.getUrl().equals(url)) {
                sub.setFrequency(newFrequency);
                System.out.println("Successfully modified subscription for " + user.getContactInfo() +
                                 " to " + url + " with new frequency: " + newFrequency);
                break;
            }
        }
    }

    public void cancelSubscription(User user, String url) {
        subscriptions.removeIf(sub -> sub.getUser().equals(user) && sub.getUrl().equals(url));
        System.out.println("Successfully canceled subscription for " + user.getContactInfo() +
                          " to " + url);
    }

    public void startMonitoring() {
        System.out.println("Starting website monitoring service...");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkWebsitesForUpdates();
            }
        }, 0, 24 * 60 * 60 * 1000); // Check daily (simplified)
    }

    private void checkWebsitesForUpdates() {
        System.out.println("Checking websites for updates...");
        for (WebsiteSubscription sub : subscriptions) {
            // In a real implementation, this would actually fetch and compare website content
            String currentHash = simulateWebsiteFetch(sub.getUrl());

            if (sub.getLastHash() == null) {
                sub.setLastHash(currentHash);
                System.out.println("Initial check completed for " + sub.getUrl());
            } else if (!sub.getLastHash().equals(currentHash)) {
                notificationService.sendNotification(
                    sub.getUser(),
                    "Website content has changed!",
                    sub.getUrl()
                );
                sub.setLastHash(currentHash);
            }

            sub.setLastChecked(new Date().toString());
        }
    }

    private String simulateWebsiteFetch(String url) {
        // Simulate fetching website content and generating a hash
        return "simulated-hash-" + System.currentTimeMillis() % 1000;
    }
}