-- Crear tabla Usuarios (base común)
CREATE TABLE Usuarios (
    ID SERIAL PRIMARY KEY,
    RUT VARCHAR(20) UNIQUE,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Password VARCHAR(100) NOT NULL,
    Telefono VARCHAR(20),
    Tipo VARCHAR(20) NOT NULL CHECK (Tipo IN ('CLIENTE', 'REPARTIDOR', 'ADMIN'))
);

-- Crear tabla Clientes (hereda de Usuarios)
CREATE TABLE Clientes (
    Usuario_ID INT PRIMARY KEY REFERENCES Usuarios(ID) ON DELETE CASCADE,
    Direccion VARCHAR(100) NOT NULL
);

-- Crear tabla Repartidores (hereda de Usuarios con nuevos atributos)
CREATE TABLE Repartidores (
    Usuario_ID INT PRIMARY KEY REFERENCES Usuarios(ID) ON DELETE CASCADE,
    Tipo_vehiculo VARCHAR(20) NOT NULL CHECK (Tipo_vehiculo IN ('AUTO', 'MOTO', 'BICICLETA', 'CAMIONETA'))
);

-- Crear tabla Administradores (nueva tabla)
CREATE TABLE Administradores (
    Usuario_ID INT PRIMARY KEY REFERENCES Usuarios(ID) ON DELETE CASCADE,
    Nivel_acceso INT DEFAULT 1,
    Departamento VARCHAR(50)
);

-- Crear tabla Medios_de_pago
CREATE TABLE Medios_de_pago (
    ID SERIAL PRIMARY KEY,
    Tipo VARCHAR(50)
);

-- Crear tabla Farmacias
CREATE TABLE Farmacias (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Lugar VARCHAR(100)
);

-- Crear tabla Pedidos (modificada para usar Usuarios)
CREATE TABLE Pedidos (
    ID SERIAL PRIMARY KEY,
    Fecha DATE,
    Urgencia BOOLEAN,
    Total_pagado INT,
    Estado_entrega VARCHAR(50),
    Fecha_entrega DATE,
    Cliente_ID INT REFERENCES Usuarios(ID),
    Medio_pago_ID INT REFERENCES Medios_de_pago(ID),
    Farmacia_ID INT REFERENCES Farmacias(ID),
    Repartidor_ID INT REFERENCES Usuarios(ID)
);

-- Crear tabla Productos
CREATE TABLE Productos (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Precio INT,
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

-- Crear tabla Calificaciones (modificada para usar Usuarios)
CREATE TABLE Calificaciones (
    ID SERIAL PRIMARY KEY,
    Puntuacion VARCHAR(50),
    Estrellas INT,
    Cliente_ID INT REFERENCES Usuarios(ID),
    Repartidor_ID INT REFERENCES Usuarios(ID)
);

-- Crear tabla Pedidos_Repartidores (modificada para usar Usuarios)
CREATE TABLE Pedidos_Repartidores (
    Pedido_ID INT REFERENCES Pedidos(ID),
    Repartidor_ID INT REFERENCES Usuarios(ID),
    PRIMARY KEY (Pedido_ID, Repartidor_ID)
);

-- Crear tabla Farmacias_Productos
CREATE TABLE Farmacias_Productos (
    Farmacia_ID INT REFERENCES Farmacias(ID),
    Producto_ID INT REFERENCES Productos(ID),
    PRIMARY KEY (Farmacia_ID, Producto_ID)
);