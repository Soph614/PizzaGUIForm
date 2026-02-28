import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PizzaGUIFrame extends JFrame {
    // INITIALIZE PANELS
    JPanel mainPnl;
    JPanel chooseCrustPnl;
    JPanel selectToppingsPnl;
    JPanel chooseSizePnl;
    JPanel controlPnl;

    // INITIALIZE RADIO BUTTONS FOR PIZZA CRUST
    JRadioButton thinCrustBtn;
    JRadioButton regularBtn;
    JRadioButton deepDishRBtn;

    // INITIALIZE JComboBox FOR PIZZA SIZE
    JComboBox sizeOptions;
    double pizzaCost;
    double pizzaTax;
    double totalCost;

    // INITIALIZE CHECK BOXES FOR PIZZA TOPPINGS
    JCheckBox pepperoniCB;
    JCheckBox sausageCB;
    JCheckBox extraCheeseCB;
    JCheckBox pineappleCB;
    JCheckBox barbecueSauceCB;
    JCheckBox mushroomsCB;

    // INITIALIZE QUIT AND DISPLAY CHOICES BUTTON
    JButton quitBtn;
    JButton displayChoicesBtn;

    public PizzaGUIFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(4,1));

        createChooseCrustPanel();
        mainPnl.add(chooseCrustPnl);

        createSizeOptionsPanel();
        mainPnl.add(chooseSizePnl);

        createCheckBoxPanel();
        mainPnl.add(selectToppingsPnl);

        createControlPanel();
        mainPnl.add(controlPnl);

        add(mainPnl);
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createChooseCrustPanel() {
        chooseCrustPnl = new JPanel();
        chooseCrustPnl.setLayout(new GridLayout(1, 4));
        chooseCrustPnl.setBorder(new TitledBorder(new EtchedBorder(),"Crust"));


        thinCrustBtn = new JRadioButton("Thin Crust");
        regularBtn = new JRadioButton("Regular");
        deepDishRBtn = new JRadioButton("Deep-Dish");

        chooseCrustPnl.add(thinCrustBtn);
        chooseCrustPnl.add(regularBtn);
        chooseCrustPnl.add(deepDishRBtn);

        regularBtn.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(thinCrustBtn);
        group.add(regularBtn);
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

        displayChoicesBtn = new JButton("Display Choices");
        displayChoicesBtn.addActionListener(
            (ActionEvent ae) -> {
                // Generate a result string and then display it with a Message Dialog
                String receipt ="Form Details\n";
                // get the size choice
                receipt += "Crust size: ";
                if(thinCrustBtn.isSelected())
                    receipt += "Thin Crust\n";
                else if(regularBtn.isSelected())
                    receipt += "Regular\n";
                else if(deepDishRBtn.isSelected())
                    receipt += "Deep-Dish\n";
                // get the items
                receipt += "With these toppings:\n";

                if (pepperoniCB.isSelected())
                    receipt += "\tPepperoni\n";

                if (sausageCB.isSelected())
                    receipt += "\tSausage\n";

                if (extraCheeseCB.isSelected())
                    receipt += "\tExtra Cheese\n";

                if (pineappleCB.isSelected())
                    receipt += "\tPineapple\n";

                if (barbecueSauceCB.isSelected())
                    receipt += "\tBarbecue Sauce\n";

                if (mushroomsCB.isSelected())
                    receipt += "\tMushrooms\n";

                receipt += "To be delivered to: ";

                receipt += (String) sizeOptions.getSelectedItem();

                receipt += "\n";
                // get the home
                JOptionPane.showMessageDialog(mainPnl, receipt);
            });

        quitBtn = new JButton("Quit!");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        controlPnl.add(displayChoicesBtn);
        controlPnl.add(quitBtn);

    }
}