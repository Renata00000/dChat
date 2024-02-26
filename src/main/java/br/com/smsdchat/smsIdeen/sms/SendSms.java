package br.com.smsdchat.smsIdeen.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendSms{

    public static void main(String[] args) {
        try {
            // Endpoint de envio de campanha SMS da API dChat
            URL url = new URL("https://sandbox.dchat.com.br/api/sms/campaign");

            // Credenciais de autenticação
            String authToken = "8|CiMHt0CyAVdXVvRDmZvGldfOLx7yT7ury2sBiBZw";

            // Corpo da solicitação JSON
            //lin opcional, caso queira mudar de false para true e add o link na url
            String requestBody = "{\"name\": \"Minha Campanha1\", \"message\": \"agora foi!\", \"use_link\": true, \"url\": \"https://github.com/Renata00000\", \"tokens\": [{ \"phone\": \"5515997530209\", \"reference\": 1 }, { \"phone\": \"5515997318966\", \"reference\": 2 }, { \"phone\": \"55599601181\", \"reference\": 3 }]}";



            // Configuração da conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + authToken);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Envio do corpo da solicitação
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());
            outputStream.flush();

            // Verifica o código de status da resposta
            int responseCode = connection.getResponseCode();
            System.out.println("Código de Status da Resposta: " + responseCode);

            // Leitura da resposta da API
            BufferedReader reader;
            if (responseCode == HttpURLConnection.HTTP_OK) { // Se a resposta for 200 (sucesso)
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else { // Se houver um erro
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n"); // Adiciona uma quebra de linha após cada linha da resposta
            }
            reader.close();

            // Exibição da resposta da API
            System.out.println("Resposta da API dChat:");
            System.out.println(response.toString());

            // Fechamento da conexão
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





