package uber.com.model.viagens.util;

import java.text.DecimalFormat;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.Duration;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class CalcularDistancia {
	
	private static String apiKey ="AIzaSyAwTNMb_-paJSpt45bfZi8PXJ44ycnGBtM";
				
	public static double calcularDistancia(String cepOrigem, String cepDestino) {
		LatLng 	coordOrigem = getCoordenadasPorCEP(cepOrigem);
		LatLng coordDestino = getCoordenadasPorCEP(cepDestino);
		
		if(coordOrigem!=null && coordDestino!=null) {
			return calcularDistancia(coordOrigem, coordDestino);
		}
		return -1;
	}
		
	private static LatLng getCoordenadasPorCEP(String cep) {
		try {
			GeoApiContext apiContext = new GeoApiContext.Builder().
					apiKey(apiKey).build();
			GeocodingResult[] results = GeocodingApi.geocode(apiContext, cep).await();
			if(results!=null && results.length>0) {
				return results[0].geometry.location;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static double calcularDistancia(LatLng coord1 , LatLng coord2) {
		double raioDaTerra = 6371;
		
		double dLat = Math.toRadians(coord2.lat - coord1.lat);
		double dLng = Math.toRadians(coord2.lng - coord1.lng);
		
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(coord1.lat)) * Math.cos(Math.toRadians(coord2.lat)) *
                Math.sin(dLng / 2) * Math.sin(dLng / 2);

     double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

     return raioDaTerra * c;
	}
	
	
	public static String calculcarTempoDeViagem(String cepOrigem,String cepDestino) {
		try {
			GeoApiContext geoAPIContext = new GeoApiContext.Builder().apiKey(apiKey).build();
			DirectionsResult dirRes = DirectionsApi.
						getDirections(geoAPIContext, cepOrigem, cepDestino).await();
			
			if(dirRes.routes != null && dirRes.routes.length>0) {
				DirectionsRoute route = dirRes.routes[0];
				Duration durations = route.legs[0].duration;
				return formatarTempo(durations.inSeconds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	return "Erro ao calcular o tempo de viagem";
	
	}
	
	
	private static String formatarTempo(long segundos) {
		long horas = segundos /3600;
		long minutos = (segundos%3600)/60;
		long segundosRestantes = segundos/60;
        return String.format("%02d:%02d:%02d", horas, minutos, segundosRestantes);
	}
	
	

	public static void main(String[] args) {
		String cepOrigem = "06654710";
		String cepDestino = "06600140";
		
		double distancia = calcularDistancia(cepOrigem, cepDestino);
		DecimalFormat df = new DecimalFormat("#.###");
		String distanciaArre = df.format(distancia);
		if(distancia>0) {
			System.out.println("Distancia entre os dois pontos: "+distanciaArre+" km");
			System.out.println("Tempo de viagem: "+calculcarTempoDeViagem(cepOrigem, cepDestino));
		}else {
			System.out.println("Erro ao calcular distancia");
		}
		
	}
}
