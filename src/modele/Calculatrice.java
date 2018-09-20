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
    private String text;

    public Calculatrice(){
        this.op1=0;
        this.op2=0;
        this.op_set = false;
        this.operator= 0;
        this.text ="";
    }

    public int calcul(){
        if (operator == EQUAL)
            op1 = calcul();
        else if (operator == PLUS) {
            op1 = Addition.calcul(op1, op2);
        }
        else if (operator == MINUS) {
            op1 = Soustraction.calcul(op1, op2);
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
        if (op_set){
            setOp2(Integer.parseInt(getText()));
        } else {
            op_set = true;
            setOp1(Integer.parseInt(getText()));
        }
        setText("");
        if (this.operator != 0) {
            calcul();
            setText(this.op1+"");
        }
        if (operator != Calculatrice.EQUAL)
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
        c.setOperator(Calculatrice.PLUS);
        c.setOp2(18);
        c.setOperator(Calculatrice.MINUS);
        c.setOp2(10);
        c.setOperator(Calculatrice.EQUAL);
        System.out.println(c.getResult());
    }
}
