package org.example;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class BuscaApi {
    public static void main(String[] args) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/e816f79aab17468e0a15569f/latest/USD"))
                    .GET()
                    .timeout(Duration.ofSeconds(5))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            System.out.println("-------------------------------------------------");
            Gson gson = new Gson();
            Conversao listaJSON = gson.fromJson(response.body(), Conversao.class);
            System.out.println(listaJSON.conversion_rates.ARS);
            System.out.println(listaJSON.conversion_rates.BOB);
            System.out.println(listaJSON.conversion_rates.BRL);
            System.out.println(listaJSON.conversion_rates.CLP);
            System.out.println(listaJSON.conversion_rates.COP);
            System.out.println(listaJSON.conversion_rates.USD);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

   }
   static class Conversao {
        Moedas conversion_rates;
   }
   static class Moedas {
        // Estas taxas de câmbio são comparadas ao valor do dólar
        String ARS, BOB, BRL, CLP, COP, USD;
   }
}
