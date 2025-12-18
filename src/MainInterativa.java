import entidades.*;
import excecoes.*;
import servicos.*;

import java.util.*;

public class MainInterativa {

    private static Scanner sc = new Scanner(System.in);

    private static List<Passageiro> passageiros = new ArrayList<>();
    private static List<Motorista> motoristas = new ArrayList<>();
    private static List<Corrida> corridas = new ArrayList<>();

    public static void main(String[] args) throws EstadoInvalidoDaCorridaException {
        carregarDadosTeste();

        int opcao = -1;

        while (opcao != 0) {

            mostrarMenu();

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                opcao = -1;
            }

            try {
                switch (opcao) {
                    case 1 -> cadastrarPassageiro();
                    case 2 -> cadastrarMotorista();
                    case 3 -> cadastrarVeiculoMotorista();
                    case 4 -> cadastrarMetodoPagamentoPassageiro();
                    case 5 -> alterarStatusMotorista();
                    case 6 -> solicitarCorrida();
                    case 7 -> iniciarCorrida();
                    case 8 -> finalizarCorrida();
                    case 9 -> cancelarCorrida();
                    case 10 -> processarPagamento();
                    case 11 -> listarPassageiros();
                    case 12 -> listarMotoristas();
                    case 13 -> listarCorridas();
                    case 14 -> avaliarMotorista();
                    case 15 -> avaliarPassageiro();
                    case 0 -> System.out.println("Encerrando programa...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("\n ERRO: " + e.getMessage());
            }

            System.out.println("\nPressione ENTER para continuar...");
            sc.nextLine();
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n====== SISTEMA DE RIDE SHARING ======");
        System.out.println("1. Cadastrar Passageiro");
        System.out.println("2. Cadastrar Motorista");
        System.out.println("3. Cadastrar Veículo ao Motorista");
        System.out.println("4. Adicionar Método de Pagamento ao Passageiro");
        System.out.println("5. Alterar status do Motorista");
        System.out.println("6. Solicitar Corrida");
        System.out.println("7. Iniciar Corrida");
        System.out.println("8. Finalizar Corrida");
        System.out.println("9. Cancelar Corrida");
        System.out.println("10. Processar Pagamento");
        System.out.println("11. Listar Passageiros");
        System.out.println("12. Listar Motoristas");
        System.out.println("13. Listar Corridas");
        System.out.println("14. Avaliar Motorista");
        System.out.println("15. Avaliar Passageiro");
        System.out.println("0. Sair");
        System.out.print("Escolha a opção: ");
    }

    private static void cadastrarPassageiro() {
        System.out.println("\n=== Cadastro de Passageiro ===");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        

        Passageiro p = new Passageiro(nome, cpf, email, telefone, senha);
        passageiros.add(p);

        System.out.println("Passageiro cadastrado com sucesso!");
        cadastrarMetodoPagamentoPassageiro();
    }

    private static void cadastrarMotorista() {
        System.out.println("\n=== Cadastro de Motorista ===");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("CNH: ");
        String cnh = sc.nextLine();
        

        Motorista m = new Motorista(nome, cpf, email, telefone, senha, cnh, null);

        motoristas.add(m);
        cadastrarVeiculoMotorista();
        System.out.println("Motorista cadastrado com sucesso!");
    }

    private static void cadastrarMetodoPagamentoPassageiro() {
        System.out.println("\n=== Cadastro de Método de Pagamento para Passageiro ===");

        Passageiro p = escolherPassageiro();
        if (p == null) return;

        System.out.println("Escolha o método de pagamento:");
        System.out.println("1. Cartão de Crédito");
        System.out.println("2. Pix");
        System.out.println("3. Dinheiro");
        int escolha = Integer.parseInt(sc.nextLine());
        
        switch (escolha) {
            case 1:
                System.out.println("Digite o número do cartão de crédito:");
                String numeroCartao = sc.nextLine();
                System.out.println("Digite a bandeira do cartão:");
                String bandeira = sc.nextLine();
                System.out.println("Digite a validade do cartão (MM/AA):");
                String validade = sc.nextLine();
                System.out.println("Digite o CVV do cartão:");
                String cvv = sc.nextLine();
                MetodoPagamento cartaoCredito = new CartaoCredito(numeroCartao, bandeira, cvv, validade);
                p.adicionarMetodoPagamento(cartaoCredito);
                break;
            case 2:
                System.out.println("Digite a chave Pix:");
                String chavePix = sc.nextLine();
                MetodoPagamento pix = new servicos.Pix(chavePix);
                p.adicionarMetodoPagamento(pix);
                break;
            case 3:
                MetodoPagamento dinheiro = new servicos.Dinheiro();
                p.adicionarMetodoPagamento(dinheiro);
                break;
            default:
                break;
        }

        System.out.println("Método de pagamento adicionado ao passageiro!");
    }

    private static void cadastrarVeiculoMotorista() {
        System.out.println("\n=== Cadastro de Veículo a Motorista ===");

        Motorista m = escolherMotorista();
        if (m == null) return;

        System.out.print("Placa: ");
        String placa = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Cor: ");
        String cor = sc.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(sc.nextLine());

        System.out.println("Categoria do veículo:");
        System.out.println("1. Comum");
        System.out.println("2. Luxo");
        System.out.print("Escolha: ");
        int cat = Integer.parseInt(sc.nextLine());

        Categoria categoria = (cat == 1) ? new CategoriaComum() : new CategoriaLuxo();

        Veiculo v = new Veiculo(placa, modelo, cor, ano, categoria);
        m.setVeiculo(v);

        System.out.println("Veículo cadastrado e atribuído ao motorista!");
    }

    private static void alterarStatusMotorista() {
        System.out.println("\n=== Alterar Status do Motorista ===");

        Motorista m = escolherMotorista();
        if (m == null) return;

        System.out.println("1. ONLINE");
        System.out.println("2. OFFLINE");
        System.out.print("Escolha: ");

        int op = Integer.parseInt(sc.nextLine());
        StatusMotorista s =
                (op == 1 ? StatusMotorista.ONLINE
                        : op == 2 ? StatusMotorista.OFFLINE
                        : m.getStatus());

        m.setStatus(s);
        System.out.println("Status alterado!");
    }

    private static void solicitarCorrida() throws Exception {
        System.out.println("\n=== Solicitar Corrida ===");

        Passageiro p = escolherPassageiro();
        if (p == null) return;

        System.out.print("Local De Partida: ");
        String origem = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        System.out.print("Distância (km): ");
        double km = Double.parseDouble(sc.nextLine());

        System.out.println("Categoria:");
        System.out.println("1. Comum");
        System.out.println("2. Luxo");
        int c = Integer.parseInt(sc.nextLine());
        Categoria categoria = (c == 1 ? new CategoriaComum() : new CategoriaLuxo());

        Corrida corrida = new Corrida(p, origem, destino, km, categoria);

        corrida.atribuirMotorista(motoristas);

        corridas.add(corrida);

        System.out.println("\nCorrida solicitada com sucesso!");
        System.out.println("Motorista atribuído: " + corrida.getMotorista().getNome());
        System.out.println("Valor estimado: R$ " + corrida.getValor());
    }

    private static void iniciarCorrida() throws Exception {
        Corrida c = escolherCorrida();
        if (c == null) return;

        c.iniciarCorrida();
        System.out.println("Corrida agora está EM ANDAMENTO!");
    }

    private static void finalizarCorrida() throws Exception {
        Corrida c = escolherCorrida();
        if (c == null) return;

        c.finalizarCorrida();
        System.out.println("Corrida finalizada!");
    }

    private static void cancelarCorrida() throws Exception {
        Corrida c = escolherCorrida();
        if (c == null) return;

        c.cancelar();
        System.out.println("Corrida cancelada com sucesso!");
    }

    private static void processarPagamento() throws EstadoInvalidoDaCorridaException {
        System.out.println("\n=== Processar Pagamento ===");

        Corrida c = escolherCorrida();
        if (c == null) {
            System.out.println("Nenhuma corrida selecionada!");
            return;
        }

        Passageiro p = c.getPassageiro();
        if (p == null) {
            System.out.println("ERRO: A corrida não possui passageiro associado!");
            return;
        }

        List<MetodoPagamento> metodos = p.getMetodosPagamento();

        if (metodos == null || metodos.isEmpty()) {
            System.out.println("O passageiro não possui métodos de pagamento cadastrados!");
            return;
        }

        System.out.println("\nSelecione método de pagamento:");
        for (int i = 0; i < metodos.size(); i++) {
            System.out.println(i + " - " + metodos.get(i).getTipoPagamento());
        }

        System.out.print("Escolha: ");
        int mp;
        try {
            mp = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Entrada inválida!");
            return;
        }

        if (mp < 0 || mp >= metodos.size()) {
            System.out.println("Índice inválido!");
            return;
        }

        MetodoPagamento forma = metodos.get(mp);
        c.setMetodoPagamento(forma);

        try {
            c.processarPagamento();
            System.out.println("Pagamento realizado com sucesso!");
        } catch (SaldoInsuficienteException | PagamentoRecusadoException e) {
            System.out.println("Falha no pagamento: " + e.getMessage());
        }
    }

    private static void avaliarMotorista() {
        Corrida c = escolherCorrida();
        if (c == null) return;

        if (c.isMotoristaAvaliado()) {
            System.out.println("Este motorista já foi avaliado nesta corrida!");
            return;
        }

        Motorista m = c.getMotorista();
        Passageiro p = c.getPassageiro();

        System.out.print("Nota (1 a 5): ");
        int nota = Integer.parseInt(sc.nextLine());

        p.avaliarMotorista(m, nota);
        c.setMotoristaAvaliado(true);

        System.out.println("Motorista avaliado!");
    }

    private static void avaliarPassageiro() {
        Corrida c = escolherCorrida();
        if (c == null) return;

        if (c.isPassageiroAvaliado()) {
            System.out.println("Este passageiro já foi avaliado nesta corrida!");
            return;
        }

        Motorista m = c.getMotorista();
        Passageiro p = c.getPassageiro();

        System.out.print("Nota (1 a 5): ");
        int nota = Integer.parseInt(sc.nextLine());

        m.avaliarPassageiro(p, nota);
        c.setPassageiroAvaliado(true);

        System.out.println("Passageiro avaliado!");
    }


    private static Passageiro escolherPassageiro() {
        if (passageiros.isEmpty()) {
            System.out.println("Nenhum passageiro cadastrado!");
            return null;
        }

        System.out.println("\nPassageiros:");
        for (int i = 0; i < passageiros.size(); i++) {
            System.out.println(i + " - " + passageiros.get(i).getNome());
        }

        System.out.print("Escolha: ");
        return passageiros.get(Integer.parseInt(sc.nextLine()));
    }

    private static Motorista escolherMotorista() {
        if (motoristas.isEmpty()) {
            System.out.println("Nenhum motorista cadastrado!");
            return null;
        }

        System.out.println("\nMotoristas:");
        for (int i = 0; i < motoristas.size(); i++) {
            System.out.println(i + " - " + motoristas.get(i).getNome() + " (" + motoristas.get(i).getStatus() + ")");
        }

        System.out.print("Escolha: ");
        return motoristas.get(Integer.parseInt(sc.nextLine()));
    }

    private static Corrida escolherCorrida() {
        if (corridas.isEmpty()) {
            System.out.println("Nenhuma corrida cadastrada!");
            return null;
        }

        System.out.println("\nCorridas:");
        for (int i = 0; i < corridas.size(); i++) {
            Corrida c = corridas.get(i);
            System.out.println(i + " - " + c + " | Passageiro: " + c.getPassageiro().getNome() + " | Motorista: " + c.getMotorista().getNome() + " | Status: " + c.getStatus());
        }

        System.out.print("Escolha: ");
        return corridas.get(Integer.parseInt(sc.nextLine()));
    }

    private static void listarPassageiros() {
        System.out.println("\n=== Passageiros ===");
        for (Passageiro p : passageiros) {
            System.out.println(p.getNome() + " | Média de Avalicação: "+ p.getMediaAvaliacao() + " | Método de Pagamento: " + p.getMetodosPagamento().get(0).getTipoPagamento());
        }
    }

    private static void listarMotoristas() {
        System.out.println("\n=== Motoristas ===");
        for (Motorista m : motoristas) {
            System.out.println(m.getNome() + " | Status: " + m.getStatus() + " | Média de Avalicação: "+ m.getMediaAvaliacao());
        }
    }

    private static void listarCorridas() {
        System.out.println("\n=== Corridas ===");
        for (Corrida c : corridas) {
            System.out.println(c + " | Passageiro: " + c.getPassageiro().getNome() + " | Motorista: " + c.getMotorista().getNome() + " | Status: " + c.getStatus());
        }
    }

    private static void carregarDadosTeste() throws EstadoInvalidoDaCorridaException {


    Passageiro p1 = new Passageiro("Ana Silva", "11111111111", "ana@email.com",
            "61988880000", "123");
    p1.adicionarMetodoPagamento(new servicos.Dinheiro());

    Passageiro p2 = new Passageiro("Bruno Costa", "22222222222", "bruno@email.com",
            "62999990000", "abc");
    p2.adicionarMetodoPagamento(new servicos.Pix("bruno@pix.com"));

    Passageiro p3 = new Passageiro("Carla Oliveira", "33333333333", "carla@email.com",
            "61977773333", "senha1");
    p3.adicionarMetodoPagamento(new CartaoCredito("5555444433332222", "Visa", "123", "08/28"));

    Passageiro p4 = new Passageiro("Diego Santos", "44444444444", "diego@email.com",
            "61966662222", "senha2");
    p4.adicionarMetodoPagamento(new servicos.Pix("diego@pix.com"));

    Passageiro p5 = new Passageiro("Eduarda Lima", "55555555555", "eduarda@email.com",
            "61955551111", "edu123");
    p5.adicionarMetodoPagamento(new servicos.Dinheiro());

    Passageiro p6 = new Passageiro("Fernando Gomes", "66666666666", "fernando@email.com",
            "61944441111", "ferpass");
    p6.adicionarMetodoPagamento(new CartaoCredito("4444333322221111", "Mastercard", "987", "09/27"));

    passageiros.addAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

    Motorista m1 = new Motorista("Carlos Mendes", "77777777777",
            "carlos@email.com", "61910101010", "321", "12345678900", null);
    m1.setVeiculo(new Veiculo("ABC-1234", "Onix", "Prata", 2020, new CategoriaComum()));
    m1.setStatus(StatusMotorista.ONLINE);

    Motorista m2 = new Motorista("Daniel Rocha", "88888888888",
            "daniel@email.com", "61920202020", "456", "98765432100", null);
    m2.setVeiculo(new Veiculo("XYZ-9876", "Corolla", "Preto", 2022, new CategoriaLuxo()));
    m2.setStatus(StatusMotorista.ONLINE);

    Motorista m3 = new Motorista("Elaine Freitas", "99999999999",
            "elaine@email.com", "61930303030", "el123", "65498732100", null);
    m3.setVeiculo(new Veiculo("JKL-4567", "HB20", "Branco", 2019, new CategoriaComum()));
    m3.setStatus(StatusMotorista.ONLINE);

    Motorista m4 = new Motorista("Fabiano Souza", "10101010101",
            "fabiano@email.com", "61940404040", "fa321", "14725836900", null);
    m4.setVeiculo(new Veiculo("MNO-7654", "Civic", "Cinza", 2021, new CategoriaLuxo()));
    m4.setStatus(StatusMotorista.ONLINE);

    Motorista m5 = new Motorista("Gabriela Torres", "12121212121",
            "gabi@email.com", "61950505050", "gtpass", "75315985200", null);
    m5.setVeiculo(new Veiculo("PQR-1122", "Argo", "Vermelho", 2018, new CategoriaComum()));
    m5.setStatus(StatusMotorista.ONLINE);

    Motorista m6 = new Motorista("Henrique Almeida", "13131313131",
            "henrique@email.com", "61960606060", "hen123", "85245696300", null);
    m6.setVeiculo(new Veiculo("STU-9988", "Compass", "Azul", 2023, new CategoriaLuxo()));
    m6.setStatus(StatusMotorista.ONLINE);

    motoristas.addAll(Arrays.asList(m1, m2, m3, m4, m5, m6));
    
    Corrida corr1 = new Corrida(passageiros.get(3), "Taguatinga", "Asa Norte", 22.5, new CategoriaComum());
    corr1.atribuirMotorista(motoristas);
    if (corr1.getPassageiro() != null) corridas.add(corr1);

    Corrida corr2 = new Corrida(passageiros.get(5), "Gama", "Setor Bancário Sul", 30.8, new CategoriaLuxo());
    corr2.atribuirMotorista(motoristas);
    if (corr2.getPassageiro() != null) corridas.add(corr2);

    Corrida corr3 = new Corrida(passageiros.get(1), "Asa Sul", "Parque da Cidade", 4.3, new CategoriaComum());
    corr3.atribuirMotorista(motoristas);
    if (corr3.getPassageiro() != null) corridas.add(corr3);

}
}
