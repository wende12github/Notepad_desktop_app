package notepad.menus;

import notepad.Notepad;
import javax.swing.*;
import java.awt.event.*;


public class FileMenu extends JMenu {

    /**
     * @param notepad The main Notepad application instance that this menu will interact with
     */

    public FileMenu(Notepad notepad) {
        // Set the menu title to "File"
        super("File");

        // Create and configure the "New" menu item
        JMenuItem newItem = new JMenuItem("New");
        // Set keyboard shortcut: Ctrl+N
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        // When clicked, call the newFile() method on the Notepad instance
        newItem.addActionListener(e -> notepad.newFile());

        // Create and configure the "Open" menu item
        JMenuItem openItem = new JMenuItem("Open...");
        // Set keyboard shortcut: Ctrl+O
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        // When clicked, call the open() method on the Notepad instance
        openItem.addActionListener(e -> notepad.open());

        // Create and configure the "Save" menu item
        JMenuItem saveItem = new JMenuItem("Save");
        // Set keyboard shortcut: Ctrl+S
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        // When clicked, call the save() method on the Notepad instance
        saveItem.addActionListener(e -> notepad.save());

        // Create and configure the "Save As" menu item
        JMenuItem saveAsItem = new JMenuItem("Save As...");
        // No keyboard shortcut by default
        // When clicked, call the saveAs() method on the Notepad instance
        saveAsItem.addActionListener(e -> notepad.saveAs());

        // Create and configure the "Exit" menu item
        JMenuItem exitItem = new JMenuItem("Exit");
        // No keyboard shortcut by default
        // When clicked, call the exit() method on the Notepad instance
        exitItem.addActionListener(e -> notepad.exit());

        // Add all menu items to the File menu in order
        add(newItem);
        add(openItem);
        add(saveItem);
        add(saveAsItem);
        addSeparator();
        add(exitItem);
    }
}