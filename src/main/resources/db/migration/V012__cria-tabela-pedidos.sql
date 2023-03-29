create table pedidos (
    id int not null auto_increment primary key,
    produto_id int not null,
    usuario_id int not null,
    quantidade int,

    foreign key(produto_id) references produtos(id),
    foreign key(usuario_id) references usuarios(id)
);