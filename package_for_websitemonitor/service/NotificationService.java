package package_for_websitemonitor.service;
import package_for_websitemonitor.model.User;
import package_for_websitemonitor.observer.Observer;

public class NotificationService implements Observer {
    @Override
    public void update(String websiteUrl, String message) {
        // This is a simple notification for demonstration.
        System.out.println("Notification: " + message + " for " + websiteUrl);
    }

    public void sendNotification(User user, String message, String websiteUrl) {
        String channel = user.getPreferredChannel();
        String contact = user.getContactInfo();

        System.out.println("Sending " + channel + " notification to " + contact + ": " + message + " for website " + websiteUrl);
    }
}