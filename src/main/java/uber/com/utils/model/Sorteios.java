package uber.com.utils.model;

import java.util.List;
import java.util.Random;

import uber.com.dao.MotoristaDAO;
import uber.com.model.Motorista;

public class Sorteios {
    
    static Random random = new Random();
    
    public static int gerarIDMotorista() {
        return random.nextInt(9000) + 1000; // Garantir que o número gerado esteja entre 1000 e 9999
    }
    
    public static String gerarCPF() {
        StringBuilder sb = new StringBuilder();
        
        // Gera os 9 primeiros dígitos do CPF
        for (int i = 0; i < 9; i++) {
            sb.append(random.nextInt(10));
        }
        
        // Gera o primeiro dígito verificador
        String cpfParcial = sb.toString();
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpfParcial.charAt(i)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        digito1 = (digito1 > 9) ? 0 : digito1;
        sb.append(digito1);
        
        // Gera o segundo dígito verificador
        soma = 0;
        cpfParcial = sb.toString();
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpfParcial.charAt(i)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        digito2 = (digito2 > 9) ? 0 : digito2;
        sb.append(digito2);
        
        return sb.toString();
    }
    
    public static int gerarIDViagem() {
        return random.nextInt(90000) + 10000; // Garantir que o número gerado esteja entre 10000 e 99999
    }
    
    public static Motorista sortearMotorista() {
        MotoristaDAO motoDAO = new MotoristaDAO();
        List<Motorista> motoristas = motoDAO.listAllMotoristas();
        if (motoristas.isEmpty()) {
            return null;
        }
        
        int index = random.nextInt(motoristas.size());
        return motoristas.get(index);
    }
    
    public static void main(String[] args) {
        System.out.println("Código do Motorista: " + gerarIDMotorista());
        System.out.println("CPF: " + gerarCPF());
        System.out.println("ID Viagem: " + gerarIDViagem());
        
        Motorista motoristaSorteado = sortearMotorista();
        if (motoristaSorteado != null) {
            System.out.println("Motorista Sorteado: " + motoristaSorteado.getNomeMotorista());
        } else {
            System.out.println("Nenhum motorista encontrado no banco de dados.");
        }
    }
}
