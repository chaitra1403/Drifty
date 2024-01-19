package backend;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileDownloader_getSpotifyDownloadLink_b1bb9816bd_Test {

    @Test
    public void testGetSpotifyDownloadLink_ValidLink() {
        // Arrange
        String validSpotifyLink = "https://open.spotify.com/track/validLink";
        FileDownloader fileDownloader = new FileDownloader(null, null, null, null, null, null, null);

        // Act
        String result = fileDownloader.getSpotifyDownloadLink(validSpotifyLink);

        // Assert
        assertNotNull(result, "The function should return a valid download link");
        assertEquals(0, fileDownloader.getExitCode(), "The exit code should be 0");
    }

    @Test
    public void testGetSpotifyDownloadLink_SpotifyExclusiveLink() {
        // Arrange
        String spotifyExclusiveLink = "https://open.spotify.com/track/exclusiveLink";
        FileDownloader fileDownloader = new FileDownloader(null, null, null, null, null, null, null);

        // Act
        String result = fileDownloader.getSpotifyDownloadLink(spotifyExclusiveLink);

        // Assert
        assertNull(result, "The function should return null");
        assertEquals(1, fileDownloader.getExitCode(), "The exit code should be 1");
    }

    @Test
    public void testGetSpotifyDownloadLink_InvalidLink() {
        // Arrange
        String invalidSpotifyLink = "https://open.spotify.com/track/invalidLink";
        FileDownloader fileDownloader = new FileDownloader(null, null, null, null, null, null, null);

        // Act
        String result = fileDownloader.getSpotifyDownloadLink(invalidSpotifyLink);

        // Assert
        assertNull(result, "The function should return null");
        assertEquals(1, fileDownloader.getExitCode(), "The exit code should be 1");
    }

    @Test
    public void testGetSpotifyDownloadLink_ExceptionHandling() {
        // Arrange
        String exceptionLink = "https://open.spotify.com/track/exceptionLink";
        FileDownloader fileDownloader = new FileDownloader(null, null, null, null, null, null, null);

        // Act
        String result = fileDownloader.getSpotifyDownloadLink(exceptionLink);

        // Assert
        assertNull(result, "The function should return null");
        assertEquals(1, fileDownloader.getExitCode(), "The exit code should be 1");
    }

    @Test
    public void testGetSpotifyDownloadLink_RemoveQueryParameters() {
        // Arrange
        String spotifyLinkWithQuery = "https://open.spotify.com/track/linkWithQuery?si=query";
        FileDownloader fileDownloader = new FileDownloader(null, null, null, null, null, null, null);

        // Act
        String result = fileDownloader.getSpotifyDownloadLink(spotifyLinkWithQuery);

        // Assert
        assertNotNull(result, "The function should remove the query parameters and return a valid download link");
    }

    @Test
    public void testGetSpotifyDownloadLink_EmptyLink() {
        // Arrange
        String emptySpotifyLink = "";
        FileDownloader fileDownloader = new FileDownloader(null, null, null, null, null, null, null);

        // Act
        String result = fileDownloader.getSpotifyDownloadLink(emptySpotifyLink);

        // Assert
        assertNull(result, "The function should return null");
        assertEquals(1, fileDownloader.getExitCode(), "The exit code should be 1");
    }
}
