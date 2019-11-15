CREATE DATABASE CashMachine;

USE CashMachine;

-- Apagando uma tabela
DROP TABLE Conta;

CREATE TABLE Conta
(
/*
Criando um atributo ID. 
Esse atributo é um inteiro, não pode ser nulo, é uma chave primária e ela incrementa sozinha.

Chave primária: Coluna que é utilizada para identificar um registro.
*/
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
nConta char(10),
senhaConta char(6),
saldo double,
qtdSaques DOUBLE,
qtdDepositos DOUBLE
);

insert into Conta
(nConta,senhaConta, saldo, qtdSaques, qtdDepositos)
values
('1234567890','123456', 0, 0, 0);

UPDATE Conta SET Saldo = 10000 WHERE id = 1;

insert into Conta
(nConta,senhaConta, saldo, qtdSaques, qtdDepositos)
values
('0987654321','654321', 0, 0, 0);


select * from Conta;

select * from Conta WHERE nConta = '1234567890' AND senhaConta = '123456'