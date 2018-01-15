
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Bill Kraynek
 */
public class IceCreamShopLite {

    Item iceCreamItem;
    JButton dishButton = new JButton("Dish");
    JButton coneButton = new JButton("Cone");
    JButton vanillaButton = new JButton("Vanilla");
    JButton chocolateButton = new JButton("Chocolate");
    JButton strawberryButton = new JButton("Strawberry");
    JButton chocolateSyrupButton = new JButton("Chocolate Syrup");
    JButton whippedCreamButton = new JButton("Whipped Cream");
    JButton finishButton = new JButton("Finish");
    int scoops;
    boolean dish;
    ArrayList<Item> items = new ArrayList<Item>();
    JFrame theFrame;
    NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);

    public IceCreamShopLite() {
        theFrame = new JFrame("Bill's Ice Cream Shop");
        Toolkit tk = Toolkit.getDefaultToolkit();
        int width = (int) (tk.getScreenSize().getWidth()) / 2;
        int height = (int) (tk.getScreenSize().getHeight()) / 2;
        theFrame.setSize(width, height);
        theFrame.setLocation(width / 4, height / 4);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setLayout(new FlowLayout(FlowLayout.CENTER, width, 30));
        JLabel coneOrDishLable = new JLabel("Dish or Cone");
        theFrame.add(coneOrDishLable);
        dishButton.addActionListener(new DishOrConeAction());
        coneButton.addActionListener(new DishOrConeAction());
        JPanel coneDishPanel = new JPanel();
        coneDishPanel.add(dishButton);
        coneDishPanel.add(coneButton);
        theFrame.add(coneDishPanel);
        JLabel flavorLable = new JLabel("Choose Flavor(s). 1 to 3 scoops only");
        theFrame.add(flavorLable);
        scoops = 0;
        vanillaButton.addActionListener(new FlavorAction());
        chocolateButton.addActionListener(new FlavorAction());
        strawberryButton.addActionListener(new FlavorAction());
        vanillaButton.setEnabled(false);
        chocolateButton.setEnabled(false);
        strawberryButton.setEnabled(false);
        JPanel flavorPanel = new JPanel();
        flavorPanel.add(vanillaButton);
        flavorPanel.add(chocolateButton);
        flavorPanel.add(strawberryButton);
        theFrame.add(flavorPanel);
        JLabel toppingLable = new JLabel("Choose topping(s) (for dish of ice cream only).");
        theFrame.add(toppingLable);
        chocolateSyrupButton.addActionListener(new ToppingAction());
        whippedCreamButton.addActionListener(new ToppingAction());
        JPanel toppingPanel = new JPanel();
        toppingPanel.add(chocolateSyrupButton);
        toppingPanel.add(whippedCreamButton);
        theFrame.add(toppingPanel);
        chocolateSyrupButton.setEnabled(false);
        whippedCreamButton.setEnabled(false);
        theFrame.add(finishButton);
        finishButton.addActionListener(new FinishAction());
        dish = false;
        theFrame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new IceCreamShopLite();
    }

    interface Item {

        public String getDescription();

        public double getCost();
    }

    class Cone implements Item {

        public String getDescription() {
            return "Cone with";
        }

        public double getCost() {
            return 1.00;
        }
    }

    class Dish implements Item {

        public String getDescription() {
            return "Dish of";
        }

        public double getCost() {
            return 0.10;
        }
    }

    class ItemWrapper implements Item {

        Item item;

        public ItemWrapper(Item item) {
            this.item = item;
        }

        public String getDescription() {
            return item.getDescription();
        }

        public double getCost() {
            return item.getCost();
        }
    }

    class Flavor extends ItemWrapper {

        String flavor;
        double cost;

        public Flavor(Item item, String flavor, double cost) {
            super(item);
            this.flavor = flavor;
            this.cost = cost;
        }

        public String getDescription() {
            return item.getDescription() + " :" + flavor + ":";
        }

        public double getCost() {
            return item.getCost() + cost;
        }
    }

    class Topping extends ItemWrapper {

        String topping;
        double cost;

        public Topping(Item item, String topping, double cost) {
            super(item);
            this.topping = topping;
            this.cost = cost;
        }

        public String getDescription() {
            return item.getDescription() + " with " + topping;
        }

        public double getCost() {
            return item.getCost() + cost;
        }
    }

    class DishOrConeAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String item = event.getActionCommand();
            if (item.equals("Dish")) {
                iceCreamItem = new Dish();
                dish = true;
            }
            if (item.equals("Cone")) {
                iceCreamItem = new Cone();
            }
            dishButton.setEnabled(false);
            coneButton.setEnabled(false);
            vanillaButton.setEnabled(true);
            chocolateButton.setEnabled(true);
            strawberryButton.setEnabled(true);
        }
    }

    class FlavorAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            scoops++;
            String flavor = event.getActionCommand();
            if (flavor.equals("Vanilla")) {
                iceCreamItem = new Flavor(iceCreamItem, flavor, 1.00);
            }
            if (flavor.equals("Chocolate")) {
                iceCreamItem = new Flavor(iceCreamItem, flavor, 1.25);
            }
            if (flavor.equals("Strawberry")) {
                iceCreamItem = new Flavor(iceCreamItem, flavor, 1.50);
            }
            if (dish) {
                chocolateSyrupButton.setEnabled(true);
                whippedCreamButton.setEnabled(true);
            }
            if (scoops == 3) {
                vanillaButton.setEnabled(false);
                chocolateButton.setEnabled(false);
                strawberryButton.setEnabled(false);
            }
        }
    }

    class ToppingAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String topping = event.getActionCommand();
            dish = false;
            if (topping.equals("Chocolate Syrup")) {
                iceCreamItem = new Topping(iceCreamItem, topping, 1.50);
                chocolateSyrupButton.setEnabled(false);
            }
            if (topping.equals("Whipped Cream")) {
                iceCreamItem = new Topping(iceCreamItem, topping, .75);
                whippedCreamButton.setEnabled(false);
            }
        }
    }

    class FinishAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (iceCreamItem == null) {
                return;
            }
            String type = iceCreamItem.getClass().toString();
            if (type.contains("Cone") || type.contains("Dish")) {
                JOptionPane.showMessageDialog(null, "Must choose ice cream!");
                return;
            }// end if
            items.add(iceCreamItem);
            String out = "Your Order is: \n";
            String description = iceCreamItem.getDescription();
            description = fixFlavors("Vanilla", description);
            description = fixFlavors("Chocolate", description);
            description = fixFlavors("Strawberry", description);
            out += description + "\n";
            out += money.format(iceCreamItem.getCost());
            JOptionPane.showMessageDialog(null, out);
            dishButton.setEnabled(true);
            coneButton.setEnabled(true);
            chocolateSyrupButton.setEnabled(false);
            whippedCreamButton.setEnabled(false);
            vanillaButton.setEnabled(false);
            chocolateButton.setEnabled(false);
            strawberryButton.setEnabled(false);
            iceCreamItem = null;
            scoops = 0;
            dish = false;
        }
    }

    String fixFlavors(String flavor, String s) {
        String match = ":" + flavor + ":";
        int first = s.indexOf(match);
        if (first == -1) {
            return s;
        }
        int next = s.indexOf(match, first + match.length());
        if (next == -1) {
            s = s.substring(0, first - 1) + " a scoop of " + flavor + s.substring(first + match.length());
            return s;
        } // end if
        s = s.substring(0, next) + s.substring(next + match.length());
        next = s.indexOf(match, first + match.length());
        if (next == -1) {
            s = s.substring(0, first - 1) + " a double scoop of " + flavor + s.substring(first + match.length());
            return s;
        } // end if
        s = s.substring(0, next) + s.substring(next + match.length());
        s = s.substring(0, first - 1) + " a triple scoop of " + flavor + s.substring(first + match.length());
        return s;
    } // end fixFlavors
}
