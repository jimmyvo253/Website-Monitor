package package_for_websitemonitor.model;

public class User {
    private String contactInfo;
    private String preferredChannel;

    public User(String contactInfo, String preferredChannel) {
        this.contactInfo = contactInfo;
        this.preferredChannel = preferredChannel;
    }

    public String getContactInfo() { return contactInfo; }
    public String getPreferredChannel() { return preferredChannel; }
    public void setPreferredChannel(String channel) { this.preferredChannel = channel; }
}