insert into clientes(nombre, apellido, email, creado_en, foto) values('patitas', 'flores', 'patas@gmail.com', '2017-08-20', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('gina', 'flores', 'gina@gmail.com', '2017-01-01', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('patitas', 'flores', 'patas@gmail.com', '2017-08-20', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('gina', 'flores', 'gina@gmail.com', '2017-01-01', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('patitas', 'flores', 'patas@gmail.com', '2017-08-20', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('gina', 'flores', 'gina@gmail.com', '2017-01-01', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('patitas', 'flores', 'patas@gmail.com', '2017-08-20', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('gina', 'flores', 'gina@gmail.com', '2017-01-01', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('patitas', 'flores', 'patas@gmail.com', '2017-08-20', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('gina', 'flores', 'gina@gmail.com', '2017-01-01', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('patitas', 'flores', 'patas@gmail.com', '2017-08-20', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('gina', 'flores', 'gina@gmail.com', '2017-01-01', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('patitas', 'flores', 'patas@gmail.com', '2017-08-20', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('gina', 'flores', 'gina@gmail.com', '2017-01-01', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('patitas', 'flores', 'patas@gmail.com', '2017-08-20', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('gina', 'flores', 'gina@gmail.com', '2017-01-01', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('patitas', 'flores', 'patas@gmail.com', '2017-08-20', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('gina', 'flores', 'gina@gmail.com', '2017-01-01', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('patitas', 'flores', 'patas@gmail.com', '2017-08-20', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('gina', 'flores', 'gina@gmail.com', '2017-01-01', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('patitas', 'flores', 'patas@gmail.com', '2017-08-20', '');
insert into clientes(nombre, apellido, email, creado_en, foto) values('gina', 'flores', 'gina@gmail.com', '2017-01-01', '');


-- Poblar tabla productos

INSERT INTO productos (nombre, precio, creado_en) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, creado_en) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, creado_en) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, creado_en) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, creado_en) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, creado_en) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, creado_en) VALUES('Mica Comoda 5 Cajones', 299990, NOW());


/* Creamos facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, creado_en) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO items_facturas (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO items_facturas (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO items_facturas (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO items_facturas (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, creado_en) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO items_facturas (cantidad, factura_id, producto_id) VALUES(3, 2, 6);
