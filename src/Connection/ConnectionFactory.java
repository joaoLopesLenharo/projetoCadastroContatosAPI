package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionFactory {

    private static Connection conexao;
    private static int numero = 1;

    public static Connection getConnection() {
        //Verifica se a conexão nunca foi aberta ou se está fechada
        try {
            if (ConnectionFactory.conexao == null || ConnectionFactory.conexao.isClosed()) {
                String stringConexao = "jdbc:mysql://localhost/sistema";
                String user = "root";
                String password = "root";
                ConnectionFactory.conexao = DriverManager.getConnection(stringConexao, user, password);
                JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso!", "Caixa de Diálogo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao tentar se conectar ao banco de dados!\n Erro: " + e.getMessage(), "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
            int n = JOptionPane.showConfirmDialog(null, "Deseja tentar a opção de conexão dois?", "Aviso!", JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                try {
                    if (ConnectionFactory.conexao == null || ConnectionFactory.conexao.isClosed()) {
                        String stringConexao = "jdbc:postgresql://localhost/sistema";
                        String user = "root";
                        String password = "root";
                        ConnectionFactory.conexao = DriverManager.getConnection(stringConexao, user, password);
                        JOptionPane.showMessageDialog(null, "Conexão dois realizada com sucesso!", "Caixa de Diálogo", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException i) {
                    JOptionPane.showMessageDialog(null, "Erro ao tentar a opção dois!\nErro :" + i);
                    System.exit(0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Encerrando aplicação...");
                try {
                    Thread.sleep(2000);
                    System.exit(0);
                } catch (InterruptedException u) {

                }
            }
        }
        return ConnectionFactory.conexao;
    }
}
