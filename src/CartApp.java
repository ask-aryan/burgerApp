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

    
    JTextArea change=new JTextArea();
    JTextField amtRecieved= new JTextField("0");
    JTextField amtGive= new JTextField();

    private final JPanel mainPanel;
    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel panel3;
    private final JRadioButton r1;
    private final JRadioButton r2;
    private final JRadioButton r3;
    private static int[] noteValue = { 500, 200, 100, 50, 10 };
    private static int[] notesAvailable = { 100, 100, 100, 100, 100 };

    public CartApp() {
        frame = new JFrame("Burger Company");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 900 , 650);
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
        panel1.setBackground(Color.LIGHT_GRAY);
        panel1.setLayout(null);
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
        quantity.setEditable(false);
        change.setEditable(false);

        
           
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

        listOfitems.setBounds(20, 250, 120, 30);
        table.setBounds(20,300,400,120);
        quantity.setBounds(20, 440, 110, 50 );
        amountTextField.setBounds(180,440,240,50);

        mainPanel.setBounds(470,20,380,470);

        clearButton.setBounds(320, 520, 100, 50);
        checkoutButton.setBounds(470,520,100,50);

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
        frame.add(mainPanel);

        newTotal();   
        frame.setVisible(true);
    }

    private JPanel createPanel1Content() {
      JPanel panel = new JPanel();

      JLabel amtRecievedL= new JLabel("Amount Recieved:");
      JLabel amtGiveL= new JLabel("Change To be Given:");
      JLabel changeL= new JLabel("Breakup of the Change To be Given:");   
      JLabel fiveL=new JLabel("500 x ");
      JLabel twoL=new JLabel("200 x ");
      JLabel hunL=new JLabel("100 x ");
      JLabel fiftyL=new JLabel("50 x ");
      JLabel tenL=new JLabel("10 x ");
      JTextField five= new JTextField("0");
      JTextField two= new JTextField("0");
      JTextField hun= new JTextField("0");
      JTextField fifty= new JTextField("0");
      JTextField ten= new JTextField("0");
      JButton calculate=new JButton("Calculate");

      amtGive.setEditable(false);
      change.setEditable(false);

      calculate.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
          int amountReceived = Integer.parseInt(amtRecieved.getText());
          int changeAmount = amountReceived - totalAmount;
          amtGive.setText(String.valueOf(changeAmount)); 
          int amountToWithdraw = Integer.parseInt(amtGive.getText());
          
          if (changeAmount < 0) {
            change.setText("Insufficient amount received.");
            return;
        }
          if (amountToWithdraw % 5 != 0) {
            change.setText("Please enter a valid amount.");
            return;
          }
          giveNote(amountToWithdraw, 0,change);
        }
      private static void giveNote(int amount, int index, JTextArea change) {
        if (amount <= 0 || index >= noteValue.length) {
            return;
        }
    
        int notes = amount / noteValue[index];
        int available = availableNotes(noteValue[index]);
        int remainingAmount;
    
        if (notes <= available) {
            amount -= notes * noteValue[index];
            change.append(notes + " x " + noteValue[index] + "₹ \n");
            updateDrawer(noteValue[index], notes);
        } else {
            amount -= available * noteValue[index];
            change.append(available + " x " + noteValue[index] + "₹\n");
            updateDrawer(noteValue[index], available);
        }
    
        remainingAmount = amount;
        giveNote(remainingAmount, index + 1, change);
    }
  
      private static int availableNotes(int noteValue) {
          switch (noteValue) {
              case 500:
                  return notesAvailable[0];
              case 200:
                  return notesAvailable[1];
              case 100:
                  return notesAvailable[2];
              case 50:
                  return notesAvailable[3];
              case 10:
                  return notesAvailable[4];
              
              default:
                  return 0;
          }
      }
    
      private static void updateDrawer(int noteValue, int notesGiven) {
          switch (noteValue) {
              case 500:
                  notesAvailable[0] -= notesGiven;
                  break;
              case 200:
                  notesAvailable[1] -=notesGiven;
                  break;
              case 100:
                  notesAvailable[2] -= notesGiven;
                  break;
              case 50:
                  notesAvailable[3] -= notesGiven;
                  break;
              case 10:
                  notesAvailable[4] -= notesGiven;
                  break;
              default:
                  break;
          }
      }
    
      });

      fiveL.setBounds(30, 60, 45,20 );
      five.setBounds(30, 80, 45,30 );
      twoL.setBounds(95, 60, 45, 20);
      two.setBounds(95, 80, 45, 30);
      hunL.setBounds(160, 60,45, 20);
      hun.setBounds(160, 80,45, 30);
      fiftyL.setBounds(225, 60, 45,20);
      fifty.setBounds(225, 80, 45,30);
      tenL.setBounds(290, 60, 45,20);
      ten.setBounds(290, 80, 45,30);
      

      amtRecievedL.setBounds(30, 10, 140, 30);
      amtRecieved.setBounds(170, 10, 180, 30);   
      amtGiveL.setBounds(30, 185, 140, 30);
      amtGive.setBounds(170, 185, 180, 30);
      changeL.setBounds(30, 235, 300, 20);
      change.setBounds(30, 265, 320, 150);
      calculate.setBounds(150, 135, 100, 30);    
      panel.add(five);
      panel.add(two);
      panel.add(hun);
      panel.add(fifty);
      panel.add(ten);
      panel.add(amtRecieved);
      panel.add(amtGive);
      panel.add(change);
      panel.add(fiveL);
      panel.add(twoL);
      panel.add(hunL);
      panel.add(fiftyL);
      panel.add(tenL);
      panel.add(amtGiveL);
      panel.add(amtRecievedL);
      panel.add(changeL);
      panel.add(calculate);
      return panel;
  }

  private JPanel createPanel2Content() {
      // Create content for panel 2 (replace with your specific content)
      JPanel panel = new JPanel();
      panel.add(new JLabel("UPI."));
      return panel;
  }

  private JPanel createPanel3Content() {
      // Create content for panel 3 (replace with your specific content)
      JPanel panel = new JPanel();
      panel.add(new JLabel("Online"));
      return panel;
  }
  
  @Override
    public void actionPerformed(ActionEvent e) {
      
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
        change.setText("");
        amtRecieved.setText("0");
        amtGive.setText("");
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
