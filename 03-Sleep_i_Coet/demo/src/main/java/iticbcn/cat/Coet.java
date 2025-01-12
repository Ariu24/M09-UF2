package iticbcn.cat;

import java.util.Scanner;


public class Coet {
    private Motor[] motors;

    public Coet() {
        motors = new Motor[4];
        for (int i = 0; i < 4; i++) {
            motors[i] = new Motor();
        }
    }

    public void passaAPotencia(int p) {
        if (p < 0 || p > 10) {
            System.out.println("Error: La potència ha d'estar entre 0 i 10");
            return;
        }
        System.out.println("Passant a potència " + p);
        for (Motor motor : motors) {
            motor.setPotencia(p);
        }
    }

    public void arranca() {
        for (Motor motor : motors) {
            motor.start();
        }
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();
        
        Scanner scanner = new Scanner(System.in);
        int potencia;
        
        do {
            potencia = scanner.nextInt();
            coet.passaAPotencia(potencia);
        } while (potencia != 0);
        
        scanner.close();
    }
}