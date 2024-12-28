package moe.waffle.moemx.utils.http;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import moe.waffle.moemx.utils.ChatColorFormatter;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DiscordHttpPostHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, 0);
            exchange.close();

            return;
        }

        try {
            // getting the data as a JsonObject:
            InputStream is = exchange.getRequestBody();
            byte[] data = is.readAllBytes();
            String requestBody = new String(data, StandardCharsets.UTF_8);
            JsonObject requestData = JsonParser.parseString(requestBody).getAsJsonObject();

            // send message
            String broadcast = ChatColorFormatter.FormatToChatColors(String.format(
                "&8[&5%s via Discord&8]: &7%s", requestData.get("username").getAsString(), requestData.get("message").getAsString()
            ));
            Bukkit.broadcastMessage(broadcast);

            // tell the bot everything is OK!
            String response = "200 OK";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
