package threadrelay;

public class GestoreGara {
    public Atleta[] atleti = new Atleta[4];
    private Observer interfacciaGrafica;

    public GestoreGara(Observer observer) {
        this.interfacciaGrafica = observer;
    }

    public void preparaGara(int msVelocita) {
        for (int i = 0; i < 4; i++) {
            atleti[i] = new Atleta(i + 1, msVelocita);
            atleti[i].addObserver(interfacciaGrafica);
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
            if (a != null) {
                a.inEsecuzione = false;
                a.interrupt();
            }
        }
    }
}