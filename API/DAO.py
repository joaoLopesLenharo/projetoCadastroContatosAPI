import mysql.connector
from mysql.connector import Error

class ConnectionFactory:
    @staticmethod
    def getConnection():
        try:
            connection = mysql.connector.connect(
                host='127.0.0.1',
                user='main',
                password='root',
                database='sistema'
            )
            return connection
        except Error as e:
            print(f"Erro ao conectar ao MySQL: {e}")
            return None

class UsuarioDAO:
    @staticmethod
    def criarTabelas():
        sql = """
        CREATE TABLE IF NOT EXISTS usuarios (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nome VARCHAR(255) NOT NULL,
            usuario VARCHAR(255) NOT NULL,
            senha VARCHAR(255) NOT NULL
        )
        """
        try:
            conn = ConnectionFactory.getConnection()
            cursor = conn.cursor()
            cursor.execute(sql)
            print("Tabela de usuários criada com sucesso!")
        except Error as e:
            print(f"Falha ao criar tabela de usuários: {e}")
        finally:
            if conn:
                cursor.close()
                conn.close()

    @staticmethod
    def adiciona(usuario):
        sql = "INSERT INTO usuarios (nome, usuario, senha) VALUES (%s, %s, %s)"
        try:
            conn = ConnectionFactory.getConnection()
            cursor = conn.cursor()
            cursor.execute(sql, (usuario['nome'], usuario['usuario'], usuario['senha']))
            conn.commit()
            print("Usuário adicionado com sucesso!")
        except Error as e:
            print(f"Falha ao adicionar usuário: {e}")
        finally:
            if conn:
                cursor.close()
                conn.close()

    @staticmethod
    def getLista():
        sql = "SELECT * FROM usuarios"
        try:
            conn = ConnectionFactory.getConnection()
            cursor = conn.cursor(dictionary=True)
            cursor.execute(sql)
            usuarios = cursor.fetchall()
            return usuarios
        except Error as e:
            print(f"Falha ao listar usuários: {e}")
            return None
        finally:
            if conn:
                cursor.close()
                conn.close()

    @staticmethod
    def pesquisaNome(nome):
        sql = "SELECT * FROM usuarios WHERE nome LIKE %s"
        try:
            conn = ConnectionFactory.getConnection()
            cursor = conn.cursor(dictionary=True)
            cursor.execute(sql, ('%' + nome + '%',))
            usuarios = cursor.fetchall()
            return usuarios
        except Error as e:
            print(f"Falha ao pesquisar usuários: {e}")
            return None
        finally:
            if conn:
                cursor.close()
                conn.close()

    @staticmethod
    def deletaUsuario(id):
        sql = "DELETE FROM usuarios WHERE id = %s"
        try:
            conn = ConnectionFactory.getConnection()
            cursor = conn.cursor()
            cursor.execute(sql, (id,))
            conn.commit()
            print("Usuário removido com sucesso!")
        except Error as e:
            print(f"Falha ao remover usuário: {e}")
        finally:
            if conn:
                cursor.close()
                conn.close()

    @staticmethod
    def atualiza(usuario):
        sql = "UPDATE usuarios SET nome = %s, usuario = %s, senha = %s WHERE id = %s"
        try:
            conn = ConnectionFactory.getConnection()
            cursor = conn.cursor()
            cursor.execute(sql, (usuario['nome'], usuario['usuario'], usuario['senha'], usuario['id']))
            conn.commit()
            print("Usuário atualizado com sucesso!")
        except Error as e:
            print(f"Falha ao atualizar usuário: {e}")
        finally:
            if conn:
                cursor.close()
                conn.close()

class ContatoDAO:
    @staticmethod
    def criarTabelas():
        sql = """
        CREATE TABLE IF NOT EXISTS contatos (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nome VARCHAR(255) NOT NULL,
            cpf VARCHAR(11) NOT NULL,
            rua VARCHAR(255) NOT NULL,
            bairro VARCHAR(255) NOT NULL,
            cep VARCHAR(8) NOT NULL,
            cidade VARCHAR(255) NOT NULL,
            numCasa VARCHAR(10) NOT NULL,
            telefone VARCHAR(11) NOT NULL
        )
        """
        try:
            conn = ConnectionFactory.getConnection()
            cursor = conn.cursor()
            cursor.execute(sql)
            print("Tabela de contatos criada com sucesso!")
        except Error as e:
            print(f"Falha ao criar tabela de contatos: {e}")
        finally:
            if conn:
                cursor.close()
                conn.close()

    @staticmethod
    def adiciona(contato):
        sql = "INSERT INTO contatos (nome, cpf, rua, bairro, cep, cidade, numCasa, telefone) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)"
        try:
            conn = ConnectionFactory.getConnection()
            cursor = conn.cursor()
            cursor.execute(sql, (contato['nome'], contato['cpf'], contato['rua'], contato['bairro'], contato['cep'], contato['cidade'], contato['numCasa'], contato['telefone']))
            conn.commit()
            print("Contato adicionado com sucesso!")
        except Error as e:
            print(f"Falha ao adicionar contato: {e}")
        finally:
            if conn:
                cursor.close()
                conn.close()

    @staticmethod
    def getLista():
        sql = "SELECT * FROM contatos"
        try:
            conn = ConnectionFactory.getConnection()
            cursor = conn.cursor(dictionary=True)
            cursor.execute(sql)
            contatos = cursor.fetchall()
            return contatos
        except Error as e:
            print(f"Falha ao listar contatos: {e}")
            return None
        finally:
            if conn:
                cursor.close()
                conn.close()

    @staticmethod
    def pesquisaNome(nome):
        sql = "SELECT * FROM contatos WHERE nome LIKE %s"
        try:
            conn = ConnectionFactory.getConnection()
            cursor = conn.cursor(dictionary=True)
            cursor.execute(sql, ('%' + nome + '%',))
            contatos = cursor.fetchall()
            return contatos
        except Error as e:
            print(f"Falha ao pesquisar contatos: {e}")
            return None
        finally:
            if conn:
                cursor.close()
                conn.close()

    @staticmethod
    def deletaContato(id):
        sql = "DELETE FROM contatos WHERE id = %s"
        try:
            conn = ConnectionFactory.getConnection()
            cursor = conn.cursor()
            cursor.execute(sql, (id,))
            conn.commit()
            print("Contato removido com sucesso!")
        except Error as e:
            print(f"Falha ao remover contato: {e}")
        finally:
            if conn:
                cursor.close()
                conn.close()

    @staticmethod
    def atualiza(contato):
        sql = "UPDATE contatos SET nome = %s, cpf = %s, rua = %s, bairro = %s, cep = %s, cidade = %s, numCasa = %s, telefone = %s WHERE id = %s"
        try:
            conn = ConnectionFactory.getConnection()
            cursor = conn.cursor()
            cursor.execute(sql, (contato['nome'], contato['cpf'], contato['rua'], contato['bairro'], contato['cep'], contato['cidade'], contato['numCasa'], contato['telefone'], contato['id']))
            conn.commit()
            print("Contato atualizado com sucesso!")
        except Error as e:
            print(f"Falha ao atualizar contato: {e}")
        finally:
            if conn:
                cursor.close()
                conn.close()

# Exemplo de uso:
if __name__ == "__main__":
    UsuarioDAO.criarTabelas()
    ContatoDAO.criarTabelas()

    usuario = {'nome': 'Fulano', 'usuario': 'fulano123', 'senha': 'senha123'}
    UsuarioDAO.adiciona(usuario)

    contatos = UsuarioDAO.pesquisaNome("João")
    if contatos:
        for contato in contatos:
            print(contato)
