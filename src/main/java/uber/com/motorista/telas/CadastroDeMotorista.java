	package uber.com.motorista.telas;
	
	import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uber.com.dao.MotoristaDAO;
import uber.com.model.Motorista;
import uber.com.utils.dao.EstadoDAO;
import uber.com.utils.model.CEP;
import uber.com.utils.model.DDD;
import uber.com.utils.model.Sorteios;

	public class CadastroDeMotorista extends Application {

	    private GridPane cadastroDeMotorista;
	    private Label lbIdMotorista, lbCPFMotorista, lbNomeDoMotorista;
	    private Label lbCEP, lbEndereco, lbnLocal, lbCidade, lbEstado;
	    private Label lbDDD, lbTelefone;

	    private TextField txIdMotorista, txCPFMotorista, txNomeDoMotorista;
	    private TextField txCEP, txEndereco, txNLocal, txCidade;
	    private TextField txDDD, txTelefone;
	    private ComboBox<String> cbEstado;
	    private Button btGerarIDMotorista, btGerarCPF, btLocalizarCEP;
	    private Button btCadastrarMotorista;

	    public CadastroDeMotorista() {
	        cadastroDeMotorista = new GridPane();

	        lbIdMotorista = new Label("Codigo do Motorista");
	        lbCPFMotorista = new Label("CPF");
	        lbNomeDoMotorista = new Label("Nome do Motorista");
	        lbDDD = new Label("DDD");
	        lbTelefone = new Label("Telefone");
	        lbCEP = new Label("CEP");
	        lbEndereco = new Label("Endereco");
	        lbnLocal = new Label("N Local");
	        lbCidade = new Label("Cidade");
	        lbEstado = new Label("Estado");

	        txIdMotorista = new TextField();
	        txCPFMotorista = new TextField();
	        txNomeDoMotorista = new TextField();
	        txDDD = new TextField();
	        txTelefone = new TextField();
	        txCEP = new TextField();
	        txEndereco = new TextField();
	        txNLocal = new TextField();
	        txCidade = new TextField();

	        cbEstado = new ComboBox<String>();

	        btGerarIDMotorista = new Button("Gerar Codigo do Motorista");
	        btGerarCPF = new Button("Gerar CPF");
	        btLocalizarCEP = new Button("Localizar CEP");
	        btCadastrarMotorista = new Button("Cadastrar Motorista");
	    }

	    private void gPane() {
	        cadastroDeMotorista.setPadding(new Insets(10, 10, 10, 10));
	        cadastroDeMotorista.setHgap(10);
	        cadastroDeMotorista.setVgap(10);
	    }

	    private void labels() {
	        cadastroDeMotorista.add(lbIdMotorista, 0, 0);
	        cadastroDeMotorista.add(lbCPFMotorista, 0, 1);
	        cadastroDeMotorista.add(lbNomeDoMotorista, 0, 2);

	        cadastroDeMotorista.add(lbCEP, 0, 3);
	        cadastroDeMotorista.add(lbEndereco, 0, 4);
	        cadastroDeMotorista.add(lbnLocal, 0, 5);
	        cadastroDeMotorista.add(lbCidade, 0, 6);
	        cadastroDeMotorista.add(lbEstado, 0, 7);
	        cadastroDeMotorista.add(lbDDD, 0, 8);
	        cadastroDeMotorista.add(lbTelefone, 0, 9);
	    }

	    private void textField() {
	        cadastroDeMotorista.add(txIdMotorista, 1, 0);
	        cadastroDeMotorista.add(txCPFMotorista, 1, 1);
	        cadastroDeMotorista.add(txNomeDoMotorista, 1, 2);
	        cadastroDeMotorista.add(txCEP, 1, 3);
	        cadastroDeMotorista.add(txEndereco, 1, 4);
	        cadastroDeMotorista.add(txNLocal, 1, 5);
	        cadastroDeMotorista.add(txCidade, 1, 6);
	        cadastroDeMotorista.add(cbEstado, 1, 7);
	        cadastroDeMotorista.add(txDDD, 1, 8);
	        cadastroDeMotorista.add(txTelefone, 1, 9);
	    }

	    private void comboBox() {
	        EstadoDAO estadoDAO = new EstadoDAO();

	        List<String> estados = estadoDAO.listaTodosEstados();
	        cbEstado.getItems().addAll(estados);
	    }

	    private void buttons() {
	        cadastroDeMotorista.add(btGerarIDMotorista, 2, 0);
	        cadastroDeMotorista.add(btGerarCPF, 2, 1);
	        cadastroDeMotorista.add(btLocalizarCEP, 2, 3);
	        cadastroDeMotorista.add(btCadastrarMotorista, 1, 10);

	        btGerarIDMotorista.setOnAction(gid -> {
	            int idMotorista = Sorteios.gerarIDMotorista();
	            txIdMotorista.setText(Integer.toString(idMotorista));
	        });

	        btGerarCPF.setOnAction(gcpf -> {
	            String cpf = Sorteios.gerarCPF();
	            txCPFMotorista.setText(cpf);
	        });

	    btLocalizarCEP.setOnAction(locCEP->{
	    	String cep = txCEP.getText();
	    	Map<String, String> endereco = CEP.consultarCEP(cep);
	    	int ddd = DDD.obterDDD(cep);
	    	
	    	
	    	String logradouro = endereco.get("logradouro");
	    	String cidade = endereco.get("cidade");
	    	
	    	txEndereco.setText(logradouro);
	    	txCidade.setText(cidade);
	    	txDDD.setText(String.valueOf(ddd));
	    });
	        
	        btCadastrarMotorista.setOnAction(cm -> {
	            int idMotorista = Integer.parseInt(txIdMotorista.getText());
	            String cpf = txCPFMotorista.getText();
	            String nomeMotorista = txNomeDoMotorista.getText();
	            String cep = txCEP.getText();
	            String endereco = txEndereco.getText();
	            int nLocal = Integer.parseInt(txNLocal.getText());
	            String cidade = txCidade.getText();
	            String estado = cbEstado.getValue();
	            int ddd = Integer.parseInt(txDDD.getText());
	            String telefone = txTelefone.getText();
	            Motorista motorista = new Motorista();
	            MotoristaDAO motoristaDAO = new MotoristaDAO();

	            motorista.setIdMotorista(idMotorista);
	            motorista.setCpf(cpf);
	            motorista.setNomeMotorista(nomeMotorista);
	            motorista.setCep(cep);
	            motorista.setEndereco(endereco);
	            motorista.setNLocal(nLocal);
	            motorista.setCidade(cidade);
	            motorista.setEstado(estado);
	            motorista.setDdd(ddd);
	            motorista.setTelefone(telefone);
	            
	            motoristaDAO.cadastrarMotorista(motorista);
	            
	            Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("Cadastro Concluído");
	            alert.setHeaderText(null);
	            alert.setContentText("O motorista foi cadastrado com sucesso!");

	            alert.showAndWait(); // Exibe o alerta e espera até que ele seja fechado

	            // Fechar a janela de cadastro
	            Stage stage = (Stage) cadastroDeMotorista.getScene().getWindow();
	            stage.close();
	        });

	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        Scene cenario = new Scene(cadastroDeMotorista, 600, 400);
	        gPane();
	        labels();
	        textField();
	        comboBox();
	        buttons();
	        primaryStage.setScene(cenario);
	        primaryStage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }

	}
