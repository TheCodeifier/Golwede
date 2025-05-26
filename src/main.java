import java.util.ArrayList;
import java.util.List;

interface Leverstrategie {
    void lever();
}

interface Observer {
    void update(Bestelling bestelling);
}

abstract class Bestelling {
    public final void verwerk() {
        selecteerGerecht();
        bereid();
        lever();
    }

    protected abstract void selecteerGerecht();
    protected abstract void bereid();
    protected abstract void lever();
}

class PizzaBestelling extends Bestelling {
    private Leverstrategie leverstrategie;

    public PizzaBestelling(Leverstrategie leverstrategie) {
        this.leverstrategie = leverstrategie;
    }

    @Override
    protected void selecteerGerecht() {
        System.out.println("Pizza geselecteerd.");
    }

    @Override
    protected void bereid() {
        System.out.println("Pizza wordt bereid...");
    }

    @Override
    protected void lever() {
        leverstrategie.lever();
    }
}

class PastaBestelling extends Bestelling {
    private Leverstrategie leverstrategie;


    public PastaBestelling(Leverstrategie leverstrategie) {
        this.leverstrategie = leverstrategie;
    }

    @Override
    protected void selecteerGerecht() {
        System.out.println("Pasta geselecteerd.");
    }

    @Override
    protected void bereid() {
        System.out.println("Pasta wordt bereid...");
    }

    @Override
    protected void lever() {
        leverstrategie.lever();
    }
}

class SaladeBestelling extends Bestelling {
    private Leverstrategie leverstrategie;

    public SaladeBestelling(Leverstrategie leverstrategie) {
        this.leverstrategie = leverstrategie;
    }

    @Override
    protected void selecteerGerecht() {
        System.out.println("Salade geselecteerd.");
    }

    @Override
    protected void bereid() {
        System.out.println("Salade wordt bereid...");
    }

    @Override
    protected void lever() {
        leverstrategie.lever();
    }
}

class FietsLevering implements Leverstrategie {
    public void lever() {
        System.out.println("Bezorgd met de fiets.");
    }
}

class ScooterLevering implements Leverstrategie {
    public void lever() {
        System.out.println("Bezorgd met de scooter.");
    }
}

class AfhalenLevering implements Leverstrategie {
    public void lever() {
        System.out.println("Klant komt afhalen.");
    }
}

class Keuken implements Observer {
    public void update(Bestelling bestelling) {
        System.out.println("Keuken: Nieuwe bestelling ontvangen.");
    }
}

class Balie implements Observer {
    public void update(Bestelling bestelling) {
        System.out.println("Balie: Nieuwe bestelling ontvangen.");
    }
}

class BestelSysteem {
    private List<Observer> observers = new ArrayList<>();

    public void voegObserverToe(Observer o) {
        observers.add(o);
    }

    public void verwijderObserver(Observer o) {
        observers.remove(o);
    }

    public void plaatsBestelling(Bestelling bestelling) {
        System.out.println("Nieuwe bestelling geplaatst...");
        notifyObservers(bestelling);
        bestelling.verwerk();
    }

    private void notifyObservers(Bestelling bestelling) {
        for (Observer o : observers) {
            o.update(bestelling);
        }
    }
}

class Main {
    public static void main(String[] args) {
        Observer keuken = new Keuken();
        Observer balie = new Balie();

        BestelSysteem systeem = new BestelSysteem();
        systeem.voegObserverToe(keuken);
        systeem.voegObserverToe(balie);

        Leverstrategie strategie = new FietsLevering(); 
        Bestelling bestelling = new PizzaBestelling(strategie);
        systeem.plaatsBestelling(bestelling);
    }
}
