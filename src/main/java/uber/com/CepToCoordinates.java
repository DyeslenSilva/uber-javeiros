	
	package uber.com;
	
	
	import java.io.IOException;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

	public class CepToCoordinates {
	
		private static String apiKey ="AIzaSyAwTNMb_-paJSpt45bfZi8PXJ44ycnGBtM";
	
	    public static void main(String[] args) throws ApiException, InterruptedException, IOException {
	        String cep = "06654710";
	        LatLng coordenadas = obterCoordenadasPorCEP(cep);
	        if(coordenadas!=null) {
	        	System.out.println("Latitude: "+coordenadas.lat);
	        	System.out.println("Longitude: "+coordenadas.lng);
	        }
	    }
	    
	    private static LatLng obterCoordenadasPorCEP(String cep) throws ApiException, InterruptedException, IOException {
	    	GeoApiContext context = new GeoApiContext
	    				.Builder().apiKey(apiKey).build();
	    	GeocodingResult[] result = GeocodingApi.geocode(context, cep).await();
	    	if(result != null && result.length>0) {
	    		return result[0].geometry.location;
	    	}
	    	return null;
	    
	    }
	

	}
