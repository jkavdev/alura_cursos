package br.com.jkavdev.alura.integracao.leilao.curso;

import br.com.jkavdev.alura.integracao.leilao.dao.CriadorDeSessao;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class CriaTabelas {

    public static void main(String[] args) {

        Configuration cfg = new CriadorDeSessao().getConfig();
        SchemaExport se = new SchemaExport(cfg);

        se.create(true, true);
    }

}
