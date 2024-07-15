package com.compass;

import com.compass.dao.PedidoDao;
import com.compass.entities.*;
import com.compass.enums.*;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        AbrigoService abrigoService = new AbrigoService();
        ItemService itemService = new ItemService();
        PessoaService pessoaService = new PessoaService();
        DistribuidoraService distribuidoraService = new DistribuidoraService();
        PedidoService pedidoService = new PedidoService();
        Menu menu = new Menu();
        ValidadorMenu validadorMenu = new ValidadorMenu();
        Date date = new Date();

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
                                    Scanner teclado = new Scanner(System.in);
                                    String descricao = null;
                                    System.out.println("Qual tipo de item sera doado " + "\n1-Roupa" + "\n2-Produto de Higiene" + "\n3-Alimento");
                                    int tipoItem = menu.lerOpcaoDeMenu(1, 3);

                                    Distribuidora distribuidora = null;
                                    switch (tipoItem) {

                                        case 1:
                                            System.out.println("Qual descrição da roupa:" + "\n1-Agasalho" + "\n2-Camisa" + "\n3-Calca" + "\n4-Bermuda");
                                            int escolhaDescricao = menu.lerOpcaoDeMenu(1, 4);

                                            descricao = menu.escolhaDeDescricaoRoupa(escolhaDescricao);

                                            if (escolhaDescricao < 1 || escolhaDescricao > 4) {
                                                break;
                                            }

                                            Genero genero = menu.escolhaDeGenero();

                                            if (genero == null) {
                                                break;
                                            }

                                            TamanhoRoupa tamanhoRoupa = menu.escolhaDeTamanhoRoupa();

                                            if (tamanhoRoupa == null) {
                                                break;
                                            }

                                            distribuidora = menu.escolhaDeDistribuidora();

                                            try {
                                                if (distribuidora != null) {
                                                    Roupa roupa = new Roupa(null, TipoItem.ROUPA, descricao, genero, tamanhoRoupa, null, distribuidora);
                                                    itemService.adiciona(roupa);
                                                    System.out.println("Item doado com sucesso");
                                                }
                                            } catch (LimiteAlcancadoException e) {
                                                System.out.println(e.getMessage());
                                                break;
                                            } catch (NullPointerException e) {
                                                System.out.println("Ocorreu um error");
                                                break;
                                            }
                                            break;
                                        case 2:
                                            System.out.println("Qual descrição do Produto de Higiene " + "\n1-Escova" + "\n2-Pasta de Dentes" + "\n3-Sabonete" + "\n4-Sabonete Liquido");
                                            escolhaDescricao = menu.lerOpcaoDeMenu(1, 4);

                                            descricao = menu.escolhaDescricaoProdutoHigiene(escolhaDescricao);
                                            distribuidora = menu.escolhaDeDistribuidora();

                                            try {
                                                if (distribuidora != null) {
                                                    ProdutoHigiene produtoHigiene = new ProdutoHigiene(null, TipoItem.PRODUTO_HIGIENE, descricao, null, distribuidora);
                                                    itemService.adiciona(produtoHigiene);
                                                    System.out.println("Item doado com sucesso");
                                                }
                                            } catch (LimiteAlcancadoException e) {
                                                System.out.println(e.getMessage());
                                                break;
                                            } catch (NullPointerException e) {
                                                System.out.println("Ocorreu um error");
                                                break;
                                            }
                                            break;
                                        case 3:
                                            System.out.println("Qual descrição do Alimento " + "\n1-Arroz" + "\n2-Feijão" + "\n3-Frango" + "\n4-Leite");
                                            escolhaDescricao = menu.lerOpcaoDeMenu(1, 4);
                                                descricao = menu.escolhaDescricaoAlimentos(escolhaDescricao);
                                            try {
                                                System.out.println("informe a quantidade do peso em medida");
                                                int quanitade = validadorMenu.validaEntrada();

                                                System.out.println("Informe a unidadeDeMedida \n" + "1-(KG)\n" + "2-(L)");
                                                int escolhaUnidadeDeMedida = validadorMenu.validaEntrada(2);

                                                UnidadeDeMedida unidadeDeMedida = menu.escolhaDaUnidadeDeMedida(escolhaUnidadeDeMedida);

                                                if (unidadeDeMedida == UnidadeDeMedida.KG && escolhaDescricao == 4) {
                                                    System.out.println("Leite não se mede em KG");
                                                    break;
                                                } else if (unidadeDeMedida == UnidadeDeMedida.L && escolhaUnidadeDeMedida != 4) {
                                                    System.out.println("Alimento solido não se mede em L");
                                                    break;
                                                }

                                                System.out.println("Informe a validade do alimento (yyyy-MM-dd)");
                                                String validadeStr = teclado.nextLine();

                                                String validade = validadorMenu.obterDataValidade(validadeStr);

                                                distribuidora = menu.escolhaDeDistribuidora();

                                                try {
                                                    if (distribuidora != null) {
                                                        Alimento alimento = new Alimento(null, TipoItem.ALIMENTO, descricao, quanitade, unidadeDeMedida, dateFormat.parse(validade), null, distribuidora);
                                                        itemService.adiciona(alimento);
                                                        System.out.println("Item doado com sucesso");
                                                    }
                                                } catch (LimiteAlcancadoException e) {
                                                    System.out.println(e.getMessage());
                                                    break;
                                                } catch (NullPointerException e) {
                                                    System.out.println("Ocorreu um error");
                                                    break;
                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("Entrada Invalida");
                                                break;
                                            } catch (ParseException e) {
                                                System.out.println(e.getMessage());
                                                break;
                                            }
                                            break;
                                    }
                                    break;
                                case 2:
                                    for (Item item : itemService.retornaItems()) {
                                        if (item instanceof Roupa roupa) {
                                            System.out.printf("%-5s | %-20s | %-10s | %-10s\n", roupa.getId(), roupa.getDescricao(), roupa.getGenero(), roupa.getTamanho());
                                        } else if (item instanceof ProdutoHigiene produtoHigiene) {
                                            System.out.printf("%-5s | %-20s\n", produtoHigiene.getId(), produtoHigiene.getDescricao());
                                        } else if (item instanceof Alimento alimento) {
                                            String formattedDate = dateFormat.format(alimento.getValidade());
                                            System.out.printf("%-5s | %-20s | %-15d | %-20s | %-15s\n", alimento.getId(), alimento.getDescricao(), alimento.getQuantidade(),
                                                    alimento.getUnidadeDeMedida(), formattedDate);
                                        }
                                    }
                                    break;
                                case 3:
                                    teclado = new Scanner(System.in);
                                    int opcaoCampo = 0;
                                    System.out.println("Digite id do item que deseja alterar");
                                    long itemId = validadorMenu.validaEntrada();

                                    Item item = itemService.buscaItemPorId(itemId);

                                    if (item instanceof Roupa roupa) {

                                        System.out.println("Qual campo deseja alterar: \n1-Descrição" + "\n2-Tamanho" + "\n3-Genero");
                                        opcaoCampo = menu.lerOpcaoDeMenu(1, 3);

                                        menu.alteraCamposRoupa(roupa, opcaoCampo, teclado);
                                    }
                                    if (item instanceof ProdutoHigiene produtoHigiene) {
                                        System.out.println("Insira a nova Descrição");
                                        descricao = teclado.nextLine();
                                        produtoHigiene.setDescricao(descricao);
                                        itemService.alteraItem(produtoHigiene);
                                        System.out.println("Campo Alterado");
                                    }
                                    if (item instanceof Alimento alimento) {
                                        System.out.println("Qual campo deseja alterar: \n1-Descrição" + "\n2-quantidade" + "\n3-unidade de medida" + "\n4-validade");
                                        opcaoCampo = menu.lerOpcaoDeMenu(1, 4);

                                        menu.alterCamposAlimento(alimento, opcaoCampo, teclado);

                                        if (opcaoCampo == 4) {
                                            System.out.println("Informe nova data de validade(yyyy-MM-dd)");
                                            String validadeStr = teclado.nextLine();
                                            try {
                                                validadorMenu.obterDataValidade(validadeStr);
                                                alimento.setValidade(dateFormat.parse(validadeStr));
                                                itemService.alteraItem(alimento);
                                                System.out.println("Campo Alterado");
                                            } catch (ParseException e) {
                                                System.out.println(e.getMessage());
                                                break;
                                            }
                                        }
                                    }
                                    break;

                                case 4:
                                    System.out.println("Digite o Id do item que deseja remover");
                                    itemId = validadorMenu.validaEntrada();
                                    itemService.removeItem(itemId);
                                    System.out.println("Item removido");
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
                                    for (Pedido pedido : pedidos) {
                                        if (pedido.getDistribuidora().getId() == distrbuidoraId && pedido.getPedidoStatus() == PedidoStatus.EM_ABERTO) {
                                            pedidosFiltrados.add(pedido);
                                        }
                                    }

                                    if (pedidosFiltrados.isEmpty()) {
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

                                    if (itemsDoPedido.isEmpty()) {
                                        System.out.println("O pedido não possui items");
                                        break;
                                    }

                                    System.out.println("1-(Aceitar)" + "\n2-(Recusar)");
                                    int escolha = validadorMenu.validaEntrada(2);

                                    List<Item> items = itemService.buscaTodosItems();
                                    int quantidadeItem = itemService.determinaQuantidadeDeItemsAbrigo(items, itemsDoPedido.get(0), abrigoDoPedido);
                                    System.out.println("LISTA na MAIN");
                                    for (Item item : items) {
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
                                    quantidadeItem = +itemsDoPedido.size();

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
                                            if (pedido != null) {
                                                System.out.println("Pedido rejeitado");
                                                System.out.println("Qual motivo: ");
                                                teclado.nextLine();
                                                String msg = teclado.nextLine();
                                                pedido.setPedidoStatus(PedidoStatus.NEGADO);
                                                pedido.setMensagem(msg);
                                                pedidoService.alteraPedido(pedido);
                                            }
                                            break;
                                        default:
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
                                    System.out.println("Informe o nome do abrigo:");
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