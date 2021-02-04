insert into mesa (nombre, cantidadMaximaComensales) values('mesa Top',5);
insert into mesa ( nombre, cantidadMaximaComensales) values('mesa Consulta',10);
insert into mesa ( nombre, cantidadMaximaComensales) values('mesa Eliminada',15);

insert into listanegra ( idCliente, nombreCliente) values('1','Cliente vetado Encontrado');
insert into listanegra ( idCliente, nombreCliente) values('2','Cliente Eliminado');

insert into reserva (idCliente, nombreCliente, cantidadComensales, fecha, idMesa) values('1','Cliente Reserva', '8', now(), '1');
insert into reserva (idCliente, nombreCliente, cantidadComensales, fecha, idMesa) values('2','Cliente Eliminar', '12', now(), '2');
