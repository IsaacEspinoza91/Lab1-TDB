-- Insertar datos en la tabla Clientes
INSERT INTO Clientes (RUT, Nombre, Apellido, Direccion, Email, Telefono) VALUES
('12345678-9', 'Maria', 'Gonzalez', 'Av. Libertador 1234', 'maria.gonzalez@gmail.com', '+56912345678'),
('17654321-0', 'Pedro', 'Perez', 'Calle Maipu 123', 'pedro.perez@gmail.com', '+56987654321'),
('19283746-5', 'Ana', 'Ramirez', 'Los Olivos 456', 'ana.ramirez@gmail.com', '+56911223344'),
('6473829-1', 'Jorge', 'Silva', 'Av. Central 789', 'jorge.silva@gmail.com', '+56999887766'),
('15374659-7', 'Laura', 'Torres', 'Pje. Las Rosas 321', 'laura.torres@gmail.com', '+56955667788'),
('12839201-3', 'Diego', 'Morales', 'Av. Sur 1001', 'diego.morales@gmail.com', '+56966778899'),
('20213487-6', 'Camila', 'Fernandez', 'Pasaje Norte 45', 'camila.fernandez@gmail.com', '+56977889900'),
('18820194-2', 'Ignacio', 'Vega', 'Calle Secundaria 21', 'ignacio.vega@gmail.com', '+56988990011'),
('17928476-4', 'Valentina', 'Navarro', 'Calle Principal 88', 'valentina.navarro@gmail.com', '+56999001122'),
('16029375-1', 'Matias', 'Cortes', 'El Roble 1123', 'matias.cortes@gmail.com', '+56912344321');

-- Insertar datos en la tabla Farmacias
INSERT INTO Farmacias (Nombre, Lugar)
VALUES 
    ('Farmacia San Juan', 'Av. Siempre Viva 123'),
    ('Farmacia La Salud', 'Calle Ficticia 456'),
    ('Farmacia El Bienestar', 'Calle Real 789'),
    ('Farmacia Santa Maria', 'Calle de la Paz 101'),
    ('Farmacia Central', 'Plaza Principal 202'),
    ('Farmacia La Vida', 'Avenida Libertad 303');

-- Insertar datos en la tabla Medios_de_pago
INSERT INTO Medios_de_pago (Tipo) VALUES
('Efectivo'),
('Tarjeta');

-- Insertar datos en la tabla Productos
INSERT INTO Productos (Nombre, Precio, Stock, Requiere_receta)
VALUES
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

-- Insertar datos en la tabla Repartidores
INSERT INTO Repartidores (RUT, Nombre, Telefono) VALUES
('11223344-5', 'Carlos Diaz', '+56911112222'),
('22334455-6', 'Francisca Soto', '+56922223333'),
('20445566-7', 'Luis Rojas', '+56933334444'),
('19556677-8', 'Paula Reyes', '+56944445555'),
('18667788-9', 'Tomas Fuentes', '+56955556666');    

-- Poblar tabla Calificaciones
INSERT INTO Calificaciones (Puntuacion, Estrellas, Cliente_ID, Repartidor_ID)
VALUES
    ('Muy rápido y amable', 5, 1, 1),
    ('Demorado pero llegó', 3, 2, 2),
    ('Excelente servicio', 5, 3, 1),
    ('Poco cordial', 2, 4, 3),
    ('Normal', 4, 5, 2),
    ('No trajo todo', 2, 6, 4),
    ('Perfecto', 5, 7, 1),
    ('Se perdió en el camino', 1, 8, 5),
    ('Servicio aceptable', 3, 9, 2),
    ('Amable y rápido', 5, 10, 3),
    ('Atención excelente', 5, 1, 4),
    ('Muy educado', 4, 2, 5),
    ('Se equivocó de dirección', 2, 3, 2),
    ('Llegó frío el pedido', 2, 4, 1),
    ('Volvería a pedir', 5, 5, 5),
    ('Sin problemas', 4, 6, 3),
    ('Tardó demasiado', 2, 7, 4),
    ('Buen trato', 5, 8, 2),
    ('Me llamó para confirmar', 4, 9, 1),
    ('Entrega incompleta', 1, 10, 5),
    ('Entrega puntual', 5, 1, 3),
    ('Se pasó la hora', 2, 2, 4),
    ('Todo correcto', 4, 3, 2),
    ('Amable y atento', 5, 4, 1);

-- Poblar Tabla intermediaria Farmacia y productos
INSERT INTO Farmacias_Productos (Farmacia_ID, Producto_ID) VALUES
(1, 1),  (1, 2),  (1, 3),  (1, 4),  (1, 5),
(2, 6),  (2, 7),  (2, 8),  (2, 9),  (2, 10),
(3, 11), (3, 12), (3, 13), (3, 14), (3, 15),
(4, 16), (4, 17), (4, 18), (4, 19), (4, 20),
(5, 21), (5, 22), (5, 23), (5, 1),  (5, 2),
(6, 3),  (6, 6),  (6, 9),  (6, 12), (6, 18);


-- Insertar datos en la tabla Pedidos con farmacia_id
INSERT INTO Pedidos (Fecha, Urgencia, Total_pagado, Estado_entrega, Fecha_entrega, Cliente_ID, Medio_pago_ID, farmacia_id) VALUES
('2025-04-01', false, 21200, 'Entregado', '2025-04-03', 1, 1, 1),
('2025-04-02', true, 44000, 'Pendiente', NULL, 2, 2, 2),
('2025-04-03', false, 6400, 'Entregado', '2025-04-04', 3, 1, 3),
('2025-04-03', true, 38880, 'Pendiente', NULL, 4, 2, 4),
('2025-04-04', false, 9600, 'Entregado', '2025-04-06', 5, 1, 5),
('2025-04-05', true, 32000, 'Pendiente', NULL, 6, 2, 6),
('2025-04-06', false, 8800, 'Entregado', '2025-04-08', 7, 1, 1),
('2025-04-06', true, 25200, 'Pendiente', NULL, 8, 2, 2),
('2025-04-07', false, 7200, 'Entregado', '2025-04-09', 9, 1, 3),
('2025-04-07', true, 22400, 'Pendiente', NULL, 10, 2, 4),
('2025-04-08', false, 12800, 'Entregado', '2025-04-10', 1, 1, 5),
('2025-04-08', true, 17600, 'Pendiente', NULL, 2, 2, 6),
('2025-04-09', false, 24900, 'Entregado', '2025-04-11', 3, 1, 1),
('2025-04-09', true, 10200, 'Pendiente', NULL, 4, 2, 2),
('2025-04-10', false, 15600, 'Entregado', '2025-04-12', 5, 1, 3),
('2025-04-11', true, 23800, 'Pendiente', NULL, 6, 2, 4),
('2025-04-11', false, 28000, 'Entregado', '2025-04-13', 7, 1, 5),
('2025-04-12', true, 6000, 'Pendiente', NULL, 8, 2, 6),
('2025-04-13', false, 9800, 'Entregado', '2025-04-15', 9, 1, 1),
('2025-04-13', true, 15000, 'Pendiente', NULL, 10, 2, 2);


-- Insertar datos para detalle de pedidos
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


