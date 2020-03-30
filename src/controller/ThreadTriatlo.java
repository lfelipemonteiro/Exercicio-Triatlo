package controller;
import java.util.concurrent.Semaphore;
public class ThreadTriatlo extends Thread {
 
    private static int coc = 0;
 
    // Ordena��o por matriz feita com o Pedro Fran�a
    private static int[][] pos = new int[25][2];
 
    private Semaphore chegar = new Semaphore(1);
    private Semaphore arma = new Semaphore(5);
 
    private int pnt = 0;
    private int Atleta;
 
    public ThreadTriatlo(int n) {
        this.Atleta = n;
    }
 
    public void run() {
        triatlo();
    }
 
    private void triatlo() {

        correr();
        try {
            arma.acquire();
            atirar();
        	}
        catch (Exception e) 
        	{
            e.printStackTrace();
        	}
        finally 
        	{
            arma.release();
        	}
        bicicleta();
 
        try {
            chegar.acquire();
            pontuacao();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            chegar.release();
        }
    }
 // Metodo para distancia
    private void correr() {
 
        System.out.println("O atleta " + Atleta + " come�ou a correr");
 
        int distanciaPercorrida = 0;

        while (distanciaPercorrida < 3000) {
            distanciaPercorrida += (int) ((Math.random() * 6) + 20);
            try {
                sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
 // Metodo para contar os pontos
    private void atirar() {
 
        int tiros = 0;
 
        System.out.println("O atleta " + Atleta + " come�ou a atirar");
 
        while (tiros < 3) {
            try {
                pnt += (int) (Math.random() * 11);
                sleep((int) ((Math.random() * 2501) + 500));
            } catch (Exception e) {
                e.printStackTrace();
            }
            tiros++;
        }
    }
    
 // metodo para a bicicleta
    private void bicicleta() {
 
        System.out.println("O atleta " + Atleta + " come�ou a pedalar");
        int distanciaPercorrida = 0;
        while (5000 > distanciaPercorrida) {
            distanciaPercorrida += (int) ((Math.random() * 11) + 30);
            try {
                sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
 // metodo para somar os pontos do tiro com a posi��o
    private void pontuacao() {
 
        int pontosTotais = 250;
		for (int cta = 0; cta < 25; cta++) {
            if (cta == coc) {
                pos[cta][0] = Atleta;
                pos[cta][1] = pontosTotais + pnt;
            }
            pontosTotais -= 10;
        }
        coc++;
        if (coc == 25) {
            organizar();
        }
 
    }
// Organizar a matriz para ordenar 
    private void organizar() {
 
        for (int i = 0; i < pos.length; i++) {
            for (int j = i + 1; j < pos.length; j++) {
                if (pos[i][1] < pos[j][1]) {
                    int[][] aux = pos;
                    pos[i][0] = pos[j][0];
                    pos[i][1] = pos[j][1];
                    pos[j][0] = aux[i][0];
                    pos[j][1] = aux[i][1];
                }
            }
        }
 
        for (int i = 0; i < pos.length; i++) {
 
            System.out.println("Jogador " + pos[i][0] + "\n PONTOS: " + pos[i][1]);
 
        }
 
    }
 
}
 