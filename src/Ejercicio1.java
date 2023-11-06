import java.util.ArrayList;
import java.util.List;

public class Ejercicio1 {
    public static class Hilo extends Thread {
        int id;
        Hilo(int id){
            this.id=id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(id*1000);
                System.out.println("Hilo"+id +" segundos "+id);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public static void main(String[] args) {
            List<Hilo> hilos=new ArrayList<>();
            List<Thread>hilosR=new ArrayList<>();

//            for (int i = 1; i <=6 ; i++) {
//                hilos.add(new Hilo(i));
//            }
//            for (Hilo hilo : hilos){
//                hilo.start();
//            }
            for (int i = 1; i <=6 ; i++) {
                hilosR.add(new Thread(new HiloR(i)));
            }
            for (Thread thread : hilosR){
                thread.start();
            }
        }
    }
  public static class HiloR implements Runnable{
        int id;
        HiloR(int id){
            this.id=id;
        }
      @Override
      public void run() {
          try {
              Thread.sleep(id*1000);
              System.out.println("HiloR "+id +" segundos "+id);
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
      }
  }
}
