package package_for_websitemonitor.service;

import package_for_websitemonitor.model.User;

public class NotificationService {
    public void sendNotification(User user, String message, String websiteUrl) {
        String channel = user.getPreferredChannel();
        String contact = user.getContactInfo();

        System.out.println("Sending " + channel + " notification to " + contact + ": " + message + " for website " + websiteUrl);
    }
}