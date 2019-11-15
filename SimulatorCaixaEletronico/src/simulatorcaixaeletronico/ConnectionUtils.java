
package simulatorcaixaeletronico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionUtils {
    //Obtém uma conexão do banco de dados 
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Só tenta/ abrir uma conexão se não existir ou estiver fechada            
        //Endereço de conexão com o banco de dados
        String dbURL = "jdbc:mysql://localhost:3306/CashMachine";
        
        Class.forName("com.mysql.jdbc.Driver");
        
        //Propriedades para armazenamento de usuário e senha
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");
        properties.put("serverTimezone", "UTC");
        //Realiza a conexão com o banco
        connection = DriverManager.getConnection(dbURL, properties);

        //Retorna a conexão
        return connection;
    }
}
