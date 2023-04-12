import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class UI extends JFrame {
    private String displayText;
    private TextField display;
    private BorderLayout layout;
    private int firstNumber;
    private int secondNumber;
    private State state;
    private Operation operation;

    public UI()
    {
        displayText = "0";
        layout = new BorderLayout();
        state = State.FirstNumber;
        firstNumber = 0;
        secondNumber = 0;

        this.setSize(300, 200);
        this.setVisible(true);

        this.setLayout(layout);
        addButtons();

        display = new TextField(displayText);
        display.setEditable(false);
        getContentPane().add(display, BorderLayout.NORTH);
    }

    private void addButtons() {
        JPanel buttonContainer = new JPanel(new GridLayout(3, 4));

        for (int i = 0; i < 10; i++) {
            Button button = new Button(String.valueOf(i));
            button.addActionListener(this::buttonPressed);
            buttonContainer.add(button);
        }

        Button plus = new Button("+");
        plus.addActionListener(this::buttonPressed);
        buttonContainer.add(plus);

        Button minus = new Button("-");
        minus.addActionListener(this::buttonPressed);
        buttonContainer.add(minus);

        Button equals = new Button("=");
        equals.addActionListener(this::buttonPressed);
        getContentPane().add(equals, BorderLayout.SOUTH);

        getContentPane().add(buttonContainer, BorderLayout.CENTER);
    }

    private void buttonPressed(ActionEvent e) {
        Button button = (Button)e.getSource();
        char action = button.getLabel().toCharArray()[0];

        if (isCharNumber(action)) {
            if (state == State.Result) {
                displayText = "0";
                state = State.FirstNumber;
            }

            displayText = Objects.equals(displayText, "0") ? String.valueOf(action) : (displayText += action);
            updateDisplay();
            return;
        }

        switch (action) {
            case '-':
                if (state == State.FirstNumber) {
                    operation = Operation.Minus;
                    firstNumber = getNumberFromDisplay();
                    state = State.SecondNumber;
                    clearDisplay();
                    break;
                }

                if (state == State.Result) {
                    operation = Operation.Minus;
                    state = State.SecondNumber;
                    clearDisplay();
                    break;
                }

                secondNumber = getNumberFromDisplay();
                firstNumber = getResult();
                clearDisplay();
                break;

            case '+':
                if (state == State.FirstNumber) {
                    operation = Operation.Plus;
                    firstNumber = getNumberFromDisplay();
                    state = State.SecondNumber;
                    clearDisplay();
                    break;
                }

                if (state == State.Result) {
                    operation = Operation.Plus;
                    state = State.SecondNumber;
                    clearDisplay();
                    break;
                }

                secondNumber = getNumberFromDisplay();
                firstNumber = getResult();
                clearDisplay();
                break;

            default:
                secondNumber = state == State.Result ? secondNumber : getNumberFromDisplay();
                firstNumber = getResult();
                displayText = String.valueOf(firstNumber);
                state = State.Result;
                updateDisplay();
            break;
        }
    }

    private boolean isCharNumber(char c) {
        try {
            Integer.parseInt(String.valueOf(c));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void handleOperation() {
        if (state == State.FirstNumber) {
            firstNumber = getNumberFromDisplay();
            clearDisplay();
            state = State.SecondNumber;
        } else if (state == State.SecondNumber) {
            firstNumber = getResult();
            secondNumber = getNumberFromDisplay();
            clearDisplay();
            state = State.SecondNumber;
        } else {
            firstNumber = getResult();
            displayText = String.valueOf(firstNumber);
            updateDisplay();
        }
    }

    private int getResult() {
        if (operation == Operation.Plus) {
            return firstNumber + secondNumber;
        }

        return firstNumber - secondNumber;
    }

    private int getNumberFromDisplay() {
        return Integer.parseInt(displayText);
    }


    private void clearDisplay() {
        displayText = "0";
        updateDisplay();
    }

    private void updateDisplay() {
        display.setText(displayText);
    }
}
