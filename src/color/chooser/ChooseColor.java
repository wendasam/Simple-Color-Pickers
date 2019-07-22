/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package color.chooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Formatter;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;

/**
 *
 * @author stown
 */
public class ChooseColor extends JFrame implements ActionListener {

    private final JButton btnChoose;
    private final JButton btnReset;
    private final JTextArea textArea;

    private final JTextField hexTextColor;
    private final JLabel hexName;

    private final JTextField rgbTextColor;
    private final JLabel rgbName;

    private JPopupMenu menu;

    public ChooseColor() {
        super("Simple Color Picker");

        textArea = new JTextArea();
        textArea.setText("\n\n\n\n\n            Please select the color");
        textArea.setFont(new Font("Dialog", Font.BOLD, 14));
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textArea.setEditable(false);
        textArea.setBounds(10, 10, 300, 200);

        btnChoose = new JButton("Choose Color");
        btnChoose.setMnemonic('C');
        btnChoose.setBounds(180, 250, 130, 30);
        btnChoose.addActionListener(this);

        btnReset = new JButton("Reset");
        btnReset.setBounds(180, 320, 130, 30);
        btnReset.setMnemonic('R');
        btnReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hexTextColor.setText(null);
                rgbTextColor.setText(null);
                textArea.append("\n\n\n\n\n            Please select the color");
                textArea.setFont(new Font("Dialog", Font.BOLD, 14));
                textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                textArea.setBackground(Color.WHITE);
            }

        });

        hexName = new JLabel("Hex Color Value: ");
        hexName.setBounds(10, 230, 150, 15);

        hexTextColor = new JTextField();
        hexTextColor.setBounds(10, 250, 300, 200);
        hexTextColor.setFont(new Font("Dialog", Font.BOLD, 12));
        hexTextColor.setSize(new Dimension(120, 30));

        rgbName = new JLabel("RGB Color Value: ");
        rgbName.setBounds(10, 300, 150, 15);

        rgbTextColor = new JTextField();
        rgbTextColor.setBounds(10, 320, 300, 30);
        rgbTextColor.setFont(new Font("Dialog", Font.BOLD, 12));
        rgbTextColor.setSize(new Dimension(120, 30));

        createPopMenu(); // panggil fungsi copy, cut, paste, dan selectAll

        add(hexName);
        add(rgbName);
        add(hexTextColor);
        add(rgbTextColor);
        add(btnChoose);
        add(btnReset);
        add(textArea);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(320, 400);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Membuat popupMenu pada textField
     */
    private void createPopMenu() {
        menu = new JPopupMenu();
        Action cut = new DefaultEditorKit.CutAction();
        cut.putValue(Action.NAME, "Cut");
        cut.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(" ctrl + x"));
        menu.add(cut);

        Action copy = new DefaultEditorKit.CopyAction();
        copy.putValue(Action.NAME, "Copy");
        copy.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(" ctrl + c"));
        menu.add(copy);

        Action paste = new DefaultEditorKit.PasteAction();
        paste.putValue(Action.NAME, "Paste");
        paste.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(" ctrl + v"));
        menu.add(paste);

        Action selectAll = new SelectAll();
        menu.add(selectAll);

        hexTextColor.setComponentPopupMenu(menu);
        rgbTextColor.setComponentPopupMenu(menu);
    }

    /**
     * Class untuk select all
     */
    static class SelectAll extends TextAction {

        public SelectAll() {
            super("Select All");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control + a"));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextComponent component = getFocusedComponent();
            component.selectAll();
            component.requestFocusInWindow();
        }
    }

    /**
     * Method untuk action pada button pilih color
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Color color = JColorChooser.showDialog(this, "Choose Color", Color.WHITE);
        textArea.setText(null);
        textArea.setFont(new Font("Dialog", Font.BOLD, 14));
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textArea.setBackground(color);

        Formatter f = new Formatter(new StringBuffer("#"));
        f.format("%02X", color.getRed()).toString();
        f.format("%02X", color.getGreen()).toString();
        f.format("%02X", color.getBlue()).toString();

        // ARGB = (255, 255, 0, 0) (Red) 
        // hex -> "ffff0000"
        String hex = Integer.toHexString(color.getRGB());
        //Integer rgb = Integer.valueOf(color.getRGB());
        // Reduced to RGB: hex -> "#ff0000"
        //hex = "#" + hex.substring(2, hex.length());

        // RGB COLOR
        Integer red = color.getRed();
        Integer green = color.getGreen();
        Integer blue = color.getBlue();
        System.out.println("\n========================");
        System.out.println("Red Value   : " + red);
        System.out.println("Green Value : " + green);
        System.out.println("Blue Value  : " + blue);

        String kodeRGB = red + ", " + green + ", " + blue;

        rgbTextColor.setText(kodeRGB);
        System.out.println("\nRGB Value: " + kodeRGB);

        hexTextColor.setText(String.valueOf(f.toString()));
        System.out.println("Hex value: " + String.valueOf(f.toString()));

    }

    /**
     * Main menu
     *
     * @param args
     */
    public static void main(String[] args) {
        ChooseColor choosolor = new ChooseColor();
    }
}
