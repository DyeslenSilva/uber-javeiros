package uber.com.menus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuPrincipal extends Application{

	private GridPane menuPrincipal;
	private Button motorista, passageiro;
	private	Stage stage;
	private MenuMotoristas menuMotoristas;
	private MenuPassageiros menuPassageiros;

	public MenuPrincipal() {
		menuPrincipal = new GridPane();
		
		motorista = new Button("Motoristas");
		passageiro = new Button("Passageiros");
	
		stage = new Stage();
		
		menuMotoristas = new MenuMotoristas();
		menuPassageiros = new MenuPassageiros();
	}
	
	
	private void gPane() {
		menuPrincipal.setPadding(new Insets(10,10,10,10));
		menuPrincipal.setVgap(10);
		menuPrincipal.setHgap(10);
	}
	
	
	private void comps()	{
		menuPrincipal.add(motorista, 0, 0);
		menuPrincipal.add(passageiro, 1, 0);
		
		
		motorista.setOnAction(soa ->{
			try {
				menuMotoristas.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		passageiro.setOnAction(soa ->{
			try {
				menuPassageiros.start(stage);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene cenario = new Scene(menuPrincipal, 320,50);
		gPane();
		comps();
		primaryStage.setScene(cenario);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
