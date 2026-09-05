# PAE_Menus_BarraHerramientas
---

# Distribuidora El Güegüense

Aplicación de escritorio en JavaFX para la gestión básica de inventario de productos (registro, edición, eliminación y visualización de detalles).

## Descripción
Interfaz gráfica que permite administrar productos en memoria. Cada producto tiene: código, nombre, categoría, precio y existencia. Ideal como ejemplo educativo sobre JavaFX, FXML y uso de `ObservableList` para tablas.

## Características
- Crear (Guardar) productos con validaciones básicas.  
- Editar productos seleccionados.  
- Eliminar productos con confirmación.  
- Ver detalle del producto en un diálogo.  
- Menú y barra de herramientas con acciones duplicadas.  
- Tabla con menú contextual (Editar, Eliminar, Ver detalle).  
- Estilos CSS personalizados para la interfaz.

## Pasos para ejecutar
1. Clonar el proyecto desde el repositorio.
2. Abrirlo en un IDE compatible con JavaFX (como IntelliJ IDEA o Eclipse).
3. Asegurarse de tener configurado el SDK de Java y la librería de JavaFX.
4. Ejecutar la clase principal `gueguenseApplication`.
#### En que caso que no deje ejecutar la clase principal, hacer click secundario en el `pom.xml` y seleccionar la opcion "ADD A MAVEN PROJECT"

## Funcionamiento (resumen)
- Al iniciar aparece un formulario (izquierda) para ingresar/editar productos y, al centro, una tabla con el inventario.  
- Seleccionar una fila carga los datos en el formulario.  
- Guardar crea un nuevo producto (valida campos y formatos numéricos).  
- Editar actualiza el producto seleccionado.  
- Eliminar solicita confirmación antes de borrar de la lista.  
- Ver detalle muestra la ficha completa del producto.  
- Los datos se mantienen solo en memoria (no hay persistencia).


## Realizado por:
Diego Silva
