package taller_de_costura;

public class Trabajador extends Thread{
    private String nombre;
    private String tipoDeTrabajador;
    private Cesto cesto;
    private int contadorJerseys=0;
    public Trabajador(String nombre, String tipoDeTrabajador,Cesto cesto){
        this.nombre=nombre;
        this.tipoDeTrabajador=tipoDeTrabajador;
        this.cesto=cesto;

    }
    private void trabajar() {
        try {
            if (tipoDeTrabajador.equals("Hace Mangas")) {
                Thread.sleep((long) ((Math.random() * 1500) + 50));
                cesto.hacerManga();
                System.out.println(" " + nombre+ " ha hecho una manga");
            }

            if (tipoDeTrabajador.equals("Hace Cuerpos")) {
                Thread.sleep((long) ((Math.random() * 3000) + 50));
                cesto.hacerCuerpo();
                System.out.println(" " + nombre+ " ha hecho un cuerpo");

            }
            if (tipoDeTrabajador.equals("Ensambla")) {
                Thread.sleep((long) ((Math.random() * 4500) + 50));
                cesto.ensamblar();
                System.out.println(" " + nombre+ " ha ensamblado un jersey");
                contadorJerseys++;
                System.out.println("jersey numero "+contadorJerseys);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        for(;;) {
            trabajar();
        }
    }
}
