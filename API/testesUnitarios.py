import os
import tempfile
import pytest
from API import app
from DAO import UsuarioDAO, ContatoDAO

@pytest.fixture
def client():
    app.config["TESTING"] = True

    # Cria um banco de dados temporário
    db_fd, app.config["DATABASE"] = tempfile.mkstemp()

    # Cria as tabelas no banco de dados temporário
    with app.app_context():
        UsuarioDAO.criarTabelas()
        ContatoDAO.criarTabelas()

    with app.test_client() as client:
        yield client

    # Fecha a conexão com o banco de dados
    os.close(db_fd)
    # Remove o banco de dados temporário
    os.unlink(app.config["DATABASE"])

def test_adiciona_usuario(client):
    """Teste unitário para adicionar um usuário."""
    data = {
        "nome": "Teste",
        "usuario": "teste",
        "senha": "123456"
    }
    response = client.post("/usuarios", json=data)
    assert response.status_code == 201

def test_get_usuarios(client):
    """Teste unitário para obter a lista de usuários."""
    response = client.get("/usuarios")
    assert response.status_code == 200

def test_pesquisa_usuario(client):
    """Teste unitário para pesquisar um usuário por nome."""
    response = client.get("/usuarios/pesquisar/Teste")
    assert response.status_code == 200

def test_deleta_usuario(client):
    """Teste unitário para deletar um usuário."""
    data = {
        "nome": "Teste",
        "usuario": "teste",
        "senha": "123456"
    }
    UsuarioDAO.adiciona(data)
    response = client.delete("/usuarios/1")
    assert response.status_code == 200

def test_atualiza_usuario(client):
    """Teste unitário para atualizar um usuário."""
    data = {
        "id": 1,
        "nome": "Teste",
        "usuario": "teste",
        "senha": "123456"
    }
    UsuarioDAO.adiciona(data)
    data["nome"] = "Teste Atualizado"
    response = client.put("/usuarios/atualizar", json=data)
    assert response.status_code == 200

def test_adiciona_contato(client):
    """Teste unitário para adicionar um contato."""
    data = {
        "nome": "Teste",
        "cpf": "12345678901",
        "rua": "Rua Teste",
        "bairro": "Bairro Teste",
        "cep": "12345-678",
        "cidade": "Cidade Teste",
        "numCasa": "123",
        "telefone": "987654321"
    }
    response = client.post("/contatos", json=data)
    assert response.status_code == 201

def test_get_contatos(client):
    """Teste unitário para obter a lista de contatos."""
    response = client.get("/contatos")
    assert response.status_code == 200

def test_pesquisa_contato(client):
    """Teste unitário para pesquisar um contato por nome."""
    response = client.get("/contatos/pesquisar/Teste")
    assert response.status_code == 200

def test_deleta_contato(client):
    """Teste unitário para deletar um contato."""
    data = {
        "nome": "Teste",
        "cpf": "12345678901",
        "rua": "Rua Teste",
        "bairro": "Bairro Teste",
        "cep": "12345-678",
        "cidade": "Cidade Teste",
        "numCasa": "123",
        "telefone": "987654321"
    }
    ContatoDAO.adiciona(data)
    response = client.delete("/contatos/1")
    assert response.status_code == 200

def test_atualiza_contato(client):
    """Teste unitário para atualizar um contato."""
    data = {
        "id": 1,
        "nome": "Teste",
        "cpf": "12345678901",
        "rua": "Rua Teste",
        "bairro": "Bairro Teste",
        "cep": "12345-678",
        "cidade": "Cidade Teste",
        "numCasa": "123",
        "telefone": "987654321"
    }
    ContatoDAO.adiciona(data)
    data["nome"] = "Teste Atualizado"
    response = client.put("/contatos/atualizar", json=data)
    assert response.status_code == 200
