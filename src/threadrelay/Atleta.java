/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

/**
 *
 * @author montedori.riccardo
 */
public class Atleta extends Thread {
    public int id;
    public int conteggio = 0;
    public int velocita;
    public boolean inEsecuzione = true;
    public boolean inPausa = false;
    private Atleta prossimoAtleta;
    private GestoreGara gestore;

    public Atleta(int id, int velocita, GestoreGara gestore) {
        this.id = id;
        this.velocita = velocita;
        this.gestore = gestore;
    }

    public void setProssimoAtleta(Atleta prossimo) {
        this.prossimoAtleta = prossimo;
    }

    @Override
    public void run() {
        while (conteggio < 100 && inEsecuzione) {
            if (!inPausa) {
                if (conteggio == 90 && prossimoAtleta != null) {
                    prossimoAtleta.start();
                }

                conteggio++;
                
                try {
                    Thread.sleep(velocita);
                } catch (InterruptedException e) {
                    break;
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
        }
    }
}
