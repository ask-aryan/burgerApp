import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartApp {
    JFrame frame;
    List<String> orderedItems = new ArrayList<>(); 
    int totalAmount = 0;
    int totalQuantity=0;
    JTextField amountTextField = new JTextField(10);
    JTextField quantity = new JTextField(5);
    JTextArea table = new JTextArea(20, 10);

    public CartApp() {
        frame = new JFrame("Burger Company");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setBounds(100, 100, 700, 600);
        frame.setLocationRelativeTo(null);
        Container c = frame.getContentPane();
        c.setLayout(null);
           
        ImageIcon icon = new ImageIcon("src\\burger (2).png"); 
        JLabel pic1 = new JLabel(icon); 
        JLabel pic2 = new JLabel(icon);
        JLabel pic3 = new JLabel(icon);

        JLabel mcVaggieLabel = new JLabel("Mc Veggie");
        JLabel mcMahaLabel = new JLabel("Mc Maharaja");
        JLabel mcAaluLabel = new JLabel("Mc Aloo Tikki");

        JLabel mcVaggiePriceLabel = new JLabel("200");
        JLabel mcMahaPriceLabel = new JLabel("150");
        JLabel mcAaluPriceLabel = new JLabel("50");
        
        JButton addVeggie = new JButton("+");
        JButton addMaha = new JButton("+");
        JButton addAalu = new JButton("+");

        JButton remVeggie = new JButton("-");
        JButton remMaha = new JButton("-");
        JButton remAalu = new JButton("-");
       
        JLabel listOfitems = new JLabel("List of ordered items");
        JButton checkoutButton = new JButton("Checkout");
        JButton clearButton =new JButton("Clear Cart");

        amountTextField.setEditable(false);
        table.setEditable(false);
           
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              clearValues(); 
            }
          });
        addVeggie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItem(mcVaggieLabel.getText(), Integer.parseInt(mcVaggiePriceLabel.getText()));
                System.out.println("item added");
            }
          });
        addMaha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItem(mcMahaLabel.getText(), Integer.parseInt(mcMahaPriceLabel.getText()));    
                System.out.println("item added");           
            }
          });
        addAalu.addActionListener(new ActionListener() {        
            public void actionPerformed(ActionEvent e) {
                addItem(mcAaluLabel.getText(), Integer.parseInt(mcAaluPriceLabel.getText()));    
                System.out.println("item added");     
            }
          });
        remVeggie.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                if(totalQuantity==0){
                    System.out.println("cart is empty");
                }
                else{
                    remItem(mcVaggieLabel.getText(), Integer.parseInt(mcVaggiePriceLabel.getText()));
                }
            }
          });
        remMaha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    if(totalQuantity==0){
                        System.out.println("cart is empty");
                    }
                    else{
                        remItem(mcMahaLabel.getText(), Integer.parseInt(mcMahaPriceLabel.getText()));    
                }
            }
          });
        remAalu.addActionListener(new ActionListener() {   
            public void actionPerformed(ActionEvent e) {
                if(totalQuantity==0){
                    System.out.println("cart is empty");
                }
                else{
                remItem(mcAaluLabel.getText(), Integer.parseInt(mcAaluPriceLabel.getText()));
                }
            }
          });
        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Processing the order...");
                if (orderedItems.isEmpty()) {   
                    JOptionPane.showMessageDialog(null, "Please add items to your order before checkout.");
                    return;
                  }
                  else{
                    JOptionPane.showMessageDialog(null, "Your Order is placed successfully.");
                    clearValues();
                  }
                System.out.println("Order is successfully Placed");
            }
          });
        
        mcVaggieLabel.setBounds(20, 20, 80, 30);
        mcVaggiePriceLabel.setBounds(25, 160, 80, 30);
        pic1.setBounds(20,60,80,80);
        addVeggie.setBounds(20, 200, 50, 30);
        remVeggie.setBounds(75, 200, 50, 30);

        mcMahaLabel.setBounds(170, 20, 80, 30);
        pic2.setBounds(170,60,80,80);
        mcMahaPriceLabel.setBounds(175, 160, 80, 30);
        addMaha.setBounds(170, 200, 50, 30);
        remMaha.setBounds(225, 200, 50, 30);

        mcAaluLabel.setBounds(320, 20, 80, 30);
        pic3.setBounds(320,60,80,80);
        mcAaluPriceLabel.setBounds(325, 160, 80, 30);
        addAalu.setBounds(320, 200, 50, 30);
        remAalu.setBounds(375, 200, 50, 30);

        amountTextField.setBounds(180,300,200,50);
        checkoutButton.setBounds(420,300,100,50);
        quantity.setBounds(20, 300, 100, 50 );
        
        listOfitems.setBounds(470, 20, 120, 30);
        table.setBounds(470,70,120,150);
        clearButton.setBounds(20, 420, 100, 30);

        frame.add(pic1);
        frame.add(pic2);
        frame.add(pic3);
        frame.add(mcVaggieLabel);
        frame.add(mcVaggiePriceLabel);
        frame.add(addVeggie);
        frame.add(mcMahaLabel);
        frame.add(mcMahaPriceLabel);
        frame.add(addMaha);
        frame.add(mcAaluLabel);
        frame.add(mcAaluPriceLabel);
        frame.add(addAalu);
        frame.add(amountTextField);
        frame.add(checkoutButton);
        frame.add(quantity);
        frame.add(listOfitems);
        frame.add(table);
        frame.add(clearButton);
        frame.add(remVeggie);
        frame.add(remMaha);
        frame.add(remAalu);
        frame.add(quantity);

        newTotal();   
        frame.setVisible(true);
    }
    private void addItem(String itemName, int price) {
        orderedItems.add(itemName+"\n");
        totalAmount += price;
        totalQuantity ++;
        newTotal();
    }
    private void remItem(String itemName, int price) {
      int index = orderedItems.indexOf(itemName + "\n");
      if (index != -1) {
        orderedItems.remove(index);
        totalAmount -= price;
        totalQuantity--;
        System.out.println("item removed");
        newTotal();
      } else {
        System.out.println("Item not found in the cart.");
      }
    }
    private void clearValues() {
      if(totalAmount==0){
        System.out.println("Cart is Empty.");
      }
      else{
        orderedItems.clear(); 
        totalAmount = 0; 
        totalQuantity = 0; 
        System.out.println("All the values have been cleared.");
        newTotal();
      }
    }
    private void newTotal() {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> itemCount = new HashMap<>();
        for (String item : orderedItems) {
          itemCount.put(item.trim(), itemCount.getOrDefault(item.trim(), 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : itemCount.entrySet()) {
          sb.append(entry.getKey()).append(" x").append(entry.getValue()).append("\n");
        }
        amountTextField.setText("Total: ₹" + totalAmount);
        quantity.setText("Total Qty.:" + totalQuantity);
        table.setText(sb.toString()); 
    }
    public static void main(String[] args) {
        new CartApp();
    }
}