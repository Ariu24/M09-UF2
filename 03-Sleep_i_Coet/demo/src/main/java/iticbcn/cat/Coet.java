package iticbcn.cat;

import java.util.ArrayList;
import java.util.Scanner;

public class Coet {
    public ArrayList<Motor> motors;

    public Coet() {
        motors = new ArrayList<Motor>();
        motors.add(new Motor("Motor 0"));
        motors.add(new Motor("Motor 1"));
        motors.add(new Motor("Motor 2"));
        motors.add(new Motor("Motor 3"));
        
        for (Motor motor : motors) {
            motor.start();
        }
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();
    }

    public void arranca() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int potencia = scanner.nextInt();
            if (potencia == 0 && potencia>=10) {
                System.out.println("Potència no vàlida");
                passaAPotencia(potencia);
                break; 
            }
            passaAPotencia(potencia);
        }
        scanner.close();
    }

    public void passaAPotencia(int potencia) {
        for (Motor motor : motors) {
            motor.setiPotenciaObjectiu(potencia);
        }
    }
}
