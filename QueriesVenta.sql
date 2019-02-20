--Mostrar Venta
Create proc SpMostrarVenta
as
select top 100 v.id_venta, t.nombre as trabajador, c.nombre as Cliente, v.fecha,
v.subtotal, v.impuesto, v.total 
from detalle_venta d inner join venta v on d.id_venta = v.id_venta
inner join cliente c on v.id_cliente = c.id_cliente
inner join trabajador t on v.id_trabajador = t.id_trabajador
order by v.id_venta desc
go

--mostrar ventas entre fechas
create proc SpMostrarVentasEntreFechas
@textobuscar varchar(50),
@textobuscar2 varchar(50)
as 
select v.id_venta, t.nombre as trabajador, c.nombre as Cliente, v.fecha,
v.subtotal, v.impuesto, v.total 
from detalle_venta d inner join venta v on d.id_venta = v.id_venta
inner join cliente c on v.id_cliente = c.id_cliente
inner join trabajador t on v.id_trabajador = t.id_trabajador
where v.fecha >= @textobuscar and v.fecha <= @textobuscar2
go

--instertar venta
create proc SpInsertarVenta
@idventa int = null output,
@idcliente int,
@idtrabajador int,
@fecha date,
@subtotal float,
@impuesto float,
@total float
as 
insert into venta (id_cliente,id_trabajador, fecha, subtotal, impuesto, total)
values (@idcliente,@idtrabajador,@fecha,@subtotal,@impuesto,@total)
go

--Anular Venta
Create proc SpEliminar_venta
@idventa int
as 
update venta set estado = 'ANULADO'
 where id_venta = @idventa
go


--insertar detalle venta
create proc SpInsertarDetalleVenta
@iddetalleventa int output,
@idventa int,
@iddetalleingreso int,
@cantidad int,
@precio float 
as
insert into detalle_venta (id_venta,id_detalle_ingreso,cantidad,precio)
values (@idventa,@iddetalleingreso,@cantidad,@precio)
go

--restablecer el stock despues de anular la venta


--Aumentar o disminuir el stock despues de una venta
create proc SPActualizarStock
@iddetalleingreso int,
@cantidad int
as
update detalle_ingreso set stock_actual = stock_actual + @cantidad
where id_detalle_ingreso = @iddetalleingreso
go

--mostrar los detalle de una venta
create proc SpDetalleVenta
@textobuscar int
as 
select d.id_detalle_ingreso, a.nombre as Articulo, d.cantidad ,d.precio,
 (d.precio * d.cantidad) as Subtotal
 from detalle_venta d inner join detalle_ingreso di on d.id_detalle_ingreso = di.id_detalle_ingreso
 inner join articulo a on di.id_articulo = a.id_articulo
 where d.id_venta = @textobuscar 
 go

 --Mostrar los articulos para la venta
 create proc SpBuscarArticuloVentaNombre
 @textobuscar varchar(50)
 as 
 select d.id_detalle_ingreso, a.nombre as Articulo, a.descripcion,
  d.stock_actual, d.precio_compra, d.precio_venta
  from articulo a inner join detalle_ingreso d on a.id_articulo = d.id_articulo
  inner join ingreso i on d.id_ingreso = i.id_ingreso
  where a.nombre like @textobuscar +'%'
  and d.stock_actual>0
  and i.estado <> 'ANULADO'
  go