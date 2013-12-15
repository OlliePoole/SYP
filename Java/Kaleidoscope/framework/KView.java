package framework;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author p0073862
 */
public class KView implements Observer {

    private JFrame frame;
    private KModel model;
    private KController controller;
    private KPanel panel;
    private JComboBox nbrSegBox;
    private JButton resetBtn;
    private JButton nudgeBtn;
    private JButton but1Btn;
    private JButton but2Btn;

    public KView(KModel model) {
        this.model = model;
        frame = new JFrame("Kaleidoscope");
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        JPanel buttonPanel = makeButtonPanel();
        contentPane.add(buttonPanel);
        panel = new KPanel(model, 300);
        frame.add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setController(KController controller) {
        this.controller = controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        frame.repaint();
    }

    private JPanel makeButtonPanel() {
        JPanel result = new JPanel();
        result.setLayout(new BoxLayout(result, BoxLayout.X_AXIS));
        JLabel label = new JLabel("Number of segments");
        result.add(label);
        nbrSegBox = new JComboBox(new Integer[]{1, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20});
        nbrSegBox.setSelectedItem(model.getNbrSegments());
        nbrSegBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                controller.changeNbrSegments((Integer) e.getItem());
            }
        });
        result.add(nbrSegBox);

        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.reset();
            }
        });

        result.add(resetBtn);
        nudgeBtn = new JButton("Nudge");
        nudgeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.nudge();
            }
        });
        result.add(nudgeBtn);
        but1Btn = new JButton("B1");
        but1Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.but1();
            }
        });

        result.add(but1Btn);
        but2Btn = new JButton("B2");
        result.add(but2Btn);
        but2Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.but2();
            }
        });


        result.add(Box.createHorizontalGlue());

        return result;
    }
}
