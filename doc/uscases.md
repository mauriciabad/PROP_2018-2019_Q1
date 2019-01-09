# Descripció dels casos d'ús
L'usuari o el sistema poden realitzar qualsevol de les següents accions.

## Gestionar Assignatures
- **Carregar Pla d'estudis**: Carregar un conjunt d'assignatures a l'hora.
- **Carregar Assignatura**: Carregar una assignatura.
- **Consultar Assignatura**: Visualitzar la informació d'una assignatura.
- **Crear Assignatura**: Crear una nova assignatura introduint: 
    - sigles
    - nom sencer
    - nivell dins del pla d'estudis
    - places disponibles
    - prerequisits
    - corequisits
    - precorequisits
    - per cada tipus de sessió:
        - nom del tipus *(normalment "Teoria", "Laboratori" o "Problemes")*
        - nombre de sessions per setmana
        - duració de la sessió
        - places dels grups
        - nombre de subgrups
- **Modificar Assignatura**: Modificar qualsevol atribut d'una assignatura.
- **Eliminar Assignatura**: Elimina una assignatura existent.

## Gestionar Aules
- **Carregar Campus**: Carregar un conjunt d'aules a l'hora.
- **Carregar Aula**: Carregar una aula.
- **Consultar Aula**: Visualitzar la informació d'una aula.
- **Crear Aula**: Crear una nova aula introduint: 
    - nom
    - capacitat
    - per cada tipus de material disponible:
        - nom del material
- **Modificar Aula**: Modificar qualsevol atribut d'una aula.
- **Eliminar Aula**: Elimina una aula existent.

## Gestionar Horaris

- **Definir Horari**: *Carregar Campus*, *Carregar Pla d'estudis*, *Generar Sessions* i *Establir Restriccions* de cada sessió.
- **Establir Restricció**: Establir les restriccions que es volen aplicar al horari.
- **Generar Horari**: *Establir Hora, Dia i Aula* a cada sessió d'un horari amb les asignatures, aules i restriccions de cada sessió definides complint el màxim de restriccions possibles segons la seva importància.
- **Visualitzar Horari**: Carrega un horari i el visualitza
- **Guardar Horari**: Guarda un horari
- **Editar Horari**: Modifica les restriccions de un horari i el torna a generar


## Sortir

Tanca el programa.  En qualsevol moment es pot sortir, així que obïem la seva existència en el diagrama per comprensibilitat.





# Diagrama de casos d’ús
![Diagrama de casos d'ús](uscases.png)