-- Creamos la base de datos primeramente
CREATE DATABASE farmaciaLabs1;

-- Conectamos a la base de datos creada previamente
\connect farmaciaLabs1s;

-- Crear tabla Clientes
CREATE TABLE Clientes (
    ID SERIAL PRIMARY KEY,
    RUT VARCHAR(20) UNIQUE,
    Nombre VARCHAR(50),
    Apellido VARCHAR(50),
    Direccion VARCHAR(100),
    Email VARCHAR(100),
    Telefono VARCHAR(20)
);

-- Crear tabla Repartidores
CREATE TABLE Repartidores (
    ID SERIAL PRIMARY KEY,
    RUT VARCHAR(20) UNIQUE,
    Nombre VARCHAR(50),
    Telefono VARCHAR(20)
);

-- Crear tabla Medios_de_pago
CREATE TABLE Medios_de_pago (
    ID SERIAL PRIMARY KEY,
    Tipo VARCHAR(50)
);

-- Crear tabla Pedidos
CREATE TABLE Pedidos (
    ID SERIAL PRIMARY KEY,
    Fecha DATE,
    Urgencia BOOLEAN,
    Total_pagado DECIMAL(10,2),
    Estado_entrega VARCHAR(50),
    Fecha_entrega DATE,
    Cliente_ID INT REFERENCES Clientes(ID),
    Medio_pago_ID INT REFERENCES Medios_de_pago(ID)
);

-- Crear tabla Farmacias
CREATE TABLE Farmacias (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Lugar VARCHAR(100)
);

-- Crear tabla Productos
CREATE TABLE Productos (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Precio DECIMAL(10,2),
    Stock INT,
    Requiere_receta BOOLEAN
);

-- Crear tabla Detalle_de_pedidos
CREATE TABLE Detalle_de_pedidos (
    ID SERIAL PRIMARY KEY,
    Pedido_ID INT REFERENCES Pedidos(ID),
    Producto_ID INT REFERENCES Productos(ID),
    Cantidad INT
);

-- Crear tabla Calificaciones
CREATE TABLE Calificaciones (
    ID SERIAL PRIMARY KEY,
    Puntuacion VARCHAR(50),
    Estrellas INT,
    Cliente_ID INT REFERENCES Clientes(ID),
    Repartidor_ID INT REFERENCES Repartidores(ID)
);

-- Crear tabla Pedidos_Repartidores
CREATE TABLE Pedidos_Repartidores (
    Pedido_ID INT REFERENCES Pedidos(ID),
    Repartidor_ID INT REFERENCES Repartidores(ID),
    PRIMARY KEY (Pedido_ID, Repartidor_ID)
);

-- Crear tabla Pedidos_Farmacias
CREATE TABLE Pedidos_Farmacias (
    Pedido_ID INT REFERENCES Pedidos(ID),
    Farmacia_ID INT REFERENCES Farmacias(ID),
    PRIMARY KEY (Pedido_ID, Farmacia_ID)
);

-- Crear tabla Farmacias_Productos
CREATE TABLE Farmacias_Productos (
    Farmacia_ID INT REFERENCES Farmacias(ID),
    Producto_ID INT REFERENCES Productos(ID),
    PRIMARY KEY (Farmacia_ID, Producto_ID)
);
