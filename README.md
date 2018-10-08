# Proyecto de formación en Spring: Gestión de Pruebas físicas
Aplicación Java que utiliza el framework de Spring.

# Antes de empezar
Será necesario:
1. Insertar algunos datos en nuestra base de datos (especialmente la tabla Score).
2. Modificar nuestro application.yml y poner nuestra contraseña.

La aplicación se encuentra en el puerto 8081.

# Resumen del contenido
Primero, las entidades más importantes:
- Corredor: CRUD completo, con paginación y búsqueda por nombre.
- Club: Crear, FindById de un único club, y findAll.
- Prueba: Crear y FindById de una prueba.
- Resultado: Crear, FindAll, búsqueda por categoría de edades, imprimir en un fichero los clubs y sus puntos de forma ordenada.

Se ha añadido una excepción para cuando no se encuentre un objeto (NotFound). La consulta compleja se encuentra en ResultDAO.

En las funciones de mayor importancia se ha añadido una breve descripción sobre su funcionamiento.
