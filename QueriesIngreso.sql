--Mostrar ingresos
select top 100 i.id_ingreso, t.nombre as trabajador, p.empresa as Proveedor,
 p.nombre as RepresentanteLegal, i.fecha, i.estado, i.precio_total
 from detalle_ingreso d inner join ingreso i on d.id_ingreso = i.id_ingreso
 inner join proveedor p on i.id_proveedor = p.id_proveedor
 inner join  trabajador t on i.id_trabajador = t.id_trabajador
 order by i.id_ingreso desc
 

 -- Mostrar ingresos entre fechas
 create proc SpBuscrarEntreFechas
 @textobuscar varchar(20),
 @textobuscar2 varchar(20)
 as
 select i.id_ingreso, t.nombre as trabajador, p.empresa as Proveedor,
 p.nombre as RepresentanteLegal, i.fecha, i.estado, i.precio_total
 from detalle_ingreso d inner join ingreso i on d.id_ingreso = i.id_ingreso
 inner join proveedor p on i.id_proveedor = p.id_proveedor
 inner join  trabajador t on i.id_trabajador = t.id_trabajador
 where i.fecha>= @textobuscar and i.fecha <= @textobuscar2
 go

 --Procedimiento Insertar ingreso
 create proc SpInsertarIngreso	
 @idingreso int = null output,
 @idtrabajador int,
 @idproveedor int,
 @fecha date,
 @estado int,
 @preciototal float
 as
 insert into ingreso (id_trabajador,id_proveedor, fecha,estado,precio_total)
 values(@idtrabajador, @idproveedor,@fecha,@estado,@preciototal
 )
 go


 --procedimiento anular ingreso
 create proc SpAnularIngreso
 @idingreso int
 as 
 update ingreso set estado = 1
 where id_ingreso = @idingreso
 go

--procedimiento para insertar los detalles de ingreso
create proc SpInsertarDetalleIngreso
@iddetalleingreso int output,
@idingreso int,
@idarticulo int,
@preciocompra float,
@precioventa float,
@stockinicial int, 
@stockactual int
as
insert into detalle_ingreso (id_ingreso,id_articulo,precio_compra,precio_venta,stock_inicial,stock_actual)
values (@idingreso,@idarticulo,@preciocompra,@precioventa,@stockinicial,@stockactual)
go

create proc SpMostrarDetalleIngreso
@textobuscar int
as
select d.id_articulo, a.nombre as Articulo, d.precio_compra, d.precio_venta, d.stock_inicial,
d.stock_actual, (d.stock_inicial * d.precio_compra) as Subtotal
from detalle_ingreso d inner join articulo a on d.id_articulo = a.id_articulo
where d.id_ingreso = @textobuscar
go