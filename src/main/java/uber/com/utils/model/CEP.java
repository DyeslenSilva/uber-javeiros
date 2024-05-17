package uber.com.utils.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CEP {

    public static Map<String, String> consultarCEP(String cep) {
        Map<String, String> resultado = new HashMap<>();
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

                String logradouro = jsonNode.get("logradouro").asText();
                String cidade = jsonNode.get("localidade").asText();
                String estado = jsonNode.get("uf").asText();
                String ddd = jsonNode.get("ddd").asText(); 
                
                resultado.put("logradouro", logradouro);
                resultado.put("cidade", cidade);
                resultado.put("estado", estado);
                resultado.put("ddd", ddd); // Adiciona o DDD ao mapa de resultados
            } else {
                System.out.println("Falha na requisição HTTP: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    // O restante do código permanece o mesmo
}


