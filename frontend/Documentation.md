# Frontend Documentación  

## Ejecuta esta proyecto  

se puede ejecutar con cualquier paquete de librerías de javascript (npm,yarn,pnpm).
En este desarrollo usaremos pnpm  

```
pnpm i
pnpm run dev
```

## Tipo de Arquitectura
 
clear architecture

### Distribución de carpetas  


- adapters :  si necesitas modificar cualquier información incluidas las respuestas de los endpoint , deberá crear un carpeta adaptador para hacerlo. De esta forma , si el endpoint cambia o la respuesta que proporciona , solo necesita modificar el adaptador para seguir la misma respuesta en toda la aplicación.

- components: si necesitas crear un componente , siempre verifique si se puede usar en mas de una vista , si esto es cierto , debe agregarlo a esta carpeta. Cada componente dentro de la carpeta raíz ./ components se puede reutilizar en toda la aplicación, esto es perfecto para obtener consistencia y aumenta la capacidad de mantenimiento.

- context : A diferencia de reduxjs, context es una solución de gestión de estado proporcionada por react que tiene una implementation especifica , compartiendo entre elementos de la misma vista. Si necesitas mantener el estado y compartirlo en toda la aplicación , reduxjs es la opción mas preferible.

- hooks : Cuando  uno determina la lógica se puede repetir en varios componentes , un hook personalizado es el camino a seguir. Por ejemplo ,puede crear un enlace personalizado para obtener siempre cierta información sobre la carga de componente o para mostrar una pantalla de carga mientras espera la información.

- models : Estamos usando typescript , por lo que la creación de interfaces personalizadas es imprescindible. Cada vez que  recibimos una nueva respuesta de un endpoint , usamos  parámetro complejo en un método, p necesitamos  transform una información en otra , vamos a crear una interfaz personalizada .

- pages: La idea principal es tener paginas para cada vista (ruta) en nuestra aplicación , cada carpeta contendrá el componente principal de la vista. Si necesitas algún componente secundario , recuerde que sera diferente de los que se entran dentro de la carpeta ./components, ya que no se compartirán entre diferentes vistas y contendrán la lógica que solo la pagina en la se que encuentra . Recuerde crear la carpeta de cada componente hijo , ya que también podrían tener mas hijos .

- services : Los diferentes servicios utilizados en la aplicación.

- styled-components : Si necesitas diseñar un determinado elemento del DOM y se utilizara en mas de un lugar, debe crear un componente con estilo.

- utilities : En caso de que detectemos cierta lógica que se pueda reutilizar en toda la aplicación , debemos crear un archivo que la contenga dentro de esta carpeta y hacerla exportable. 

## Jsonplaceholder

ejecuta el la api falsa de la carpeta api con el siguiente comando 

```
  json-server --watch  api/tuAPiFalsa.json
  
```