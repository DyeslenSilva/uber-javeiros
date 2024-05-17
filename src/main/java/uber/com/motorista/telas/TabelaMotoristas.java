package uber.com.motorista.telas;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uber.com.dao.MotoristaDAO;
import uber.com.model.Motorista;

public class TabelaMotoristas extends Application{

	
	private GridPane tabelaMotorista;
	private TableView<Motorista> twMotorista;
	private ObservableList<Motorista> motoristaData = FXCollections.observableArrayList();
	private Button carregarMotoristas;
	private TableColumn<Motorista, Integer> tcIDMotorista, tcDDD, tcNLocal;
	private TableColumn<Motorista, String> tcCPF,tcNomeMotorista ,tcTelefone,tcCEP,tcEndereco;
	private TableColumn<Motorista, String> tcCidade, tcEstado;
	
	
	public TabelaMotoristas() {
		tabelaMotorista = new GridPane();
		twMotorista = new TableView<>();
		
		carregarMotoristas = new Button("Carregar Motoristas");
		
		tcIDMotorista = new TableColumn<>("Id Motorista");
		tcCPF = new TableColumn<>("CPF");
		tcNomeMotorista = new TableColumn<>("Nome do Motorista");
		tcDDD = new TableColumn<>("DDD");
		tcTelefone = new TableColumn<>("Telefone");
		tcCEP = new TableColumn<>("CEP");
		tcEndereco = new TableColumn<>("Endereco");
		tcNLocal = new TableColumn<>("N Local");
		tcCidade = new TableColumn<>("Cidade");
		tcEstado = new TableColumn<>("Estado");
	}

	
	@SuppressWarnings("unchecked")
	private void setTable() {
		tcIDMotorista.setCellValueFactory(new PropertyValueFactory<>("idMotorista"));
		tcCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tcNomeMotorista.setCellValueFactory(new PropertyValueFactory<>("nomeMotorista"));
		tcDDD.setCellValueFactory(new PropertyValueFactory<>("ddd"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tcCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tcNLocal.setCellValueFactory(new PropertyValueFactory<>("nLocal"));
		tcCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
		tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
		
		twMotorista.getColumns().addAll(tcIDMotorista,tcCPF,tcNomeMotorista,tcDDD,
					tcTelefone,tcCEP,tcEndereco,tcNLocal,tcCidade,tcEstado);
		
		tcNomeMotorista.setPrefWidth(150);
		tcEndereco.setPrefWidth(150);
		tcTelefone.setPrefWidth(150);
		
		twMotorista.setItems(motoristaData);
	}
	
	private void gPane() {
		tabelaMotorista.setPadding(new Insets(10, 10, 10, 10));
		tabelaMotorista.setHgap(10);
		tabelaMotorista.setVgap(10);
		tabelaMotorista.add(twMotorista, 0, 1,55,1);
	}
	
	
	private void comps() {
		tabelaMotorista.add(carregarMotoristas, 0, 0);
		
		carregarMotoristas.setOnAction(cm ->{
			MotoristaDAO motoDAO = new MotoristaDAO();
			List<Motorista>  todosOsMotoristas = 
					motoDAO.listAllMotoristas();
			motoristaData.addAll(todosOsMotoristas);
		});
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene cenario = new Scene(tabelaMotorista,1500,600);
		gPane();
		setTable();
		comps();
		primaryStage.setScene(cenario);
		primaryStage.show();
	}

	
	public static void main(String[] args) {
		launch(args);
	}
	
}
