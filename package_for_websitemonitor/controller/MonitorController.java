package package_for_websitemonitor.controller;

import package_for_websitemonitor.model.User;
import package_for_websitemonitor.service.NotificationService;
import package_for_websitemonitor.service.WebsMonitor;
import package_for_websitemonitor.strategy.*;
import package_for_websitemonitor.view.MonitorView;

public class MonitorController {
    private WebsMonitor monitor;
    private MonitorView view;

    public MonitorController() {
        NotificationService notificationService = new NotificationService();
        this.monitor = new WebsMonitor(notificationService);
        this.view = new MonitorView();
    }

    public void setComparisonStrategy(String strategyType) {
        switch (strategyType.toLowerCase()) {
            case "size":
                monitor.setComparisonStrategy(new SizeComparison());
                break;
            case "text":
                monitor.setComparisonStrategy(new TextContentComparison());
                break;
            case "exact":
            default:
                monitor.setComparisonStrategy(new ExactHtmlComparison());
        }
    }

    public void registerUserWebsite(User user, String url, String frequency) {
        monitor.registerWebsite(user, url, frequency);
        view.showSubscriptionSuccess(user.getContactInfo(), url, frequency);
    }

    public void modifyUserSubscription(User user, String url, String newFrequency) {
        monitor.modifySubscription(user, url, newFrequency);
        view.showModificationSuccess(user.getContactInfo(), url, newFrequency);
    }

    public void cancelUserSubscription(User user, String url) {
        monitor.cancelSubscription(user, url);
        view.showCancellationSuccess(user.getContactInfo(), url);
    }

    public void startMonitoring() {
        monitor.startMonitoring();
        view.showMonitoringStart();
    }
}