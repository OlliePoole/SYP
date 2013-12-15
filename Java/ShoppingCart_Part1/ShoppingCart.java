/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcart;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Shopping Cart Class
 * This contains all the code for the GUI and implementations of actions
 * @author Ollie Poole
 */
public class ShoppingCart extends JFrame implements ActionListener {

    //Panels for storing the different parts of the display
    private final JPanel leftPanel = new JPanel();
    private final JPanel newItemPanel = new JPanel();
    private final JPanel errorPanel = new JPanel();
    private final JPanel cartPanel = new JPanel();
   
    //New Item
    private final JPanel newItemLabelPanel = new JPanel();
    private final JLabel newItemLabel = new JLabel("Add New Item");
    private final JPanel itemNamePanel = new JPanel();
    private final JLabel itemNameLabel = new JLabel("Name:");
    private final JTextField newItemTF = new JTextField(15);
    private final JPanel itemQuanPanel = new JPanel();
    private final JLabel itemQuanLabel = new JLabel("Weight/Quantity:");
    private final JTextField itemQuanTF = new JTextField(15);
    private final JPanel itemPricePanel = new JPanel();
    private final JLabel itemPriceLabel = new JLabel("Price:");
    private final JTextField itemPriceTF = new JTextField(15);
    private final JPanel itemResetAddPanel = new JPanel();
    private final JButton itemResetButton = new JButton("Reset");
    private final JButton quantityButton = new JButton("Add by Quantity");
    private final JButton weightButton = new JButton("Add by Weight");
   
    //Error Report
    private final JPanel errorTCPanel = new JPanel();
    private final JLabel errorLabel = new JLabel("Error Screen");
    private final JButton errorClearButton = new JButton("Clear");
    private final JTextArea errorArea = new JTextArea(8, 25);
    private final JScrollPane errorSP = new JScrollPane(errorArea);
    
    //Cart
    private final JLabel cartLabel = new JLabel("Your Shopping Cart");
    private final JTextArea cartTA = new JTextArea(15, 20);
    private final JScrollPane cartSP = new JScrollPane(cartTA);
    private final JPanel cartLabelPanel = new JPanel();
    private final JLabel cartQuanLabel = new JLabel("Num of Items:");
    private final JLabel cartPriceLabel = new JLabel("Total: £");
    private final JPanel cartTFPanel = new JPanel();
    private final JTextField totalTF = new JTextField();
    private final JTextField priceTF = new JTextField();
    
    ArrayList<Purchase> purchases = new ArrayList<>();
    String choice = "";

    /**
     * Constructor
     * Builds the GUI
     */
    public ShoppingCart() {
        //Changing the top of the window
        super("Shopping Cart Program");

        //Getting the content pane and giving it a box layout on the X AXIS
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        InitMainGrid();
        InitAddItem();
        InitErrorScreen();
        InitCart();

        //Adding the other panels to the left panel so they are grouped
        //on the left hand side of the screen
        leftPanel.add(newItemPanel);
        leftPanel.add(errorPanel);

        //Adding the left and right panels to the content Pane
        contentPane.add(leftPanel);
        contentPane.add(cartPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);

    }

    /**
     * Builds the main grid
     */
    private void InitMainGrid() {
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        newItemPanel.setLayout(new BoxLayout(newItemPanel, BoxLayout.Y_AXIS));
        errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

        leftPanel.setBorder(BorderFactory.createEtchedBorder());
        cartPanel.setBorder(BorderFactory.createEtchedBorder());
        errorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }
    /**
     * Builds the add item panel
     */
    private void InitAddItem() {
        itemResetAddPanel.setLayout(new BoxLayout(itemResetAddPanel, BoxLayout.X_AXIS));
        newItemLabelPanel.setLayout(new BoxLayout(newItemLabelPanel, BoxLayout.X_AXIS));
        itemNamePanel.setLayout(new BoxLayout(itemNamePanel, BoxLayout.Y_AXIS));
        itemQuanPanel.setLayout(new BoxLayout(itemQuanPanel, BoxLayout.X_AXIS));
        itemPricePanel.setLayout(new BoxLayout(itemPricePanel, BoxLayout.Y_AXIS));

        weightButton.addActionListener(this);
        quantityButton.addActionListener(this);
        itemResetButton.addActionListener(this);

        itemResetAddPanel.add(weightButton);
        itemResetAddPanel.add(quantityButton);
        itemResetAddPanel.add(itemResetButton);

        newItemLabelPanel.add(newItemLabel);

        itemNamePanel.add(itemNameLabel);
        itemNamePanel.add(itemQuanLabel);
        itemNamePanel.add(itemPriceLabel);
        
        itemPricePanel.add(newItemTF);
        itemPricePanel.add(itemQuanTF);
        itemPricePanel.add(itemPriceTF);
        
        itemQuanPanel.add(itemNamePanel);
        itemQuanPanel.add(itemPricePanel);
       

        newItemPanel.add(newItemLabelPanel);

        newItemPanel.add(itemQuanPanel);
        newItemPanel.add(itemResetAddPanel);

    }

    /**
     * Builds the error screen panel
     */
    private void InitErrorScreen() {
        errorTCPanel.setLayout(new BoxLayout(errorTCPanel, BoxLayout.X_AXIS));

        errorClearButton.addActionListener(this);

        errorArea.setEditable(false);

        errorTCPanel.add(errorLabel);
        errorTCPanel.add(errorClearButton);

        errorPanel.add(errorTCPanel);
        errorPanel.add(errorSP);
    }
    /**
     * Builds the cart panel
     */
    private void InitCart() {
        cartTFPanel.setLayout(new BoxLayout(cartTFPanel, BoxLayout.X_AXIS));
        cartLabelPanel.setLayout(new BoxLayout(cartLabelPanel, BoxLayout.X_AXIS));

        cartSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        cartTA.setEditable(false);
        totalTF.setEditable(false);
        priceTF.setEditable(false);

        cartTFPanel.add(totalTF);
        cartTFPanel.add(priceTF);

        cartLabelPanel.add(cartQuanLabel);
        cartLabelPanel.add(cartPriceLabel);

        cartPanel.add(cartLabel);
        cartPanel.add(cartSP);
        cartPanel.add(cartLabelPanel);
        cartPanel.add(cartTFPanel);

    }

    public static void main(String[] args) {
        // TODO code application logic here
        ShoppingCart shoppingCart = new ShoppingCart();
    }
    
    /**
     * Adds a new purchase to the arrayList
     * @param choice    Is either weight or Quantity depending on the type of purchase
     */
    public void addPurchase(String choice) {
        String name = newItemTF.getText();
        double price = Double.parseDouble(itemPriceTF.getText());

        switch (choice) {
            case "Weight":
                double weight = Double.parseDouble(itemQuanTF.getText());
                purchases.add(new Weight(name, price, weight));
                break;
            case "Quantity":
                int quantity = Integer.parseInt(itemQuanTF.getText());
                purchases.add(new Number(name, price, quantity));
                break;
        }
    }
    
    /**
     * Validates the text fields
     * @param choice    Either weight or Quantity depending on button pressed
     * @return          A boolean value, true if validation passes else false
     */
    public boolean validateEntry(String choice) {
        assert choice.equals("Weight")||choice.equals("Quantity") : "Make sure weight or quantity has been selected";
        //Assert to make sure the choice is correct
        
        boolean check = true;
        try {
            String name = newItemTF.getText();

            assert !(name.equals("")) : "Make sure the name field is not blank";
            //Assert making sure the name field is not blank before making a new class

            if (name.equals("") || name.equals(" ")) {
                check = false;
                throw new Exception();
            }

        } catch (Exception e) {
            errorArea.append("Make sure the name is entered correctly" + "\n");
            check = false;
        }

        switch (choice) {
            case "Weight":
                try {
                    double weight = Double.parseDouble(itemQuanTF.getText());

                    assert weight <= 0 : "Make sure the weight is greater than 0";
                    //Assert to make sure the weight has been entered correctly

                    if (weight <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    errorArea.append("Make sure the weight is entered correctly (i.e 5.5)" + "\n");
                    check = false;
                }
                break;

            case "Quantity":
                try {
                    int quantity = Integer.parseInt(itemQuanTF.getText());

                    assert quantity <= 0 : "Make sure the quantity is greater than 0";
                    //Assert to make sure the quantity is greater than 0

                    if (quantity <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    errorArea.append("Make sure the quantity is entered correctly (i.e 5)" + "\n");
                    check = false;
                }
                break;
        }
        
        try {
            int index;
            String sub;
            double price = Double.parseDouble(itemPriceTF.getText());
            
            index = itemPriceTF.getText().indexOf(".");
            sub = itemPriceTF.getText().substring(0, index+1);
            
            if (itemPriceTF.getText().length()-sub.length() > 2) {
                throw new NumberFormatException();
            }
            
           
            assert price <= 0 : "Make sure the price is a positive number";
            //Assert to make sure the price has been entered correctly before making a new class

            if (price <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            errorArea.append("Make sure the price is entered correctly (i.e 1.09)" + "\n");
            check = false;
        }

        return check;
    }

    /**
     * Clears the input fields
     */
    public void clearInputFields() {
        newItemTF.setText("");
        itemQuanTF.setText("");
        itemPriceTF.setText("");
    }

    /**
     * Reloads the cart interface and re-populates with the current purchases
     */
    public void refreshCart() {
        int total = 0;
        cartTA.setText("");
        for (int x = 0; x < purchases.size(); x++) {
            cartTA.append(purchases.get(x).toString());
            total += purchases.get(x).getIntPrice();
        }
        DecimalFormat df = new DecimalFormat("0.00");
        priceTF.setText("" + df.format(total / 100.00));
        totalTF.setText("" + purchases.size());

    }
    
    /**
     * The actions for each button press
     * @param e     The action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == weightButton) {
            choice = "Weight";
            addItemToCart(choice);

        } else if (e.getSource() == quantityButton) {
            choice = "Quantity";
            addItemToCart(choice);

        } else if (e.getSource() == errorClearButton) {
            errorArea.setText("");

        } else if (e.getSource() == itemResetButton) {
            clearInputFields();
        }

    }
    
    /**
     * adds the item to the cart
     * @param choice        The type of item to be added
     */
    public void addItemToCart(String choice) {
        if (validateEntry(choice)) {
            addPurchase(choice);
        } else {
            errorArea.append("One or more errors, please correct then continue" + "\n\n");
        }

        refreshCart();
    }
}
