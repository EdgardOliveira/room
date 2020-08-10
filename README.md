# App desenvolvido para estudar o funcionamento do Room Database

## Alguns conceitos importantes:
1. Entity: Anotações em uma classe que descreve como uma tabela de banco de dados irá trabalhar com Room.
2. SQLite Database: Banco de dados para armazenamento no dispositivo. A biblioteca de persistência do Room cria e mantém esse banco de dados para você.
3. DAO: Data Access Object (Objeto de acesso aos dados). Um mapeamento de consultas SQL para funções. Ao usar um DAO, você chama os métodos e a Room cuida do resto.
4. Room Database: Simplifica o trabalho do banco de dados e serve como um ponto de acesso ao banco de dados SQLite subjacente (oculta SQLiteOpenHelper). O banco de dados Room usa o DAO para fazer consultas ao banco de dados SQLite.
5. Repository: Não faz parte da arquitetura ROOM, mas é considerada uma boa prática, pois através dele, podemos gerenciar múltiplas fontes de dados (Exemplo: RESTful (web) /Local(SQLite)).

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
public interface BaseDao<T> {
    
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
public interface AlunoDao {
    @Insert
    long inserir(Aluno aluno);

    @Update
    void atualizar(Aluno aluno);

    @Delete
    void excluir(Aluno aluno);

    @Query("SELECT * FROM alunos")
    List<Aluno> listar();

    @Query("SELECT * FROM alunos WHERE alunoId = :id")
    Aluno listarPorId(long id);
}
```

### Exemplo de repository
```java

```

### Exemplo de AsyncTask




