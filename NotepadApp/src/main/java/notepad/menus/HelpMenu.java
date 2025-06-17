package notepad.menus;

import notepad.Notepad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelpMenu extends JMenu {
    public HelpMenu(Notepad notepad) {
        super("Help");

        JMenuItem helpItem = new JMenuItem("View Help");
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        helpItem.addActionListener(e -> showHelpDialog());

        JMenuItem aboutItem = new JMenuItem("About Notepad");
        aboutItem.addActionListener(e -> notepad.showAbout());

        add(helpItem);
        addSeparator();
        add(aboutItem);
    }

    private void showHelpDialog() {
        JTextArea helpText = new JTextArea();
        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setFont(new Font("Arial", Font.PLAIN, 12));

        String helpContent =
                """
                        JAVA NOTEPAD HELP
                       \s
                        FILE OPERATIONS:
                        • New (Ctrl+N): Create a new document
                        • Open (Ctrl+O): Open an existing text file
                        • Save (Ctrl+S): Save current document
                        • Save As: Save with a new filename
                        • Exit: Close the application
                       \s
                        EDIT OPERATIONS:
                        • Undo (Ctrl+Z): Reverse last action
                        • Redo (Ctrl+Y): Restore undone action
                        • Cut (Ctrl+X): Remove selection to clipboard
                        • Copy (Ctrl+C): Copy selection to clipboard
                        • Paste (Ctrl+V): Insert clipboard content
                        • Delete (Del): Remove selected text
                        • Find (Ctrl+F): Search for text
                        • Replace (Ctrl+H): Find and replace text
                        • Select All (Ctrl+A): Highlight entire document
                       \s
                        FORMAT OPTIONS:
                        • Word Wrap: Toggle line wrapping
                        • Font: Change text appearance
                       \s
                        KEYBOARD SHORTCUTS:
                        • Ctrl+Z: Undo
                        • Ctrl+Y: Redo
                        • Ctrl+X: Cut
                        • Ctrl+C: Copy
                        • Ctrl+V: Paste
                        • Ctrl+A: Select All
                        • Ctrl+F: Find
                        • Ctrl+H: Replace
                        • Ctrl+N: New File
                        • Ctrl+O: Open File
                        • Ctrl+S: Save
                        • F1: Help
                       \s
                        TIPS:
                        • The asterisk (*) in the title bar indicates unsaved changes
                        • Use Find/Replace to quickly modify text
                        • Adjust font size for better readability
               \s""";

        helpText.setText(helpContent);

        JScrollPane scrollPane = new JScrollPane(helpText);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(
                null,
                scrollPane,
                "Notepad Help",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}