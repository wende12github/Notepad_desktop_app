package notepad.components;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {
    public AboutDialog(JFrame parent) {
        super(parent, "About Notepad", true);
        setSize(350, 250);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Java Notepad App", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JTextArea infoArea = getJTextArea(panel);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dispose());

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.add(titleLabel, BorderLayout.NORTH);
        centerPanel.add(infoArea, BorderLayout.CENTER);

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(okButton, BorderLayout.SOUTH);

        add(panel);
    }

    private static JTextArea getJTextArea(JPanel panel) {
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setBackground(panel.getBackground());
        infoArea.setFont(new Font("Arial", Font.PLAIN, 12));
        infoArea.setText(
                "Version 1.0\n\n" +
                        "A simple text editor built with Java Swing\n\n" +
                        "Features:\n" +
                        "- Full text editing capabilities\n" +
                        "- File management\n" +
                        "- Find and replace\n" +
                        "- Customizable formatting\n\n" +
                        "Created as a demonstration project" +
                        "\n\tCreated by Wendmagegn Tajura"
        );
        return infoArea;
    }
}