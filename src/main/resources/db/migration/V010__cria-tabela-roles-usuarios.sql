create table usuarios_roles (
    usuario_id int not null,
    roles_id int not null,

    foreign key (usuario_id) references usuarios(id),
    foreign key (roles_id) references roles(id)
);