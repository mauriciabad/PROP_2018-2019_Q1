# Diagrama del model conceptual del domini

![Diagrama del model conceptual](C:/Users/mauri/OneDrive/Escritorio/PracticaPROP/doc/model.png)

# Descripcció del model conceptual del domini

## Curriculum

Representa un pla d'estudis, o sigui un conjunt d'assignatures. Es guarda en format JSON a [/data/curriculum/](../data/curriculum/).

> Aquesta classe no apareix en el codi perquè s'obïa, ja que només es treballa amb un únic pla d'estudis durant l'execució.

## Subject

Representa una assignatura.

## ClassType

Representa un tipus de classe, típicament *Teoria*, *Laboratori* o *Problemes*.

## Session

Representa la realització d'una classe, per exemple: la 1a sessió de la setmana, del grup 20, de Teoria, de PROP es fa el Dilluns a les 8 a.m. a l'aula A6202.

Les sessions tenen restriccións associades.

Les sessions poden no tenir definida l'hora, dia o aula.

Cada sessió té un conjunt amb ClassDayHour amb puntuació positiva.

## Schedule

Representa un horari, és a dir un conjunt de sessions amb hora, dia i aula definides.

L'horari conté exactament totes les sessions que es poden realitzar, a partir del pla d'estudis.

## ClassroomDayHour

Representa una possible aula, dia i hora en la que es pot realitzar una sessió.

A més te una puntuació que és positiva si compleix totes les restriccións de la sessió i quant més positiva més bé les cumpleix. I quant més negativa, més les viola.

> És una classe auxiliar

## Classroom

Representa una aula.

Quan introdueixes el nom el desglosa en edifici, planta i sala.

## Campus

Representa un campus, o sigui un conjunt d'aules. Es guarda en format JSON a [./data/campus/](data/campus/).

> Aquesta classe no apareix en el codi perquè s'obïa, ja que només es treballa amb un únic campus durant l'execució.

## RestrictionUnary

Representa una restricció sobre una sessió que per ser comprovada necessita només l'informació d'**una sessió**.

**\***: Restricció que **no** s'aplica per defecte a una sessió.

### FixSessionTime <sup>\*</sup>

Comprova si la sessió es realitza a certa hora i dia.

> És útil en el cas de voler fixar una sessió a una hora i dia concreta.

### FixSessionClassroom <sup>\*</sup>

Comprova si la sessió es realitza en una aula concreta.

> És útil en el cas de voler fixar una sessió a una aula concreta.

### AllowedTime

Comprova si la sessió es realitza en una hora permesa de l'horari lectiu.

### ClassroomCapacity

Comprova si la sessió es realitza en una aula amb la capacitat suficient i sobrin poques places.

### Material

Comprova si la sessió es realitza en una aula amb el material requerit pel ClassType de la sessió.

## RestrictionBinary

Representa una restricció sobre una sessió que per ser comprovada necessita la informació de **més d'una sessió**.

**\***: Restricció que **no** s'aplica per defecte a una sessió.

### LevelCompact

Comprova si la sessió es realitza en un dia i hora que no deixi forats dins d'un mateix nivell i grup.

### LevelOverlapping

Comprova si la sessió es realitza en un dia i hora que no se superposi amb una altra sessió del mateix nivell i grup.

### CoRequisit

Comprova si la sessió es realitza en un dia i hora que no se superposi amb una altra sessió que sigui correquisit o precorrequisit d'aquesta.

### SameTypeOverlapping  <sup>\*</sup>

Comprova si la sessió es realitza en un dia i hora que no se superposi amb una altra sessió del mateix tipus.

> És útil en el cas que tots els grups d'una assignatura s'imparteixen pel mateix professor.

### NearClassroom

Comprova si la sessió es realitza en una aula a prop de l'aula de la sessió que acaba a la mateixa hora que aquesta comença i és del mateix nivell i grup.

## RestrictionGlobal

Representa una restricció sobre una sessió que per ser comprovada necessita la informació de **totes les sessions**.

### WeekPayload

Comprova si la sessió es realitza en un dia amb una càrrega lectiva dins d'un interval respecte a les altres sessions del mateix nivell i grup.