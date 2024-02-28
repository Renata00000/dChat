package br.com.smsdchat.smsIdeen.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmsUnico {

    public static void main(String[] args) {
        try {
            // Endpoint de envio de SMS único da API dChat
            URL url = new URL("https://sandbox.dchat.com.br/api/sms/single");

            // Credenciais de autenticação
            String authToken = "8|CiMHt0CyAVdXVvRDmZvGldfOLx7yT7ury2sBiBZw"; //TOKEN_AQUI.
            String requestBody = "{\"name\": \"ENVIOUNICO\", \"message\": \"This is my message!\", \"phone\": \"5511999999999\", \"reference\": \"reprehenderit\"}";


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + authToken);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);


            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());
            outputStream.flush();


            int responseCode = connection.getResponseCode();
            System.out.println("Código de Status da Resposta: " + responseCode);


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


            System.out.println("Resposta da API dChat:");
            System.out.println(response.toString());


            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

