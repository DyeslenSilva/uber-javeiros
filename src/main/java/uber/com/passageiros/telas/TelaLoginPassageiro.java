package uber.com.passageiros.telas;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uber.com.dao.PassageiroDAO;
import uber.com.model.Passageiros;
import uber.com.viagens.telas.SolicitarViagem;

public class TelaLoginPassageiro extends Application{

	private GridPane telaLogin;
	private Label lbLogin, lbSenha;
	private TextField txLogin;
	private PasswordField psSenha;
	private Button btLogin;
	
	
	public TelaLoginPassageiro() {
		telaLogin = new GridPane();
		
		lbLogin = new Label("Login");
		lbSenha = new Label("Senha");
		
		txLogin = new TextField();
		psSenha = new PasswordField();
		
		btLogin = new Button("Login");	           
	}
	
	
	private void comps() {
		telaLogin.add(lbLogin, 0, 0);
		telaLogin.add(lbSenha, 0, 1);
		
		telaLogin.add(txLogin, 1, 0);
		telaLogin.add(psSenha, 1, 1);
		
		telaLogin.add(btLogin, 1, 2);
		
		btLogin.setOnAction(login ->{
			String cpf = txLogin.getText();
			String telefone = psSenha.getText();
			
			PassageiroDAO passDAO = new PassageiroDAO();
			Passageiros passageiros = passDAO.loginPassageiros(cpf, telefone);
			
			if (passageiros !=null) {
				solicitarViagem(passageiros);
			}else {
				mostrarAlertaErro("Credenciais Invalidas");
			}
		});	
	}
	
	
	private void mostrarAlertaErro(String mensagem) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}

	
	
	private void solicitarViagem(Passageiros pass) {
		SolicitarViagem solicitaViagem = new SolicitarViagem();
		
		solicitaViagem.txCPF.setText(pass.getCpf());
		solicitaViagem.txNomeDoPassageiro.setText(pass.getNomePassageiro());
		
		solicitaViagem.labels();
		solicitaViagem.textField();
		solicitaViagem.button();
		solicitaViagem.combobox();
		
	    Stage stage = new Stage();
	    Scene cena = new Scene(solicitaViagem.gPane(), 1200, 600);
	    stage.setScene(cena);
	    stage.show();

	}
	
	private void gPane() {
		telaLogin.setPadding(new Insets(10,10,10,10));
		telaLogin.setHgap(10);
		telaLogin.setVgap(10);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene cenario = new Scene(telaLogin,400,200);
		comps();
		gPane();
		primaryStage.setScene(cenario);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
