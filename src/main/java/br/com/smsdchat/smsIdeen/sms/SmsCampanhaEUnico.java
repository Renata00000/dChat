package br.com.smsdchat.smsIdeen.sms;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmsCampanhaEUnico {

    public static void main(String[] args) {
        try {
            //            ENDPOINT DE CAMPANHA

            String url = "https://sandbox.dchat.com.br/api/sms/campaign";
            String requestBody = "{\"name\": \"tenetur\", \"message\": \"This is my message with {LINK}\", \"use_link\": true, \"url\": \"https://dchat.com.br\", \"tokens\": [\"et\"]}";


            // ENDPOIN DE SMS UNICO

//            String url = "https://sandbox.dchat.com.br/api/sms/single";
//            String requestBody = "{\"name\": \"LINCADOunico\", \"message\": \"This is my message!\", \"phone\": \"5511999999999\", \"reference\": \"reprehenderit\"}";



            // Verifica se o requestBody contém tokens, o que indica uma campanha
            if (requestBody.contains("tokens")) {
                url = "https://sandbox.dchat.com.br/api/sms/campaign";
                requestBody = "{\"name\": \"LINCADOcampanha\", \"message\": \"Aqui vai sua mensagem para a campanha.\", \"use_link\": false, \"tokens\": [{ \"phone\": \"5515997530209\", \"reference\": 1 }, { \"phone\": \"5515997318966\", \"reference\": 2 }, { \"phone\": \"55599601181\", \"reference\": 3 }]}";
            }

            // Configuração da conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " +"8|CiMHt0CyAVdXVvRDmZvGldfOLx7yT7ury2sBiBZw");//TOKEN AKI.
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
                response.append(line).append("\n");
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
