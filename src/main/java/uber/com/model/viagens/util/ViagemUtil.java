package uber.com.model.viagens.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

public class ViagemUtil {

    private static String apikey = "AIzaSyAwTNMb_-paJSpt45bfZi8PXJ44ycnGBtM";

	private static LatLng obterCoordenadasPorCEP(String cep) {
        try {
            GeoApiContext context = new GeoApiContext.Builder().apiKey(apikey ).build();
            GeocodingResult[] results = GeocodingApi.geocode(context, cep).await();
            if (results != null && results.length > 0) {
                return results[0].geometry.location;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static double calcularDistancia(LatLng origem, LatLng destino) {
    	double earthRadius = 6371;
    	double dLat = Math.toRadians(destino.lat - origem.lat);
    	double dLng = Math.toRadians(destino.lng - origem.lng);
    	
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(origem.lat)) * Math.cos(Math.toRadians(destino.lat)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }
    
    
    public static double calcularPrecoDaPassagem(double distancia) {
    	double precoBasePorKM = 1.5;
    	double adicionalPorKm = 0.5;
    	double preco;
    	
    	
    	preco = Math.abs(precoBasePorKM * distancia + adicionalPorKm * distancia);
    	return preco;
    }
    

	public static void main(String[] args) throws MalformedURLException, IOException {
		String cepOrigem = "06654710";
		String cepDestino = "06600140";
		
		LatLng origem = obterCoordenadasPorCEP(cepOrigem);
		LatLng destino = obterCoordenadasPorCEP(cepDestino);
		
		if(origem!=null && destino!=null) {
			double distancia  = calcularDistancia(origem, destino);
			
			if(distancia >0) {
				double preco = calcularPrecoDaPassagem(distancia);
				DecimalFormat df = new DecimalFormat("#.##");
				
				
				System.out.println("Preco da passagem: "+df.format(preco));
			}else {
				System.err.println("Nao foi possivel calcular o preco da passagem");
			}
		}else {
			System.err.println("Nao foi possivel obter as coordenadas para o CEPs: "+cepOrigem+" "+cepDestino);
		}
	}
	
}
