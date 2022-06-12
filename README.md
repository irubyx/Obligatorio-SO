# Obligatorio Sistemas Operativo
Repositorio creado con el fin de almacenar el proyecto final de sistemas operativos. Además, documentar el proceso y la solución del proyecto obligatorio de la materia.

    Asignatura: Sistemas Operativos, 1er semestre 2022
    Institución: Universidad Católica del Uruguay
    Integrantes: Sebastián Fripp, Rodrigo Jauregui, Sofia Pareja, Nicolás Maisonnave.
    Fecha de creación: 07/06/2022
    Fecha de entrega: 20/07/2022

<hr>

## Contenido

- [Obligatorio Sistemas Operativo](#obligatorio-sistemas-operativo)
  - [Contenido](#contenido)
  - [Problema](#problema)
    - [Definición](#definición)
    - [Escenario](#escenario)
    - [Requisitos](#requisitos)
  - [Como utilizar la aplicación](#como-utilizar-la-aplicación)
    - [Setup](#setup)
    - [Dashboard](#dashboard)
      - [Agregar proceso](#agregar-proceso)
      - [Modificar proceso](#modificar-proceso)

## Problema
### Definición
La consigna, en simples palabras, solicitaba la creación de un administrador de tareas (similar al que se puede encontrar en Windows) con una interfaz gráfica de usuario (**GUI**) para poder listar los procesos, verificar el estado del mismo, bloquearlo, agregar nuevos procesos, y demás funcionalidades que están descritas en las siguientes secciones de este documento.

### Escenario
Se nos fue otorgado el siguiente escenario en la letra del obligatorio:

> *Se nos ha contratado para realizar un planificador de corto plazo para un sistema operativo servidor que se instalará en un servidor de mediano porte. Pero antes de comenzar el desarrollo, se nos pide que generemos una simulación del mismo como para poderla evaluar su comportamiento. Esto es con el objetivo de validar si el diseño es correcto para este servidor.*

### Requisitos
Se nos solicitó además que el software resultado contenga las siguientes características (y más):

- Posibilidad de indicar la cantidad de procesadores o núcleos del sistema a simular. :white_check_mark:
- Posibilidad de modificar la cantidad de tiempo que deben permanecer los procesos en el CPU. :white_check_mark:
- Posibilidad de modificar la prioridad de los procesos en tiempo de ejecución (prioridad de 1 a 99). :white_check_mark:
- Posibilidad de bloquear un proceso en tiempo de ejecución. :white_check_mark:
- Posibilidad de cargar múltiples procesos de un solo ingreso.
- Posibilidad de insertar procesos, ya sea del sistema operativo o de usuario indicando:
  - Tiempo total de ejecución.
  - Cada qué tiempo se realiza una entrada/salida (periódica sin modificación).
  - Tiempo en que espera por la entrada/salida (puede ser diferente para cada proceso).
- Interfaz gráfica de usuario que contenga:
  - Procesos en ejecución en el CPU (o en los CPUs).
  - Lista de los procesos listos indicando el orden en que ingresaron al CPU.
  - Lista de los procesos bloqueados (indicando si se encuentra bloqueado por el usuario o por una entrada/salida) ordenada en cada momento por cuál será el próximo a ser desbloqueado.

## Como utilizar la aplicación
### Setup
Ni bien se inicia la aplicación el usuario deberá visualizar una interfaz como la que se presenta a continuación, llamada `Setup`, en la cual se ha de indicar las especificaciones del sistema de cómputo a simular:

![]()

A continuación se brinda una breve descripción de cada campo:

- **Cantidad de núcleos del sistema:**
  - Se ha de proporcionar la cantidad de núcleos que tendrá el sistema en la simulación.
- **Tiempo disponible en CPU:**
  - Se ha de indicar
- **RAM de la PC:**
  - Al igual que el primer campo, se ha de indicar la cantidad de gigabytes de RAM que tendrá el sistema en la simulación.

Una vez especificadas las características del sistema, se deberá dar click en `Inicio` para iniciar la simulación.

### Dashboard
Lo siguiente que se debe visualizar es el `Dashboard`, el cuál contiene dos tablas. La tabla de `Procesos`, que contiene todos los procesos corriendo en el sistema. Y la tabla de `Núcleos` ...

En la tabla de `Procesos` se brinda la siguiente información:
- **ID:**
  - Identificador único que tiene cada proceso.
- **Nombre:**
  - Nombre del proceso.
- **Estado:**
  - Estado en el que se encuentra el proceso actualmente. Las posibles opciones son: `Running`, `Blocked` y `Ready`.
- **Prioridad:**
  - Prioridad que tiene el proceso. Entero entre 1 y 99, siendo 1 la prioridad más baja de todas y 99 la prioridad más alta. 

En la tabla de `Núcleos` se brinda la siguiente información:
...

![]()

Debajo de la misma tabla, se encuentra la opción de `Agregar` y `Modificar` procesos, donde se podrán agregar nuevos procesos al sistema indicando características propias del proceso a agregar, o modificar un proceso que fue agregado previamente.

#### Agregar proceso
Si se desea agregar un proceso, se ha de dar click en la opción de `Agregar`, mencionada en el apartado de [Dashboard](#dashboard). Una vez dentro, se solicita al usuario indicar las siguientes características del proceso a agregar:

- **Nombre:**
  - Se ha de indicar el nombre del proceso a agregar.
- **Tiempo requerido:**
  - coso
- **Prioridad:**
  - Prioridad que tendrá este proceso. Se debe proveer un número entre 1 y 99, siendo 1 la prioridad más baja de todas y 99 la prioridad más alta. 
- **Tiempo entre E/S:**
  - coso
- **ID Padre:**
  - ID del proceso padre del proceso a agregar.
- **Tiempo salir E/S:**
  - coso
- **Tamaño memoria:**
  - Se debe indicar la cantidad de memoria del sistema que consume el proceso a agregar.

![]()

Una vez se especifiquen los campos requeridos, se debe de dar click en el botón de `Agregar` para agregar el proceso con las características que fueron indicadas.\
Opcionalmente, existe la posibilidad de volver a la pestaña de `Dashboard`, para ello se ha de dar click en el botón de `Atrás`.

#### Modificar proceso
Si se desea modificar un proceso que fue agregado previamente al sistema, se ha de dar click en el botón de `Modificar`, mencionada en el apartado de [Dashboard](#dashboard). Una vez dentro, se brindan los campos modificables de cada proceso, a continuación se brinda la lista de los mismos y una breve descripción:

...

![]()

Una vez modificados los campos del proceso, se debe dar click en el botón de `Guardar cambios` para guardar y modificar los datos del proceso seleccionado.\
Opcionalmente, existe la posibilidad de volver a la pestaña de `Dashboard`, para ello se ha de dar click en el botón de `Atrás`.