package moe.waffle.moemx.utils.http;

import org.bukkit.Bukkit;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;

public class PostMessagesToURL {
    public static void PostMessage(String contentAsJson) {
        try {
            // HTTPS URL
            URL url = new URL("https://bot.lilycatgirl.dev/okabot/minecraft");

            // Open connection
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Write JSON payload
            try (OutputStream os = connection.getOutputStream()) {
                os.write(contentAsJson.getBytes());
                os.flush();
            }

            // Optional: Check the response code
            int responseCode = connection.getResponseCode();
            Bukkit.getLogger().info("(moemx) POST Response code: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
