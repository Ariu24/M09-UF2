1. Per què s'atura l'execució al cap d'un temps?
L'execució del programa s'atura perquè el mètode wait() s'usa en dues condicions diferents per gestionar l'accés a places disponibles. 
Quan tots els assistents queden bloquejats (sense places o sense accions per cancel·lar), els fils poden quedar en espera indefinida si no es realitzen més accions. 
Això passa si la lògica de reserves i cancel·lacions no manté un equilibri entre places i operacions. Per evitar-ho, cal garantir que els assistents es puguin desbloquejar, ja sigui amb notificacions (notify()) o revisant les condicions d'espera.

2. Canvi de probabilitats: 70% (ferReserva) - 30% (cancel·lar) vs. 30% (ferReserva) - 70% (cancel·lar)
Cas 1: 70% (ferReserva) i 30% (cancel·lar)
El canvi de probabilitats fa que hi hagi una major probabilitat que els assistents facin una reserva en lloc de cancel·lar-la. 
Això farà que les places disponibles es redueixin més ràpidament, i el sistema arribarà més ràpidament a l'estat en què no hi ha places disponibles (després de diverses reserves), 
fent que els assistents quedin en espera fins que algú cancel·li una reserva.
codi:
if (rnd.nextInt(100) < 70) {  // 70% probabilitat de fer una reserva
    try {
        this.esdeveniment.ferReserva(this);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
} else {  // 30% probabilitat de cancel·lar
    try {
        this.esdeveniment.cancelaReserva(this);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

Cas 2: 30% (ferReserva) i 70% (cancel·lar)
En aquest cas, la probabilitat que els assistents cancel·lin una reserva és més alta que la de fer-ne una. 
Això farà que les places disponibles augmentin més ràpidament, ja que molts assistents cancel·len les seves reserves en lloc de fer-ne noves. 
Això permetrà que més assistents realitzin reserves, ja que sempre hi haurà més places disponibles per fer-ho.
codi:
if (rnd.nextInt(100) < 30) {  // 30% probabilitat de fer una reserva
    try {
        this.esdeveniment.ferReserva(this);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
} else {  // 70% probabilitat de cancel·lar
    try {
        this.esdeveniment.cancelaReserva(this);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

Sortida resultant per cada cas:
Cas 1: 70% (ferReserva) - 30% (cancel·lar)
Amb una probabilitat més alta per a les reserves, veurem més assistents intentant reservar places. Al principi, hi haurà diverses reserves fins que les places disponibles siguin molt limitades (potser fins a 0). Quan això passi, els assistents que intentin fer una reserva es quedaran bloquejats esperant que algú cancel·li una reserva per poder continuar.

Exemple de sortida:
Assistent-1 ha pogut fer una reserva. Places disponibles: 4
Assistent-2 ha pogut fer una reserva. Places disponibles: 3
Assistent-3 ha pogut fer una reserva. Places disponibles: 2
Assistent-4 ha pogut fer una reserva. Places disponibles: 1
Assistent-5 ha pogut fer una reserva. Places disponibles: 0
Assistent-6 no ha pogut fer una reserva. Places disponibles: 0
Assistent-7 no ha pogut fer una reserva. Places disponibles: 0
Assistents-Assistent-5 ha cancel·lat una reserva. Places disponibles: 1
Assistent-6 ha pogut fer una reserva. Places disponibles: 0


Cas 2: 30% (ferReserva) - 70% (cancel·lar)
Amb una probabilitat més alta per a les cancel·lacions, veurem més assistents intentant cancel·lar les seves reserves. Això farà que les places disponibles augmentin més ràpidament, facilitant que més assistents puguin fer una reserva.

Exemple de sortida:
Assistent-1 ha pogut fer una reserva. Places disponibles: 4
Assistent-2 no ha pogut fer una reserva. Places disponibles: 4
Assistent-3 no ha pogut fer una reserva. Places disponibles: 4
Assistent-4 no ha pogut fer una reserva. Places disponibles: 4
Assistents-Assistent-1 ha cancel·lat una reserva. Places disponibles: 5
Assistent-2 ha pogut fer una reserva. Places disponibles: 4
Assistent-3 ha pogut fer una reserva. Places disponibles: 3
Assistent-4 ha pogut fer una reserva. Places disponibles: 2


3. Perquè fa falta la llista d'assistents i no valdria només amb una variable sencera de reserves?
La raó per la qual es fa servir una llista d'assistents i no només una variable sencera de reserves és perquè, a més de la quantitat de places disponibles, el sistema necessita mantenir un seguiment de qui ha fet una reserva i qui la va cancel·lar. La llista d'assistents permet:

Rastrejar qui té places reservades: Necessitem saber quins assistents han fet una reserva perquè puguem comprovar qui està intentant cancel·lar una reserva.

Evitar cancel·lacions errònies: Sense la llista, seria impossible saber si un assistent té o no una reserva per cancel·lar. Una variable sencera de reserves només indicaria el nombre total de places ocupades, però no qui les ocupa.

Gestió precisa de la cancel·lació: Quan un assistent vol cancel·lar la seva reserva, cal comprovar que aquesta reserva existeix abans de permetre la cancel·lació. La llista permet fer aquest control de manera precisa.