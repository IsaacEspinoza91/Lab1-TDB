-- CONSULTAS
-- 1. ¿Qué cliente ha gastado más dinero en pedidos entregados? [Omar]
SELECT c.Usuario_ID AS Cliente_ID, SUM(p.Total_pagado) AS TotalGastado
FROM Clientes c
JOIN Pedidos p ON p.Cliente_ID = c.Usuario_ID
WHERE p.Estado_entrega = 'Entregado'
GROUP BY c.Usuario_ID
ORDER BY TotalGastado DESC
LIMIT 1;


--2. ¿Cuáles son los productos más pedidos en el último mes por categoría?


-- 3. Listar las farmacias con más entregas fallidas.[Isaac]
SELECT f.id, f.nombre, f.lugar, COUNT(*) AS entregas_fallidas
FROM pedidos AS p INNER JOIN farmacias AS F
	ON p.farmacia_id = f.id
WHERE p.estado_entrega = 'Fallido'
GROUP BY f.id, f.nombre, f.lugar
ORDER BY entregas_fallidas DESC;


-- 4. Calcular el tiempo promedio entre pedido y entrega por repartidor.


-- 5. Obtener los 3 repartidores con mejor rendimiento (basado en entregas y puntuación).


-- 6. ¿Qué medio de pago se utiliza más en pedidos urgentes?



-- PROCEDIMIENTOS ALMACENADOS
-- 7. Registrar un pedido completo. [Omar]
CREATE OR REPLACE PROCEDURE registrar_pedido(
  p_fecha DATE,
  p_urgencia BOOLEAN,
  p_total INT,
  p_estado TEXT,
  p_fecha_entrega DATE,
  p_medio_pago_id INT,
  p_farmacia_id INT,
  p_repartidor_id INT,
  p_cliente_id INT,
  p_detalles JSON
)
LANGUAGE plpgsql
AS $$
DECLARE
  item JSON;
  pedido_id INT;
BEGIN
  INSERT INTO "pedidos" (
    "fecha", "urgencia", "total_pagado", "estado_entrega", "fecha_entrega", "cliente_id",
    "medio_pago_id", "farmacia_id", "repartidor_id"
  )
  VALUES (
    p_fecha, p_urgencia, p_total, p_estado, p_fecha_entrega,
    p_medio_pago_id, p_farmacia_id, p_repartidor_id, p_cliente_id
  )
  RETURNING "id" INTO pedido_id;

  FOR item IN SELECT * FROM json_array_elements(p_detalles)
  LOOP
    INSERT INTO "detalle_de_pedidos" ("id", "pedido_id", "producto_id", "cantidad")
    VALUES (
      (SELECT COALESCE(MAX("id"), 0) + 1 FROM "detalle_de_pedidos"),
      pedido_id,
      (item->>'producto_id')::INT,
      (item->>'cantidad')::INT
    );
  END LOOP;
END;
$$;


-- 8. Cambiar el estado de un pedido con validación. [Isaac]
CREATE OR REPLACE PROCEDURE cambiar_estado_pedido(
    IN id_pedido INT,
    IN nuevo_estado VARCHAR(50),
    OUT resultado VARCHAR(100)
)
LANGUAGE plpgsql
AS $cuerpo$
BEGIN
    -- verificar si pedido existe
    IF NOT EXISTS (SELECT * FROM pedidos WHERE id = id_pedido) THEN
        resultado := 'Error: Pedido no existe';
        RETURN;
    END IF;
    
    -- validar el nuevo estado
    IF nuevo_estado NOT IN ('Entregado', 'Fallido', 'Pendiente') THEN
        resultado := 'Error: Estado invalido';
        RETURN;
    END IF;
    
    -- Actualizar el estado del pedido
    UPDATE pedidos 
    SET estado_entrega = nuevo_estado
    WHERE id = id_pedido;
    resultado := 'Estado del pedido actualizado correctamente';
END;
$cuerpo$;
-- Ejemplo llamada, muestra tabla con resultadod de ejecuion
CALL cambiar_estado_pedido(2,'Fallido', NULL);


-- 9. Descontar stock al confirmar pedido.


-- TRIGGERS
-- 10. Insertar automáticamente la fecha de entrega al marcar como entregado.


-- 11. Registrar una notificación si un medicamento con receta es pedido sin validación.


-- 12. Insertar una calificación automática si no se recibe en 48 horas.


-- VISTAS
-- 13. Resumen de pedidos por cliente (monto total, número de pedidos).


-- 14. Vista de desempeño por repartidor.


-- 15. Vista de farmacias con mayor volumen de productos entregados.[Isaac]
CREATE OR REPLACE VIEW farmacias_volumen_entregas_exitosas AS
SELECT 
    f.id, 
	f.Nombre AS farmacia, 
	f.Lugar,
    COALESCE(SUM(dp.cantidad), 0) AS total_productos_pedidos, -- pedidos de todos los estados
    COALESCE(SUM(dp.cantidad) FILTER (WHERE p.estado_entrega = 'Entregado'), 0) AS total_productos_entregados
FROM farmacias AS f LEFT JOIN pedidos AS p
	ON f.id = p.farmacia_id
	LEFT JOIN detalle_de_pedidos AS dp
	ON p.id = dp.pedido_id
GROUP BY f.id, f.nombre, f.lugar
ORDER BY total_productos_entregados DESC;
