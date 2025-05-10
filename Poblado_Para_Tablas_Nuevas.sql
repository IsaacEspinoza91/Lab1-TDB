-- Primero insertar en la tabla Usuarios (base común)
INSERT INTO Usuarios (RUT, Nombre, Apellido, Email, Password, Telefono, Tipo) VALUES
-- Clientes
('12345678-9', 'Maria', 'Gonzalez', 'maria.gonzalez@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56912345678', 'CLIENTE'),
('17654321-0', 'Pedro', 'Perez', 'pedro.perez@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56987654321', 'CLIENTE'),
('19283746-5', 'Ana', 'Ramirez', 'ana.ramirez@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56911223344', 'CLIENTE'),
('6473829-1', 'Jorge', 'Silva', 'jorge.silva@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56999887766', 'CLIENTE'),
('15374659-7', 'Laura', 'Torres', 'laura.torres@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56955667788', 'CLIENTE'),
('12839201-3', 'Diego', 'Morales', 'diego.morales@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56966778899', 'CLIENTE'),
('20213487-6', 'Camila', 'Fernandez', 'camila.fernandez@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56977889900', 'CLIENTE'),
('18820194-2', 'Ignacio', 'Vega', 'ignacio.vega@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56988990011', 'CLIENTE'),
('17928476-4', 'Valentina', 'Navarro', 'valentina.navarro@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56999001122', 'CLIENTE'),
('16029375-1', 'Matias', 'Cortes', 'matias.cortes@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56912344321', 'CLIENTE'),
-- Repartidores
('11223344-5', 'Carlos', 'Diaz', 'carlos.diaz@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56911112222', 'REPARTIDOR'),
('22334455-6', 'Francisca', 'Soto', 'francisca.soto@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56922223333', 'REPARTIDOR'),
('20445566-7', 'Luis', 'Rojas', 'luis.rojas@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56933334444', 'REPARTIDOR'),
('19556677-8', 'Paula', 'Reyes', 'paula.reyes@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56944445555', 'REPARTIDOR'),
('18667788-9', 'Tomas', 'Fuentes', 'tomas.fuentes@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56955556666', 'REPARTIDOR'),
-- Administradores
('19887766-5', 'Jorge', 'Gonzalez', 'sali.con.tu.mujer@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56912345678', 'ADMIN'),
('18776655-4', 'Claudio', 'Narea', 'claudio.n@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMy...', '+56998765432', 'ADMIN');

-- Insertar datos específicos de Clientes
INSERT INTO Clientes (Usuario_ID, Direccion) VALUES
(1, 'Av. Libertador 1234'),
(2, 'Calle Maipu 123'),
(3, 'Los Olivos 456'),
(4, 'Av. Central 789'),
(5, 'Pje. Las Rosas 321'),
(6, 'Av. Sur 1001'),
(7, 'Pasaje Norte 45'),
(8, 'Calle Secundaria 21'),
(9, 'Calle Principal 88'),
(10, 'El Roble 1123');

-- Insertar datos específicos de Repartidores
INSERT INTO Repartidores (Usuario_ID, Tipo_vehiculo) VALUES
(11, 'AUTO'),
(12, 'MOTO'),
(13, 'BICICLETA'),
(14, 'CAMIONETA'),
(15, 'MOTO');

-- Insertar datos específicos de Administradores
INSERT INTO Administradores (Usuario_ID, Nivel_acceso, Departamento) VALUES
(16, 1, 'Sistemas'),
(17, 2, 'Operaciones');

-- Insertar datos en la tabla Farmacias (sin cambios)
INSERT INTO Farmacias (Nombre, Lugar) VALUES 
('Farmacia San Juan', 'Av. Siempre Viva 123'),
('Farmacia La Salud', 'Calle Ficticia 456'),
('Farmacia El Bienestar', 'Calle Real 789'),
('Farmacia Santa Maria', 'Calle de la Paz 101'),
('Farmacia Central', 'Plaza Principal 202'),
('Farmacia La Vida', 'Avenida Libertad 303'),
('Farmacia Salud Usach', 'Av. Victor Jara 91');

-- Insertar datos en la tabla Medios_de_pago (sin cambios)
INSERT INTO Medios_de_pago (Tipo) VALUES
('Efectivo'),
('Tarjeta');

-- Insertar datos en la tabla Productos (sin cambios)
INSERT INTO Productos (Nombre, Precio, Stock, Requiere_receta) VALUES
('Aspirina', 8400, 100, false),
('Ibuprofeno', 12240, 150, false),
('Amoxicilina', 20000, 50, true),
('Paracetamol', 6400, 200, false),
('Diazepam', 14400, 80, true),
('Antibiótico', 28000, 60, true),
('Vitamina C', 4400, 300, false),
('Jarabe para la tos', 9600, 120, false),
('Loratadina', 7200, 130, false),
('Clonazepam', 17600, 90, true),
('Glicerina', 6000, 180, false),
('Cefalexina', 22400, 70, true),
('Lactulosa', 12800, 150, false),
('Paroxetina', 24000, 40, true),
('Omeprazol', 11000, 160, false),
('Metformina', 13400, 140, true),
('Enalapril', 9800, 75, true),
('Losartán', 10200, 85, true),
('Salbutamol', 11500, 95, false),
('Simvastatina', 12600, 100, true),
('Cetirizina', 7800, 120, false),
('Prednisona', 15000, 65, true),
('Miconazol', 8800, 70, false),
('Paroxetina', 24000, 40, true);

-- Insertar datos en la tabla Pedidos (modificado para usar IDs de Usuarios)
INSERT INTO Pedidos (Fecha, Urgencia, Total_pagado, Estado_entrega, Fecha_entrega, Cliente_ID, Medio_pago_ID, Farmacia_ID, Repartidor_ID) VALUES
('2025-04-01', false, 21200, 'Entregado', '2025-04-03', 1, 1, 1, 11),
('2025-04-02', true, 44000, 'Pendiente', NULL, 2, 2, 2, 12),
('2025-04-03', false, 6400, 'Entregado', '2025-04-04', 3, 1, 3, 13),
('2025-04-03', true, 38880, 'Pendiente', NULL, 4, 2, 4, 14),
('2025-04-04', false, 9600, 'Entregado', '2025-04-06', 5, 1, 5, 15),
('2025-04-05', true, 32000, 'Pendiente', NULL, 6, 2, 6, 11),
('2025-04-06', false, 8800, 'Entregado', '2025-04-08', 7, 1, 1, 12),
('2025-04-06', true, 25200, 'Pendiente', NULL, 8, 2, 2, 13),
('2025-04-07', false, 7200, 'Entregado', '2025-04-09', 9, 1, 3, 14),
('2025-04-07', true, 22400, 'Pendiente', NULL, 10, 2, 4, 15),
('2025-04-08', false, 12800, 'Entregado', '2025-04-10', 1, 1, 5, 11),
('2025-04-08', true, 17600, 'Pendiente', NULL, 2, 2, 6, 12),
('2025-04-09', false, 24900, 'Entregado', '2025-04-11', 3, 1, 1, 13),
('2025-04-09', true, 10200, 'Pendiente', NULL, 4, 2, 2, 14),
('2025-04-10', false, 15600, 'Entregado', '2025-04-12', 5, 1, 3, 15),
('2025-04-11', true, 23800, 'Pendiente', NULL, 6, 2, 4, 11),
('2025-04-11', false, 28000, 'Entregado', '2025-04-13', 7, 1, 5, 12),
('2025-04-12', true, 6000, 'Pendiente', NULL, 8, 2, 6, 13),
('2025-04-13', false, 9800, 'Entregado', '2025-04-15', 9, 1, 1, 14),
('2025-04-13', true, 15000, 'Pendiente', NULL, 10, 2, 2, 15);

-- Insertar datos para detalle de pedidos (sin cambios)
INSERT INTO Detalle_de_pedidos (Pedido_ID, Producto_ID, Cantidad) VALUES
(1, 1, 2),
(1, 7, 1),
(2, 3, 1),
(2, 14, 1),
(3, 4, 1),
(4, 2, 2),
(4, 5, 1),
(5, 8, 1),
(6, 5, 1),
(6, 10, 1),
(7, 15, 1),
(8, 16, 2),
(9, 9, 1),
(10, 12, 1),
(11, 7, 1),
(11, 1, 1),
(12, 10, 1),
(13, 17, 1),
(13, 18, 1),
(14, 20, 1),
(15, 21, 2),
(16, 13, 1),
(16, 19, 1),
(17, 6, 1),
(18, 11, 1),
(19, 22, 1),
(20, 23, 1);

-- Poblar tabla Calificaciones (modificado para usar IDs de Usuarios)
INSERT INTO Calificaciones (Puntuacion, Estrellas, Cliente_ID, Repartidor_ID) VALUES
('Muy rápido y amable', 5, 1, 11),
('Demorado pero llegó', 3, 2, 12),
('Excelente servicio', 5, 3, 11),
('Poco cordial', 2, 4, 13),
('Normal', 4, 5, 12),
('No trajo todo', 2, 6, 14),
('Perfecto', 5, 7, 11),
('Se perdió en el camino', 1, 8, 15),
('Servicio aceptable', 3, 9, 12),
('Amable y rápido', 5, 10, 13),
('Atención excelente', 5, 1, 14),
('Muy educado', 4, 2, 15),
('Se equivocó de dirección', 2, 3, 12),
('Llegó frío el pedido', 2, 4, 11),
('Volvería a pedir', 5, 5, 15),
('Sin problemas', 4, 6, 13),
('Tardó demasiado', 2, 7, 14),
('Buen trato', 5, 8, 12),
('Me llamó para confirmar', 4, 9, 11),
('Entrega incompleta', 1, 10, 15),
('Entrega puntual', 5, 1, 13),
('Se pasó la hora', 2, 2, 14),
('Todo correcto', 4, 3, 12),
('Amable y atento', 5, 4, 11);

-- Poblar Tabla intermediaria Farmacia y productos (sin cambios)
INSERT INTO Farmacias_Productos (Farmacia_ID, Producto_ID) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 6), (2, 7), (2, 8), (2, 9), (2, 10),
(3, 11), (3, 12), (3, 13), (3, 14), (3, 15),
(4, 16), (4, 17), (4, 18), (4, 19), (4, 20),
(5, 21), (5, 22), (5, 23), (5, 1), (5, 2),
(6, 3), (6, 6), (6, 9), (6, 12), (6, 18);

-- Insertar datos en la tabla Pedidos_Repartidores (relación muchos a muchos)
INSERT INTO Pedidos_Repartidores (Pedido_ID, Repartidor_ID) VALUES
(1, 11),
(2, 12),
(3, 13),
(4, 14),
(5, 15),
(6, 11),
(7, 12),
(8, 13),
(9, 14),
(10, 15),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 11),
(17, 12),
(18, 13),
(19, 14),
(20, 15);