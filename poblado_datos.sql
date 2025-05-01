-- Insertar datos en la tabla Farmacias
INSERT INTO Farmacias (Nombre, Lugar)
VALUES 
    ('Farmacia San Juan', 'Av. Siempre Viva 123'),
    ('Farmacia La Salud', 'Calle Ficticia 456'),
    ('Farmacia El Bienestar', 'Calle Real 789'),
    ('Farmacia Santa Maria', 'Calle de la Paz 101'),
    ('Farmacia Central', 'Plaza Principal 202'),
    ('Farmacia La Vida', 'Avenida Libertad 303');

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
    ('Miconazol', 8800, 70, false);
