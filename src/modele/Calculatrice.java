package modele;

import modele.operateur.Addition;
import modele.operateur.Soustraction;

import java.util.Observable;

public class Calculatrice extends Observable {
    private int op1;
    private int op2;
    private String operator;
    private String text;

    public Calculatrice(){
        this.op1=0;
        this.op2=0;
        this.operator="";
        this.text ="";
    }

    private int calcul(){
        if (operator.equals("+"))
            op1 = Addition.calcul(op1, op2);
        else if (operator.equals("-"))
            op1 = Soustraction.calcul(op1, op2);
        return op1;
    }

    public void setOp1(int op){
        this.op1 = op;
    }

    public void setOp2(int op){
        this.op2 = op;
    }

    public void setOperator(String operator){
        calcul();
        this.operator = operator;
    }

    public int getResult(){
        return op1;
    }

    public String getText() {
        return text;
    }

    public void setText(String t){
        this.text = t;
        setChanged();
        notifyObservers();
    }

    public static void main(String args[]){
        Calculatrice c = new Calculatrice();
        c.setOp1(32);
        c.setOperator("+");
        c.setOp2(18);
        c.setOperator("-");
        c.setOp2(10);
        c.setOperator("+");
        System.out.println(c.getResult());
    }
}
