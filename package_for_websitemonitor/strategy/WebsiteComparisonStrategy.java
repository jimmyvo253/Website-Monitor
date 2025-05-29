package package_for_websitemonitor.strategy;

import java.io.IOException;

public interface WebsiteComparisonStrategy {
    boolean isContentChanged(String currentFile, String previousFile) throws IOException;
}