package notepad.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReplaceDialog extends JDialog {
    private JTextField findField;
    private JTextField replaceField;
    private JCheckBox matchCase;
    private JButton findNextButton;
    private JButton replaceButton;
    private JButton replaceAllButton;
    private JButton cancelButton;
    private JTextArea textArea;
    private int lastFindPosition = 0;

    public ReplaceDialog(JFrame parent, JTextArea textArea) {
        super(parent, "Replace", true);
        this.textArea = textArea;
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        JPanel findPanel = new JPanel(new BorderLayout(5, 5));
        JLabel findLabel = new JLabel("Find what:");
        findField = new JTextField();
        findPanel.add(findLabel, BorderLayout.NORTH);
        findPanel.add(findField, BorderLayout.CENTER);

        JPanel replacePanel = new JPanel(new BorderLayout(5, 5));
        JLabel replaceLabel = new JLabel("Replace with:");
        replaceField = new JTextField();
        replacePanel.add(replaceLabel, BorderLayout.NORTH);
        replacePanel.add(replaceField, BorderLayout.CENTER);

        inputPanel.add(findPanel);
        inputPanel.add(replacePanel);

        matchCase = new JCheckBox("Match case");

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        findNextButton = new JButton("Find Next");
        replaceButton = new JButton("Replace");
        replaceAllButton = new JButton("Replace All");
        cancelButton = new JButton("Cancel");

        buttonPanel.add(findNextButton);
        buttonPanel.add(replaceButton);
        buttonPanel.add(replaceAllButton);

        panel.add(inputPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout(5, 5));
        southPanel.add(matchCase, BorderLayout.WEST);
        southPanel.add(buttonPanel, BorderLayout.EAST);

        panel.add(southPanel, BorderLayout.SOUTH);

        findNextButton.addActionListener(e -> findNext(false));
        replaceButton.addActionListener(e -> replace());
        replaceAllButton.addActionListener(e -> replaceAll());
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

    private void findNext(boolean replaceMode) {
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
            lastFindPosition = index + (replaceMode ? 0 : 1);
        } else {
            if (lastFindPosition != 0) {
                lastFindPosition = 0;
                findNext(replaceMode);
            } else {
                JOptionPane.showMessageDialog(this, "Cannot find \"" + findField.getText() + "\"", "Notepad", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void replace() {
        if (textArea.getSelectedText() != null &&
                textArea.getSelectedText().equals(findField.getText())) {
            textArea.replaceSelection(replaceField.getText());
        }
        findNext(true);
    }

    private void replaceAll() {
        String text = textArea.getText();
        String search = findField.getText();
        String replace = replaceField.getText();

        if (search.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter search text", "Notepad", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (!matchCase.isSelected()) {
            text = text.toLowerCase();
            search = search.toLowerCase();
        }

        int count = 0;
        int index = text.indexOf(search);

        while (index != -1) {
            textArea.setSelectionStart(index);
            textArea.setSelectionEnd(index + search.length());
            textArea.replaceSelection(replaceField.getText());
            count++;

            text = textArea.getText();
            if (!matchCase.isSelected()) {
                text = text.toLowerCase();
            }

            index = text.indexOf(search, index + replace.length());
        }

        if (count > 0) {
            JOptionPane.showMessageDialog(this, "Replaced " + count + " occurrences", "Notepad", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Cannot find \"" + findField.getText() + "\"", "Notepad", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}