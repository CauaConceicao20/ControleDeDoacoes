package com.compass.util.menu;

import com.compass.entities.*;
import com.compass.enums.Genero;
import com.compass.enums.PedidoStatus;
import com.compass.enums.TamanhoRoupa;
import com.compass.enums.UnidadeDeMedida;
import com.compass.service.AbrigoService;
import com.compass.service.DistribuidoraService;
import com.compass.service.ItemService;
import com.compass.service.PedidoService;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {


    ItemService itemService = new ItemService();
    DistribuidoraService distribuidoraService = new DistribuidoraService();
    PedidoService pedidoService = new PedidoService();
    AbrigoService abrigoService = new AbrigoService();

    private ValidadorMenu validadorMenu = new ValidadorMenu();

    public void exibirMenuPrincipal() {
        System.out.println("<-----MENU----->");
        System.out.println("1- Operações Item");
        System.out.println("2- Operações Distribuidoras");
        System.out.println("3- Operações Abrigo");
        System.out.println("4- Operações Pedido");
        System.out.println("5- Operações Pessoa");
        System.out.println("6- Finalizar Programa");
    }

    public void exibirMenuItem() {
        System.out.println("<-----Items----->");
        System.out.println("1- Fazer Doação");
        System.out.println("2- Listar Items");
        System.out.println("3- Alterar Item");
        System.out.println("4- Remover Item");
        System.out.println("5- Voltar ao Menu");

    }

    public void exibirMenuDistribuidora() {
        System.out.println("<-----Distribuidora----->");

        System.out.println("1- Cadastrar Distribuidora");
        System.out.println("2- Listar Distribuidora");
        System.out.println("3- Alterar Distribuidora");
        System.out.println("4- Remover Distribuidora");
        System.out.println("5- Voltar ao Menu");
    }

    public void exibirMenuAbrigo() {
        System.out.println("<-----Abrigo----->");

        System.out.println("1- Cadastrar Abrigo");
        System.out.println("2- Listar Abrigos");
        System.out.println("3- Alterar Abrigo");
        System.out.println("4- Remover Abrigo");
        System.out.println("5- Voltar ao Menu");
    }

    public void exibirMenuPedido() {
        System.out.println("<-----Pedidos----->");

        System.out.println("1- Fazer Pedidos");
        System.out.println("2- Listar Pedidos");
        System.out.println("3- Alterar Pedido");
        System.out.println("4- Remover Pedido");
        System.out.println("5- Voltar ao Menu");
    }

    public void exibirMenuPessoa() {
        System.out.println("<-----Pedidos----->");

        System.out.println("1- Cadastrar Pessoa");
        System.out.println("2- Listar Pessoas");
        System.out.println("3- Alterar Pessoa");
        System.out.println("4- Remover Pessoa");
        System.out.println("5- Voltar ao Menu");
    }

    public int lerOpcaoDeMenu(int min, int max) {
        int opcao = 0;
        while (true) {
            Scanner teclado = new Scanner(System.in);
            try {
                opcao = teclado.nextInt();
                teclado.nextLine();
                validadorMenu.validaEntrada(opcao, min, max);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Opção Inválida! Digite novamente.");
                opcao = -1;
                break;
            }
        }
        return opcao;
    }

    public int chamarSubMenu(int opcao, Menu menu) {
        switch (opcao) {
            case 1:
                menu.exibirMenuItem();
                break;
            case 2:
                menu.exibirMenuDistribuidora();
                break;
            case 3:
                menu.exibirMenuAbrigo();
                break;
            case 4:
                menu.exibirMenuPedido();
                break;
            case 5:
                menu.exibirMenuPessoa();
                break;
            default:
                break;
        }
        return opcao;
    }

    public TamanhoRoupa escolhaDeTamanhoRoupa() throws InputMismatchException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Qual tamanho:\n" + "1-(GG)\n" + "2-(G)\n" + "3-(M)\n" + "4-(P)\n" + "5-(PP)");
        int tamanho = teclado.nextInt();

        TamanhoRoupa tamanhoRoupa = null;
        switch (tamanho) {
            case 1:
                tamanhoRoupa = TamanhoRoupa.GG;
                break;
            case 2:
                tamanhoRoupa = TamanhoRoupa.G;
                break;
            case 3:
                tamanhoRoupa = TamanhoRoupa.M;
                break;
            case 4:
                tamanhoRoupa = TamanhoRoupa.P;
                break;
            case 5:
                tamanhoRoupa = TamanhoRoupa.PP;
                break;
            default:
                System.out.println("Essa opção não existe");
                break;
        }
        return tamanhoRoupa;
    }

    public Genero escolhaDeGenero() throws InputMismatchException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Qual genero:\n" + "1-(M)\n" + "2-(F)");
        int opcaoGenero = teclado.nextInt();

        Genero genero = null;
        switch (opcaoGenero) {
            case 1:
                genero = Genero.M;
                break;
            case 2:
                genero = Genero.F;
                break;
            default:
                System.out.println("Essa opção não existe");
                break;
        }
        return genero;
    }

    public UnidadeDeMedida escolhaDaUnidadeDeMedida(int escolha) {
        UnidadeDeMedida unidadeDeMedida = null;
        switch (escolha) {
            case 1:
                unidadeDeMedida = UnidadeDeMedida.KG;
                break;
            case 2:
                unidadeDeMedida = UnidadeDeMedida.L;
                break;
            default:
                System.out.println("Essa opção não existe");
        }
        return unidadeDeMedida;
    }

    public void criacaoDePedido(int tipoItem, int quantidade, Abrigo abrigoEncontrado) throws NullPointerException, InputMismatchException {
        Scanner teclado= new Scanner(System.in);
        switch (tipoItem) {
            case 1:
                criacaoDePedidoRoupa(teclado, quantidade, abrigoEncontrado);
                break;
            case 2:
                criacaoDePedidoProdutoHigiene(teclado, quantidade, abrigoEncontrado);
                break;
            case 3:
                criacaoDePedidoAlimentos(teclado, quantidade, abrigoEncontrado);
                break;
            default:
                System.out.println("Essa Opção não existe");
        }
    }

    public void criacaoDePedidoRoupa(Scanner teclado, int quantidade, Abrigo abrigoEncontrado) {
        int quantidadeDistribuidora1 = 0;
        int quantidadeDistribuidora2 = 0;
        int quantidadeDistribuidora3 = 0;
        while (true) {
            System.out.println("Descrição: ");
            String descricao = teclado.nextLine();

            TamanhoRoupa tamanhoRoupa = escolhaDeTamanhoRoupa();

            if (tamanhoRoupa == null) {
                break;
            }
            Genero genero = escolhaDeGenero();

            if (genero == null) {
                break;
            }

            System.out.println("Qual a quantidade desejada ?");
            quantidade = validadorMenu.validaEntrada();
            if (quantidade <= 0) {
                break;
            }

            int quantidadeSolicitada = quantidade;
            List<Distribuidora> distribuidoras = distribuidoraService.retornaDistribuidoras();
            List<Roupa> roupasEncontradas = itemService.buscaPersonalizadaDeRoupas(descricao, tamanhoRoupa, genero);

            if (roupasEncontradas != null && !roupasEncontradas.isEmpty()) {
                for (Item roupas : roupasEncontradas) {
                    if (roupas.getDistribuidora().getId() == 1) {
                        quantidadeDistribuidora1++;

                    } else if (roupas.getDistribuidora().getId() == 2) {
                        quantidadeDistribuidora2++;
                    } else if (roupas.getDistribuidora().getId() == 3) {
                        quantidadeDistribuidora3++;
                    }
                }

                distribuidoras = distribuidoraService.adicionaQuantidade(distribuidoras, quantidadeDistribuidora1, quantidadeDistribuidora2, quantidadeDistribuidora3);

                distribuidoraService.organizaDistribuidoraPorQuantidade(distribuidoras, quantidadeSolicitada);

                System.out.println("");
                int i = 0;
                for (Distribuidora distribuidora : distribuidoras) {
                    System.out.println(distribuidora.getNome() + " = " + distribuidora.getQuantidade());
                    if (quantidadeSolicitada > distribuidora.getQuantidade()) {
                        i++;
                    }
                }

                if (i >= 3) {
                    System.out.println("");
                    System.out.println("Nenhuma distribuidora possui a quantidade solicitada\n"
                            + "emissão de pedido cancelada");
                    break;
                }

                System.out.println("");
                System.out.println("Deseja fazer o pedido ?\n" + "1-(aceitar)\n" + "0-(rejeitar)");
                int opcao = teclado.nextInt();
                teclado.nextLine();

                if (opcao == 1) {
                    Pedido pedido = new Pedido(null, PedidoStatus.EM_ABERTO, abrigoEncontrado, distribuidoras.get(0));
                    pedidoService.adicionaPedido(pedido);
                    System.out.println("Pedido enviado");

                    for (Roupa roupas : roupasEncontradas) {
                        roupas.getPedidos().add(pedido);
                        itemService.alteraItem(roupas);
                    }
                    break;
                } else {
                    System.out.println("Pedido cancelado");
                    break;
                }
            } else {
                System.out.println("Não foi encontrado nenhuma roupa com esses dados");
                break;
            }
        }
    }

    public void criacaoDePedidoProdutoHigiene(Scanner teclado, int quantidade, Abrigo abrigoEncontrado) {
        int quantidadeDistribuidora1 = 0;
        int quantidadeDistribuidora2 = 0;
        int quantidadeDistribuidora3 = 0;
        while (true) {
            System.out.println("Descrição: ");
            String descricao = teclado.nextLine();


            System.out.println("Qual a quantidade desejada ?");
            quantidade = validadorMenu.validaEntrada();
            if (quantidade <= 0) {
                break;
            }

            int quantidadeSolicitada = quantidade;
            List<Distribuidora> distribuidoras = distribuidoraService.retornaDistribuidoras();
            List<ProdutoHigiene> produtoHigienesEncontrados = itemService.buscaPersonalizadaDeProdutosHigiene(descricao);

            if (produtoHigienesEncontrados != null && !produtoHigienesEncontrados.isEmpty()) {
                for (Item produtosHigiene : produtoHigienesEncontrados) {
                    if (produtosHigiene.getDistribuidora().getId() == 1) {
                        quantidadeDistribuidora1++;

                    } else if (produtosHigiene.getDistribuidora().getId() == 2) {
                        quantidadeDistribuidora2++;
                    } else if (produtosHigiene.getDistribuidora().getId() == 3) {
                        quantidadeDistribuidora3++;
                    }
                }

                distribuidoras = distribuidoraService.adicionaQuantidade(distribuidoras, quantidadeDistribuidora1, quantidadeDistribuidora2, quantidadeDistribuidora3);

                distribuidoraService.organizaDistribuidoraPorQuantidade(distribuidoras, quantidadeSolicitada);

                System.out.println("");
                int i = 0;
                for (Distribuidora distribuidora : distribuidoras) {
                    System.out.println(distribuidora.getNome() + " = " + distribuidora.getQuantidade());
                    if (quantidadeSolicitada > distribuidora.getQuantidade()) {
                        i++;
                    }
                }

                if (i >= 3) {
                    System.out.println("");
                    System.out.println("Nenhuma distribuidora possui a quantidade solicitada\n"
                            + "emissão de pedido cancelada");
                    break;
                }

                System.out.println("");
                System.out.println("Deseja fazer o pedido ?\n" + "1-(aceitar)\n" + "0-(rejeitar)");
                int opcao = teclado.nextInt();
                teclado.nextLine();

                if (opcao == 1) {
                    Pedido pedido = new Pedido(null, PedidoStatus.EM_ABERTO, abrigoEncontrado, distribuidoras.get(0));
                    pedidoService.adicionaPedido(pedido);
                    System.out.println("Pedido enviado");

                    for (ProdutoHigiene produtoHigiene : produtoHigienesEncontrados) {
                        produtoHigiene.getPedidos().add(pedido);
                        itemService.alteraItem(produtoHigiene);
                    }
                    break;
                } else {
                    System.out.println("Pedido cancelado");
                    break;
                }
            } else {
                System.out.println("Não foi encontrado nenhuma roupa com esses dados");
                break;
            }
        }
    }

    public void criacaoDePedidoAlimentos(Scanner teclado, int quantidade, Abrigo abrigoEncontrado) {
        int quantidadeDistribuidora1 = 0;
        int quantidadeDistribuidora2 = 0;
        int quantidadeDistribuidora3 = 0;
        while (true) {
            System.out.println("Descrição: ");
            String descricao = teclado.nextLine();

            System.out.println("Informe a Quantidade da unidade de medida");
            int quantidadeAlimento = validadorMenu.validaEntrada();

            if(quantidadeAlimento <= 0) {
                break;
            }

            System.out.println("Informe a unidadeDeMedida \n" + "1-(KG)\n" + "2-(L)");
            int escolhaUnidadeDeMedida = validadorMenu.validaEntrada(2);

            if (escolhaUnidadeDeMedida <= 0) {
                break;
            }

            UnidadeDeMedida unidadeDeMedida = escolhaDaUnidadeDeMedida(escolhaUnidadeDeMedida);

            System.out.println("Qual a quantidade de alimentos desejada ?");
            quantidade = validadorMenu.validaEntrada();
            if (quantidade <= 0) {
                break;
            }

            int quantidadeSolicitada = quantidade;
            List<Distribuidora> distribuidoras = distribuidoraService.retornaDistribuidoras();
            List<Alimento> alimentosEncontrados = itemService.buscaPersonalzadaDeAlimentos(descricao, unidadeDeMedida, quantidadeAlimento);

            if (alimentosEncontrados != null && !alimentosEncontrados.isEmpty()) {
                for (Item produtosHigiene : alimentosEncontrados) {
                    if (produtosHigiene.getDistribuidora().getId() == 1) {
                        quantidadeDistribuidora1++;

                    } else if (produtosHigiene.getDistribuidora().getId() == 2) {
                        quantidadeDistribuidora2++;
                    } else if (produtosHigiene.getDistribuidora().getId() == 3) {
                        quantidadeDistribuidora3++;
                    }
                }

                distribuidoras = distribuidoraService.adicionaQuantidade(distribuidoras, quantidadeDistribuidora1, quantidadeDistribuidora2, quantidadeDistribuidora3);

                distribuidoraService.organizaDistribuidoraPorQuantidade(distribuidoras, quantidadeSolicitada);

                System.out.println("");
                int i = 0;
                for (Distribuidora distribuidora : distribuidoras) {
                    System.out.println(distribuidora.getNome() + " = " + distribuidora.getQuantidade());
                    if (quantidadeSolicitada > distribuidora.getQuantidade()) {
                        i++;
                    }
                }

                if (i >= 3) {
                    System.out.println("");
                    System.out.println("Nenhuma distribuidora possui a quantidade solicitada\n"
                            + "emissão de pedido cancelada");
                    break;
                }

                System.out.println("");
                System.out.println("Deseja fazer o pedido ?\n" + "1-(aceitar)\n" + "0-(rejeitar)");
                int opcao = teclado.nextInt();
                teclado.nextLine();

                if (opcao == 1) {
                    Pedido pedido = new Pedido(null, PedidoStatus.EM_ABERTO, abrigoEncontrado, distribuidoras.get(0));
                    pedidoService.adicionaPedido(pedido);
                    System.out.println("Pedido enviado");

                    for (Alimento alimento : alimentosEncontrados) {
                        alimento.getPedidos().add(pedido);
                        itemService.alteraItem(alimento);
                    }
                    break;
                } else {
                    System.out.println("Pedido cancelado");
                    break;
                }
            } else {
                System.out.println("Não foi encontrado nenhuma roupa com esses dados");
                break;
            }
        }
    }
}