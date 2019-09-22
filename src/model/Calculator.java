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
    private Stack stack;
    private Queue queue;

    public Calculator() {
        this.setPreferredSize(new Dimension(400, 500));
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
        equal.setPreferredSize(new Dimension(70,70));
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
        numberPanel.add(new JButton(""));
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
        stack = new Stack();
        queue = new LinkedList();

        this.pack();
    }

    public void selectNum(int input) {
        if (input == 0 && stack.empty())
            return;
        else{
            stack.push(input);
            updateStack();
        }
    }

    public void selectSign(String input) {
        textField.setText(input);
        queue.offer(result);
        queue.offer(input);
        while(!stack.empty())
            stack.pop();
    }

    public void selectEqualSign(){

    }

    public void selectBack(){
        //Add if statement that removes the sign when textfield is a sign
        //if(
        stack.pop();
        updateStack();
    }

    public void updateStack(){
        int i = 0;
        int acc = 0;
        Iterator iterator = stack.iterator();
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
        result = new_i;
        textField.setText(Integer.toString(result));
    }

    //Implement PQ that computes multiplication and division first
}
