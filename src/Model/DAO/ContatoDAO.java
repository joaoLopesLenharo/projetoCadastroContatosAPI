package Model.DAO;

import Model.Bean.Contato;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ContatoDAO {

    public void adiciona(Contato contato) {
        // Lógica para adicionar contato à API
        try {
            URL url = new URL("http://localhost:5000/contatos");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            Gson gson = new Gson();
            String requestBody = gson.toJson(contato);

            connection.getOutputStream().write(requestBody.getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Contato adicionado com sucesso!", "Caixa de Diálogo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao adicionar contato! Código de resposta: " + responseCode, "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao adicionar contato!\n Erro: " + e.getMessage(), "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Contato> getLista() {
        // Lógica para obter a lista de contatos da API
        List<Contato> contatos = new ArrayList<>();
        
        try {
            URL url = new URL("http://localhost:5000/contatos");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            Gson gson = new Gson();
            contatos = gson.fromJson(response.toString(), new TypeToken<List<Contato>>() {}.getType());

            reader.close();
            connection.disconnect();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao listar contatos!\nErro: " + e.getMessage(), "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
        }
        return contatos;
    }

    
    public List<Contato> pesquisaNome(String nome) {
        // Lógica para pesquisar contatos pelo nome na API
        List<Contato> contatos = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:5000/contatos/pesquisar/" + nome);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            Gson gson = new Gson();
            contatos = gson.fromJson(response.toString(), new TypeToken<List<Contato>>() {}.getType());

            reader.close();
            connection.disconnect();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao pesquisar contatos por nome!\nErro: " + e.getMessage(), "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
        }
        return contatos;
    }

    public void deletaContato(long id) {
        // Lógica para deletar contato da API
        try {
            URL url = new URL("http://localhost:5000/contatos/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Contato removido com sucesso!", "Caixa de Diálogo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao remover contato! Código de resposta: " + responseCode, "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover contato!\nErro: " + e.getMessage(), "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualiza(Contato contato) {
        // Lógica para atualizar contato na API
        try {
            URL url = new URL("http://localhost:5000/contatos/atualizar");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            Gson gson = new Gson();
            String requestBody = gson.toJson(contato);

            connection.getOutputStream().write(requestBody.getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Contato atualizado com sucesso!", "Caixa de Diálogo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao atualizar contato! Código de resposta: " + responseCode, "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar contato!\nErro: " + e.getMessage(), "Caixa de Diálogo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
