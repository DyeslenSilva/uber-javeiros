package uber.com.menus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uber.com.passageiros.telas.CadastroPassageiros;
import uber.com.passageiros.telas.ListaTodosPassageiros;
import uber.com.passageiros.telas.PassageiroPorCEP;
import uber.com.passageiros.telas.PassageiroPorCPF;
import uber.com.passageiros.telas.PassageiroPorEstado;

public class MenuPassageiros extends Application {
	
	private GridPane menuPassageiro;
	private Button cadastrarPassageiros, listaTodosOsPassageiros;
	private Button passageiroPorCPF, passageirosPorCEP,passageirosPorEstado;
	private Stage stage;
	
	public MenuPassageiros() {
			menuPassageiro = new GridPane();
			
			cadastrarPassageiros = new Button("Cadastro de Passageiros");
			listaTodosOsPassageiros = new Button("Lista Todos os Passageiros");
			passageiroPorCPF = new Button("Passageiros por CPF");
			passageirosPorCEP = new Button("Passageiro por CEP");
			passageirosPorEstado = new Button("Passageiro por Estado");
			
			stage = new Stage();
	}
	
	
	private void gPane() {
		menuPassageiro.setPadding(new Insets(10,10,10,10));
		menuPassageiro.setVgap(10);
		menuPassageiro.setHgap(10);
	}
	
	private void buttons() {
		menuPassageiro.add(cadastrarPassageiros, 0, 0);
		menuPassageiro.add(listaTodosOsPassageiros, 1, 0);
		menuPassageiro.add(passageiroPorCPF, 0,1);
		menuPassageiro.add(passageirosPorCEP, 1,1);
		menuPassageiro.add(passageirosPorEstado, 2, 1);
		
		cadastrarPassageiros.setOnAction(cp ->{
			try {
				CadastroPassageiros cadPass = new CadastroPassageiros();
				cadPass.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
		listaTodosOsPassageiros.setOnAction(ltp ->{
			try {
				ListaTodosPassageiros listaPass = new ListaTodosPassageiros();
				listaPass.start(stage);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
		
		passageiroPorCPF.setOnAction(cpf ->{
			try {
				PassageiroPorCPF passCPF = new PassageiroPorCPF();
				passCPF.start(stage);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		});
		
		passageirosPorCEP.setOnAction(cep ->{
			try {
				PassageiroPorCEP passCEp = new PassageiroPorCEP();
				passCEp.start(stage);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		});
		
		passageirosPorEstado.setOnAction(passEs->{
			try {
				PassageiroPorEstado passEstado = new PassageiroPorEstado();
				passEstado.start(stage);
			} catch (Exception e4) {
				e4.printStackTrace();
			}
		});
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene sc = new Scene(menuPassageiro,600,200);
		gPane();
		buttons();
		primaryStage.setScene(sc);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
