package uber.com.utils.model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DDD {

    public static int obterDDD(String cep) {
        int ddd = -1; // Valor padrão se o DDD não for encontrado
        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(response.toString());

                JsonNode dddNode = jsonNode.get("ddd");
                if (dddNode != null) {
                    ddd = dddNode.asInt();
                } else {
                    System.out.println("Campo 'ddd' não encontrado no JSON retornado pela API.");
                }
            } else {
                System.out.println("Falha na requisição HTTP: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ddd;
    }

    public static void main(String[] args) {
        String cep = "06654710"; // Exemplo de CEP
        int ddd = obterDDD(cep);
        System.out.println("O DDD correspondente ao CEP " + cep + " é: " + ddd);
    }
}
