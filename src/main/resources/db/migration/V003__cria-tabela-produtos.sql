create table produtos (
    id int not null auto_increment primary key,
    nome varchar(255),
    descricao varchar(255),
    preco decimal(19,2),
    categoria_id int not null,
    foreign key (categoria_id) references categorias(id)
);