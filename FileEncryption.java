import java.io.File;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.FileOutputStream;

public class FileEncryption {
    public static void encrypt(int key) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            int i = 0;
            for (byte b : data) {
                System.out.println(b);
                data[i] = (byte) (b ^ key);
                i++;
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("test");
        JFrame frame = new JFrame();
        frame.setTitle("File Encryption");
        frame.setSize(450, 80);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Fira Code", Font.ITALIC, 18);
        // Label
        JLabel label = new JLabel("Key: ");
        label.setFont(font);
        // Text Field
        JTextField textField = new JTextField(10);
        textField.setFont(font);
        // Button(s)
        JButton button = new JButton();
        button.setText("Select File");
        button.setFont(font);
        button.setPreferredSize(null);
        button.addActionListener(e -> {
            String text = textField.getText();
            int temp = Integer.parseInt(text);
            encrypt(temp);
        });

        frame.setLayout(new FlowLayout());
        frame.add(label);
        frame.add(textField);
        frame.add(button);
        frame.setVisible(true);
    }
}