package package_for_websitemonitor;

import package_for_websitemonitor.controller.MonitorController;
import package_for_websitemonitor.model.User;

public class Main {
    public static void main(String[] args) {
        MonitorController controller = new MonitorController();

        // Example usage
        User user1 = new User("votansang253@gmail.com.com", "email");
        controller.registerUserWebsite(user1, "https://youtube.com", "daily");

        User user2 = new User("10422114@student.vgu.edu.vn", "sms");
        controller.registerUserWebsite(user2, "https://google.com", "weekly");

        controller.startMonitoring();
    }
}