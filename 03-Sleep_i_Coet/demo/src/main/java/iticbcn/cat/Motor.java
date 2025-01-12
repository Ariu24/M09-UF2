package iticbcn.cat;
public class Motor extends Thread {
    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;
    private boolean running = true;
    private boolean stopped = false;
    private boolean waiting = false;

    public synchronized void setPotencia(int p) {
        this.potenciaObjectiu = p;
        this.waiting = false;  // engegar el motor quan li arriba una potencia nova introduïda per l'usuari
        if (stopped && p > 0) {
            stopped = false;
            running = true;
            Thread newThread = new Thread(this);
            newThread.start();
        }
    }

    @Override
    public void run() {
        while (running) {
            if (!waiting) {  //només es fara cuan el motor no estigui esperant
                if (potenciaActual < potenciaObjectiu) {
                    potenciaActual++;
                    if (potenciaActual < potenciaObjectiu) {
                        System.out.println("Motor " + Thread.currentThread().getId() % 4 + 
                            ": Incre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    } else {
                        System.out.println("Motor " + Thread.currentThread().getId() % 4 + 
                            ": FerRes Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                        waiting = true;  //esperar a que l'usuari introdueixi una nova potencia
                    }
                } else if (potenciaActual > potenciaObjectiu) {
                    potenciaActual--;
                    if (potenciaActual > potenciaObjectiu) {
                        System.out.println("Motor " + Thread.currentThread().getId() % 4 + 
                            ": Decre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    } else {
                        System.out.println("Motor " + Thread.currentThread().getId() % 4 + 
                            ": FerRes Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                        if (potenciaObjectiu == 0) {
                            running = false;
                            stopped = true;
                            break;
                        }
                        waiting = true;  // Esperar a l'usuari nova potencia
                    }
                } else {
                    System.out.println("Motor " + Thread.currentThread().getId() % 4 + 
                        ": FerRes Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    if (potenciaObjectiu == 0) {
                        running = false;
                        stopped = true;
                        break;
                    }
                    waiting = true; 
                }
            }

            try {
                Thread.sleep((long) (Math.random() * 1000 + 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
