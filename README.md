# M09-UF2
Comportament 1: Execució Seqüencial de Fils
En aquest comportament, definim el mètode run() sobrecarregat per mostrar l'execució de cada fil. Cada fil imprimeix una sèrie de números del 1 al 9, seguit d'un missatge indicant que ha acabat.
El mètode run() s'implementa de la següent manera:
@Override
public void run() {
    int contador = 1;
    while (contador <= 9) {
        System.out.printf("%s %d\n",nom, contador);
        contador++;
    }
    System.out.println("Termina el fil " + nom);
}

Comportament 2: Assignació de Prioritats als Fils
En aquest comportament, es canvia la prioritat d'execució dels fils. Assignem una prioritat a cada fil per determinar quin ha d'executar-se primer. Això es fa utilitzant el mètode setPriority(int priority) de la classe Thread.
@Override
    public  void run() {
        int contador = 1;
        while (contador <= 9) {
            System.out.printf("%s %d\n",nom, contador);
            contador++;
        }
        System.out.println("Termina el fil " + nom);
    }

Comportament 3: Sincronització de Fils amb Thread.sleep()
Aquest comportament introdueix la sincronització entre fils mitjançant el mètode sleep(). A través de Thread.sleep(100), fem que cada fil es "deturi" després de cada iteració, permetent que l'altre fil tingui oportunitat d'executar-se. Això provoca que els fils s'executin alternant-se en lloc de ser executats de manera seqüencial.
@Override
public  void run() {
    int contador = 1;
    while (contador <= 9) {
        System.out.printf("%s %d\n",nom, contador);
        contador++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    System.out.println("Termina el fil " + nom);
}