from flask import Flask, request, jsonify
from flask_restful import Resource, Api
from DAO import UsuarioDAO, ContatoDAO

app = Flask(__name__)
api = Api(app)

class UsuarioResource(Resource):
    def post(self):
        data = request.get_json()
        UsuarioDAO.adiciona(data)
        return {'message': 'Usuário adicionado com sucesso!'}, 201

    def get(self):
        return UsuarioDAO.getLista(), 200

class UsuarioPesquisaResource(Resource):
    def get(self, nome):
        return UsuarioDAO.pesquisaNome(nome), 200

class UsuarioDeleteResource(Resource):
    def delete(self, id):
        UsuarioDAO.deletaUsuario(id)
        return {'message': 'Usuário removido com sucesso!'}, 200

class UsuarioUpdateResource(Resource):
    def put(self):
        data = request.get_json()
        UsuarioDAO.atualiza(data)
        return {'message': 'Usuário atualizado com sucesso!'}, 200

class ContatoResource(Resource):
    def post(self):
        data = request.get_json()
        ContatoDAO.adiciona(data)
        return {'message': 'Contato adicionado com sucesso!'}, 201

    def get(self):
        return ContatoDAO.getLista(), 200

class ContatoPesquisaResource(Resource):
    def get(self, nome):
        return ContatoDAO.pesquisaNome(nome), 200

class ContatoDeleteResource(Resource):
    def delete(self, id):
        ContatoDAO.deletaContato(id)
        return {'message': 'Contato removido com sucesso!'}, 200

class ContatoUpdateResource(Resource):
    def put(self):
        data = request.get_json()
        ContatoDAO.atualiza(data)
        return {'message': 'Contato atualizado com sucesso!'}, 200

api.add_resource(UsuarioResource, '/usuarios')
api.add_resource(UsuarioPesquisaResource, '/usuarios/pesquisar/<string:nome>')
api.add_resource(UsuarioDeleteResource, '/usuarios/<int:id>')
api.add_resource(UsuarioUpdateResource, '/usuarios/atualizar')
api.add_resource(ContatoResource, '/contatos')
api.add_resource(ContatoPesquisaResource, '/contatos/pesquisar/<string:nome>')
api.add_resource(ContatoDeleteResource, '/contatos/<int:id>')
api.add_resource(ContatoUpdateResource, '/contatos/atualizar')

if __name__ == '__main__':
    app.run(debug=True)
