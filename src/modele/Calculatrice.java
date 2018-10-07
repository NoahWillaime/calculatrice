package modele;

import modele.operateur.Addition;
import modele.operateur.Soustraction;

import java.util.Observable;

public class Calculatrice extends Observable {
    public static final int PLUS = -300;
    public static final int MINUS = -200;
    public static final int EQUAL = -100;

    private int op1;
    private int op2;
    private int operator;
    private boolean op_set;
    private boolean clear;
    private String text;

    public Calculatrice(){
        this.op1=0;
        this.op2=0;
        this.op_set = false;
        this.clear = false;
        this.operator= 0;
        this.text ="";
    }

    public int calcul(){
        if (operator == EQUAL) {
            op1 = calcul();
            op_set = false;
        }
        else {
            if (operator == PLUS) {
                op1 = Addition.calcul(op1, op2);
            } else if (operator == MINUS) {
                op1 = Soustraction.calcul(op1, op2);
            }
        }
        return op1;
    }

    public void setOp1(int op){
        this.op1 = op;
    }

    public void setOp2(int op){
        this.op2 = op;
    }

    public void setOperator(int operator){
        clear = true;
        if (op_set) {
            setOp2(getNumber());
            calcul();
            setText(this.op1);
        } else {
            setOp1(getNumber());
            op_set = true;
            setText(0);
        }
        if (operator != Calculatrice.EQUAL)
            this.operator = operator;
        clear = true;
    }

    public int getResult(){
        return op1;
    }

    public int getNumber() {
        return Integer.parseInt(text);
    }

    public void setText(int number){
        if (clear) {
            this.text = number + "";
            clear = false;
        } else {
            this.text += number;
        }
        setChanged();
        notifyObservers();
    }

    public static void main(String args[]){
        Calculatrice c = new Calculatrice();
        c.setOp1(32);
        c.setOperator(Calculatrice.PLUS);
        c.setOp2(18);
        c.setOperator(Calculatrice.MINUS);
        c.setOp2(10);
        c.setOperator(Calculatrice.EQUAL);
        System.out.println(c.getResult());
    }
}
