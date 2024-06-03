import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/", new MyHandler());

        server.createContext("/search", new Search());

        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String responseFromDB = Select.SelectRow();

            String responseHTML = "<html>" +
                    "<head>" +
                    "<meta charset=\"UTF-8\">" +
                    "</head> " +
                   "<style>" +
                        "form {" +
                            "display: inline-block;" +
                            "margin-right: 20px;" +
                        "}" +
                    "</style>" +
                    "<body>" +
                        "<h1>Документы</h1>" +
                            "<form action=\"/search\" method=\"POST\">" +
                                "<p>Поиск по дате</p>" +
                                "<p><input id=\"searchInput\" name=\"searchInput\" maxlength=\"25\" size=\"13\"></p>" +
                                "<button type=\"submit\">Найти</button>" +
                            "</form>" +
                            "<form>" +
                                "<p>Поиск по типу</p>" +
                                "<p><input maxlength=\"25\" size=\"13\"></p>" +
                                "<button>Найти</button>" +
                            "</form>" +
                            "<form>" +
                                "<p>Поиск по дате</p>" +
                                "<p><input maxlength=\"25\" size=\"13\"></p>" +
                                "<button>Найти</button>" +
                            "</form>" +
                        responseFromDB +
                    "</body>" +
                    "</html>";
            exchange.sendResponseHeaders(200, responseHTML.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(responseHTML.getBytes());
            os.close();
        }
    }

}
