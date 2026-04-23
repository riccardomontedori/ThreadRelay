/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

/**
 *
 * @author montedori.riccardo
 */
public class GestoreGara {
    public Atleta[] atleti = new Atleta[4];

    public void preparaGara(int msVelocita) {
        for (int i = 0; i < 4; i++) {
            atleti[i] = new Atleta(i + 1, msVelocita, this);
        }
        
        atleti[0].setProssimoAtleta(atleti[1]);
        atleti[1].setProssimoAtleta(atleti[2]);
        atleti[2].setProssimoAtleta(atleti[3]);
    }

    public void avvia() {
        if (atleti[0] != null) atleti[0].start();
    }

    public void pausa(boolean statoPausa) {
        for (Atleta a : atleti) {
            if (a != null) a.inPausa = statoPausa;
        }
    }

    public void stop() {
        for (Atleta a : atleti) {
            if (a != null) a.inEsecuzione = false;
        }
    }
}