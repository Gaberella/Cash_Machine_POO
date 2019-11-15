
package simulatorcaixaeletronico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ContaDAO {
    /*
    Método contaUsuario
    Responsável por retornar os dados de um usuário específico
    */
    public static Conta contaUsuario (String nConta , String senhaConta) throws SQLException 
    {
        /*
        Comando SELECT responsável por selecionar os dados de um determinado usuário filtrado pelo nConta & senhaConta
        */
        String sql = "SELECT * FROM Conta WHERE nConta = ? AND senhaConta = ?";
        
        Connection conn = null; //Objeto que irá realizar a conexão com o banco de dados
        PreparedStatement pst = null; //Objeto que irá preparar e executar seu comando SQL
        
        try{
            conn = ConnectionUtils.getConnection(); //Realizando a conexão com o banco
            pst = conn.prepareStatement(sql); //Instanciando um PreparedStatement para poder preparar e executar o comando SQL
            
            pst.setString(1, nConta); //O primeiro parâmetro (1) irá receber o valor de nConta
            pst.setString(2, senhaConta); //O segundo parâmetro (2) irá receber o valor de senhaConta
            
            //Query - Script SQL
            /*
            O ResultSet é um conjunto de registros que serão retornados do Banco de Dados.
            É de fato uma tabela em memória (memória do computador) que contém todos os registros
            que foram retornados pelo SELECT realizado no banco.
            */
            ResultSet rs = pst.executeQuery(); 
            
            if(rs.next()) //Caso exista algum registro no ResultSet
            {
                Conta c = new Conta(); // Cria uma nova conta
                
                //Armazena seus dados da conta
                c.setId(rs.getInt("id"));
                c.setnConta(rs.getString("nConta"));
                c.setSenhaConta(rs.getString("senhaConta"));
                c.setTotalDepositos(rs.getInt("qtdDepositos"));
                c.setTotalSaques(rs.getInt("qtdSaques"));
                c.setSaldo(rs.getDouble("saldo"));
                
                return c; // Retorna essa conta depois de armazenar seus dados
            }
            
        } catch (Exception ex) {
            return null;
        }
        finally{
            //Fechando o PreparedStatement
            if(pst != null && !pst.isClosed())
                pst.close();
            //Fechando a conexão com o banco de dados (A conexão nunca pode se manter aberta)
            if (conn != null && !conn.isClosed())
                conn.close();
        }
        
        return null;
    }
    
    public static void RealizaDeposito(int id, int quantidadeDeposito) throws SQLException{
        //Comando para realizar um UPDATE em um determinado registro do banco de dados
        //O registro está sendo filtrado pelo comando WHERE. Esse comando funciona basicamente como um IF.
        String sql = "UPDATE Conta SET saldo = saldo + ?, qtdDepositos = qtdDepositos + ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection();
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, quantidadeDeposito);
            pst.setInt(2, quantidadeDeposito);
            pst.setInt(3, id);
            
            pst.execute();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally{
            if(pst != null && !pst.isClosed())
                pst.close();
            if (conn != null && !conn.isClosed())
                conn.close();
        }
    }
    
    public static void RealizaSaque(int id, int quantidade) throws SQLException{
        //Comando para realizar um UPDATE em um determinado registro do banco de dados
        //O registro está sendo filtrado pelo comando WHERE. Esse comando funciona basicamente como um IF.
        String sql = "UPDATE Conta SET saldo = saldo - ?, qtdSaques = qtdSaques + ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement pst = null;
        
        try{
            conn = ConnectionUtils.getConnection();
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, quantidade);
            pst.setInt(2, quantidade);
            pst.setInt(3, id);
            
            pst.execute();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally{
            if(pst != null && !pst.isClosed())
                pst.close();
            if (conn != null && !conn.isClosed())
                conn.close();
        }
    }
}
