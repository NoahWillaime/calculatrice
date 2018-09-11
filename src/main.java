import modele.Calculatrice;
import vue.VuePrincipale;

public class main {
    public static void main(String args[]){
        Calculatrice modele = new Calculatrice();
        new VuePrincipale(modele);
    }
}
