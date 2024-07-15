package com.compass;

import com.compass.dao.PedidoDao;
import com.compass.entities.*;
import com.compass.enums.Genero;
import com.compass.enums.PedidoStatus;
import com.compass.enums.TamanhoRoupa;
import com.compass.enums.TipoItem;
import com.compass.exception.LimiteAlcancadoException;
import com.compass.service.*;
import com.compass.util.CsvReader;
import com.compass.util.menu.ValidadorMenu;
import com.compass.util.menu.Menu;
import org.hibernate.boot.jaxb.SourceType;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    public static void main(String[] args) {


        AbrigoService abrigoService = new AbrigoService();
        ItemService itemService = new ItemService();
        PessoaService pessoaService = new PessoaService();
        DistribuidoraService distribuidoraService = new DistribuidoraService();
        PedidoService pedidoService = new PedidoService();
        Menu menu = new Menu();
        ValidadorMenu validadorMenu = new ValidadorMenu();

        Endereco endereco = new Endereco();
        Pessoa responsavel = new Pessoa(null, "Feliphe", "888-999-444.61");
        pessoaService.adicionaPessoa(responsavel);

        Abrigo abrigo1 = new Abrigo(null, "AbrigoTest", endereco, responsavel, "71-982446140", "gg@gmail.com", 300);

        abrigoService.addAbrigo(abrigo1);


        CsvReader csvReader = new CsvReader();


        distribuidoraService.adicionaDistribuidorasCsv(csvReader.lerDadosDeDistribuidora());
        itemService.adicionaItemsCsv(csvReader.lerDadosDeItems(distribuidoraService.retornaDistribuidoras()));


        int opcaoMenu = 0;
        int opcaoSubMenu = 0;

        while (true) {
            menu.exibirMenuPrincipal();
            opcaoMenu = menu.lerOpcaoDeMenu(0, 6);

            if (opcaoMenu > 0 && opcaoMenu <= 5) {
                while (true) {
                    int subMenu = menu.chamarSubMenu(opcaoMenu);
                    opcaoSubMenu = menu.lerOpcaoDeMenu(0, 6);

                    if (opcaoMenu == 2 && opcaoSubMenu == 6) {
                        break;
                    } else if (opcaoMenu != 2 && opcaoSubMenu == 5) {
                        break;
                    }

                    switch (subMenu) {
                        case 1:
                            switch (opcaoSubMenu) {
                                case 1:
                                    System.out.println("Inicia cadastro de item");
                                    break;
                                case 2:
                                    System.out.println("Lista de items");
                                    break;
                                case 3:
                                    System.out.println("Altera item");
                                    break;
                                case 4:
                                    System.out.println("Remove item");
                                    break;
                            }
                            break;
                        case 2:
                            switch (opcaoSubMenu) {
                                case 1:
                                    Scanner teclado = new Scanner(System.in);

                                    System.out.println("Informe id da distribuidora");
                                    long distrbuidoraId = teclado.nextLong();

                                    Distribuidora distribuidora = distribuidoraService.buscaDistribuidoraPorId(distrbuidoraId);

                                    if (distribuidora == null) {
                                        System.out.println("Nenhuma distribuidora encontrada");
                                        break;
                                    }

                                    List<Pedido> pedidos = pedidoService.buscaTodosPedidos();

                                    if (pedidos.isEmpty()) {
                                        System.out.println("Nenhum pedido encontrado");
                                        break;
                                    }

                                    List<Pedido> pedidosFiltrados = new ArrayList<>();
                                    for(Pedido pedido : pedidos) {
                                        if(pedido.getDistribuidora().getId() == distrbuidoraId && pedido.getPedidoStatus() == PedidoStatus.EM_ABERTO) {
                                            pedidosFiltrados.add(pedido);
                                        }
                                    }

                                    if(pedidosFiltrados.isEmpty()) {
                                        System.out.println("Nenhum pedido está associado ao " + distribuidora.getNome());
                                        break;
                                    }

                                    for (Pedido pedido : pedidosFiltrados) {
                                        System.out.println("id:" + pedido.getId() + "\nstatus: " + pedido.getPedidoStatus() + "\nEmissor: " + pedido.getAbrigo().getNome());
                                        System.out.println("---------------");
                                    }

                                    System.out.println("Digite o id do pedido para ver detalhes");
                                    long idPedido = validadorMenu.validaEntrada();

                                    List<Item> itemsDoPedido = new ArrayList<>();
                                    Abrigo abrigoDoPedido = null;
                                    for (Pedido pedido : pedidosFiltrados) {
                                        if (idPedido == pedido.getId() && pedido.getPedidoStatus() == PedidoStatus.EM_ABERTO) {
                                            System.out.println("Detalhes do Pedido de ID: " + pedido.getId());
                                            System.out.println("item: " + pedido.getItems().get(0).getDescricao());
                                            System.out.println("Quantidade: " + pedido.getItems().size());
                                            itemsDoPedido = pedido.getItems();
                                            abrigoDoPedido = pedido.getAbrigo();
                                        }
                                    }

                                    if(itemsDoPedido.isEmpty()) {
                                        System.out.println("O pedido não possui items");
                                        break;
                                    }

                                    System.out.println("1-(Aceitar)" + "\n2-(Recusar)");
                                    int escolha = validadorMenu.validaEntrada(2);

                                    List<Item> items = itemService.buscaTodosItems();
                                    int quantidadeItem = itemService.determinaQuantidadeDeItemsAbrigo(items, itemsDoPedido.get(0), abrigoDoPedido);
                                    System.out.println("LISTA na MAIN");
                                    for(Item item : items) {
                                        if (abrigoDoPedido != null && item.getAbrigo() != null) {
                                            if (item instanceof Roupa && item.getAbrigo().getId().equals(abrigoDoPedido.getId())) {

                                                System.out.println(item.getDescricao());
                                            }
                                        }
                                    }

                                        if (quantidadeItem >= 5) {
                                        System.out.println("Limite do abrigo foi alcancado =" + quantidadeItem);
                                        break;
                                    }
                                    quantidadeItem =+ itemsDoPedido.size();

                                    System.out.println("Quantidade =" + quantidadeItem);

                                    if (quantidadeItem >= 5) {
                                        System.out.println("Limite do abrigo será ultrapassado quantidade atual: " + quantidadeItem + " quantidade de envio: " + itemsDoPedido.size());
                                        break;
                                    }

                                    Pedido pedido = pedidoService.buscaPedidoPorId(idPedido);

                                    switch (escolha) {
                                        case 1:
                                            for (Item item : itemsDoPedido) {
                                                if (item.getAbrigo() == null && item.getDistribuidora().getId() == distrbuidoraId) {
                                                    item.setDistribuidora(null);
                                                    item.setAbrigo(abrigoDoPedido);
                                                    itemService.alteraItem(item);
                                                }
                                            }
                                            pedido.setPedidoStatus(PedidoStatus.ACEITO);
                                            pedidoService.alteraPedido(pedido);

                                            System.out.println("Pedido Aceito");
                                            break;
                                        case 2:
                                            if(pedido != null) {
                                                System.out.println("Pedido rejeitado");
                                                System.out.println("Qual motivo: ");
                                                teclado.nextLine();
                                                String msg = teclado.nextLine();
                                                pedido.setPedidoStatus(PedidoStatus.NEGADO);
                                                pedido.setMensagem(msg);
                                                pedidoService.alteraPedido(pedido);
                                            }
                                            break;
                                        default :
                                            System.out.println("Opção invalida");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Cadastro de distribuidoras");
                                    break;
                                case 3:
                                    System.out.println("Lista de distribuidoras");
                                    break;
                                case 4:
                                    System.out.println("Altera distribuidora");
                                    break;
                                case 5:
                                    System.out.println("Remove distribuidora");
                                    break;
                            }
                            break;
                        case 3:
                            switch (opcaoSubMenu) {
                                case 1:
                                    System.out.println("Cadastro de abrigos");
                                    break;
                                case 2:
                                    System.out.println("Lista abrigos");
                                    break;
                                case 3:
                                    System.out.println("Altera abrigo");
                                    break;
                                case 4:
                                    System.out.println("Remove abrigo");
                                    break;
                            }
                            break;
                        case 4:
                            switch (opcaoSubMenu) {
                                case 1:
                                    try {
                                        long idAbrigo = 0L;
                                        int tipoItem = 0;
                                        int quantidade = 0;

                                        Scanner teclado = new Scanner(System.in);
                                        System.out.println("digite o id do abrigo");
                                        idAbrigo = teclado.nextLong();
                                        teclado.nextLine();

                                        Abrigo abrigoEncontrado = abrigoService.buscaAbrigoPorId(idAbrigo);

                                        if (abrigoEncontrado == null) {
                                            System.out.println("Nenhuma Abrigo encontrado");
                                            break;
                                        }

                                        System.out.println("Qual tipo do item que você precisa " + abrigoEncontrado.getNome() + "\n1- Roupa\n" + "2- Produto de Higiene\n" + "3- Alimento");
                                        tipoItem = validadorMenu.validaEntrada(3);

                                        menu.criacaoDePedido(tipoItem, quantidade, abrigoEncontrado);
                                        break;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Digito Invalido");
                                    } catch (NullPointerException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 2:
                                    System.out.println("Listar pedidos");
                                    break;
                                case 3:
                                    System.out.println("Altera pedido");
                                    break;
                                case 4:
                                    System.out.println("Remove pedido");
                                    break;
                            }
                            break;
                        case 5:
                            switch (opcaoSubMenu) {
                                case 1:
                                    System.out.println("Cadastra pessoa");
                                    break;
                                case 2:
                                    System.out.println("Listar pessoas");
                                    break;
                                case 3:
                                    System.out.println("Altera pessoa");
                                    break;
                                case 4:
                                    System.out.println("Remove pessoa");
                                    break;
                            }
                            break;
                    }
                }
            } else if (opcaoMenu == 6) {
                System.out.println("Programa finalizado");
                break;
            }
        }
    }
}


      /*
        Roupa roupa = new Roupa(null, TipoItem.ROUPA, "Camisa", Genero.M, TamanhoRoupa.P, distribuidoraService.buscaDistribuidoraPorId(1));
        try {
            itemService.adiciona(roupa);
        }catch (LimiteAlcancadoException | IllegalArgumentException e) {
             System.out.println(e.getMessage());
        }

        System.out.println("Hello world!");
         */