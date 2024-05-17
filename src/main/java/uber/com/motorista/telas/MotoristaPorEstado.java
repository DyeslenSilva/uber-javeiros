package uber.com.motorista.telas;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uber.com.dao.MotoristaDAO;
import uber.com.model.Motorista;
import uber.com.utils.dao.EstadoDAO;

public class MotoristaPorEstado extends Application{

    private GridPane motoristaPorEstado;
    private Label lbEstado;
    private ComboBox<String> cbEstado;
    private Button btBuscaMotoristaPorEstado;
    private TableView<Motorista> twMotoristaPorEstado;
    private TableColumn<Motorista, Integer> tcIDMotorista;
    private TableColumn<Motorista, String> tcCPF, tcNomeMotorista, tcTelefone, tcCEP, tcEndereco, tcCidade, tcEstado;
    private TableColumn<Motorista, Integer> tcDDD, tcNLocal;
    private ObservableList<Motorista> tableListMotorista = FXCollections.observableArrayList();

    public MotoristaPorEstado() {
        motoristaPorEstado = new GridPane();

        lbEstado = new Label("Estado");

        cbEstado = new ComboBox<String>();

        btBuscaMotoristaPorEstado = new Button("Buscar Motorista");

        twMotoristaPorEstado = new TableView<Motorista>();

        tcIDMotorista = new TableColumn<Motorista, Integer>("ID Motorista");
        tcCPF = new TableColumn<Motorista, String>("CPF");
        tcNomeMotorista = new TableColumn<Motorista, String>("Nome do Motorista");
        tcDDD = new TableColumn<Motorista, Integer>("DDD");
        tcTelefone = new TableColumn<Motorista, String>("Telefone");
        tcCEP = new TableColumn<Motorista, String>("CEP");
        tcEndereco = new TableColumn<Motorista, String>("Endereço");
        tcNLocal = new TableColumn<Motorista, Integer>("N Local");
        tcCidade = new TableColumn<Motorista, String>("Cidade");
        tcEstado = new TableColumn<Motorista, String>("Estado");
    }


    private void gPane() {
        motoristaPorEstado.setPadding(new Insets(10,10,10,10));
        motoristaPorEstado.setHgap(10);
        motoristaPorEstado.setVgap(10);
        motoristaPorEstado.add(twMotoristaPorEstado,0,1,55,1);
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


        twMotoristaPorEstado.getColumns().addAll(tcIDMotorista,tcCPF,tcNomeMotorista,
                tcDDD, tcTelefone,tcCEP,tcEndereco,tcNLocal,tcCidade,tcEstado);



        tcNomeMotorista.setPrefWidth(200);
        tcTelefone.setPrefWidth(150);
        tcEndereco.setPrefWidth(150);
    }



    private void comps() {
        motoristaPorEstado.add(lbEstado, 0, 0);
        motoristaPorEstado.add(cbEstado, 1,0);
        motoristaPorEstado.add(btBuscaMotoristaPorEstado, 2,0);

        btBuscaMotoristaPorEstado.setOnAction(bm ->{
            String estado = cbEstado.getValue();
            if (estado != null && !estado.isEmpty()) {
                MotoristaDAO motoDAO = new MotoristaDAO();
                List<Motorista> motoristas = motoDAO.motoristaPorEstado(estado);
                if (motoristas != null) {
                    tableListMotorista.clear();
                    tableListMotorista.addAll(motoristas);
                    twMotoristaPorEstado.setItems(tableListMotorista);
                } else {
                    // Exibir mensagem de erro ou vazio
                    // Por exemplo: Alert.showMessageDialog(...)
                }
            } else {
                // Exibir mensagem indicando que o estado não foi selecionado
                // Por exemplo: Alert.showMessageDialog(...)
            }
        });

    }

    private void estados() {
        EstadoDAO estadoDAO = new EstadoDAO();
        List<String> listaEstado = estadoDAO.listaTodosEstados();

        cbEstado.getItems().addAll(listaEstado);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene cenario = new  Scene(motoristaPorEstado,1500,600);
        gPane();
        setTable();
        comps();
        estados();
        primaryStage.setScene(cenario);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
