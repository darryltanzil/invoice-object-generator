package ui;

import model.Invoice;
import model.InvoiceLineItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * The SwingUI class
 * provides a GUI based interface in which users can interact with the Invoice Generator App
 */
public class SwingUI {
    private JFrame frame;
    private JTable table;
    private TerminalUI terminalUI;

    public SwingUI(TerminalUI terminalUI) {
        this.terminalUI = terminalUI;

        frame = new JFrame("Invoice Generator V1");
        frame.getContentPane().setBackground(Color.white);
        setButtons();

        JLabel image = new JLabel(); //JLabel Creation
        image.setIcon(new ImageIcon(
                new ImageIcon("src/main/ui/save.png").getImage()
                        .getScaledInstance(150, 150, Image.SCALE_DEFAULT))); //Sets the image to be displayed as an icon

        Dimension size = image.getPreferredSize(); //Gets the size of the image
        image.setBounds(130, 30, size.width, size.height); //Sets the location of the image

        frame.add(image);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(400, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // EFFECTS: Sets up the buttons for add invoices and invoice line items
    @SuppressWarnings("methodlength")
    private void setButtons() {

        JButton addInvoice = new JButton("Add Invoice");
        JButton addInvoiceLineItem = new JButton("Add Invoice Line Item");
        JButton save = new JButton("Save Current Invoices");
        JButton viewInvoices = new JButton("View Invoices");
        JButton invoiceLineItems = new JButton("View Invoice Line Items");

        saveButtonListener(save);
        seeInvoicesListener(viewInvoices);
        viewInvoiceLineItem(invoiceLineItems);

        JButton load = new JButton("Load from Previous Save");
        loadButtonListener(load);

        JLabel title = new JLabel("INVOICE GENERATOR", SwingConstants.CENTER);
        title.setForeground(new Color(86, 92, 94));

        addInvoice.setBounds(0, 270, 350, 40);
        addInvoiceLineItem.setBounds(0, 310, 350, 40);
        save.setBounds(0, 350, 350, 40);
        load.setBounds(0, 390, 350, 40);
        viewInvoices.setBounds(0, 430, 350, 40);
        invoiceLineItems.setBounds(0, 470, 350, 40);

        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(0, 190, 300, 350);

        frame.add(addInvoiceLineItem);
        frame.add(addInvoice);
        frame.add(viewInvoices);
        frame.add(invoiceLineItems);
        frame.add(save);
        frame.add(load);
        frame.add(title);

        addInvoice.setSize(400, 34);
        addInvoiceListener(addInvoice);
        addIliListener(addInvoiceLineItem);

        addInvoiceLineItem.setSize(400, 35);
        save.setSize(400, 35);
        load.setSize(400, 35);
        title.setSize(400, 30);
        viewInvoices.setSize(400, 35);
        invoiceLineItems.setSize(400, 35);
    }

    // EFFECTS: button listener for handling the "save" events and saving previous JSON files
    private void saveButtonListener(JButton save) {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "Items have been saved!");
                terminalUI.saveApp();
            }
        });
    }

    // EFFECTS: loading button listener for handling "load" events, and loading the previous JSON files
    private void loadButtonListener(JButton load) {
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminalUI.loadApp();
                showMessageDialog(null, "Items have been loaded!");
            }
        });
    }

    // https://stackoverflow.com/a/5255930/230513

    // MODIFIES: f
    // EFFECTS: button listener for viewing invoices
    private void seeInvoicesListener(JButton seeInv) {
        seeInv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Invoices");
                String[] data2 = terminalUI.getApp().getInvoices().keySet().toArray(new String[0]);
                f.add(new JList(data2));
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                f.setSize(200, 400);
                f.setLocation(100, 150);
            }
        });
    }

    // EFFECTS: for adding invoices
    // TEXT FIELD referenced from
    // https://study.com/academy/lesson/adding-jtexfields-jbuttons-tool-tips-to-a-
    // jframe-in-java.html#:~:text=You%20need%20to%20create%20an,JTextField%20textField%20%3D%20new%20JTextField()%3B
    @SuppressWarnings("methodlength")
    private void addInvoiceListener(JButton addInvoice) {
        addInvoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Add Invoice Item");
                f.setSize(200, 300);
                f.setLocation(100, 150);
                f.setDefaultLookAndFeelDecorated(true);
                JLabel labelM = new JLabel("Add Invoice Item");
                labelM.setFont(new Font("Arial", Font.BOLD, 18));
                labelM.setBounds(25, 50, 200, 30);
                JTextField name = new JTextField();
                addPlaceHolderText(name, "Enter Invoice Name");
                JButton submit = new JButton("Submit");
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        terminalUI.getApp().generateInvoice(name.getText());
                        showMessageDialog(null, "Invoice " + name.getText() + " has been added!");
                    }
                });
                name.setBounds(0, 100, 200, 30);
                submit.setBounds(0, 200, 200, 30);
                f.add(labelM);
                f.add(name);
                f.add(submit);
                f.setLayout(null);
                f.setVisible(true);
                f.setResizable(false);
            }
        });
    }

    // EFFECTS: Invoice Line Item viewer
    @SuppressWarnings("methodlength")
    private void viewInvoiceLineItem(JButton addInvoice) {
        addInvoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("View Invoice Line Item");
                f.setSize(200, 300);
                f.setLocation(100, 150);
                f.setDefaultLookAndFeelDecorated(true);
                JLabel labelM = new JLabel("View Invoice Line Item");
                labelM.setFont(new Font("Arial", Font.BOLD, 15));
                labelM.setBounds(25, 50, 200, 30);
                JTextField name = new JTextField();
                addPlaceHolderText(name, "Enter Invoice Name");
                JButton submit = new JButton("Submit");
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame f = new JFrame("Invoices");
                        Invoice invoice = terminalUI.getApp().getInvoices().get(name.getText());
                        ArrayList<String> result = new ArrayList<>();
                        for (InvoiceLineItem ili: invoice.getInvoiceLineItems()) {
                            result.add("hours: " + ili.getHours() + " hours, rate: $"
                                    + ili.getRate() + ", desc: " + ili.getDesc());
                        }
                        String[] resultArray = new String[result.size()];
                        for (int i = 0; i < result.size(); i++) {
                            resultArray[i] = result.get(i);
                        }
                        f.add(new JList(resultArray));
                        f.pack();
                        f.setLocationRelativeTo(null);
                        f.setVisible(true);
                        f.setSize(400, 400);
                        f.setLocation(100, 150);
                    }
                });
                name.setBounds(0, 100, 200, 30);
                submit.setBounds(0, 200, 200, 30);
                f.add(labelM);
                f.add(name);
                f.add(submit);
                f.setLayout(null);
                f.setVisible(true);
                f.setResizable(false);
            }
        });
    }

    @SuppressWarnings("methodlength")
    private void addIliListener(JButton addInvoice) {
        addInvoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Add Invoice Line Item");
                f.setSize(200, 400);
                f.setLocation(100, 150);
                f.setDefaultLookAndFeelDecorated(true);
                JLabel labelM = new JLabel("Add Invoice Line Item");
                labelM.setFont(new Font("Arial", Font.BOLD, 18));
                labelM.setBounds(5, 50, 200, 30);

                JTextField invoice = new JTextField("Invoice");
                JTextField hours = new JTextField("Hours");
                JTextField rate = new JTextField("Rate");
                JTextField description = new JTextField("Description");
                JButton iliSubmit = new JButton("Submit");


                addPlaceHolderText(hours, "Hours");
                addPlaceHolderText(invoice, "Invoice");
                addPlaceHolderText(rate, "Rate");
                addPlaceHolderText(description, "Description");

                iliSubmit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            terminalUI.getApp().createInvoiceLineItem(invoice.getText(),
                                    description.getText(), Double.parseDouble(hours.getText()),
                                    Double.parseDouble(rate.getText()));
                            showMessageDialog(null, "Invoice Line item has been added"
                                    + " to " + invoice.getText() + "!");

                        } catch (Exception error) {

                            if (error.getMessage() == null) {
                                showMessageDialog(null,
                                        "It seems that invoice " + invoice.getText() + ""
                                                + " does not exist. Perhaps check spelling?");
                            } else {
                                showMessageDialog(null,
                                        "It seems that you have not inputted numbers for hours "
                                                + "and rate. \n Error "
                                                + "Message: " + error.getMessage());
                            }
                        }
                    }
                });

                invoice.setBounds(0, 100, 200, 30);
                hours.setBounds(0, 130, 200, 30);
                rate.setBounds(0, 160, 200, 30);
                description.setBounds(0, 190, 200, 30);
                iliSubmit.setBounds(0, 220, 200, 30);

                f.add(rate);
                f.add(invoice);
                f.add(rate);
                f.add(description);
                f.add(labelM);
                f.add(hours);
                f.add(iliSubmit);
                f.setLayout(null);
                f.setVisible(true);
                f.setResizable(false);
            }
        });
    }

    // EFFECTS: Adds a placeholder for JTextFields
    // CODE REFERENCED FROM https://stackoverflow.com/questions/16213836/java-swing-jtextfield-set-placeholder
    private void addPlaceHolderText(JTextField button, String text) {
        button.setForeground(Color.GRAY);
        button.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (button.getText().equals(text)) {
                    button.setText("");
                    button.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (button.getText().isEmpty()) {
                    button.setForeground(Color.GRAY);
                    button.setText(text);
                }
            }
        });
    }
}
