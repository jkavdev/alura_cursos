# Testes de Integração - Alura

* para realizar o teste de integracao dos `DAO`s precisamos mesmo bater no banco de dados
* para isso podemos utilizar bancos leves em memoria que simulem um banco de dados
* estaremos utilizando o `hsqldb`

    <dependency>
        <groupId>org.hsqldb</groupId>
        <artifactId>hsqldb</artifactId>
        <version>2.4.0</version>
    </dependency>

* temos a classe que `CriaTabelas` que inicia a estrutura para o banco a ser utilizado nos testes

    Configuration cfg = new CriadorDeSessao().getConfig();
    SchemaExport se = new SchemaExport(cfg);
    se.create(true, true);

* com isso ele criara a estrutura do banco na raiz do projeto
* montando a estrutura inicial podemos iniciar os testes

    @Test
    public void deveEncontrarPeloNomeEEmailEncontradoTest(){
        Session session = new CriadorDeSessao().getSession();
        UsuarioDao usuarioDao = new UsuarioDao(session);
        Usuario jhonatan = new Usuario("Jhonatan Kolen", "jhonatan@gmail.com");
        usuarioDao.salvar(jhonatan);
        Usuario jhonatanSalvo = usuarioDao.porNomeEEmail("Jhonatan Kolen", "jhonatan@gmail.com");
        assertEquals(jhonatan.getNome(), jhonatanSalvo.getNome());
        assertEquals(jhonatan.getEmail(), jhonatanSalvo.getEmail());
    }

# Testes de integracao, sao testes que verificam a comunicacao do sistema com outros sistemas,
* no caso estamos testando a conexao com o banco de dados

