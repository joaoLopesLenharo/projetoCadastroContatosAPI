-- Criação Banco de Dados sistema
CREATE DATABASE sistema;

-- DROP DATABASE sistema;

-- Usar o Banco de Dados
USE sistema;

-- Criação da tabela contatos
CREATE TABLE contatos (
	id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(255),
    rua VARCHAR(255) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    numCasa VARCHAR(30) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) not null,
    PRIMARY KEY (id)
);

-- Criação da tabela usuarios
CREATE TABLE usuarios (
	id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    usuario VARCHAR(25) NOT NULL,
    senha VARCHAR(25) NOT NULL,
    PRIMARY KEY (id)
);

insert into usuarios (nome, senha, usuario) VALUes ("a", "a", "a");