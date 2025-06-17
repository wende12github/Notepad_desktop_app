package notepad.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FindDialog extends JDialog {
    private JTextField findField;
    private JCheckBox matchCase;
    private JButton findNextButton;
    private JButton cancelButton;
    private JTextArea textArea;
    private int lastFindPosition = 0;

    public FindDialog(JFrame parent, JTextArea textArea) {
        super(parent, "Find", true);
        this.textArea = textArea;
        setSize(350, 150);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        JLabel findLabel = new JLabel("Find what:");
        findField = new JTextField();

        inputPanel.add(findLabel, BorderLayout.NORTH);
        inputPanel.add(findField, BorderLayout.CENTER);

        matchCase = new JCheckBox("Match case");

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        findNextButton = new JButton("Find Next");
        cancelButton = new JButton("Cancel");

        buttonPanel.add(findNextButton);
        buttonPanel.add(cancelButton);

        panel.add(inputPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout(5, 5));
        southPanel.add(matchCase, BorderLayout.WEST);
        southPanel.add(buttonPanel, BorderLayout.EAST);

        panel.add(southPanel, BorderLayout.SOUTH);

        findNextButton.addActionListener(e -> findNext());
        cancelButton.addActionListener(e -> dispose());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                findField.requestFocus();
                lastFindPosition = 0;
            }
        });

        add(panel);
    }

    private void findNext() {
        String text = textArea.getText();
        String search = findField.getText();

        if (search.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter search text", "Notepad", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (!matchCase.isSelected()) {
            text = text.toLowerCase();
            search = search.toLowerCase();
        }

        int index = text.indexOf(search, lastFindPosition);

        if (index != -1) {
            textArea.setSelectionStart(index);
            textArea.setSelectionEnd(index + search.length());
            textArea.grabFocus();
            lastFindPosition = index + 1;
        } else {
            if (lastFindPosition != 0) {
                lastFindPosition = 0;
                findNext();
            } else {
                JOptionPane.showMessageDialog(this, "Cannot find \"" + findField.getText() + "\"", "Notepad", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}