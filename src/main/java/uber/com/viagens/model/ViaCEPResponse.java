package uber.com.viagens.model;

import lombok.Data;

@Data
public class ViaCEPResponse {

	
	private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
    private String latitude;
    private String longitude;
    
    
    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    
}
