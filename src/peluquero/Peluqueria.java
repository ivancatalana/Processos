package peluquero;

import java.util.ArrayList;

public class Peluqueria {
    public enum estadoPeluquero {
        Durmiendo, Cortando, Libre
    }

    BarberoDormilon barberoDormilon;


    private Enum<estadoPeluquero> estat;

    private ArrayList<Cliente> clientesSentados;

    public Peluqueria() {
        estat = estadoPeluquero.Durmiendo;
        barberoDormilon = new BarberoDormilon(this);
        clientesSentados = new ArrayList<>();
    }

    public synchronized Enum<estadoPeluquero> getEstat() {
        return estat;
    }

    public void clienteLLega(Cliente c) throws InterruptedException {
        if (estat == estadoPeluquero.Durmiendo) {
            System.out.println("el barbero se despierta");
            estat = estadoPeluquero.Libre;
            //procesoPeluquero(barberoDormilon,estat);
        }
        if (clientesSentados.size() < 4) {
            clientesSentados.add(c);
            System.out.println("El cliente: " + c.getName() + " ocupa una silla . total clientes sentados: " + clientesSentados.size());
        } else if (clientesSentados.size() == 4) {
            System.out.println("El cliente " + c.getName() + " se va a su casa TRISTE");
        }
        cortedepelo(c);
    }

    public synchronized void cortedepelo(Cliente c) throws InterruptedException {

        while (estat != estadoPeluquero.Libre && clientesSentados.size() < 4) c.wait();
        estat = estadoPeluquero.Cortando;
        System.out.println("el barbero corta el pelo a un cliente");
        try {
            Thread.sleep((long) Math.random() * 5000 + 8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("el cliente " + clientesSentados.get(0).getId() + " sale de la peluqueria con el pelo CORTADO");
        clientesSentados.remove(0);
        estadoFinalPeluquero();
        notifyAll();
       // procesoPeluquero(barberoDormilon,estat);

    }

    public void estadoFinalPeluquero() {

        if (clientesSentados.isEmpty()) {
            estat = estadoPeluquero.Durmiendo;
            System.out.println("el batbero se  acuesta");

        } else estat = estadoPeluquero.Libre;
    }



    //Caldría synchronized per un sol proces?
    public void procesoPeluquero(BarberoDormilon b,Enum<estadoPeluquero> e ) throws InterruptedException {
        synchronized (barberoDormilon) {

            while (e == estadoPeluquero.Durmiendo) {
                b.wait();
                System.out.println("el batbero se  acuesta");
            }
            if (clientesSentados.size() == 0) {
                b.notify();
                System.out.println("el barbero se despierta");
            }
        }

    }




        public static void main(String[] args) {
        Peluqueria peluqueria = new Peluqueria();
        int contadorClientes = 0;
        while (true) {
            contadorClientes++;
            Cliente cliente = new Cliente("Cliente num" + contadorClientes, peluqueria);
            cliente.start();
            try {
                Thread.sleep((long) ((Math.random() * 0 + 2000)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
