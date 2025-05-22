package package_for_websitemonitor.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import package_for_websitemonitor.model.User;
import package_for_websitemonitor.model.WebsiteSubscription;
import package_for_websitemonitor.observer.Observer;
import package_for_websitemonitor.observer.Subject;

public class WebsMonitor implements Subject {
    private List<WebsiteSubscription> subscriptions;
    private List<Observer> observers;
    // private Random random = new Random(); // For simulating changes

    public WebsMonitor(NotificationService notificationService) {
        this.subscriptions = new ArrayList<>();
        this.observers = new ArrayList<>();
    }
     @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String websiteUrl, String message) {
        for (Observer observer : observers) {
            observer.update(websiteUrl, message);
        }
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
        }, 0, 10 * 1000); // Check daily
    }

    public void checkWebsitesForUpdates() {
        System.out.println("Checking websites for updates...");
        // Ensure the directory exists
        Path snapshotDir = Path.of("html_snapshots");
        if (!Files.exists(snapshotDir)) {
            try {
                Files.createDirectories(snapshotDir);
            } catch (IOException e) {
                System.err.println("Could not create html_snapshots directory: " + e.getMessage());
                return;
            }
        }
        for (WebsiteSubscription sub : subscriptions) {
            System.out.println("Checking: " + sub.getUrl());
            try {
                String url = sub.getUrl();
                String currentFile = "html_snapshots/" + url.hashCode() + "_current.html";
                String previousFile = "html_snapshots/" + url.hashCode() + "_previous.html";

                // Download current HTML
                HtmlDownloader.downloadHtmlToFile(url, currentFile);

                // Compare with previous version
                boolean updated = false;
                if (Files.exists(Path.of(previousFile))) {
                    updated = HtmlDownloader.isFileUpdated(previousFile, currentFile);
                } else {
                    // First time, treat as updated
                    updated = true;
                }

                if (updated) {
                    System.out.println("Website updated: " + url);
                    notifyObservers(url, "Website updated!");
                    Files.copy(Path.of(currentFile), Path.of(previousFile), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                } else {
                    System.out.println("No changes detected for: " + url);
                }

                sub.setLastChecked(new java.util.Date().toString());

            } catch (Exception e) {
                System.err.println("Failed to check " + sub.getUrl() + ": " + e.getMessage());
            }
        }
    }

    private String fetchWebsiteContent(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    private String generateContentHash(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(content.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return Integer.toString(content.hashCode()); // Fallback
        }
    }
}