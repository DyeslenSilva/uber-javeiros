package uber.com.passageiros.telas;

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
import javafx.stage.Stage;
import uber.com.dao.PassageiroDAO;
import uber.com.model.Passageiros;

public class ListaTodosPassageiros extends Application{

	private GridPane listaTodosOsPassageiros;
	private Button btListaTodosPassageiros;
	private TableView<Passageiros> tvPassageiros;
	private TableColumn<Passageiros,String> tcCPF, tcNomePassageiro,tcTelefone;
	private TableColumn<Passageiros, Integer> tcDDD, tcNLocal;
	private TableColumn<Passageiros, String> tcCEP, tcEndereco,tcCidade,tcEstado;
	private ObservableList<Passageiros> listaPassageiros;
	
	
	public ListaTodosPassageiros() {
		listaTodosOsPassageiros = new GridPane();
		
		btListaTodosPassageiros = new Button("Lista todos os Passageiros");
		
		tvPassageiros = new TableView<Passageiros>();
		
		tcCPF = new TableColumn<>("CPF");
		tcNomePassageiro = new TableColumn<>("Nom do Motorista");
		tcDDD = new TableColumn<>("DDD");
		tcTelefone = new TableColumn<>("Telefone");
		tcCEP  = new TableColumn<>("CEP");
		tcEndereco = new TableColumn<>("Endereco");
		tcNLocal = new TableColumn<>("N Local");
		tcCidade  =new TableColumn<>("Cidade");
		tcEstado = new TableColumn<>("Estado");
		
		listaPassageiros = FXCollections.observableArrayList();
	}
	
	private void gPane() {
		listaTodosOsPassageiros.setPadding(new Insets(10,10,10,10));
		listaTodosOsPassageiros.setHgap(10);
		listaTodosOsPassageiros.setVgap(10);
		
		listaTodosOsPassageiros.add(tvPassageiros, 0, 1,55,1);
	}
	
	private void botaoListar() {
		listaTodosOsPassageiros.add(btListaTodosPassageiros, 0, 0);
		btListaTodosPassageiros.setOnAction(tp->{
			PassageiroDAO passDAO = new PassageiroDAO();
			List<Passageiros> listaDePassageiros = passDAO
					.listaTodosPassageiros();
			this.listaPassageiros.addAll(listaDePassageiros);
			
			tvPassageiros.setItems(listaPassageiros);
		});
	}
	
	@SuppressWarnings("unchecked")
	private void setTable() {
		tcCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tcNomePassageiro.setCellValueFactory(new PropertyValueFactory<>("nomePassageiro"));
		tcDDD.setCellValueFactory(new PropertyValueFactory<>("ddd"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tcCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tcNLocal.setCellValueFactory(new PropertyValueFactory<>("nLocal"));
		tcCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
		tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
		
		tvPassageiros.getColumns().addAll(tcCPF,tcNomePassageiro,tcDDD,tcTelefone,
				tcCEP,tcEndereco,tcNLocal,tcCidade,tcEstado);
		
		tcNomePassageiro.setPrefWidth(150);
		tcEndereco.setPrefWidth(150);
		tcTelefone.setPrefWidth(150);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene cenario = new Scene(listaTodosOsPassageiros,1000,600);
		gPane();
		botaoListar();
		setTable();
		primaryStage.setScene(cenario);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
