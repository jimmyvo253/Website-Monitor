package package_for_websitemonitor.strategy;

import java.io.IOException;

public interface WebsiteComparison {
    boolean isContentChanged(String currentFile, String previousFile) throws IOException;
}