import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main implements ActionListener {
    private JFrame mainFrame;
    private JTextField displayField;
    private JPanel mainPanel;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton clrButton, delButton, decButton, negButton, eqlButton;
    private JButton addButton, subButton, mulButton, divButton, modButton;
    private double num1, num2, result;
    private char operator;

    //main frame configurations
    private final int FRAME_WIDTH = 310;
    private final int FRAME_HEIGHT = 490;
    private final String FRAME_TITLE = "Calculator";

    public Main() {
        mainFrame = new JFrame(FRAME_TITLE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);

        //add all components to frame
        createDisplayField();
        createButtonGrid();

        mainFrame.setVisible(true);
    }

    private void createDisplayField() {
        displayField = new JTextField();
        displayField.setBounds(15, 20, 280, 50);
        displayField.setEditable(false);
        displayField.setBackground(Color.white);
        mainFrame.add(displayField);
    }

    private void createButtons() {
        numberButtons = new JButton[10];
        for(int i=0; i<10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFocusable(false);
        }

        functionButtons = new JButton[10];
        clrButton = new JButton("c");
        delButton = new JButton("B");
        decButton = new JButton(".");
        negButton = new JButton("(-)");
        eqlButton = new JButton("=");
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("×");
        divButton = new JButton("/");
        modButton = new JButton("%");

        functionButtons[0] = clrButton;
        functionButtons[1] = delButton;
        functionButtons[2] = decButton;
        functionButtons[3] = negButton;
        functionButtons[4] = eqlButton;
        functionButtons[5] = addButton;
        functionButtons[6] = subButton;
        functionButtons[7] = mulButton;
        functionButtons[8] = divButton;
        functionButtons[9] = modButton;

        for(int i=0; i<functionButtons.length; i++) {
            functionButtons[i].setFocusable(false);
            functionButtons[i].addActionListener(this);
        }


    }

    private void createButtonGrid() {
        mainPanel = new JPanel();
        mainPanel.setBounds(15 , 90 ,280, 350);
        mainPanel.setLayout(new GridLayout(5 , 4));
        addButtonsToGrid(mainPanel);
        mainFrame.add(mainPanel);


    }

    private void addButtonsToGrid(JPanel mainPanel) {
        createButtons();
        //first row
        mainPanel.add(clrButton);
        mainPanel.add(divButton);
        mainPanel.add(mulButton);
        mainPanel.add(delButton);

        //second row
        mainPanel.add(numberButtons[7]);
        mainPanel.add(numberButtons[8]);
        mainPanel.add(numberButtons[9]);
        mainPanel.add(subButton);

        //third row
        mainPanel.add(numberButtons[4]);
        mainPanel.add(numberButtons[5]);
        mainPanel.add(numberButtons[6]);
        mainPanel.add(addButton);

        //fourth row
        mainPanel.add(numberButtons[1]);
        mainPanel.add(numberButtons[2]);
        mainPanel.add(numberButtons[3]);
        mainPanel.add(negButton);

        //fifth row
        mainPanel.add(modButton);
        mainPanel.add(numberButtons[0]);
        mainPanel.add(decButton);
        mainPanel.add(eqlButton);
    }

    private void setOperator(char operator) {
        num1 = Double.parseDouble(displayField.getText());
        this.operator = operator;
        displayField.setText("");

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //number button functionality
        for(int i=0; i<10; i++) {
            if(actionEvent.getSource() == numberButtons[i]) {
                displayField.setText(displayField.getText().concat(String.valueOf(i)));
            }
        }

        //clear button functionality
        if(actionEvent.getSource() == clrButton) {
            displayField.setText("");
        }

        //delete button functionality
        if(actionEvent.getSource() == delButton) {
            String displayFieldText = displayField.getText();
            displayField.setText("");
            for(int i=0; i<displayFieldText.length()-1;i++) {
                displayField.setText(displayField.getText().concat(String.valueOf(displayFieldText.charAt(i))));
            }
        }

        //decimal button functionality
        if(actionEvent.getSource() == decButton) {
            displayField.setText(displayField.getText().concat("."));
        }

        if(actionEvent.getSource() == negButton) {
            double numberDisplayed = Double.parseDouble(displayField.getText());
            numberDisplayed *= -1;
            displayField.setText(String.valueOf(numberDisplayed));
        }


        //math buttons functionality
        if(actionEvent.getSource() == addButton) {
            setOperator('+');
        }

        if(actionEvent.getSource() == subButton) {
            setOperator('-');
        }

        if(actionEvent.getSource() == mulButton) {
            setOperator('*');
        }

        if(actionEvent.getSource() == divButton) {
            setOperator('/');
        }

        if(actionEvent.getSource() == modButton) {
            setOperator('%');
        }

        //equal button functionality
        if(actionEvent.getSource() == eqlButton) {
            num2 = Double.parseDouble(displayField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
                case '%':
                    result = num1 % num2;
                    break;
            }

            displayField.setText(String.valueOf(result));
            num1 = result;
        }


    }

    public static void main(String[] args) {
        Main calculator = new Main();
    }
}
