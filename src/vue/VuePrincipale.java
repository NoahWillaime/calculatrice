package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import modele.Calculatrice;

public class VuePrincipale implements Observer{

    public static final int PLUS = -100;
    public static final int MINUS = -200;
    public static final int EQUAL = -400;
    // ...
    private Calculatrice mod;
    private JFrame frame = new JFrame("Calculator");
    private JPanel[] panels = new JPanel[6];
    private JTextField textField = new JTextField();
    private JButton[] numberButtons = new JButton[10];
    private JButton subtractButton = new JButton("-");
    private JButton addButton = new JButton("+");
    private JButton equateButton = new JButton(" = ");

    public VuePrincipale(Calculatrice modele) {
        // ...
        this.mod = modele;
        modele.addObserver(this);
        buildFrame();
    }

    public void buildFrame() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = (JPanel) frame.getContentPane();

        // initialize panels
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
        }

        // initialize button 0-9
        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = new JButton(" " + i + " ");
            numberButtons[i].addActionListener(new DigitListener(this.mod, i));
        }

        // default layout = BorderLayout.CENTER
        textField.setColumns(20);
        textField.setText("0");
        textField.setHorizontalAlignment(JTextField.RIGHT);
        panels[0].add(textField);

        // layout = FlowLayout.LEFT
        panels[1].setLayout(new FlowLayout(FlowLayout.LEFT));
        panels[1].add(numberButtons[7]);
        panels[1].add(numberButtons[8]);
        panels[1].add(numberButtons[9]);
        addButton.addActionListener(new OperatorListener(this.mod, Calculatrice.PLUS));
        panels[1].add(addButton);

        // layout = FlowLayout.LEFT
        panels[2].setLayout(new FlowLayout(FlowLayout.LEFT));
        panels[2].add(numberButtons[4]);
        panels[2].add(numberButtons[5]);
        panels[2].add(numberButtons[6]);
        subtractButton.addActionListener(new OperatorListener(this.mod, Calculatrice.MINUS));
        panels[2].add(subtractButton);

        // layout = FlowLayout.LEFT
        panels[3].setLayout(new FlowLayout(FlowLayout.LEFT));
        panels[3].add(numberButtons[1]);
        panels[3].add(numberButtons[2]);
        panels[3].add(numberButtons[3]);
        panels[3].add(equateButton);
        equateButton.addActionListener(new OperatorListener(this.mod, Calculatrice.EQUAL));
        // layout = FlowLayout.LEFT
        panels[4].setLayout(new FlowLayout(FlowLayout.LEFT));
        panels[4].add(numberButtons[0]);

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        for (JPanel jPanel : panels) {
            contentPane.add(jPanel);
        }

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        textField.setText(((Calculatrice)o).getNumber()+"");
    }

    class DigitListener implements ActionListener {
        private int digit;
        private Calculatrice calc;

        public DigitListener(Calculatrice c, int digit) {
            this.digit = digit;
            this.calc = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            calc.setText(digit);
        }
    }

    class OperatorListener implements ActionListener {
        private int op;
        private Calculatrice calc;

        public OperatorListener(Calculatrice c, int op){
            this.op = op;
            this.calc = c;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            calc.setOperator(this.op);
        }
    }
}
