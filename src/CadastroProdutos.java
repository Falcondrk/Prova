import java.util.ArrayList;
import java.util.Scanner;

public class CadastroProdutos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Venda> vendas = new ArrayList<>();
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Incluir produto");
            System.out.println("2 - Consultar produto");
            System.out.println("3 - Listagem de produtos");
            System.out.println("4 - Vendas por período - detalhado");
            System.out.println("5 - Realizar venda");
            System.out.println("0 - Sair");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    Produto produto = new Produto();
                    System.out.print("Digite o código do produto: ");
                    produto.setCodigo(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Digite o nome do produto: ");
                    produto.setNome(scanner.nextLine());
                    System.out.print("Digite o valor do produto: ");
                    produto.setValor(scanner.nextDouble());
                    System.out.print("Digite a quantidade em estoque: ");
                    produto.setQuantidade(scanner.nextInt());
                    produtos.add(produto);
                    break;
                case 2:
                    System.out.print("Digite o código do produto: ");
                    int codigo = scanner.nextInt();
                    for (Produto p : produtos) {
                        if (p.getCodigo() == codigo) {
                            System.out.println(p);
                            break;
                        }
                    }
                    break;
                case 3:
                    for (Produto p : produtos) {
                        System.out.println(p);
                    }
                    break;
                case 4:
                    System.out.print("Digite a data inicial (dd/mm/aaaa): ");
                    scanner.nextLine();
                    String dataInicial = scanner.nextLine();
                    System.out.print("Digite a data final (dd/mm/aaaa): ");
                    String dataFinal = scanner.nextLine();
                    double totalVendas = 0;
                    int quantidadeVendida = 0;
                    for (Venda v : vendas) {
                        if (v.getData().compareTo(dataInicial) >= 0 && v.getData().compareTo(dataFinal) <= 0) {
                            totalVendas += v.getValorTotal();
                            quantidadeVendida += v.getQuantidade();
                            System.out.println(v);
                        }
                    }
                    double valorMedioVendas = totalVendas / quantidadeVendida;
                    System.out.printf("Valor médio das vendas para o período: R$%.2f\n", valorMedioVendas);
                    break;
                case 5:
                    Venda venda = new Venda();
                    System.out.print("Digite a data da venda (dd/mm/aaaa): ");
                    scanner.nextLine();
                    venda.setData(scanner.nextLine());
                    System.out.print("Digite o código do produto: ");
                    venda.setCodigoProduto(scanner.nextInt());
                    Produto p = null;
                    for (Produto prod : produtos) {
                        if (prod.getCodigo() == venda.getCodigoProduto()) {
                            p = prod;
                            break;
                        }
                    }
                    if (p == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }
                    System.out.print("Digite a quantidade vendida: ");
                    venda.setQuantidade(scanner.nextInt());
                    if (venda.getQuantidade() > p.getQuantidade()) {
                        System.out.println("Não há estoque suficiente.");
                        break;
                    }
                    p.setQuantidade(p.getQuantidade() - venda.getQuantidade());
                    venda.setValorUnitario(p.getValor());
                    venda.setValorTotal(venda.getQuantidade() * p.getValor());
                    vendas.add(venda);
            }
        }
    }

    static class Produto {
        private int codigo;
        private String nome;
        public int getCodigo() {
            return codigo;
        }

        public Object getValor() {
            return null;
        }

        public int getQuantidade() {
            return 0;
        }

        public void setQuantidade(int nextInt) {
        }

        public void setValor(double nextDouble) {
        }

        public void setNome(String nextLine) {
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public String getNome() {
            return nome;
        }
    }
}