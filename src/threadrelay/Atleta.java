package threadrelay;

import java.util.ArrayList;
import java.util.List;

public class Atleta extends Thread implements Subject {
    public int id;
    public int conteggio = 0;
    public int velocita;
    public boolean inEsecuzione = true;
    public boolean inPausa = false;
    private Atleta prossimoAtleta;
    private List<Observer> observers = new ArrayList<>();

    public Atleta(int id, int velocita) {
        this.id = id;
        this.velocita = velocita;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(conteggio, id);
        }
    }

    public void setProssimoAtleta(Atleta prossimo) {
        this.prossimoAtleta = prossimo;
    }

    @Override
    public void run() {
        while (conteggio < 100 && inEsecuzione) {
            if (!inPausa) {
                conteggio++;
                notifyObservers();

                if (conteggio == 90 && prossimoAtleta != null) {
                    prossimoAtleta.start();
                }

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