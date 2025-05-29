package package_for_websitemonitor.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// Strategy 2: Compare exact HTML content
public class ExactHtmlComparison implements WebsiteComparison {
    @Override
    public boolean isContentChanged(String currentFile, String previousFile) throws IOException {
        byte[] currentContent = Files.readAllBytes(Path.of(currentFile));
        byte[] previousContent = Files.readAllBytes(Path.of(previousFile));
        return !java.util.Arrays.equals(currentContent, previousContent);
    }
}
