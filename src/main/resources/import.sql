INSERT INTO instrumentos (id, nombre, tipo) VALUES (1, 'clarinete', 'viento-madera');
INSERT INTO instrumentos (id, nombre, tipo) VALUES (2, 'flauta', 'viento-madera');
INSERT INTO componentes (nombre, apellido, direccion, dni, instrumento_id, create_at, foto) VALUES ('Jorge', 'Doe','mi calle','47536486V', 1, '2017-08-28','');
INSERT INTO componentes (nombre, apellido, direccion, dni, instrumento_id, create_at, foto) VALUES ('Cris', 'Doe','mi calle','47536486V', 1, '2017-08-28','');
INSERT INTO componentes (nombre, apellido, direccion, dni, instrumento_id, create_at, foto) VALUES ('Mama', 'Doe','mi calle','47536486V', 1, '2017-08-28','');
INSERT INTO componentes (nombre, apellido, direccion, dni, instrumento_id, create_at, foto) VALUES ('Papa', 'Doe','mi calle','47536486V', 1, '2017-08-28','');


INSERT INTO tipo_marchas (id, nombre, descripcion) VALUES (1, 'funebre', '');
INSERT INTO tipo_marchas (id, nombre, descripcion) VALUES (2, 'clasica', '');

INSERT INTO marchas (nombre, compositor, fecha_creacion, tipo_id, guia) VALUES ('A ti Manué', 'Juan José Puntas','2015-01-01',1,'');
INSERT INTO marchas (nombre, compositor, fecha_creacion, tipo_id, guia) VALUES ('Al Cielo con Ella', 'Pedro Morales','2015-01-01',2,'');
INSERT INTO marchas (nombre, compositor, fecha_creacion, tipo_id, guia) VALUES ('Al cielo la Paz', 'J.J. Espinosa de los Monteros','2015-01-01',1,'');
INSERT INTO marchas (nombre, compositor, fecha_creacion, tipo_id, guia) VALUES ('Alma de la Trinidad', 'Eloy García','2015-01-01',2,'');
INSERT INTO marchas (nombre, compositor, fecha_creacion, tipo_id, guia) VALUES ('Amarguras', 'Manuel Font de Anta','2015-01-01',1,'');


INSERT INTO materiales (id, nombre) VALUES (1, 'Traje Completo');
INSERT INTO materiales (id, nombre) VALUES (2, 'Camisa Verano');
INSERT INTO materiales (id, nombre) VALUES (3, 'Instrumento');
