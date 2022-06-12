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