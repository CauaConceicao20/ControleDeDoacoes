package com.compass;

import com.compass.entities.*;
import com.compass.enums.*;
import com.compass.exception.LimiteAlcancadoException;
import com.compass.service.*;
import com.compass.util.CsvReader;
import com.compass.util.menu.ValidadorMenu;
import com.compass.util.menu.Menu;
import org.hibernate.boot.model.source.spi.SingularAttributeSourceToOne;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        AbrigoService abrigoService = new AbrigoService();
        ItemService itemService = new ItemService();

        DistribuidoraService distribuidoraService = new DistribuidoraService();
        PedidoService pedidoService = new PedidoService();
        Menu menu = new Menu();
        ValidadorMenu validadorMenu = new ValidadorMenu();
        Date date = new Date();

        Endereco endereco1 = new Endereco("Rua A", 100, "Centro", "São Paulo", "São Paulo", "01000-000");
        Abrigo abrigo1 = new Abrigo(null, "AbrigoTest1", endereco1, "Pedro vieara", "(71) 98244-6140", "gg@gmail.com", 300);
        abrigoService.addAbrigo(abrigo1);


        Endereco endereco2 = new Endereco("Rua B", 200, "Jardim", "Rio de Janeiro", "Rio de Janeiro", "20000-000");
        Abrigo abrigo2 = new Abrigo(null, "AbrigoTest2", endereco2, "Rodrigo vieira", "(71) 98244-6141", "hh@gmail.com", 250);
        abrigoService.addAbrigo(abrigo2);


        Endereco endereco3 = new Endereco("Rua C", 300, "Vila", "Belo Horizonte", "Minas Gerais", "30000-000");
        Abrigo abrigo3 = new Abrigo(null, "AbrigoTest3", endereco3, "Bruno vieira", "(71) 98244-6142", "ii@gmail.com", 200);
        abrigoService.addAbrigo(abrigo3);


        CsvReader csvReader = new CsvReader();

        distribuidoraService.adicionaDistribuidorasCsv(csvReader.lerDadosDeDistribuidora());
        itemService.adicionaItemsCsv(csvReader.lerDadosDeItems(distribuidoraService.retornaDistribuidoras()));

        int opcaoMenu = 0;
        int opcaoSubMenu = 0;

        while (true) {
            menu.exibirMenuPrincipal();
            opcaoMenu = menu.lerOpcaoDeMenu(0, 5);

            if (opcaoMenu > 0 && opcaoMenu <= 5) {
               if (opcaoMenu == 5) {
                    System.out.println("Programa finalizado");
                    break;
                }
                while (true) {
                    int subMenu = menu.chamarSubMenu(opcaoMenu);
                    opcaoSubMenu = menu.lerOpcaoDeMenu(0, 6);

                    if (opcaoMenu == 2 && opcaoSubMenu == 6) {
                        break;
                    } else if (opcaoMenu != 2 && opcaoSubMenu == 5) {
                        break;
                    }
                    long distribuidoraId = 0L;
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
                                                int quantidade = validadorMenu.validaEntrada();

                                                if (quantidade <= 0) {
                                                    break;
                                                }

                                                System.out.println("Informe a unidadeDeMedida \n" + "1-(KG)\n" + "2-(L)");
                                                int escolhaUnidadeDeMedida = validadorMenu.validaEntrada(2);

                                                if (escolhaUnidadeDeMedida <= 0) {
                                                    break;
                                                }

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
                                                        Alimento alimento = new Alimento(null, TipoItem.ALIMENTO, descricao, quantidade, unidadeDeMedida, dateFormat.parse(validade), null, distribuidora);
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
                                    try {
                                        System.out.println("Digite id do item que deseja alterar");
                                        long itemId = validadorMenu.validaEntrada();

                                        if (itemId <= 0) {
                                            break;
                                        }
                                        Item item = itemService.buscaItemPorId(itemId);

                                        if (item == null) {
                                            System.out.println("Item não encontrado");
                                            break;
                                        }

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

                                            menu.alteraCamposAlimento(alimento, opcaoCampo, teclado);

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
                                    }catch (InputMismatchException e) {
                                        System.out.println("Digito invalido");
                                        break;
                                    }

                                case 4:
                                    System.out.println("Digite o Id do item que deseja remover");
                                    try {
                                        long itemId = validadorMenu.validaEntrada();
                                        if (itemId <= 0) {
                                            break;
                                        }
                                        itemService.removeItem(itemId);
                                    } catch (InputMismatchException e) {
                                        System.out.println("Entrada Invalida");
                                    }
                                    break;
                            }
                            break;
                        case 2:
                            Distribuidora distribuidora = null;
                            switch (opcaoSubMenu) {
                                case 1:
                                    Scanner teclado = new Scanner(System.in);

                                    try {
                                        System.out.println("Informe id da distribuidora");
                                        distribuidoraId = teclado.nextLong();

                                        distribuidora = distribuidoraService.buscaDistribuidoraPorId(distribuidoraId);

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
                                            if (pedido.getDistribuidora().getId() == distribuidoraId && pedido.getPedidoStatus() == PedidoStatus.EM_ABERTO) {
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

                                        if (idPedido <= 0) {
                                            break;
                                        }

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

                                        if (items.isEmpty()) {
                                            System.out.println("Lista de items está vazia");
                                            break;
                                        }

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
                                                    if (item.getAbrigo() == null && item.getDistribuidora().getId() == distribuidoraId) {
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
                                    }catch (InputMismatchException e) {
                                        System.out.println("Digito invalido");
                                        break;
                                    }
                                    break;
                                case 2:
                                    teclado = new Scanner(System.in);
                                    System.out.println("Informe nome da distribuidora");
                                    String nome = teclado.nextLine();
                                    if (nome.trim().isEmpty()) {
                                        System.out.println("Nome do abrigo não pode ser vazio");
                                        break;
                                    }
                                    Endereco endereco = menu.cadastraEndereco(teclado);

                                    distribuidora = new Distribuidora(null, nome, endereco);
                                    break;
                                case 3:
                                    List<Distribuidora> distribuidoras = distribuidoraService.retornaDistribuidoras();
                                    if (distribuidoras.isEmpty()) {
                                        System.out.println("Lista de distribuidoras vazia");
                                        break;
                                    }
                                    for (Distribuidora distribuidoraList : distribuidoras) {
                                        System.out.printf(
                                                "ID: %d\nNome: %s\nLogradouro: %s\nNúmero: %d\nBairro: %s\nCidade: %s\nEstado: %s\nCEP: %s\n\n", distribuidoraList.getId(),
                                                distribuidoraList.getNome(), distribuidoraList.getEndereco().getLogradouro(), distribuidoraList.getEndereco().getNumero(),
                                                distribuidoraList.getEndereco().getBairro(), distribuidoraList.getEndereco().getCidade(), distribuidoraList.getEndereco().getEstado(),
                                                distribuidoraList.getEndereco().getCep()
                                        );
                                    }
                                    break;
                                case 4:
                                    try {
                                        teclado = new Scanner(System.in);
                                        System.out.println("Insira o id da distribuidora que deseja alterar");
                                        distribuidoraId = validadorMenu.validaEntrada();

                                        if(distribuidoraId <= 0) {
                                            break;
                                        }

                                        Distribuidora distribuidoraEncontrada = distribuidoraService.buscaDistribuidoraPorId(distribuidoraId);
                                        if (distribuidoraEncontrada == null) {
                                            System.out.println("Distribuidora não encontrada");
                                            break;
                                        }

                                        System.out.println("Qual campo deseja alterar " + "\n1-Nome do Distribuidora" + "\n2-Bairro" + "\n3-Cep" +
                                                "\n4-Estado" + "\n5-cidade" + "\n6-numero" + "\n7-logradouro");
                                        int opcaoCampo = menu.lerOpcaoDeMenu(1, 10);

                                        menu.alteraDistribuidora(opcaoCampo, distribuidoraEncontrada, teclado);

                                    } catch (InputMismatchException e) {
                                        System.out.println("Digito invalido");
                                        break;
                                    }
                                    break;
                                case 5:
                                    try {
                                        System.out.println("Informe id da distribuidora que deseja remover");
                                        distribuidoraId = validadorMenu.validaEntrada();
                                        if(distribuidoraId <= 0) {;
                                            break;
                                        }
                                        distribuidoraService.buscaDistribuidoraPorId(distribuidoraId);
                                    } catch (InputMismatchException e) {
                                        System.out.println("Digito invalido");
                                        break;
                                    }

                                    break;
                            }
                            break;
                        case 3:
                            switch (opcaoSubMenu) {
                                case 1:
                                    String regexLetras = "^[a-zA-Z]{4,}$";
                                    try {
                                        Scanner teclado = new Scanner(System.in);
                                        System.out.println("informe nome do abrigo");
                                        String nome = teclado.nextLine();
                                        if (!nome.matches(regexLetras)) {
                                            System.out.println("Nome do abrigo deve ter apenas letras e no mínimo 4 caracteres");
                                            break;
                                        }

                                        System.out.println("informe nome do responsavel");
                                        String responsavel = teclado.nextLine();
                                        if (!responsavel.matches(regexLetras)) {
                                            System.out.println("Nome do responsavel deve ter apenas letras e no mínimo 4 caracteres");
                                            break;
                                        }

                                        System.out.println("Informe Email");
                                        String email = teclado.nextLine();

                                        if (!validadorMenu.validaEmail(email)) {
                                            System.out.println("Email invalido");
                                            break;
                                        }

                                        // Solicita e valida o telefone
                                        System.out.println("Informe Telefone:");
                                        String telefone = teclado.nextLine();

                                        if (!validadorMenu.validaTelefone(telefone)) {
                                            System.out.println("Telefone Inválido");
                                            break;
                                        }

                                        System.out.println("Qual a capacidade de pessoas suportadas:");
                                        int capacidade = validadorMenu.validaEntrada();

                                        if(capacidade <=0) {
                                            break;
                                        }
                                        Endereco endereco = menu.cadastraEndereco(teclado);

                                        Abrigo abrigo = new Abrigo(null, nome, endereco, responsavel, telefone, email, capacidade);

                                        abrigoService.addAbrigo(abrigo);

                                        System.out.println("Abrigo adicionado");
                                    } catch (InputMismatchException e) {
                                        System.out.println("Numero invalido");
                                        break;
                                    }
                                    break;
                                case 2:
                                    List<Abrigo> abrigos = abrigoService.buscaTodosAbrigos();
                                    if (abrigos.isEmpty()) {
                                        System.out.println("Lista de abrigos vazia");
                                        break;
                                    }
                                    System.out.println("Lista de Abrigos:\n");
                                    for (Abrigo abrigo : abrigos) {
                                        int quantidadeItens = abrigo.getItems() != null ? abrigo.getItems().size() : 0;
                                        System.out.printf("ID: %d\nNome: %s\nResponsável: %s\nTelefone: %s\nEmail: %s\nCapacidade: %d\nQuantidade: %d\n\n", abrigo.getId(), abrigo.getNome(), abrigo.getResponsavel(),
                                                abrigo.getTelefone(), abrigo.getEmail(), abrigo.getCapacidade(), quantidadeItens);
                                    }
                                    break;
                                case 3:
                                    try {
                                        Scanner teclado = new Scanner(System.in);
                                        System.out.println("Digite id do abrigo que deseja alterar");
                                        long abrigoId = validadorMenu.validaEntrada();

                                        if(abrigoId <= 0) {
                                            break;
                                        }

                                        Abrigo abrigo = abrigoService.buscaAbrigoPorId(abrigoId);
                                        if (abrigo == null) {
                                            System.out.println("Abrigo não encontrdo");
                                            break;
                                        }
                                        System.out.println("Qual campo deseja alterar " + "\n1-Nome do abrigo" + "\n2-Nome do responsavel" + "\n3-Telefone" + "\n4-email" + "\n5-Bairro" + "\n6-Cep" +
                                                "\n7-Estado" + "\n8-cidade" + "\n9-numero" + "\n10-logradouro");
                                        int opcaoCampo = menu.lerOpcaoDeMenu(1, 10);
                                        menu.alteraAbrigo(opcaoCampo, abrigo, teclado);
                                    } catch (InputMismatchException e) {
                                        System.out.println("Digito invalido");
                                        break;
                                    }
                                    break;
                                case 4:
                                    System.out.println("Digite id do abrigo que deseja remover");
                                    try {
                                        long abridoId = 0L;
                                        abridoId = validadorMenu.validaEntrada();

                                        if (abridoId <= 0) {
                                            break;
                                        }
                                        abrigoService.removeAbrigo(abridoId);
                                    } catch (InputMismatchException e) {
                                        System.out.println("Digito invalido");
                                    }
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
                                    List<Pedido> pedidos = pedidoService.buscaTodosPedidos();
                                    if (pedidos.isEmpty()) {
                                        System.out.println("Lista de pedidos está vazia");
                                        break;
                                    }

                                    for (Pedido pedido : pedidos) {
                                        if (pedido.getAbrigo() != null) {
                                            System.out.printf("ID do Pedido: %d\nStatus do Pedido: %s\nNome do Abrigo: %s\nNome da Distribuidora: %s\n\n",
                                                    pedido.getId(), pedido.getPedidoStatus(), pedido.getAbrigo().getNome(), pedido.getDistribuidora().getNome());
                                        } else {
                                            System.out.printf("ID do Pedido: %d\nStatus do Pedido: %s\nNome da Distribuidora: %s\n\n",
                                                    pedido.getId(), pedido.getPedidoStatus(), pedido.getDistribuidora().getNome());
                                        }
                                    }
                                    break;
                                case 3:
                                    try {
                                        System.out.println("Informe id do pedido que deseja alterar");
                                        long pedidoId = validadorMenu.validaEntrada();

                                        if (pedidoId <= 0) {
                                            break;
                                        }

                                        Pedido pedido = pedidoService.buscaPedidoPorId(pedidoId);

                                        System.out.println("Para qual status gostaria de altera o pedido:" + "\n1-aceito" + "\n2-em aberto" + "\n3-fechado");
                                        int opcaoCampo = validadorMenu.validaEntrada();

                                        if (opcaoCampo <= 0) {
                                            break;
                                        }

                                        switch (opcaoCampo) {
                                            case 1:
                                                if(pedido.getPedidoStatus() == PedidoStatus.ACEITO) {
                                                    System.out.println("Esse pedido já está como aceito");
                                                    break;
                                                }
                                                pedido.setPedidoStatus(PedidoStatus.ACEITO);
                                                pedidoService.alteraPedido(pedido);
                                                System.out.println("Pedido alterado");
                                                break;
                                            case 2:
                                                if(pedido.getPedidoStatus() == PedidoStatus.EM_ABERTO) {
                                                    System.out.println("Esse pedido já está como Em Aberto");
                                                    break;
                                                }
                                                pedido.setPedidoStatus(PedidoStatus.EM_ABERTO);
                                                pedidoService.alteraPedido(pedido);
                                                System.out.println("Pedido Alterado");
                                                break;
                                            case 3:
                                                if(pedido.getPedidoStatus() == PedidoStatus.NEGADO) {
                                                    System.out.println("Esse pedido já está como Negado");
                                                    break;
                                                }
                                                pedido.setPedidoStatus(PedidoStatus.NEGADO);
                                                pedidoService.alteraPedido(pedido);
                                                System.out.println("Pedido Alterado");
                                                break;
                                        }

                                    } catch (InputMismatchException e) {
                                        System.out.println("Digito invalido");
                                    }
                                    break;
                                case 4:
                                    try {
                                        System.out.println("Digite id do Pedido: ");
                                        long pedidoId = validadorMenu.validaEntrada();

                                        if (pedidoId <= 0) {
                                            break;
                                        }
                                        pedidoService.removePedido(pedidoId);
                                    }catch(InputMismatchException e) {
                                        System.out.println("Digito invalido");
                                    }
                                    break;
                            }
                            break;
                    }
                }
            }
        }
    }
}
