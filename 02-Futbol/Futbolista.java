import java.util.Random;

public class Futbolista extends Thread{
    // Constants
    public static final int NUM_JUGADORS = 11;
    public static final int NUM_TIRADES = 20;
    public static final float PROBABILITAT = 0.5f;

    private int ngols=0;
    private int ntirades=0;

    public Futbolista(String nom){
        super(nom);
    }
    public int getGols() {
        return ngols;
    }
    @Override
    public void run() {
        Random rand = new Random();
        try {
            for (int i = 1; i <= NUM_TIRADES; i++) {
                ntirades++;
                if (rand.nextFloat() <= PROBABILITAT) {
                    ngols++;
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " va ser interromput durant l'entrenament.");
        }
    }
    public static void main(String[] args) {
        String[] nomsJugadors = {"Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", "Lewan", "Belli", "Arnau","Aspas", "Messi", "MBapé"};
        Futbolista[] jugadors = new Futbolista[NUM_JUGADORS];
        System.out.println("Inici dels xuts -----------");
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(nomsJugadors[i]);
        }
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i].start();
        }
        try {
            for (Futbolista jugador : jugadors) {
                jugador.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Error");
        }
        System.out.println("Fi dels xuts --------------");
        System.out.println("------ Estadístiques ------");
        for (Futbolista jugador : jugadors) {
            System.out.println(jugador.getName() + " -> " + jugador.getGols() + " gols");
        }
    }
}