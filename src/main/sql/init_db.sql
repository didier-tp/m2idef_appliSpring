create database if not exists minibankdb;
use minibankdb;

drop table if exists Compte;

create table Compte(
numero integer primary key auto_increment,
label VARCHAR(32),
solde double
);

describe Compte;


INSERT INTO Compte(numero,label,solde) VALUES (1,'compte 1',150.0);


SELECT * FROM Compte;
