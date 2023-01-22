package taller_de_costura;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Taller {
    public static Lock lockA = new ReentrantLock();

    public static void main(String[] args) {

        Cesto cesto=new Cesto();

        Trabajador t1 = new Trabajador("Abel", "Hace Mangas",cesto);
        Trabajador t2 = new Trabajador("Belen", "Hace Cuerpos",cesto);
        Trabajador t3 = new Trabajador("Jordi", "Ensambla",cesto);

        t1.start();
        t2.start();
        t3.start();
    }
}
