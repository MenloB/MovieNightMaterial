package nikola.bottomnavigationview.Model;

/**
 * Created by Nikola on 6/4/2017.
 */

public class Projekcija {
    public String vremeProjekcije;
    public int preostaloMesta;
    public String bioskop;

    public Projekcija() {
    }

    public Projekcija(String vremeProjekcije, int preostaloMesta, String bioskop) {
        this.vremeProjekcije = vremeProjekcije;
        this.preostaloMesta = preostaloMesta;
        this.bioskop = bioskop;
    }

    public String getVremeProjekcije() {
        return vremeProjekcije;
    }

    public void setVremeProjekcije(String vremeProjekcije) {
        this.vremeProjekcije = vremeProjekcije;
    }

    public int getPreostaloMesta() {
        return preostaloMesta;
    }

    public void setPreostaloMesta(int preostaloMesta) {
        this.preostaloMesta = preostaloMesta;
    }

    public String getBioskop() {
        return bioskop;
    }

    public void setBioskop(String bioskop) {
        this.bioskop = bioskop;
    }
}
