package package_for_websitemonitor.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// Strategy 1: Compare file sizes
public class SizeComparison implements WebsiteComparison {
    @Override
    public boolean isContentChanged(String currentFile, String previousFile) throws IOException {
        long currentSize = Files.size(Path.of(currentFile));
        long previousSize = Files.size(Path.of(previousFile));
        return currentSize != previousSize;
    }
}
