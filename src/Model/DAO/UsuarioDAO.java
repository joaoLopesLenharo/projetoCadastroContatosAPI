package Model.DAO;

import Model.Bean.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    public void adiciona(Usuario usuario) {
        // Lógica para adicionar usuário à API
        try {
            URL url = new URL("http://localhost:5000/usuarios");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            Gson gson = new Gson();
            String requestBody = gson.toJson(usuario);

            connection.getOutputStream().write(requestBody.getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!", "Caixa de Diálogo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao adicionar usuário! Código de resposta: " + responseCode, "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao adicionar usuário!\n Erro: " + e.getMessage(), "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Usuario> getLista() {
        // Lógica para obter a lista de usuários da API
        List<Usuario> users = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:5000/usuarios");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            Gson gson = new Gson();
            users = gson.fromJson(response.toString(), new TypeToken<List<Usuario>>() {}.getType());

            reader.close();
            connection.disconnect();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao listar usuários!\nErro: " + e.getMessage(), "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
        }
        return users;
    }

    public List<Usuario> pesquisaNome(String nome) {
        // Lógica para pesquisar usuários pelo nome na API
        List<Usuario> usuarios = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:5000/usuarios/pesquisar/" + nome);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            Gson gson = new Gson();
            usuarios = gson.fromJson(response.toString(), new TypeToken<List<Usuario>>() {}.getType());

            reader.close();
            connection.disconnect();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao pesquisar usuários por nome!\nErro: " + e.getMessage(), "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
        }
        return usuarios;
    }

    public void deletaUsuario(long id) {
        // Lógica para deletar usuário da API
        try {
            URL url = new URL("http://localhost:5000/usuarios/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!", "Caixa de Diálogo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao remover usuário! Código de resposta: " + responseCode, "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover usuário!\nErro: " + e.getMessage(), "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualiza(Usuario user) {
        // Lógica para atualizar usuário na API
        try {
            URL url = new URL("http://localhost:5000/usuarios/atualizar");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            Gson gson = new Gson();
            String requestBody = gson.toJson(user);

            connection.getOutputStream().write(requestBody.getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!", "Caixa de Diálogo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao atualizar usuário! Código de resposta: " + responseCode, "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar usuário!\nErro: " + e.getMessage(), "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
