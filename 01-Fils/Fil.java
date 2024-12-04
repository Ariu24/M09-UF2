public class Fil extends Thread {
    private String nom;

    public Fil(String nom) {
        this.nom = nom;
    }

    @Override
    public  void run() {
        int contador = 1;
        while (contador <= 9) {
            System.out.printf("%s %d\n",nom, contador);
            contador++;
        }
        System.out.println("Termina el fil " + nom);
    }
}
