package package_for_websitemonitor.model;

public class WebsiteSubscription {
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
    public User getUser() { return user; }
    public String getUrl() { return url; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public String getLastChecked() { return lastChecked; }
    public void setLastChecked(String lastChecked) { this.lastChecked = lastChecked; }
    public String getLastHash() { return lastHash; }
    public void setLastHash(String lastHash) { this.lastHash = lastHash; }
}
