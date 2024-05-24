import requests

def testar_api_contatos():
    # Adicionando um contato
    novo_contato = {'nome': 'Novo Contato', 'cpf': '12345678901', 'rua': 'Rua Nova', 'bairro': 'Novo Bairro', 'cep': '12345-678', 'cidade': 'Nova Cidade', 'numCasa': '123', 'telefone': '987654321'}
    response = requests.post('http://localhost:5000/contatos', json=novo_contato)
    print(response.json())

    # Listando contatos
    response = requests.get('http://localhost:5000/contatos')
    print(response.json())

    # Pesquisando contato por nome
    response = requests.get('http://localhost:5000/contatos/pesquisar/Novo')
    print(response.json())

    # Atualizando um contato
    contato_atualizado = {'id': 1, 'nome': 'Contato Atualizado', 'cpf': '12345678901', 'rua': 'Rua Atualizada', 'bairro': 'Bairro Atualizado', 'cep': '12345-678', 'cidade': 'Cidade Atualizada', 'numCasa': '123', 'telefone': '987654321'}
    response = requests.put('http://localhost:5000/contatos/atualizar', json=contato_atualizado)
    print(response.json())

    # Deletando um contato
    response = requests.delete('http://localhost:5000/contatos/1')
    print(response.json())

    # Listando contatos após exclusão
    response = requests.get('http://localhost:5000/contatos')
    print(response.json())

if __name__ == '__main__':
    testar_api_contatos()
