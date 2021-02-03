create table mesa (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 cantidadMaximaComensales int(8) not null,
 primary key (id)
);

create table listanegra (
 idCliente int(11) not null auto_increment,
 nombreCliente varchar(100) not null,
 primary key (idCliente)
);

create table reserva (
 id int(11) not null auto_increment,
 idCliente int(11) not null,
 nombreCliente varchar(100) not null,
 cantidadComensales int(8) not null,
 fecha datetime not null,
 idMesa int(11) not null,
 primary key (id)
);