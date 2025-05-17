package package_for_websitemonitor;

import package_for_websitemonitor.model.User;
import package_for_websitemonitor.service.NotificationService;
import package_for_websitemonitor.service.WebsMonitor;

public class Main {
    public static void main(String[] args) {
       try {
        NotificationService notificationService = new NotificationService();
        WebsMonitor monitor = new WebsMonitor(notificationService);

        User user1 = new User("user1@example.com", "email");
        monitor.registerWebsite(user1, "https://www.wikipedia.org/", "daily");

        monitor.startMonitoring();

    } catch (Exception e) {
        System.err.println("Fatal error: " + e.getMessage());
        e.printStackTrace();
    }
    }
}