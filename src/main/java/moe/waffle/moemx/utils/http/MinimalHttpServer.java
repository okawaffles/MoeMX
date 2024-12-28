package moe.waffle.moemx.utils.http;


import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class MinimalHttpServer {
    private static HttpServer server;
    public static void CreateServer() throws Exception {
        server = HttpServer.create(new InetSocketAddress(9258), 0);
        server.createContext("/discord", new DiscordHttpPostHandler());
        server.start();
    }

    public static void ShutdownServer() {
        server.stop(0);
    }
}
