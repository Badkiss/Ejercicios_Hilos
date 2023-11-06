import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio3 {
    public static  class Hilo extends Thread{
        File file;
        Hilo(File file){
            this.file=file;
        }
        @Override
        public void run() {
            String[] pat =("wc,"+file).split(",");
            ProcessBuilder processBuilder=new ProcessBuilder(pat);
            processBuilder.inheritIO();
            try {
                Process p= processBuilder.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        List<File> files=new ArrayList<>();

        files.add(new File("/home/agrueiror/empresa.sql"));
        files.add(new File("/home/agrueiror/pytooos.py"));

        List<Hilo> hilos=new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            hilos.add(new Hilo(files.get(i)));
        }
        for(Hilo hilo:hilos){
            hilo.start();
        }
        for(Hilo hilo:hilos){
            hilo.join();
        }
    }
}
