  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polycalc;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author p0073862
 */
public class GUI {

    private final JFrame frame = new JFrame("Polynomial Calculator");
    private final JTextField entryField = new JTextField(30);
    private final JTextField storeField = new JTextField(30);
    private final Polynomial entryPoly;
    private final Polynomial storePoly;

    public GUI(Polynomial entryPoly, Polynomial storePoly) {
        this.entryPoly = entryPoly;
        this.storePoly = storePoly;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        entryField.setEditable(false);
        contentPane.add(entryField);
        storeField.setEditable(false);
        contentPane.add(storeField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(makeMemStoreButton());
        buttonPanel.add(makeMemPlusButton());
        buttonPanel.add(makeMemTimesButton());
        buttonPanel.add(makeMemClearButton());

        buttonPanel.add(makeEntryButton("x"));
        buttonPanel.add(makeEntryButton("^"));
        buttonPanel.add(makeDelButton());
        buttonPanel.add(makeClearButton());

        buttonPanel.setLayout(new GridLayout(6, 4));
        for (int i = 1; i <= 3; i++) {
            buttonPanel.add(makeEntryButton(Integer.toString(i)));
        }
        buttonPanel.add(makeEntryButton("+"));

        for (int i = 4; i <= 6; i++) {
            buttonPanel.add(makeEntryButton(Integer.toString(i)));
        }
        buttonPanel.add(makeEntryButton("-"));

        for (int i = 7; i <= 9; i++) {
            buttonPanel.add(makeEntryButton(Integer.toString(i)));
        }
        buttonPanel.add(makeEqualsButton());

        buttonPanel.add(makeEntryButton("0"));

        contentPane.add(buttonPanel);

        frame.pack();
        frame.setVisible(true);

    }

    private JButton makeEntryButton(String entry) {
        final String buttonText = entry;
        final JButton button = new JButton(buttonText);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addToEntry(buttonText);
            }
        });
        return button;
    }

    private JButton makeOperatorButton(String op) {
        JButton button = new JButton(op);
        return button;
    }

    private void addToEntry(String x) {
        String current = entryField.getText();
        entryField.setText(current + x);
    }

    private JButton makeEqualsButton() {
        final JButton eqButton = new JButton("=");
        eqButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateEntry();
            }

        });
        return eqButton;

    }

    private String polyToString(Polynomial poly) {
        StringBuilder s = new StringBuilder();

        boolean first = true;
        for (Term term : poly) {

            int coeff = term.getCoefficient();
            int power = term.getPower();

            if (coeff >= 0 && !first) {
                s.append("+");
            }
            if (coeff == -1 && power != 0) {
                s.append("-");
            } else if (!(coeff == 1 && power != 0)) {
                s.append(Integer.toString(term.getCoefficient()));
            }
            if (power != 0) {
                s.append("x");
                if (power != 1) {
                    s.append("^");
                    s.append(Integer.toString(term.getPower()));
                }
            }
            first = false;
        }

        return s.toString();
    }

    private boolean stringToPoly(String text, Polynomial poly) {
        Tokeniser tok = new Tokeniser(text);
        poly.clear();
        try {
            while (tok.hasNext()) {
                poly.addTerm(tok.getNextToken());
            }
            return true;
        } catch (ParseException ex) {
            //System.err.println(ex);
            //System.err.println("at index " + ex.getErrorOffset());
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            poly.clear();
            return false;
        }
    }

    private boolean updateEntry() {
        String text = entryField.getText();
        boolean result = stringToPoly(text, entryPoly);
        if (result) {
            entryField.setText(polyToString(entryPoly));
        }
        return result;
    }

    private void updateStore() {
        storeField.setText(polyToString(storePoly));
    }

    private JButton makeClearButton() {
        final JButton clrButton = new JButton("C");
        clrButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //entryPoly.clear();
                entryField.setText("");
                updateEntry();
            }

        });
        return clrButton;
    }

    private JButton makeMemClearButton() {
        final JButton button = new JButton("MC");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                storePoly.clear();
                updateStore();
            }

        });
        return button;
    }

    private JButton makeMemStoreButton() {
        final JButton button = new JButton("MS");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateEntry()) {
                    copy(entryPoly, storePoly);
                    updateStore();
                }
            }

        });
        return button;
    }

    private JButton makeMemPlusButton() {
        final JButton button = new JButton("M+");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateEntry()) {
                    storePoly.addPoly(entryPoly);
                    updateStore();
                }
            }

        });
        return button;
    }

    private JButton makeMemTimesButton() {
        final JButton button = new JButton("M*");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateEntry()) {
                    storePoly.multiplyByPoly(entryPoly);
                    updateStore();
                }
            }

        });
        return button;
    }

    private void copy(Polynomial p1, Polynomial p2) {
        p2.clear();
        for (Term term : p1) {
            p2.addTerm(term);
        }
    }

    private JButton makeDelButton() {
        final JButton clrButton = new JButton("Del");
        clrButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String text = entryField.getText();
                text = text.substring(0, text.length() - 1);

                entryField.setText(text);
            }

        });
        return clrButton;
    }
}
