package package_for_websitemonitor.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class HtmlDownloader {

    // Downloads the HTML content of the given URL and saves it to the specified file path
    public static void downloadHtmlToFile(String url, String filePath) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        try (PrintWriter out = new PrintWriter(filePath)) {
            out.print(response.body());
        }
    }

    // Compares two files and returns true if their contents are different
    public static boolean isFileUpdated(String filePath1, String filePath2) throws IOException {
        byte[] file1 = Files.readAllBytes(Path.of(filePath1));
        byte[] file2 = Files.readAllBytes(Path.of(filePath2));
        return !Objects.deepEquals(file1, file2);
    }
}