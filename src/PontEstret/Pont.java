package PontEstret;

public class Pont {
    public enum estatDelPont {
        LLIURE, OCUPATDRETA, OCUPATESQUERRA, PLE
    }
    private  Enum<estatDelPont> estat;
    private int contador;
    private int MAXC;



    public Pont (int maxc) {

        estat = estatDelPont.LLIURE;
        contador=0;
        MAXC= maxc;
    }

    public synchronized Enum<estatDelPont> getEstat() {
        return estat;
    }
    public synchronized void entrar(String p) {
                Enum<estatDelPont> direccioDelCotxe=null;
                if(p.equals("esquerra"))direccioDelCotxe=estatDelPont.OCUPATESQUERRA;
                else if(p.equals("dreta"))direccioDelCotxe=estatDelPont.OCUPATDRETA;
        try {

            while ((estat != estatDelPont.LLIURE && direccioDelCotxe!=estat) || estat==estatDelPont.PLE ) wait();
            if(estat == estatDelPont.LLIURE) estat = direccioDelCotxe ;
            // Ho Posem Ple Per que no passin mes cotxes fins que quedi lliure
            if(contador++ == MAXC) estat = estatDelPont.PLE;
           // System.out.println("Entra un Vehicle amb sentit de la marxa "+p.toUpperCase()+ " i hi ha aquest numero de vehicles al pont: "+contador);
            System.out.println("Hi han " + contador + " cotxes en sentit " + p);
            notifyAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void sortir() {
        contador--;
        System.out.println("surt el vehicle numero " + contador+ " i el pont encara esta "+ estat);
        //si queda lliure actualitzem l'estat a LLiure
        if(contador == 0) estat = estatDelPont.LLIURE;
        notifyAll();
    }

    public static void main(String[] args) {
        Pont pont=new Pont(2);
        Vehicle vehiclesEsquerra[]= new Vehicle[10];
        Vehicle vehiclesDreta[]= new Vehicle[10];
        for (int i = 0; i < 10; i++) {
            vehiclesEsquerra[i]=new Vehicle("cotxe-"+i,"esquerra",pont);
            vehiclesDreta[i]=new Vehicle("cotxe-"+i,"dreta",pont);
        }
        for (int i = 0; i < 10; i++) {
            vehiclesEsquerra[i].start();
            vehiclesDreta[i].start();
        }
    }
}


