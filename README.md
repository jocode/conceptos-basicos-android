# conceptos-basicos-android
Conceptos útiles sobre Java y Android

Cuanto se trabaja para móviles, debemos tener en cuenta varios factores como:
- La memoria
- El poder de procesamiento
- Los diferentes tipos de pantalla y resoluciones
- La trasferencia de datos
- La batería

¿Si el usuario se le acaba el espacio, qué borrará?¿Fotos, documentos o nuestra app? 

Android está creado desde el kernel de linux, el runtime de Android ART, y lo que permite es compilar todo antes de que se ejecute, lo que permite que la ejecución sea mucho más veloz.

La estructura es la siguiente:
* Aplicaciones
* Frameworks de aplicaciones
* Librerías y ART 
* Kernel de Linux

## Componentes de Android

### Actividades
Son las pantallas que el usuario ve en la aplicación. Una actividad se mostrará a la vez. Haciendo una analogía son como una baraja de cartas, donde se ve sólo la primera.

## Servicios
No requiere una interfaz gráfica. Los servicios no tiene un hilo de ejecición aparte, se ejecutarán en el *main thread*. Por ejemplo los reproductores de música, son servicios.

## Broadcast receiver
Capturan en el momento que se ejecuten en servicios o actividades.


Los tres son activados con **Intents**. Un intent es la intención de hacer algo. A través de los intents decidimos que vamos a hacer.

Las actividades y servicios tienen su ciclo de vida. Que son reflejados en métodos de Java, como `onCreate`, `onResume`, `onPause`, `onDestroy`.

El intent, permite crear algunos filtros para realizar algunas funciones espeíficas.

## Tipos de Intents 
Cuando es definido por nosotros o es definido por el sistema. Puede ser `implícito` o `explícito`.

## Content Provider
Es el elemento que maneja datos y son activados, con `Contents Resolver`. Este componente no es tán común. Y se usa para exponer datos a terceros, por ejemplo para la galería, contactos.

## Android Manifest
Es un archivo de configuración para la aplicación.

En resumen, los componentes basicos de android son:
* Activados con Intents 
	- Activity
	- Service 
	- Broadcast 

* Activados con Content Resolver
	- Content Provider

Y todo esto se configura en el archivo android Manifest.


## Fragmentos
Un fragmento, es una porción de interfaz gráfica. Puedo tener múltiples fragmentos dentro de una actividad. Y un fragmento siempre debe pertenecer a una actividad.

Dentro de los fragmentos hay varios métodos.
- `onActivityCreated` Se llama cuando la actividad es creada.

Los fragmentos se pueden agregar de forma dinámica, y de forma estática.

De forma dinámica, se llama desde código, por ejemplo:

```java
FragmentManager fragmentManager = getFragmentManager(); 
FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

ExampleFragment fragment = new ExampleFragment(); 
fragmentTransaction.add(R.id.fragmentContainer, fragment);
fragmentTransaction.commit();
```

Podemos usar una actividad con varios fragmentos. Usando Navigation Manager.

> Para comunicar fragmentos con actividades, podemos usar interfaces con callbacks.

## Librerías Útiles
- Dagger 
- ButterKnife 

**Dagger** Nos permite inyectar dependencias.

**ButterKnife** Nos permite inyectar (vincular) vistas. Lo que hace es que no usemos findViewById() y en lugar de eso, usamos anotaciones.

Para eso debemos agregar soporte al archivo, en gradle `build.gradle`
```gradle 
dependencies {
	compile 'com.jakewharton:butterknife:7.0.1'
}
```

**Usando la Librería**
```java
// Se crea el vínculo
@Bind(R.id.input_name) EditText inputName; 

@Override 
protected void onCreate(Bundle saveInstanceState){
	super.onCreate(saveInstanceState);
	setContentView(R.layout.activity_main);
	ButterKnife.bind(this); 
}

// Usando anotaciones para usar los eventos
@OnClick(R.id.btnSubmit)
public void submit(){
	// Acción a realizar
}
```

> **IMPORTANTE** Hay un pluggin para android studio llamado `ButterKnife - Zelezny`, que permite crear las inyecciones automáticas. Solo basta con xml y seleccionar los elementos que quiero enlazar.


## Layout

Son archivos en xml, que se crean para la interfaz de usuario.
Algunos contenedores son:
* ViewGroup
* FrameLayout
* LinearLayout
* RelativeLayot
* CoordinatorLayout

## Controles de Entrada

Son descritos en XML, y están contenidos dentro de un layout. Algunos son:
- EditText 
	textField : number|password
- TextView
- Button 
- ImageView
- ToggleButton
- Spinner
- RadioButton
- Checkbox
- Picker

## Recycler View

Son elementos que muestra contenido como listado o como grilla.
Los recycler view no tienen tantas responsabilidades. Por ejemplo, para la organización de los elementos se encarga el `LayoutManager`, para la animación de los elementos, se encarga `ItemAnimator`, y no se encarga de ningún evento, a exepción del scroll.

Para el recycler view se necesita.
- LayoutManager `LinearLayoutManager | GridLayoutManager | StaggeredGridLayoutManager`
- Adapter (ViewHolder - Reutiliza los elementos)
- Dataset (Fuente de datos)

Para los eventos en el recyclerView, podemos crear una interfaz con un callback.

Para el recyclerView, se debe agregar el soporte en `build.gradle`
```
dependencies {
	compile 'com.android.support:recyclerview-v7:+'
}
```
