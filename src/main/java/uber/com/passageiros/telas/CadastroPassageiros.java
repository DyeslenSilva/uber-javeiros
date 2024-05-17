package uber.com.passageiros.telas;

import java.util.List;
import java.util.Map;

import estado.sistema.model.Estado;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uber.com.dao.PassageiroDAO;
import uber.com.model.Passageiros;
import uber.com.utils.dao.EstadoDAO;
import uber.com.utils.model.CEP;
import uber.com.utils.model.DDD;
import uber.com.utils.model.Sorteios;

public class CadastroPassageiros extends Application {

    private GridPane cadastroPassageiros;
    private Label lbCPF, lbNomePassageiro, lbCEP, lbEndereco;
    private Label lbNCasa, lbCidade, lbEstado, lbDDD, lbTelefoneCelular;
    private TextField txCPF, txNomePassageiro, txCEP, txEndereco;
    private TextField txNCasa, txCidade, txDDD, txTelefone;
    private ComboBox<String> estados;
    private Button btGerarCPF, btConsultarCEP, btCadastrarPassageiro;

    public CadastroPassageiros() {
        cadastroPassageiros = new GridPane();

        lbCPF = new Label("CPF");
        lbNomePassageiro = new Label("Nome do Passageiro");
        lbCEP = new Label("CEP");
        lbDDD = new Label("DDD");
        lbTelefoneCelular = new Label("Telefone Celular");
        lbEndereco = new Label("Endereço");
        lbNCasa = new Label("Nº Casa");
        lbCidade = new Label("Cidade");
        lbEstado = new Label("Estado");

        txCPF = new TextField();
        txNomePassageiro = new TextField();
        txCEP = new TextField();
        txDDD = new TextField();
        txTelefone = new TextField();
        txEndereco = new TextField();
        txNCasa = new TextField();
        txCidade = new TextField();

        estados = new ComboBox<String>();

        btGerarCPF = new Button("Gerar CPF");
        btConsultarCEP = new Button("Consultar CEP");
        btCadastrarPassageiro = new Button("Cadastrar Passageiro");
    }

    private void gPane() {
        cadastroPassageiros.setPadding(new Insets(10, 10, 10, 10));
        cadastroPassageiros.setVgap(10);
        cadastroPassageiros.setHgap(10);
    }

    private void labels() {
        cadastroPassageiros.add(lbCPF, 0, 0);
        cadastroPassageiros.add(lbNomePassageiro, 0, 1);
        cadastroPassageiros.add(lbCEP, 0, 2);
        cadastroPassageiros.add(lbDDD, 0, 3);
        cadastroPassageiros.add(lbTelefoneCelular, 0, 4);
        cadastroPassageiros.add(lbEndereco, 0, 5);
        cadastroPassageiros.add(lbNCasa, 0, 6);
        cadastroPassageiros.add(lbCidade, 0, 7);
        cadastroPassageiros.add(lbEstado, 0, 8);
    }

    private void textField() {
        cadastroPassageiros.add(txCPF, 1, 0);
        cadastroPassageiros.add(txNomePassageiro, 1, 1);
        cadastroPassageiros.add(txCEP, 1, 2);
        cadastroPassageiros.add(txDDD, 1, 3);
        cadastroPassageiros.add(txTelefone, 1, 4);
        cadastroPassageiros.add(txEndereco, 1, 5);
        cadastroPassageiros.add(txNCasa, 1, 6);
        cadastroPassageiros.add(txCidade, 1, 7);
    }

    private void combobox() {
        cadastroPassageiros.add(estados, 1, 8);

        EstadoDAO estadoDAO = new EstadoDAO();
        List<String> estadosLista = estadoDAO.listaTodosEstados();
        estados.getItems().addAll(estadosLista);
    }

    private void buttons() {
        cadastroPassageiros.add(btGerarCPF, 2, 0);
        cadastroPassageiros.add(btConsultarCEP, 2, 2);
        cadastroPassageiros.add(btCadastrarPassageiro, 1, 9);

        btGerarCPF.setOnAction(gcpf -> {
            String cpf = Sorteios.gerarCPF();
            txCPF.setText(cpf);
        });

        btConsultarCEP.setOnAction(ccep -> {
            String cep = txCEP.getText();
            Map<String, String> endereco = CEP.consultarCEP(cep);
            int ddd = DDD.obterDDD(cep);

            String logradouro = endereco.get("logradouro");
            String cidade = endereco.get("cidade");

            txEndereco.setText(logradouro);
            txCidade.setText(cidade);
            txDDD.setText(String.valueOf(ddd));

            if (ddd != 0) {
                txDDD.setText(String.valueOf(ddd));
            } else {
                System.out.println("Não foi possível obter o DDD para o CEP fornecido.");
            }
        });

        btCadastrarPassageiro.setOnAction(cp -> {
            String cpf = txCPF.getText();
            String nome = txNomePassageiro.getText();
            String cep = txCEP.getText();
            String endereco = txEndereco.getText();
            int nCasa = Integer.parseInt(txNCasa.getText());
            String cidade = txCidade.getText();
            String estado = estados.getValue();
            String dddStr = txDDD.getText();
            String telefone = txTelefone.getText(); // Obtenha o valor do campo de telefone

            if (!cpf.isEmpty() && !nome.isEmpty() && !cep.isEmpty() && !endereco.isEmpty() && nCasa != 0
                    && !cidade.isEmpty() && !estado.isEmpty() && !dddStr.isEmpty() && !telefone.isEmpty()) { // Verifique se o campo de telefone não está vazio
                int ddd = Integer.parseInt(dddStr);
                Passageiros passageiros = new Passageiros();
                passageiros.setCpf(cpf);
                passageiros.setNomePassageiro(nome);
                passageiros.setCep(cep);
                passageiros.setEndereco(endereco);
                passageiros.setNLocal(nCasa);
                passageiros.setCidade(cidade);
                passageiros.setEstado(estado);
                passageiros.setDdd(ddd);
                passageiros.setTelefone(telefone); // Defina o telefone

                PassageiroDAO passDAO = new PassageiroDAO();
                passDAO.cadastrarPassageiro(passageiros);
                
                telaAlertaCadastro();
            } else {
                System.out.println("Por favor, preencha todos os campos obrigatórios.");
            }
        });
    }

    
    private void telaAlertaCadastro() {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Confirmação de cadastro");
    	alert.setContentText("Usuario cadastrado com sucesso");
    	alert.show();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene cenario = new Scene(cadastroPassageiros, 400, 400);
        primaryStage.setScene(cenario);
        gPane();
        labels();
        textField();
        combobox();
        buttons();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
