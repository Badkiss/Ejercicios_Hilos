import java.util.ArrayList;

public class Ejercicio4 {
    public static class HiloT extends Thread{
        int casilla;
        char[][] pista;
        HiloT(char[][] pista){
            this.pista=pista;
            casilla=1;
        }
        public  void AvanceRapido(){
            if (casilla+3>=70)
                casilla=70;
            casilla+=3;
        }
        public  void AvanceLento(){
            casilla+=1;
        }
        public  void Resbalo(){
            if(casilla!=1){
                if (casilla-6<=1){
                    casilla=1;
                }else {
                    casilla-=6;
                }
            }
        }

        @Override
        public void run() {
            int num;
            int intentos=0;
            ArrayList<Character> carrera=new ArrayList<>();
                while (casilla<70){
                   num =(int)(Math.random()*100+1);
                   if (num<=50){
                       AvanceRapido();
                   } else if (num>=51 && num<=70) {
                       Resbalo();
                   } else if (num>=71 && num<=100) {
                       AvanceLento();
                   }
                    synchronized (pista){
                        for (int i = 0; i < 70; i++){
                            if (pista[0][i]=='T'){
                                pista[0][i]='-';
                            }
                            if (i==casilla-1){
                                pista[0][i]='T';
                            }
                        }
                        for (int i = 0; i < 70; i++) {
                            System.out.print(pista[0][i]);
                        }
                        System.out.println();
                        if (pista[1][69]=='L'){
                            break;
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

        }
    }

    public static class HiloL extends Thread{
        int casilla;
        char[][] pista;
        HiloL(char[][] pista){
            this.pista=pista;
            casilla=1;
        }
        public  void Duerme(){

        }
        public  void GranSalto(){
            if (casilla+9>=70)
                casilla=70;
            casilla+=9;
        }
        public  void Resvalon_grande(){
            if(casilla!=1){
                if (casilla-12<=1){
                    casilla=1;
                }else {
                    casilla-=12;
                }
            }
        }
        public  void PequeñoSalto(){
            casilla+=1;
        }
        public  void ResbalonPequeño(){
            if(casilla!=1){
                if (casilla-2<=1){
                    casilla=1;
                }else {
                    casilla-=2;
                }
            }
        }
        @Override
        public void run() {
            int num=1;
            int intentos=0;
            while (casilla<70){
                num =(int)(Math.random()*100+1);
                if (num<=20){
                    Duerme();
                } else if (num>=21 && num<=40) {
                    GranSalto();
                } else if (num>=41 && num<=50) {
                    Resvalon_grande();
                } else if (num>=51 && num<=80) {
                    PequeñoSalto();
                } else if (num<=81 && num<=100) {
                    ResbalonPequeño();
                }
                synchronized (pista){
                    for (int i = 0; i < 70; i++){
                        if (pista[1][i]=='L'){
                            pista[1][i]='-';
                        }
                        if (i==casilla-1){
                            pista[1][i]='L';
                        }
                    }
                    for (int i = 0; i < 70; i++) {
                        System.out.print(pista[1][i]);
                    }
                    System.out.println();
                    if (pista[0][69]=='T'){
                        break;
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        char[][] pista=new char[2][70];
        for (int i = 0; i < 70; i++) {
            if (i==0){
                pista[0][i]='T';
            }else{
                pista[0][i]='-';
            }

            if (i==0){
                pista[1][i]='L';
            }else{
                pista[1][i]='-';
            }


        }
        HiloL liebre=(new HiloL(pista));
        HiloT tortuga=(new HiloT(pista));

        tortuga.start();
        liebre.start();

        liebre.join();
        tortuga.join();

    }
}
