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

* para melhorar o codigo de teste, podemos utilizar as funcionalidades do `JUnit`

    Session session;
    UsuarioDao usuarioDao;
    LeilaoDao leilaoDao;
    @Before
    public void antes() {
        session = new CriadorDeSessao().getSession();
        usuarioDao = new UsuarioDao(session);
        leilaoDao = new LeilaoDao(session);
        session.beginTransaction();
    }
    @After
    public void depois() {
        session.getTransaction().rollback();
        session.close();
    }

* criaremos as dependencias utilizadas pelos testes antes de serem executados
* e fecharemos qualqer recurso criado durante o teste depois de executa-lo

* em testes que envolvam insercao no banco
* uma boa pratica eh fazer com que seu teste nao realize as alteracoes no banco
* garantindo uma consistencia para os outros testes, nao deixando um banco com dados sujos
* podemos iniciar uma transacao, mas no final dos testes, realizamos um rollback

    @Before
    public void antes() {
        session.beginTransaction();
    }
    @After
    public void depois() {
        session.close();
    }

