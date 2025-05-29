package package_for_websitemonitor.strategy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// Strategy 3: Compare only text content (ignoring HTML tags/whitespace)
public class TextContentComparisonStrategy implements WebsiteComparisonStrategy {
    @Override
    public boolean isContentChanged(String currentFile, String previousFile) throws IOException {
        String currentText = extractText(Files.readString(Path.of(currentFile)));
        String previousText = extractText(Files.readString(Path.of(previousFile)));
        return !currentText.equals(previousText);
    }

    private String extractText(String html) {
        // Simple HTML tag removal - consider using Jsoup for robust parsing
        return html.replaceAll("<[^>]*>", "").trim();
    }
}