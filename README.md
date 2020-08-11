# App desenvolvido para estudar o funcionamento do Room Database

## Alguns conceitos importantes:
1. Entity: Anotações em uma classe que descreve como uma tabela de banco de dados irá trabalhar com Room.
2. SQLite Database: Banco de dados para armazenamento no dispositivo. A biblioteca de persistência do Room cria e mantém esse banco de dados para você.
3. DAO: Data Access Object (Objeto de acesso aos dados). Um mapeamento de consultas SQL para funções. Ao usar um DAO, você chama os métodos e a Room cuida do resto.
4. Room Database: Simplifica o trabalho do banco de dados e serve como um ponto de acesso ao banco de dados SQLite subjacente (oculta SQLiteOpenHelper). O banco de dados Room usa o DAO para fazer consultas ao banco de dados SQLite.
5. Repository: Não faz parte da arquitetura ROOM, mas é considerada uma boa prática, pois através dele, podemos gerenciar múltiplas fontes de dados (Exemplo: RESTful (web) /Local(SQLite)).

## Diagrama de Classes
<img src="https://github.com/EdgardOliveira/room/blob/master/imagens/ClassDiagram.png" alt="diagramaClasse"  height="500" width="300">


## Importe a biblioteca
1. Abra o build.gradle (Module: app) e adicione as dependências:

```
dependencies {
    def room_version = "2.2.5"
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
```

### Exemplo de Entidade
```java
@Entity(tableName = "alunos")
public class Aluno {

    @PrimaryKey(autoGenerate = true)
    public long alunoId;
    private String primeiroNome;
    private String ultimoNome;
    private String cpf;
}
```

### Exemplo de DAO Genérico com as operações comuns (que todos os DAO's irão ter (CRUD))
```java
@Dao
public interface GenericoDao<T> {

    /**
     * Função responsável por inserir um objeto na base de dados
     *
     * @param obj o objeto que será inserido
     */
    @Insert
    long inserir(T obj);

    /**
     * Função responsável por inserir um array de objetos na base de dados
     *
     * @param obj o objeto a ser inserido na base de dados
     */
    @Insert
    void inserir(T... obj);

    /**
     * Função responsável por atualizar um objeto na base de dados
     *
     * @param obj o objeto a ser atualizado
     */
    @Update
    void atualizar(T obj);

    /**
     * Função responsável por atualizar um array de objetos na base de dados
     *
     * @param obj o objeto a ser atualizado
     */
    @Update
    void atualizar(T... obj);

    /**
     * Função reponsável por excluir um objeto da base de dados
     *
     * @param obj objeto a ser ser excluído da base de dados
     */
    @Delete
    void excluir(T obj);
}
```

### Exemplo de DAO (somente as consultas específicas)

```java
@Dao
public abstract class AlunoDao implements GenericoDao<Aluno> {

    @Query("SELECT * FROM alunos")
    public abstract List<Aluno> listar();

    @Query("SELECT * FROM alunos WHERE alunoId = :id")
    public abstract Aluno listarPorId(long id);
}
```

### Exemplo de repository
```java
public class AlunoRepository {

    private UniversidadeDatabase universidadeDatabase;

    public AlunoRepository(Context context){
        universidadeDatabase = UniversidadeDatabase.getInstance(context);
    }

    public long cadastrar(Aluno aluno){
        CadastrarAlunoAsyncTask asyncTask = new CadastrarAlunoAsyncTask(universidadeDatabase.getAlunoDao());
        Long id = null;
        try {
            id = asyncTask.execute(aluno).get().longValue();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void atualizar(Aluno aluno){
        new AtualizarAlunoAsyncTask(universidadeDatabase.getAlunoDao()).execute(aluno);
    }

    public void excluir(Aluno aluno){
        new ExcluirAlunoAsyncTask(universidadeDatabase.getAlunoDao()).execute(aluno);
    }

    public List<Aluno> listar() {
        return universidadeDatabase.getAlunoDao().listar();
    }

    public Aluno listarPorId(Long id) {
        return universidadeDatabase.getAlunoDao().listarPorId(id);
    }
}
```

### Exemplo de AsyncTask

## Cadastrar
```java
public class CadastrarAlunoAsyncTask extends AsyncTask<Aluno, Void, Long> {

    private AlunoDao alunoDao;

    public CadastrarAlunoAsyncTask(AlunoDao alunoDao) {
        this.alunoDao = alunoDao;
    }

    @Override
    protected Long doInBackground(Aluno... alunos) {
        return alunoDao.inserir(alunos[0]);
    }

    @Override
    protected void onPostExecute(Long id) {
        super.onPostExecute(id);
    }
}
```
## Atualizar
```java
public class AtualizarAlunoAsyncTask extends AsyncTask<Aluno, Void, Void> {

    private AlunoDao alunoDao;

    public AtualizarAlunoAsyncTask(AlunoDao alunoDao) {
        this.alunoDao = alunoDao;
    }

    @Override
    protected Void doInBackground(Aluno... alunos) {
        alunoDao.atualizar(alunos[0]);
        return null;
    }
}
```

## Excluir
```java
public class ExcluirAlunoAsyncTask extends AsyncTask<Aluno, Void, Void> {

    private AlunoDao alunoDao;

    public ExcluirAlunoAsyncTask(AlunoDao alunoDao) {
        this.alunoDao = alunoDao;
    }

    @Override
    protected Void doInBackground(Aluno... alunos) {

        alunoDao.excluir(alunos[0]);

        return null;
    }
}
```
