package notepad.menus;

import notepad.Notepad;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FormatMenu extends JMenu {
    public FormatMenu(Notepad notepad) {
        super("Format");

        // Word Wrap
        JCheckBoxMenuItem wordWrapItem = new JCheckBoxMenuItem("Word Wrap");
        wordWrapItem.addActionListener(e -> notepad.wordWrap());

        // Font
        JMenuItem fontItem = new JMenuItem("Font...");
        fontItem.addActionListener(e -> {
            Font currentFont = notepad.getTextArea().getFont();
            FontChooser fontChooser = new FontChooser(currentFont);
            int result = JOptionPane.showConfirmDialog(
                    null,
                    fontChooser,
                    "Choose Font",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                notepad.setFont(fontChooser.getSelectedFont());
            }
        });

        // Add items to menu
        add(wordWrapItem);
        add(fontItem);
    }
}

class FontChooser extends JPanel {
    private JComboBox<String> fontFamilyCombo;
    private JComboBox<String> fontSizeCombo;
    private JCheckBox boldCheck;
    private JCheckBox italicCheck;

    public FontChooser(Font currentFont) {
        setLayout(new GridLayout(4, 1));

        // Font family
        String[] fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontFamilyCombo = new JComboBox<>(fontFamilies);
        fontFamilyCombo.setSelectedItem(currentFont.getFamily());

        // Font size
        String[] sizes = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72"};
        fontSizeCombo = new JComboBox<>(sizes);
        fontSizeCombo.setSelectedItem(String.valueOf(currentFont.getSize()));

        // Font style
        boldCheck = new JCheckBox("Bold");
        boldCheck.setSelected(currentFont.isBold());

        italicCheck = new JCheckBox("Italic");
        italicCheck.setSelected(currentFont.isItalic());

        // Add components
        add(fontFamilyCombo);
        add(fontSizeCombo);
        add(boldCheck);
        add(italicCheck);
    }

    public Font getSelectedFont() {
        String family = (String) fontFamilyCombo.getSelectedItem();
        int style = Font.PLAIN;
        if (boldCheck.isSelected()) style |= Font.BOLD;
        if (italicCheck.isSelected()) style |= Font.ITALIC;
        int size = Integer.parseInt((String) Objects.requireNonNull(fontSizeCombo.getSelectedItem()));

        return new Font(family, style, size);
    }
}