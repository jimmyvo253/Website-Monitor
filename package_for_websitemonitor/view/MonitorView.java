package package_for_websitemonitor.view;

public class MonitorView {
    public void showSubscriptionSuccess(String contactInfo, String url, String frequency) {
        System.out.println("Successfully subscribed " + contactInfo +
                         " to " + url + " with " + frequency + " monitoring");
    }

    public void showModificationSuccess(String contactInfo, String url, String newFrequency) {
        System.out.println("Successfully modified subscription for " + contactInfo +
                         " to " + url + " with new frequency: " + newFrequency);
    }

    public void showCancellationSuccess(String contactInfo, String url) {
        System.out.println("Successfully canceled subscription for " + contactInfo +
                          " to " + url);
    }

    public void showMonitoringStart() {
        System.out.println("Starting website monitoring service...");
    }

    public void showUpdateCheck() {
        System.out.println("Checking websites for updates...");
    }

    public void showInitialCheck(String url) {
        System.out.println("Initial check completed for " + url);
    }
}