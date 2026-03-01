import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

public class PizzaGUIFrame extends JFrame {
    // INITIALIZE PANELS
    JPanel mainPnl;
    JPanel chooseCrustPnl;
    JPanel selectToppingsPnl;
    JPanel chooseSizePnl;
    JPanel controlPnl;
    JPanel receiptPnl;

    // INITIALIZE RADIO BUTTONS FOR PIZZA CRUST
    JRadioButton thinCrustRBtn;
    JRadioButton regularRBtn;
    JRadioButton deepDishRBtn;

    // INITIALIZE JComboBox FOR PIZZA SIZE
    JComboBox sizeOptions;

    // INITIALIZE CHECK BOXES FOR PIZZA TOPPINGS
    JCheckBox pepperoniCB;
    JCheckBox sausageCB;
    JCheckBox extraCheeseCB;
    JCheckBox pineappleCB;
    JCheckBox barbecueSauceCB;
    JCheckBox mushroomsCB;

    // INITIALIZE QUIT AND DISPLAY CHOICES BUTTON
    JButton placeOrderBtn;
    JButton clearChoicesBtn;
    JButton quitBtn;

    // VARIABLES FOR CALCULATING AND PRINTING RECEIPT
    JTextArea receiptTA;
    JScrollPane scrollbar;
    double sizeCost;
    double toppingCost;
    double pizzaCost;
    double pizzaTax;
    double totalCost;
    String receipt;



    public PizzaGUIFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(5,1));

        createChooseCrustPanel();
        mainPnl.add(chooseCrustPnl);

        createSizeOptionsPanel();
        mainPnl.add(chooseSizePnl);

        createCheckBoxPanel();
        mainPnl.add(selectToppingsPnl);

        createReceiptPanel();
        mainPnl.add(receiptPnl);

        createControlPanel();
        mainPnl.add(controlPnl);

        add(mainPnl);
        {
            // CENTER FRAME IN SCREEN...CODE ADAPTED FROM CAY HORSTMANN
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenHeight = screenSize.height;
            int screenWidth = screenSize.width;

            setSize(screenWidth / 5, screenHeight / 2);
            double twoPointFive = 2.5;
            setLocation((int) (screenWidth / twoPointFive), screenHeight / 4);


            setTitle("Customize Your Pizza");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }
    }

    private void createChooseCrustPanel() {
        chooseCrustPnl = new JPanel();
        chooseCrustPnl.setLayout(new GridLayout(3, 1));
        chooseCrustPnl.setBorder(new TitledBorder(new EtchedBorder(),"Crust"));


        thinCrustRBtn = new JRadioButton("Thin Crust");
        regularRBtn = new JRadioButton("Regular");
        deepDishRBtn = new JRadioButton("Deep-Dish");

        chooseCrustPnl.add(thinCrustRBtn);
        chooseCrustPnl.add(regularRBtn);
        chooseCrustPnl.add(deepDishRBtn);

        regularRBtn.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(thinCrustRBtn);
        group.add(regularRBtn);
        group.add(deepDishRBtn);
    }

    private void createSizeOptionsPanel() {
        chooseSizePnl = new JPanel();
        chooseSizePnl.setBorder(new TitledBorder(new EtchedBorder(),"Size"));

        sizeOptions = new JComboBox();
        sizeOptions.addItem("Small        $8.00");
        sizeOptions.addItem("Medium  $12.00");
        sizeOptions.addItem("Large      $16.00");
        sizeOptions.addItem("Super      $20.00");
        chooseSizePnl.add(sizeOptions);
    }

    private void createCheckBoxPanel() {
        selectToppingsPnl = new JPanel();
        selectToppingsPnl.setLayout(new GridLayout(3,2));
        selectToppingsPnl.setBorder(new TitledBorder(new EtchedBorder(),"Toppings"));

        pepperoniCB = new JCheckBox("Pepperoni");
        sausageCB = new JCheckBox("Sausage");
        extraCheeseCB = new JCheckBox("Extra Cheese");
        pineappleCB = new JCheckBox("Pineapple");
        barbecueSauceCB = new JCheckBox("Barbecue Sauce");
        mushroomsCB = new JCheckBox("Mushrooms");

        selectToppingsPnl.add(pepperoniCB);
        selectToppingsPnl.add(sausageCB);
        selectToppingsPnl.add(extraCheeseCB);
        selectToppingsPnl.add(pineappleCB);
        selectToppingsPnl.add(barbecueSauceCB);
        selectToppingsPnl.add(mushroomsCB);
    }


    private void createControlPanel() {
        controlPnl = new JPanel();
        controlPnl.setBorder(new TitledBorder(new EtchedBorder(),"Next Steps"));

        placeOrderBtn = new JButton("Place Order");
        placeOrderBtn.addActionListener(
            (ActionEvent ae) -> {
                calculateCostAndDisplayReceipt();
            });

        clearChoicesBtn = new JButton("Clear Choices");
        clearChoicesBtn.addActionListener((ActionEvent ae) -> {
            clearChoices();
        });

        quitBtn = new JButton("Quit!");
        quitBtn.addActionListener((ActionEvent ae) -> {
            int userOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "CONFIRM QUIT", JOptionPane.YES_NO_OPTION);
            if (userOption == YES_OPTION) {
                System.exit(0);
            }
            if (userOption == NO_OPTION) {
            }
        });

        controlPnl.add(placeOrderBtn);
        controlPnl.add(clearChoicesBtn);
        controlPnl.add(quitBtn);
    }

    private void calculateCostAndDisplayReceipt() {
        // Generate a result string and then display it with a Message Dialog
        receipt = "======================================\n";
        // SIZE CHOICE
        if (sizeOptions.getSelectedIndex() == 0) { // small
            receipt += "Small Pizza, ";
            sizeCost = 8.0;
        }
        else if (sizeOptions.getSelectedIndex() == 1) { // medium
            receipt += "Medium Pizza, ";
            sizeCost = 12.0;
        }
        else if (sizeOptions.getSelectedIndex() == 2) { // large
            receipt += "Large Pizza, ";
            sizeCost = 16.0;
        }
        else if (sizeOptions.getSelectedIndex() == 3) { // super
            receipt += "Super Pizza, ";
            sizeCost = 20.0;
        }

        // CRUST CHOICE
        if(thinCrustRBtn.isSelected()) {
            receipt += " Thin Crust\t\t$" + sizeCost + "\n";
        }
        else if(regularRBtn.isSelected()) {
            receipt += " Regular Crust\t\t$" + sizeCost + "\n";
        }
        else if(deepDishRBtn.isSelected()) {
            receipt += " Deep-Dish Crust\t\t$" + sizeCost + "\n";
        }

        // TOPPING SELECTIONS
        toppingCost = 0.0;
        if (pepperoniCB.isSelected()) {
            receipt += "Pepperoni\t\t\t$1.00\n";
            toppingCost += 1.00;
        }
        if (sausageCB.isSelected()) {
            receipt += "Sausage\t\t\t$1.00\n";
            toppingCost += 1.00;
        }
        if (extraCheeseCB.isSelected()) {
            receipt += "Extra Cheese\t\t\t$1.00\n";
            toppingCost += 1.00;
        }
        if (pineappleCB.isSelected()) {
            receipt += "Pineapple\t\t\t$1.00\n";
            toppingCost += 1.00;
        }
        if (barbecueSauceCB.isSelected()) {
            receipt += "Barbecue Sauce\t\t\t$1.00\n";
            toppingCost += 1.00;
        }
        if (mushroomsCB.isSelected()) {
            receipt += "Mushrooms\t\t\t$1.00\n";
            toppingCost += 1.00;
        }

        pizzaCost = sizeCost + toppingCost;

        receipt += "\n";
        receipt += "\n";
        receipt += "Sub-total:\t\t\t$" + pizzaCost + "\n";
        pizzaTax = pizzaCost * 0.07;
        receipt += "Tax:\t\t\t$" + pizzaTax + "\n";
        receipt += "-----------------------------------------------------------\n";
        totalCost = pizzaCost + pizzaTax;
        receipt += "Total:\t\t\t$" + totalCost + "\n";
        receipt += "======================================\n";
        receiptTA.append(receipt);
    }

    private void createReceiptPanel() {
        receiptPnl = new JPanel();
        receiptPnl.setBorder(new TitledBorder(new EtchedBorder(),"Receipt"));
        receiptTA = new JTextArea(3, 28);
        receiptTA.setEditable(false);
        receiptTA.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        scrollbar = new JScrollPane(receiptTA);
        receiptPnl.add(scrollbar);
    }

    private void clearChoices() {
        sizeOptions.setSelectedIndex(0);

        thinCrustRBtn.setSelected(false);
        regularRBtn.setSelected(true);
        deepDishRBtn.setSelected(false);

        pepperoniCB.setSelected(false);
        sausageCB.setSelected(false);
        extraCheeseCB.setSelected(false);
        pineappleCB.setSelected(false);
        barbecueSauceCB.setSelected(false);
        mushroomsCB.setSelected(false);
    }
}