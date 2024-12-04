public class Principal {
    public static void main(String[] args) {
        // Crear dos fils, Juan i Pepe
        Fil filJuan = new Fil("Juan");
        Fil filPepe = new Fil("Pepe");

        filPepe.setPriority(1);
        filJuan.setPriority(10);
        
        // Iniciar els fils
        filJuan.start();
        filPepe.start();
        
        
        System.out.println("Termina thread main");
    }
}
