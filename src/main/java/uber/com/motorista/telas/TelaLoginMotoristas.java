package uber.com.motorista.telas;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaLoginMotoristas extends Application{

	private GridPane telaLoginMotorista;
	private Label lbIdMotorista, lbSenha;
	private TextField txIDMotorista;
	private PasswordField psSenha;
	private Button btLogin;
	
	
	public TelaLoginMotoristas() {
		telaLoginMotorista = new GridPane();
		
		lbIdMotorista = new Label("ID Motorista");
		lbSenha = new Label("Senha");
		
		txIDMotorista = new TextField();
		psSenha = new PasswordField();
		
		btLogin = new Button("Login");
	}
	
	private void gPane() {
		telaLoginMotorista.setPadding(new Insets(10,10,10,10));
		telaLoginMotorista.setHgap(10);
		telaLoginMotorista.setVgap(10);
	}
	
	
	private void comps() {
		telaLoginMotorista.add(lbIdMotorista, 0, 0);
		telaLoginMotorista.add(lbSenha, 0, 1);
		
		telaLoginMotorista.add(txIDMotorista, 1, 0);
		telaLoginMotorista.add(psSenha, 1, 1);
		
		telaLoginMotorista.add(btLogin, 1, 2);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene cenario = new Scene(telaLoginMotorista,300,200);
		gPane();
		comps();
		primaryStage.setScene(cenario);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
