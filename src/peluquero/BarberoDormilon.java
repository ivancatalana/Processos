package peluquero;

public class BarberoDormilon extends  Thread{
    Peluqueria peluqueria;

    public BarberoDormilon(Peluqueria peluqueria){
        this.peluqueria=peluqueria;
    }


    @Override
    public void run() {

            trabajar();

    }

    private void trabajar() {

    }
    private void dormir(){

    }
}
