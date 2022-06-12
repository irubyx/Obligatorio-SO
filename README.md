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
    - [Escenario](#escenario)
    - [Requisitos](#requisitos)
  - [Como utilizar la aplicación](#como-utilizar-la-aplicación)

## Problema
La consigna, en simples palabras, solicitaba la creación de un administrador de tareas (similar al que se puede encontrar en Windows) con una interfaz gráfica de usuario (**GUI**) para poder listar los procesos, verificar el estado del mismo, bloquearlo, agregar nuevos procesos, y demás funcionalidades que están descritas en las siguientes secciones de este documento.

### Escenario
Se nos fue otorgado el siguiente escenario en la letra del obligatorio:

> *Se nos ha contratado para realizar un planificador de corto plazo para un sistema operativo servidor que se instalará en un servidor de mediano porte. Pero antes de comenzar el desarrollo, se nos pide que generemos una simulación del mismo como para poderla evaluar su comportamiento. Esto es con el objetivo de validar si el diseño es correcto para este servidor.*

### Requisitos
Se nos solicitó además que el software resultado contenga las siguientes características (y más):

- Posibilidad de indicar la cantidad de procesadores o núcleos del sistema a simular. :white_check_mark:
- Posibilidad de modificar la cantidad de tiempo que deben permanecer los procesos en el CPU.
- Posibilidad de modificar la prioridad de los procesos en tiempo de ejecución (prioridad de 1 a 99).
- Posibilidad de bloquear un proceso


Para que la evaluación del planificador sea lo más realista posible, se nos pide que el mismo contemple (entre otras cosas): 

Poder ingresar la cantidad de procesadores o cores.
Poder modificar la cantidad de tiempo que los procesos se encuentran en CPU.
Poder modificar la prioridad de los mismos en tiempo de ejecución (prioridad de 1 a 99).
Poder bloquear un proceso en cualquier momento.
Poder cargar (de alguna forma) múltiples procesos de un solo ingreso
Poder insertar procesos ya sea del S.O. como de usuario indicando:
Tiempo total de ejecución
Cada qué tiempo realiza una E/S (periódica sin modificación)
Tiempo en que espera por la E/S (puede ser diferente para cada proceso).


En todo momento se deberá visualizar lo siguiente:
	Proceso ejecutando en CPU (o en CPUs)
	Lista de los procesos listos indicando el orden en que ingresaran a CPU.
	Lista de los procesos bloqueados (indicando si se encuentra bloqueado por el usuario o por una entrada salida) ordenada en cada momento por quien sería el próximo a ser desbloqueado.


## Como utilizar la aplicación

