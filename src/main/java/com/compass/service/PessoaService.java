package com.compass.service;

import com.compass.dao.PessoaDao;
import com.compass.entities.Pessoa;

public class PessoaService {

    PessoaDao pessoaDao = new PessoaDao();

    public void adicionaPessoa(Pessoa pessoa) {
        pessoaDao.addPessoa(pessoa);
    }
}
