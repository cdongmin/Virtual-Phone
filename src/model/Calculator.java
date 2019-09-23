package model;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Stack;

public class Calculator extends JFrame {
    private JPanel numberPanel;
    private JButton one;
    private JButton two;
    private JButton three;
    private JButton four;
    private JButton five;
    private JButton six;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    private JButton clear;
    private JButton zero;
    private JButton backspace;

    private JPanel signPanel;
    private JButton plus;
    private JButton minus;
    private JButton multiply;
    private JButton divide;
    private JButton equal;

    private JPanel textFieldPanel;
    private JTextField textField;
    private int result;
    private Stack numberDigitStack;
    private Queue queue;

    private boolean isLastElementInQueueSign;

    private JPanel homePanel;
    private JButton homeButton;

    public Calculator() {
        this.setPreferredSize(new Dimension(400, 525));
        this.setTitle("Calculator");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        textFieldPanel = new JPanel();
        textFieldPanel.setPreferredSize(new Dimension(400, 150));
        this.add(textFieldPanel);
        textField = new JTextField(33);
        textField.setPreferredSize(new Dimension(400, 150));
        textField.setEditable(true);
        textField.setEnabled(true);
        textFieldPanel.add(textField);

        signPanel = new JPanel();
        signPanel.setLayout(new FlowLayout());
        signPanel.setPreferredSize(new Dimension(400, 85));
        this.add(signPanel);
        plus = new JButton("+");
        plus.setPreferredSize(new Dimension(70, 70));
        signPanel.add(plus);
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSign("+");
            }
        });
        minus = new JButton("-");
        minus.setPreferredSize(new Dimension(70, 70));
        signPanel.add(minus);
        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSign("-");
            }
        });
        multiply = new JButton("*");
        multiply.setPreferredSize(new Dimension(70, 70));
        signPanel.add(multiply);
        multiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSign("*");
            }
        });
        divide = new JButton("/");
        divide.setPreferredSize(new Dimension(70, 70));
        signPanel.add(divide);
        divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSign("/");
            }
        });
        equal = new JButton("=");
        equal.setPreferredSize(new Dimension(70, 70));
        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectEqualSign();
            }
        });
        signPanel.add(equal);

        numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(0, 3));
        this.add(numberPanel);
        one = new JButton("1");
        numberPanel.add(one);
        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNum(1);
            }
        });
        two = new JButton("2");
        numberPanel.add(two);
        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNum(2);
            }
        });
        three = new JButton("3");
        numberPanel.add(three);
        three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNum(3);
            }
        });
        four = new JButton("4");
        numberPanel.add(four);
        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNum(4);
            }
        });
        five = new JButton("5");
        numberPanel.add(five);
        five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNum(5);
            }
        });
        six = new JButton("6");
        numberPanel.add(six);
        six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNum(6);
            }
        });
        seven = new JButton("7");
        numberPanel.add(seven);
        seven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNum(7);
            }
        });
        eight = new JButton("8");
        numberPanel.add(eight);
        eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNum(8);
            }
        });
        nine = new JButton("9");
        numberPanel.add(nine);
        nine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNum(9);
            }
        });
        clear = new JButton("CLEAR");
        numberPanel.add(clear);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectClear();
            }
        });
        zero = new JButton("0");
        numberPanel.add(zero);
        zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNum(0);
            }
        });
        backspace = new JButton("<-");
        numberPanel.add(backspace);
        backspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectBack();
            }
        });

        result = 0;
        numberDigitStack = new Stack();
        queue = new LinkedList();
        isLastElementInQueueSign = false;

        homePanel = new JPanel();
        homePanel.setLayout(new GridLayout());
        homeButton = new JButton("HOME");
        homeButton.setPreferredSize(new Dimension(400, 25));
        homeButton.setBackground(Color.green);
        homePanel.add(homeButton);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        this.add(homePanel);

        this.pack();
    }

    public void selectNum(int input) {
        numberDigitStack.push(input);
        updateStack();
        isLastElementInQueueSign = false;
    }

    public void selectSign(String input) {
        if (isLastElementInQueueSign) {
            textField.setText("Not allowed, please enter a number");
        } else {
            textField.setText(input);
            queue.offer(result);
            queue.offer(input);
            while (!numberDigitStack.empty())
                numberDigitStack.pop();
            isLastElementInQueueSign = true;
        }
    }

    public void selectEqualSign() {
        queue.offer(result);
        queue = doMultAndDivFirst(queue);
        while (!queue.isEmpty()) {
            Object object = queue.remove();
            if (object.equals("+") || object.equals("-") || object.equals("*") || object.equals("/")) {
                if (object.equals("+"))
                    result += (int) queue.remove();
                else if (object.equals("-"))
                    result -= (int) queue.remove();
                else if (object.equals("*"))
                    result *= (int) queue.remove();
                else
                    result /= (int) queue.remove();
            } else {
                result = (int) object;
            }
        }
        textField.setText(Integer.toString(result));
        while (!numberDigitStack.empty())
            numberDigitStack.pop();
    }

    public void selectClear() {
        textField.setText("");
        while (!numberDigitStack.empty())
            numberDigitStack.pop();
        while (!queue.isEmpty())
            queue.remove();
        result = 0;
        isLastElementInQueueSign = false;
    }

    public void selectBack() {
        if (textField.getText().equals("+") || textField.getText().equals("-") || textField.getText().equals("*") || textField.getText().equals("/")) {
            textField.setText("");
            Queue newQueue = new LinkedList();
            while (queue.size() > 1) {
                Object notLastElement = queue.remove();
                newQueue.offer(notLastElement);
            }
            queue = newQueue;
            textField.setText(Integer.toString(result));
            isLastElementInQueueSign = false;
        } else {
            if (numberDigitStack.isEmpty())
                textField.setText("Not allowed, already cleared");
            else {
                numberDigitStack.pop();
                updateStack();
            }
        }
    }

    public void updateStack() {
        int i = 0;
        int acc = 0;

        Iterator iterator = numberDigitStack.iterator();
        while (iterator.hasNext()) {
            i += (int) iterator.next() * Math.pow(10, acc);
            acc++;
        }
        int new_i = 0;
        while (i != 0) {
            int a = i % 10;
            new_i *= 10;
            new_i += a;
            i /= 10;
        }
        while (String.valueOf(new_i).length() != acc) {
            new_i *= 10;
        }
        result = new_i;
        textField.setText(Integer.toString(result));
    }

    public Queue doMultAndDivFirst(Queue queue){
        Queue intermediateQueue = new LinkedList();
        while(!queue.isEmpty()){
            Object object = queue.peek();
            if((!object.equals("*")) && (!object.equals("/"))){
                intermediateQueue.offer(queue.remove());
            }
            else{
                if(object.equals("*")){
                    Queue intermediateQueue2 = new LinkedList();
                    queue.remove();
                    int second = (int) queue.remove();
                    Iterator iterator = intermediateQueue.iterator();
                    Object first = null;
                    while(iterator.hasNext()){
                        first = iterator.next();
                    }
                    int intFirst = (int) first;
                    int result = intFirst * second;
                    int a = intermediateQueue.size();
                    while(a > 1){
                        intermediateQueue2.offer(intermediateQueue.remove());
                        a--;
                    }
                    intermediateQueue2.offer(result);
                    intermediateQueue = intermediateQueue2;
                }
                else if(object.equals("/")){
                    Queue intermediateQueue2 = new LinkedList();
                    queue.remove();
                    int second = (int) queue.remove();
                    Iterator iterator = intermediateQueue.iterator();
                    Object first = null;
                    while(iterator.hasNext()){
                        first = iterator.next();
                    }
                    int intFirst = (int) first;
                    int result = intFirst / second;
                    int a = intermediateQueue.size();
                    while(a > 1){
                        intermediateQueue2.offer(intermediateQueue.remove());
                        a--;
                    }
                    intermediateQueue2.offer(result);
                    intermediateQueue = intermediateQueue2;
                }
            }
        }
        return intermediateQueue;
    }

    //For later: Implement decimals
}
