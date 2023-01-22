package taller_de_costura;

public class Cesto {
    private int numMaxMangas = 10;
    private int numMaxCuerpos = 5;
    private int contadorMangas;
    private int contadorCuerpos;
    private int contadorJerseys;
    public Cesto(){

    }
    public synchronized void hacerManga() {
        try {

            while (contadorMangas >= numMaxMangas) wait();
            contadorMangas++;
            System.out.println("Hay " + contadorMangas +" Mangas");

            notifyAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
}
    public synchronized void hacerCuerpo() {
        try {

            while (contadorCuerpos >= numMaxCuerpos) wait();
            contadorCuerpos++;
            System.out.println("Hay " + contadorCuerpos +" Cuerpos");
            notifyAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
}
public synchronized void ensamblar() {
        try {

        while (contadorMangas <= 1||contadorCuerpos <1) wait();
        contadorMangas-=2;
        contadorCuerpos--;
        contadorJerseys++;
        System.out.println("Hay "+contadorJerseys+" Jerseys hechos");
        notifyAll();

        } catch (InterruptedException e) {
        e.printStackTrace();
        }
    }
}
