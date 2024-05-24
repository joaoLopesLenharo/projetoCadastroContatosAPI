package Model.DAO;
import Model.Bean.Usuario;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CEP {
  private record Endereco(
    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String localidade,
    String uf,
    String ibge,
    String gia,
    String ddd,
    String siafi
  ) {
  }

    /**
     *
     * @param CEP
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static Usuario buscaCEP(String CEP) throws IOException, InterruptedException {

    final HttpRequest request = HttpRequest
      .newBuilder(URI.create("https://viacep.com.br/ws/%s/json/".formatted(CEP)))
      .build();

    final HttpClient client = HttpClient.newHttpClient();

    final HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

    final ObjectMapper objectMapper = new ObjectMapper();

    final Endereco endereco = objectMapper.readValue(response.body(), Endereco.class);

    Usuario user = new Usuario();
    user.setBairro(endereco.bairro);
    user.setComplemento(endereco.complemento);
    user.setLocalidade(endereco.localidade);
    user.setLogradouro(endereco.logradouro);
    user.setUf(endereco.uf);
    user.setComplemento(endereco.complemento);
    return user;
  }
}