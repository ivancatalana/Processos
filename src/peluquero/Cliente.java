package peluquero;

import java.util.concurrent.ThreadLocalRandom;

public class Cliente extends Thread{
    public String getNombre() {
        return nombre;
    }

    private String nombre;
    private Peluqueria peluqueria;
    public Cliente(String nombre,Peluqueria p){

        this.nombre=nombre;
        this.peluqueria=p;

    }
    public void irseAcortarElPelo(){
        System.out.println("Persona: " + nombre + " va hacia la peluqueria");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(500, 10000));
            peluqueria.clienteLLega(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
            irseAcortarElPelo();

    }
}
