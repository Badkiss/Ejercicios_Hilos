import java.util.ArrayList;
import java.util.List;

public class Ejercicio2 {

    public static class Hilo extends Thread{
        Cont cont;

        Hilo(Cont cont){
            this.cont=cont;
        }


        @Override
        public void run() {
            synchronized (cont) {
                for (int i = 1; i <= 5000; i++) {
                    cont.Incrementa();
                }
                System.out.println(cont);
            }

        }

        }
    public static class Cont{
        int cont;
        Cont(){
            cont=0;
        }
        public synchronized void  Incrementa(){
            cont+=1;
        }

        @Override
        public String toString() {
            return ""+cont;
        }
    }

    public static void main(String[] args) throws InterruptedException {
Cont cont=new Cont();
        List<Hilo> hilos=new ArrayList<>();
        for (int i = 0; i <2 ; i++) {
            hilos.add(new Hilo(cont))
;        }
        for (Hilo hilo:hilos){
            hilo.start();
        }
        for (Hilo hilo:hilos){
            hilo.join();
        }

    }
}
