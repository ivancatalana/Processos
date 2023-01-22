package PontEstret;

public class Vehicle extends  Thread{
    private String direccioMarxa;

    private Pont pont;


    public Vehicle (String id, String direccioMarxa, Pont pont) {
        super(id);
        this.direccioMarxa = direccioMarxa;
        this.pont= pont;
    }
    private void surtDeCasa() {
       ///*
        try {

            Thread.sleep((long) ((Math.random() * 8000) + 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //*/
    }
    private void creuarElPont() {
        System.out.println(getName()+ " está al pont");
        ///*
         try {
            Thread.sleep((long) ((Math.random() * 2000) + 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //*
    }
    public void run() {
        for(;;) {
            surtDeCasa();
            pont.entrar(direccioMarxa);
            creuarElPont();
            pont.sortir();
        }
    }
}
