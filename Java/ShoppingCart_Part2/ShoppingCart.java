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
import java.util.Locale;
import java.util.ResourceBundle;
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
 * Provides the implementation of the GUI
 * @author Ollie Poole
 */
public class ShoppingCart extends JFrame {

    //Panels for storing the different parts of the display
    private final JPanel leftPanel = new JPanel();
    private final JPanel newItemPanel = new JPanel();
    private final JPanel errorPanel = new JPanel();
    private final JPanel cartPanel = new JPanel();

    //New Item
    private final JPanel newItemLabelPanel = new JPanel();
    private final JLabel newItemLabel = new JLabel();

    private final JPanel itemPanel = new JPanel();

    private final JPanel itemLabelPanel = new JPanel();
    private final JLabel itemNameLabel = new JLabel();
    private final JLabel itemTempLabel = new JLabel(" ");
    private final JLabel itemTemp2Label = new JLabel(" ");
    private final JLabel itemQuanLabel = new JLabel();

    private final JPanel itemTFPanel = new JPanel();
    private final JTextField itemNameTF = new JTextField(5);
    private final JTextField itemQuanTF = new JTextField(5);

    private final JPanel itemResetAddPanel = new JPanel();
    private final JButton itemResetButton = new JButton();
    private final JButton quantityButton = new JButton();
    private final JButton weightButton = new JButton();

    //Error Report
    private final JPanel errorTCPanel = new JPanel();
    private final JLabel errorLabel = new JLabel();
    private final JButton errorClearButton = new JButton();
    private final JTextArea errorArea = new JTextArea(8, 25);
    private final JScrollPane errorSP = new JScrollPane(errorArea);

    //Cart
    private final JLabel cartLabel = new JLabel();
    private final JTextArea cartTA = new JTextArea(15, 25);
    private final JScrollPane cartSP = new JScrollPane(cartTA);
    private final JPanel cartLabelPanel = new JPanel();
    private final JLabel cartQuanLabel = new JLabel();
    private final JLabel cartTempLabel = new JLabel(" ");
    private final JLabel cartPriceLabel = new JLabel();
    private final JPanel cartTFPanel = new JPanel();
    private final JTextField totalTF = new JTextField();
    private final JTextField priceTF = new JTextField();

    ArrayList<Purchase> purchases = new ArrayList<>();
    ArrayListStructure itemsStored = new ArrayListStructure();
    final static int WEIGHT = 1;
    final static int QUANTITY = 0;
    ResourceBundle messages;
    String choice = "";

    /**
     * The constructor
     * Builds the GUI
     */
    public ShoppingCart() {
        super();

        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {

                        //Getting the content pane and giving it a box layout on the X AXIS
                        Container contentPane = getContentPane();
                        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

                        InitMainGrid();
                        InitAddItem();
                        InitErrorScreen();
                        InitCart();

                        setIntText();

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
                }
        );
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
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
        itemLabelPanel.setLayout(new BoxLayout(itemLabelPanel, BoxLayout.Y_AXIS));
        itemTFPanel.setLayout(new BoxLayout(itemTFPanel, BoxLayout.Y_AXIS));

        weightButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                choice = messages.getString("weight");
                addItemToCart(choice);
            }

        }
        );
        quantityButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                choice = messages.getString("quantity");
                addItemToCart(choice);
            }

        });
        itemResetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                itemNameTF.setText("");
                itemQuanTF.setText("");
            }

        });

        itemResetAddPanel.add(weightButton);
        itemResetAddPanel.add(quantityButton);
        itemResetAddPanel.add(itemResetButton);

        newItemLabelPanel.add(newItemLabel);

        itemLabelPanel.add(itemNameLabel);
        itemLabelPanel.add(itemTempLabel);
        itemLabelPanel.add(itemTemp2Label);
        itemLabelPanel.add(itemQuanLabel);

        itemTFPanel.add(itemNameTF);
        itemTFPanel.add(itemQuanTF);

        itemPanel.add(itemLabelPanel);
        itemPanel.add(itemTFPanel);

        newItemPanel.add(newItemLabelPanel);
        newItemPanel.add(itemPanel);
        newItemPanel.add(itemResetAddPanel);

    }

    /**
     * Builds the error screen
     */
    private void InitErrorScreen() {
        errorTCPanel.setLayout(new BoxLayout(errorTCPanel, BoxLayout.X_AXIS));

        errorClearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                errorArea.setText("");
            }

        });

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
        cartLabelPanel.add(cartTempLabel);
        cartLabelPanel.add(cartPriceLabel);

        cartPanel.add(cartLabel);
        cartPanel.add(cartSP);
        cartPanel.add(cartLabelPanel);
        cartPanel.add(cartTFPanel);

    }
    /**
     * Gets the international labels 
     */
    private void setIntText() {
        newItemLabel.setText(messages.getString("newItemLabel"));
        itemNameLabel.setText(messages.getString("itemNameLabel"));
        itemQuanLabel.setText(messages.getString("itemQuanLabel"));
        itemResetButton.setText(messages.getString("itemResetButton"));
        quantityButton.setText(messages.getString("quantityButton"));
        weightButton.setText(messages.getString("weightButton"));
        errorLabel.setText(messages.getString("errorLabel"));
        errorClearButton.setText(messages.getString("errorClearButton"));
        cartLabel.setText(messages.getString("cartLabel"));
        cartQuanLabel.setText(messages.getString("cartQuanLabel"));
        cartPriceLabel.setText(messages.getString("cartPriceLabel"));
    }

    public static void main(String[] args) {

        String language;
        String country;

        if (args.length != 2) {
            language = "en";
            country = "US";
        } else {
            language = args[0];
            country = args[1];
        }

        Locale currentLocale;

        currentLocale = new Locale(language, country);

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.messages
                = ResourceBundle.getBundle("MessagesBundle", currentLocale);

        shoppingCart.itemsStored.recordItem(1, shoppingCart.messages.getString("item1"), 1.99, QUANTITY);
        shoppingCart.itemsStored.recordItem(2, shoppingCart.messages.getString("item2"), 3.49, WEIGHT);
        shoppingCart.itemsStored.recordItem(3, shoppingCart.messages.getString("item3"), 9.99, QUANTITY);
        shoppingCart.itemsStored.recordItem(4, shoppingCart.messages.getString("item4"), 25, WEIGHT);
        shoppingCart.itemsStored.recordItem(5, shoppingCart.messages.getString("item5"), 1.50, WEIGHT);
        shoppingCart.itemsStored.recordItem(6, shoppingCart.messages.getString("item6"), 0.99, QUANTITY);
        shoppingCart.itemsStored.recordItem(7, shoppingCart.messages.getString("item7"), 0.5, QUANTITY);
        shoppingCart.itemsStored.recordItem(8, shoppingCart.messages.getString("item8"), 19.99, WEIGHT);
        shoppingCart.itemsStored.recordItem(9, shoppingCart.messages.getString("item9"), 2.35, QUANTITY);

    }
    /**
     * Adds a new purchase
     * @param choice        Either weight or quantity depending on user input
     */
    public void addPurchase(String choice) {
        int itemNumber = Integer.parseInt(itemNameTF.getText());

        if (choice.equals(messages.getString("weight"))) {
            double weight = Double.parseDouble(itemQuanTF.getText());
            purchases.add(new Weight(itemsStored.getDescription(itemNumber), itemsStored.getUnitPrice(itemNumber), weight));
            refreshCart();

        } else if (choice.equals(messages.getString("quantity"))) {

            int quantity = Integer.parseInt(itemQuanTF.getText());
            purchases.add(new Number(itemsStored.getDescription(itemNumber), itemsStored.getUnitPrice(itemNumber), quantity));
            refreshCart();

        }
    }

    /**
     * Validates the input
     * @param choice        either weight or quantity depending on user input
     * @return              true or false depending on if the tests are passed
     */
    public boolean validateEntry(String choice) {
        assert choice.equals(messages.getString("weight")) || choice.equals(messages.getString("quantity")) : messages.getString("assert1");
        //Assert to make sure the choice is correct

        int itemNumber = 0;

        boolean check = true;
        try {
            itemNumber = Integer.parseInt(itemNameTF.getText());

            assert !(itemNumber < 0) : messages.getString("assert2");
            //Assert making sure the name field is not blank before making a new class

            if (itemNumber < 0) {
                check = false;
                throw new Exception();
            }

            for (int x = 0; x < itemsStored.size(); x++) {
                if (!(itemsStored.IsKnownItemNumber(itemNumber))) {
                    check = false;
                    throw new Exception();
                }
            }

        } catch (Exception e) {
            errorArea.append(messages.getString("error1") + "\n");
            check = false;
        }

        if (choice.equals(messages.getString("weight"))) {

            try {
                double weight = Double.parseDouble(itemQuanTF.getText());

                assert weight <= 0 : messages.getString("assert3");
                //Assert to make sure the weight has been entered correctly

                if (weight <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                errorArea.append(messages.getString("error2") + "\n");
                check = false;
            }
            try {
                for (int x = 0; x < itemsStored.size(); x++) {
                    if (itemsStored.get(x).getID() == itemNumber) {
                        if (itemsStored.get(x).getSort() != WEIGHT) {
                            check = false;
                            throw new Exception();
                        }
                    }
                }

            } catch (Exception ex) {
                errorArea.append(messages.getString("error3") + "\n");
                check = false;
            }

        } else if (choice.equals(messages.getString("quantity"))) {
            try {
                int quantity = Integer.parseInt(itemQuanTF.getText());

                assert quantity <= 0 : messages.getString("assert4");
                //Assert to make sure the quantity is greater than 0

                if (quantity <= 0) {
                    check = false;
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                errorArea.append(messages.getString("error4") + "\n");
                check = false;
            }

            try {
                for (int x = 0; x < itemsStored.size(); x++) {
                    if (itemsStored.get(x).getID() == itemNumber) {
                        if (itemsStored.get(x).getSort() != QUANTITY) {
                            check = false;
                            throw new Exception();
                        }
                    }
                }

            } catch (Exception ex) {
                errorArea.append(messages.getString("error5") + "\n");
                check = false;
            }

        }

        return check;
    }

    /**
     * Clears the input fields
     */
    public void clearInputFields() {
        itemNameTF.setText("");
        itemQuanTF.setText("");
    }

    /**
     * Reloads the cart with the current purchases
     */
    public void refreshCart() {
        int total = 0;
        cartTA.setText("");

        for (int x = 0; x < purchases.size(); x++) {
            for (int y = 1; y <= itemsStored.size(); y++) {
                if (itemsStored.getDescription(y).equals(purchases.get(x).getName())) {
                    switch (itemsStored.getSort(y)) {
                        case WEIGHT:
                            total += purchases.get(x).getIntPrice() * purchases.get(x).getWeight();
                            cartTA.append(messages.getString("cartName") +
                                    purchases.get(x).getName() + " " +
                                    messages.getString("cartPrice") +
                                    purchases.get(x).getDecimalPrice() + " " +
                                    messages.getString("cartWeight")+
                                    purchases.get(x).getWeight() + "\n");
                            break;
                        case QUANTITY:
                            total += purchases.get(x).getIntPrice() * purchases.get(x).getQuantity();
                            cartTA.append(messages.getString("cartName") +
                                    purchases.get(x).getName() + " " +
                                    messages.getString("cartPrice") +
                                    purchases.get(x).getDecimalPrice() + " " +
                                    messages.getString("cartQuantity") +
                                    purchases.get(x).getQuantity() + "\n");
                            break;
                    }
                    break;
                }
            }

        }

        DecimalFormat df = new DecimalFormat("0.00");
        priceTF.setText("" + df.format(total / 100.00));
        totalTF.setText("" + purchases.size());
    }

    /**
     * Adds a new purchase
     * @param choice        Either quantity or weight depending on user input
     */
    public void addItemToCart(String choice) {
        if (validateEntry(choice)) {
            addPurchase(choice);
        } else {
            errorArea.append(messages.getString("error6") + "\n\n");
        }
        refreshCart();
    }

}
