/*
 * ==============================
 *  Assignment 3
 * ==============================
 * Author: Adam Andrade
 * Date: 2/21/13
 * Due: 2/21/13 11AM
 * I certify that this work is built upon the IceCreamShopLite and was modified by only the Author listed 
 * above.
 * 
 * _______________________________________________________________
 * Purpose: To create a ice cream shop GUI using inheritance.
 * 
 *  
 */
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *@originalTitle IceCreamShopLite
 * @originalAuthor Bill Kraynek
 * @Modifier Adam Andrade
 */
public class Assignment3 {

    Item iceCreamItem;//stores a single item order
    int scoops;//stores number of scoops per item
    boolean dish;//flag for if a dish is selected
    String address; //holds URL for data files as a string
    URL url; //holds URL as a URL for data files
    Scanner buttonMaker; //scanner for making flavor and topping buttons
    //stores ice cream items for creating a reciept
    ArrayList<Item> items = new ArrayList<>();
    //arraylist serve no functionality besides fulfilling the requirement for assignement
    ArrayList<FlavorButton> flavorslist = new ArrayList<>();
    ArrayList<ToppingButton> toppingslist = new ArrayList<>();
    JFrame displayFrame;//Jframe for displaying order interface
    //sets currency format
    NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
    //creates buttons for GUI
    //buttons for selecting containers
    JButton dishButton = new JButton("Dish");
    JButton coneButton = new JButton("Cone");
    //buttons to edit/complete order
    JButton cancelButton = new JButton("Cancel");
    JButton finishButton = new JButton("Finish");
    JButton closeButton = new JButton("Close");
    //Jframe elements editted by actionListeners or static methods
    //creates a label for choosing toppings 
    JLabel toppingLabel = new JLabel("Choose topping(s)");
    JPanel toppingPanel = new JPanel();//panel for topping buttons    
    JPanel flavorPanel = new JPanel();//panel for flavor buttons    
    JPanel containerPanel = new JPanel();//creates a panel to store the buttons

    public Assignment3() throws IOException {
        scoops = 0;// sets number of scoops to zero by default
        //set up the JFrame display settings
        displayFrame = new JFrame("Khaos Kremes");
        Toolkit tk = Toolkit.getDefaultToolkit();
        int frameWidth = (int) tk.getScreenSize().getWidth() * 4 / 5;
        int frameHeight = (int) tk.getScreenSize().getHeight() * 4 / 5;
        int startWidth = frameWidth / 8;
        int startHeight = frameHeight / 16;
        displayFrame.setSize(frameWidth, frameHeight);
        displayFrame.setLocation(startWidth, startHeight);
        displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayFrame.setLayout(new FlowLayout(FlowLayout.CENTER, (int) tk.getScreenSize().getWidth(), 30));
        //the following code adds content to the JFrame
        //creates label with instructions for selecting a container
        JLabel containerLabel = new JLabel("Step 1: Select Either a Dish or a Cone");
        displayFrame.add(containerLabel);// adds the label to the container
        //adds container actionListeners to container buttons
        dishButton.addActionListener(new containerAction());
        coneButton.addActionListener(new containerAction());
        //add buttons to the panel
        containerPanel.add(dishButton);
        containerPanel.add(coneButton);
        displayFrame.add(containerPanel);//adds the panel to the frame
        //creates label with instructions for selecting ice cream
        JLabel flavorLable = new JLabel("Choose Flavor(s), up to 3 scoops");
        displayFrame.add(flavorLable); //add the label to the frame
        //code to create flavor buttons
        //file containing flavor info
        address = "http://users.cis.fiu.edu/~kraynek/COP3337-assignments/Spring-2013/flavors.data";
        url = new URL(address);//url variable for scanner
        buttonMaker = new Scanner(url.openStream()); //reads input from file   
        buttonMaker.useDelimiter("[:\n\r]+");// sets delimeter to : and endlines
        while (buttonMaker.hasNext()) {//while tokens exist
            //add a flavor button using the flavor and cost of each line, converts cost to a double
            flavorPanel.add(new FlavorButton(buttonMaker.next(), Double.parseDouble(buttonMaker.next())));
            //this line adds the button to a FlavorButton Array list, simply to satisfy assignment.
            flavorslist.add((FlavorButton) flavorPanel.getComponent(flavorPanel.getComponentCount() - 1));
        }//end while loop    
        //disable flavor buttons
        enableButtons(flavorPanel, false);
        displayFrame.add(flavorPanel);//adds flavor panel to the frame
        //label is invisible until toppings are selectable
        toppingLabel.setVisible(false);
        displayFrame.add(toppingLabel);//adds label to Frame
        //code to create topping buttons
        //same code as flavors except reading topping.data file
        address = "http://users.cis.fiu.edu/~kraynek/COP3337-assignments/Spring-2013/toppings.data";
        url = new URL(address);
        buttonMaker = new Scanner(url.openStream());
        buttonMaker.useDelimiter("[:\n\r]+");
        while (buttonMaker.hasNext()) {
            //adds new toppingButton using topping and cost tokens from file converting cost to a double
            toppingPanel.add(new ToppingButton(buttonMaker.next(), Double.parseDouble(buttonMaker.next())));
            //as per assignment guidelines, buttons are added to an Arraylist
            toppingslist.add((ToppingButton) toppingPanel.getComponent(toppingPanel.getComponentCount() - 1));
        }//end while loop    
        //disable flavor buttons
        enableButtons(toppingPanel, false);
        toppingPanel.setVisible(false);//topping panel is invisible until enabled
        displayFrame.add(toppingPanel);//adds the panel to the frame
        //disables toppings buttons until after toppings for dish are selected
        //disableButtons(toppings);
        //creates a label for order completion instructions
        JLabel orderLabel = new JLabel("Edit or Complete Your Order Here");
        displayFrame.add(orderLabel);//adds label to frame
        JPanel orderPanel = new JPanel();//creates panel for order buttons
        //adds actionListeners for each button
        finishButton.addActionListener(new FinishAction());
        closeButton.addActionListener(new CloseAction());
        cancelButton.addActionListener(new CancelAction());
        //adds order buttons to order panel
        orderPanel.add(cancelButton);
        orderPanel.add(finishButton);
        orderPanel.add(closeButton);
        displayFrame.add(orderPanel);//adds panel to frame
        dish = false;//a dish container is not initially selected
        displayFrame.setVisible(true);//makes frame visible at start
    }

    public static void main(String[] args) throws IOException {
        new Assignment3();//calls assignment3 object constructor
    }//end main method

    //item interface
    interface Item {
        //unimplemented methods to get variables

        public String getDescription();

        public double getCost();
    }//end item interface

    //cone class
    class Cone implements Item {

        @Override
        public String getDescription() {//returns a description
            return "Cone with ";
        }

        @Override
        public double getCost() {//returns a cost of a cone
            return 0.70;
        }
    }//end cone class

    // dish class
    class Dish implements Item {

        @Override
        public String getDescription() {//returns a description
            return "Dish with ";
        }

        @Override
        public double getCost() {//returns cost of a cup
            return 0.10;
        }
    }//end dish class

    //ItemWrapper class
    class ItemWrapper implements Item {

        Item item;//an item class object

        public ItemWrapper(Item item) {
            this.item = item;// sets item to parameter variable
        }

        @Override
        public String getDescription() {
            return item.getDescription();//uses item's getDescription method
        }

        @Override
        public double getCost() {
            return item.getCost();//uses parameter item's getCost method
        }
    }// end itemWrapper class

    //Flavor class
    class Flavor extends ItemWrapper {

        String flavor;//the type of flavor
        double cost;//this flavor's cost

        public Flavor(Item item, String flavor, double cost) {
            super(item);//calls ItemWrapper constructor
            this.flavor = flavor;//sets the flavor to the parameter
            this.cost = cost; //sets cost to the passed parameter
        }

        //adds :'flavor': to the previous item's description 
        @Override
        public String getDescription() {
            return item.getDescription() + " :" + flavor + ":";

        }

        //adds to the previous item's cost
        @Override
        public double getCost() {
            return item.getCost() + cost;
        }
    }//end Flavor class

    //Topping Class
    class Topping extends ItemWrapper {

        String topping;//the type of topping
        double cost;//cost of the topping

        public Topping(Item item, String topping, double cost) {
            super(item);//calls the ItemWrapper constructor
            this.topping = topping;//sets the topping object's topping type
            this.cost = cost;//sets it's cost here
        }

        //adds topping name between two colons to passed item's description
        @Override
        public String getDescription() {
            return item.getDescription() + " :" + topping + ":";
        }
        //adds to previous item's cost

        @Override
        public double getCost() {
            return item.getCost() + cost;
        }
    }//end Topping class

    //Button to be used for Ice cream flavors
    class FlavorButton extends JButton {

        private String flavor;//name of flavor
        private double cost;//cost of flavor
        //object used for actionlistener
        private ButtonPress button = new ButtonPress();

        //flavor button constructor
        public FlavorButton(String flavor, double cost) {
            this.flavor = flavor;//sets flavor field
            this.cost = cost;//sets cost field
            super.setText(flavor);//sets the buttons label
            this.addActionListener(button);//adds the actionListener below
        }
        //class to implement actionListener

        class ButtonPress implements ActionListener {
            //method performed when button clicked

            @Override
            public void actionPerformed(ActionEvent event) {
                scoops++;//add a scoop
                String flavor = event.getActionCommand();//string set to button label
                iceCreamItem = new Flavor(iceCreamItem, flavor, cost);//flavor "added" to ice cream item
                if (dish) {//if container is a dish
                    enableButtons(toppingPanel, true);//allow toppings
                    //and make the toppings stuff visible
                    toppingLabel.setVisible(true);
                    toppingPanel.setVisible(true);
                }
                if (scoops == 3) {//if user has chosen 3 scoops
                    enableButtons(flavorPanel, false);//disable flavor buttons
                }
            }//end actionPerformed()
        }//end ButtonPress class
    }//end FlavorButton class

    //topping Button used for toppings
    class ToppingButton extends JButton {

        private String topping;//topping name
        private double cost;//topping cost
        //object to pass actionlistener
        private ButtonPress button = new ButtonPress();

        //topping button constructor
        public ToppingButton(String topping, double cost) {
            //initiates variables like flavor button
            this.topping = topping;
            this.cost = cost;
            super.setText(topping);
            this.addActionListener(button);//actionlistener
        }
        //returns topping name of button

        public String getTopping() {
            return topping;
        }
        //class to pass actionlistener to button

        class ButtonPress implements ActionListener {
            //method performed when button pressed

            @Override
            public void actionPerformed(ActionEvent event) {
                String topping = event.getActionCommand(); //topping set to button label
                dish = false;//set dish to false
                iceCreamItem = new Topping(iceCreamItem, topping, cost);//add topping to ice cream item
                enableButtons(flavorPanel, false);
                //check all components in toppingPanel
                for (Component component : toppingPanel.getComponents()) {
                    //if the component's topping name is the same as the pressed button's label
                    if (((ToppingButton) component).getTopping().equals(topping)) {
                        component.setEnabled(false);//disable that button
                        break;//and stop the loop
                    }
                }//end for loop
            }//end actionPerformed()
        }//end ButtonPress class
    }//end ToppingsButton class

    //class for implementing the action Listener for selecting a container
    class containerAction implements ActionListener {
        //method performed when button is pressed

        @Override
        public void actionPerformed(ActionEvent event) {
            //Store button command in a string
            String item = event.getActionCommand();
            //check if dish was selected
            if (item.equals("Dish")) {
                iceCreamItem = new Dish();// create a new dish item
                dish = true;//flag to allow topping selection
            }
            //check if cone was selected
            if (item.equals("Cone")) {
                iceCreamItem = new Cone(); //create new cone item
            }
            //disable the container buttons
            enableButtons(containerPanel, false);
            //enable flavor buttons
            enableButtons(flavorPanel, true);
        }
    }//end container action class
    //action listener for cancel button

    class CancelAction implements ActionListener {
        //action method

        @Override
        public void actionPerformed(ActionEvent event) {
            //if the item is not a container nor null, proceed with code
            if (iceCreamItem != null) {
                //switch statement to see what class object is before canceling addition
                switch (iceCreamItem.getClass().toString()) {
                    case "class Assignment3$Topping": //if it was a topping
                        //check to see which topping is being cancelled by checking the ice cream item's current topping
                        for (Component component : toppingPanel.getComponents()) {
                            //if the component's topping name is the same as the pressed button's label
                            if (((ToppingButton) component).getTopping().equals(((Topping) iceCreamItem).topping)) {
                                component.setEnabled(true);//enable that button
                                break;//and stop the loop
                            }//end if statement
                        }//end for loop
                        break;//break out of case 1
                    //if it was a scoop
                    case "class Assignment3$Flavor":
                        scoops--;//subtract from scoops
                        break;//break from case 2
                    default://if it is a container
                        enableButtons(containerPanel, true);//re-enable the container buttons
                        iceCreamItem = null;
                        //disable flavor and toppings buttons and hide toppings buttons
                        enableButtons(flavorPanel, false);
                        dish = false;//set dish to false
                }//end switch statement
                //if the item was not set to null during above switch
                if (iceCreamItem != null) {
                    iceCreamItem = ((ItemWrapper) iceCreamItem).item;//"delete" addition

                    //switch statement for after change
                    switch (iceCreamItem.getClass().toString()) {
                        //if it is a topping
                        case "class Assignment3$Topping":
                            break;//do nothing
                        //if it is a flavor
                        case "class Assignment3$Flavor":
                            if (dish) {//if the container is a dish
                                //make sure toppings are selectable
                                enableButtons(toppingPanel, true);
                            }//end if
                            if (scoops < 3) {//if there are less than 3 scoops
                                //enable the flavor buttons again
                                enableButtons(flavorPanel, true);
                            }
                            break;//exit switch
                        default://if it is a container at the end
                            //disable and hide the topping buttons
                            enableButtons(toppingPanel, false);
                            toppingLabel.setVisible(false);
                            toppingPanel.setVisible(false);
                            break;//then exit switch statement
                    }
                }
            } //if the iceCreamItem is empty
            else {
                JOptionPane.showMessageDialog(null, "There is nothing to cancel!");
            }
        }
    }

    //action listener for finish button
    class FinishAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            //if nothing is ordered, and ice cream item is empty, display message then end action
            if (iceCreamItem == null) {
                JOptionPane.showMessageDialog(null, "You have to order something first!");
                return;
            }
            //store the class as a string
            String type = iceCreamItem.getClass().toString();
            //if the string contains cone or dish in it, display message and end action
            if (type.contains("Cone") || type.contains("Dish")) {
                JOptionPane.showMessageDialog(null, "Must choose ice cream!");
                return;
            }// end if          
            items.add(iceCreamItem);//add ice cream item to the arraylist of items
            String out = "Your Order is: \n";//initiate output string
            String description = iceCreamItem.getDescription();
            for (Component component : flavorPanel.getComponents()) {
                description = fixFlavors(((FlavorButton) component).getText(), description);
            }
            for (Component component : toppingPanel.getComponents()) {
                description = fixToppings(((ToppingButton) component).getText(), description);
            }
            out += description + "\n";
            out += money.format(iceCreamItem.getCost());
            JOptionPane.showMessageDialog(null, out);
            //reset buttons, scoops, the ice cream item and dish flag
            enableButtons(containerPanel, true);
            enableButtons(toppingPanel, false);
            enableButtons(flavorPanel, false);
            toppingLabel.setVisible(false);
            toppingPanel.setVisible(false);
            iceCreamItem = null;
            scoops = 0;
            dish = false;
        }//end actionPerformed()
    }//end FinishAction class

    //closes out order system 
    class CloseAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {//action method
            //if nothing is ordered, display message and exit
            if (items.isEmpty()) {
                JOptionPane.showMessageDialog(null, "nothing has been ordered");
            } else {//otherwise, displays all previous orders in the same format as the finish button for each item
                String out = "Order History: \n\n";//initiate output string
                for (int i = 0; i < items.size(); i++) {
                    String order = items.get(i).getDescription();
                    for (Component component : flavorPanel.getComponents()) {
                        order = fixFlavors(((FlavorButton) component).getText(), order);
                    }
                    for (Component component : toppingPanel.getComponents()) {
                        order = fixToppings(((ToppingButton) component).getText(), order);
                    }
                    order += " " + money.format(items.get(i).getCost()) + "\n\n";
                    out += order;
                }

                JOptionPane.showMessageDialog(null, out);//displays orders
            }
            System.exit(0);//exits program
        }
    }

    //fixes the way the flavors are displayed
    String fixFlavors(String flavor, String s) {

        String match = ":" + flavor + ":";//sets the string to match based on the flavor input
        int first = s.indexOf(match);//starting location of flavor in the string s (description)
        if (first == -1) {//if no match is found, return the unaltered description
            return s;
        }
        int next = s.indexOf(match, first + match.length());//checks for another occurance of the string
        if (next == -1) {//if no other is found, it means there is only 1 scoop of that flavor
            s = s.substring(0, first - 1) + (s.contains("scoop") && !s.contains("and") ? " and" : "") + " a scoop of " + flavor + (s.substring(first + match.length()).length() > 0 ? "," : ".") + s.substring(first + match.length());
            return s;
        } // end if
        s = s.substring(0, next) + s.substring(next + match.length());//excludes the second occurance of the flavor
        next = s.indexOf(match, first + match.length());//checks for a third
        if (next == -1) {//if one is not found, that means there are 2 scoops fo the flavor
            s = s.substring(0, first - 1) + (s.contains("scoop") && !s.contains("and") ? " and" : "") + " a double scoop of " + flavor + (s.substring(first + match.length()).length() > 0 ? "," : ".") + s.substring(first + match.length());
            return s;
        } // end if
        s = s.substring(0, next) + s.substring(next + match.length());//removes third occurance of the string
        //there are 3 scoops of the flavor and the description will reflect that
        s = s.substring(0, first - 1) + (s.contains("scoop") && !s.contains("and") ? " and" : "") + " a triple scoop of " + flavor + (s.substring(first + match.length()).length() > 0 ? "," : ".") + s.substring(first + match.length());
        return s;
    } // end fixFlavors

    //method to fix the toppings in the description
    String fixToppings(String topping, String s) {
        String match = ":" + topping + ":";//sets the string to match based on the topping input   
        int first = s.indexOf(match);//starting location of topping in the string s (description)
        if (first == -1) {//if no match is found, return the unaltered description
            return s;
        }        //else return an altered description
        s = s.substring(0, first - 1) + (s.substring(10).contains("with") ? " and " : " with ") + topping + (s.substring(first + match.length()).length() > 0 ? "," : ".") + s.substring(first + match.length());
        return s;
    }

    //static method for enabling buttons in a panel
    static public void enableButtons(JPanel panel, boolean x) {
        for (Component components : panel.getComponents()) {
            components.setEnabled(x);
        }
    }
}//end Assignment3 class
