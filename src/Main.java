import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;
import java.awt.Toolkit;

public class Main {
    public static void main(String[] args) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) size.getWidth();
        int height = (int) size.getHeight();

        JFrame obj = new JFrame();
        obj.setBounds(width / 3, height / 3, 700, 600);
        obj.setTitle("WordCounter");
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //obj.getContentPane().setBackground(new Color(16, 161, 210));

        JPanel panel = new JPanel(null);
        obj.add(panel);

        panel.setBackground(new Color(93, 185, 210));

        JTextPane textPane = new JTextPane();
        textPane.setBounds(10, 10, 660, 350);
        textPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2) );
        textPane.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(textPane);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(10, 10, 660, 350);
        panel.add(scrollPane);

        Font font = new Font("Arial", Font.BOLD, 15);

        String[] list = {"Count words", "Count characters", "<html>Count characters<br>&nbsp without space</html>"};
        JComboBox<String> choices = new JComboBox<>(list);
        choices.setBounds(250, 375, 150, 40);
        choices.setFont(font);
        panel.add(choices);

        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        choices.setRenderer(renderer);

        JButton countButton = new JButton("Count");
        countButton.setBounds(170, 475, 150, 50);
        countButton.setFont(font);
        panel.add(countButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(330, 475, 150, 50);
        clearButton.setFont(font);
        panel.add(clearButton);

        JLabel result = new JLabel("");
        result.setBounds(320, 430, 300, 30);
        result.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(result);

        clearButton.addActionListener(e -> {
            textPane.setText("");
            result.setText("0");
        });

        //text = text.replaceAll("\\s", "");
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem cut = new JMenuItem("Cut");
        popupMenu.add(cut);
        JMenuItem copy = new JMenuItem("Copy");
        popupMenu.add(copy);
        JMenuItem paste = new JMenuItem("Paste");
        popupMenu.add(paste);
        JMenuItem selectAll = new JMenuItem("SelectAll");
        popupMenu.add(selectAll);

        textPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3){
                    popupMenu.show(textPane, e.getX(), e.getY());
                }
            }
        });

        cut.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    textPane.cut();
                }
            }
        });

        copy.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    textPane.copy();
                }
            }
        });

        paste.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    textPane.paste();
                }
            }
        });

        selectAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    textPane.selectAll();
                }
            }
        });

        countButton.addActionListener(e -> {
            String text = textPane.getText();

            if (choices.getSelectedItem() == "Count words")
                result.setText("" + CountWords(text));
            else if (choices.getSelectedItem() == "Count characters"){
                result.setText("" + text.length());
            }
            else{
                text = text.replaceAll("\\s", "");
                result.setText("" + text.length());
            }

            obj.revalidate();
        });

        obj.setVisible(true);
    }

    public static int CountWords(String str) {
        if (str == null || str.isEmpty())
            return 0;

        StringTokenizer tokens = new StringTokenizer(str);
        int counter = 0;

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (!token.equals(".") && !token.equals(",")) {
                counter++;
            }
        }

        return counter;
    }
}