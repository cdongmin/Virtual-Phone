package ui;

import model.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Virtual Phone");
        mainFrame.setPreferredSize(new Dimension(400,500));
        mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(),BoxLayout.PAGE_AXIS));
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.blue);
        panel.setLayout(new GridLayout(0,2));
        panel.setPreferredSize(new Dimension(400,275));
        mainFrame.add(panel);

        JButton callButton = new JButton("Call");
        panel.add(callButton);
        callButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton messageButton = new JButton("Message");
        panel.add(messageButton);
        messageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton gameButton = new JButton("Game");
        panel.add(gameButton);
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton youtubeButton = new JButton("Youtube");
        panel.add(youtubeButton);
        youtubeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URL("http://www.youtube.com").toURI());
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton webtoonButton = new JButton("Webtoon");
        panel.add(webtoonButton);
        webtoonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URL("https://www.comic.naver.com/webtoon/weekday.nhn").toURI());
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton calculatorButton = new JButton("Calculator");
        panel.add(calculatorButton);
        calculatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calculator mainCalculator = new Calculator();
                mainCalculator.setVisible(true);
            }
        });

        JPanel homePanel = new JPanel();
        homePanel.setLayout(new GridLayout(1,1));
        mainFrame.add(homePanel);

        JButton homeButton = new JButton("HOME");
        homeButton.setPreferredSize(new Dimension(400,100));
        homeButton.setBackground(Color.green);
        homePanel.add(homeButton);

        mainFrame.pack();

    }
}
