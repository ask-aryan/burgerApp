import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartApp implements ActionListener {
    JFrame frame;
    List<String> orderedItems = new ArrayList<>(); 
    int totalAmount = 0;
    int totalQuantity=0;
    JTextField amountTextField = new JTextField(10);
    JTextField quantity = new JTextField(5);
    JTextArea table = new JTextArea(20, 10);
    private final JPanel mainPanel;
    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel panel3;
    private final JRadioButton r1;
    private final JRadioButton r2;
    private final JRadioButton r3;

    public CartApp() {
        frame = new JFrame("Burger Company");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setBounds(100, 100, 700, 600);
        frame.setLocationRelativeTo(null);
        Container c = frame.getContentPane();
        c.setLayout(null);
           
        var icon = new ImageIcon("burgerApp/src/burger (2).png"); 
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

        r1=new JRadioButton("Cash");    
        r2=new JRadioButton("Card");
        r3=new JRadioButton("UPI");
        r1.setSelected(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        panel1 = createPanel1Content();
        panel2 = createPanel2Content();
        panel3 = createPanel3Content();

        ButtonGroup bg= new ButtonGroup();
        bg.add(r1);bg.add(r2);bg.add(r3);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(r1);
        buttonPanel.add(r2);
        buttonPanel.add(r3);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(panel1, BorderLayout.CENTER);

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
          r1.addActionListener((ActionListener) this);
          r2.addActionListener((ActionListener) this);
          r3.addActionListener((ActionListener) this);
          
        
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

        listOfitems.setBounds(470, 20, 120, 30);
        table.setBounds(470,70,120,150);
        quantity.setBounds(20, 300, 100, 50 );
        amountTextField.setBounds(180,300,200,50);
        // r1.setBounds(420,300,60,30);
        // r2.setBounds(490,300,60,30);
        // r3.setBounds(560,300,60,30); 
        mainPanel.setBounds(420,300,250,200);
        clearButton.setBounds(50, 420, 100, 50);
        checkoutButton.setBounds(250,420,100,50);

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
        //frame.add(r1);frame.add(r2);frame.add(r3);
        frame.add(mainPanel);

        newTotal();   
        frame.setVisible(true);
    }

    private JPanel createPanel1Content() {
      // Create content for panel 1 (replace with your specific content)
      JPanel panel = new JPanel();
      panel.add(new JLabel("This is the content for Option 1."));
      return panel;
  }

  private JPanel createPanel2Content() {
      // Create content for panel 2 (replace with your specific content)
      JPanel panel = new JPanel();
      panel.add(new JLabel("This is the content for Option 2."));
      return panel;
  }

  private JPanel createPanel3Content() {
      // Create content for panel 3 (replace with your specific content)
      JPanel panel = new JPanel();
      panel.add(new JLabel("This is the content for Option 3."));
      return panel;
  }
  
  @Override
    public void actionPerformed(ActionEvent e) {
        // Handle radio button selection
        JRadioButton selectedButton = (JRadioButton) e.getSource();

        if (selectedButton == r1) {
            mainPanel.remove(panel2);
            mainPanel.remove(panel3);
            mainPanel.add(panel1, BorderLayout.CENTER);
        } else if (selectedButton == r2) {
            mainPanel.remove(panel1);
            mainPanel.remove(panel3);
            mainPanel.add(panel2, BorderLayout.CENTER);
        } else if (selectedButton == r3) {
            mainPanel.remove(panel1);
            mainPanel.remove(panel2);
            mainPanel.add(panel3, BorderLayout.CENTER);
        }

        // Optionally, revalidate and repaint for immediate UI update
        mainPanel.revalidate();
        mainPanel.repaint();
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
