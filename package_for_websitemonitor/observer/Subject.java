package package_for_websitemonitor.observer;

public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(String websiteUrl, String message);
}
