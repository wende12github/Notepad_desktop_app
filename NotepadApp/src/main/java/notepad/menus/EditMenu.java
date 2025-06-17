package notepad.menus;

import notepad.Notepad;
import javax.swing.*;
import java.awt.event.*;

 // It provides standard text editing operations like Undo, Redo, Cut, Copy, Paste, etc.
public class EditMenu extends JMenu {
    // Menu items that need to be accessed externally (for enabling/disabling)
    private JMenuItem undoItem;
    private JMenuItem redoItem;


//     Constructor that creates the Edit menu with all its editing functions.

    public EditMenu(Notepad notepad) {
        super("Edit");

        undoItem = new JMenuItem("Undo");
        // Set keyboard shortcut: Ctrl+Z
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        // When clicked, call the undo() method on the Notepad instance
        undoItem.addActionListener(e -> notepad.undo());

        redoItem = new JMenuItem("Redo");
        // Set keyboard shortcut: Ctrl+Y
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
        // When clicked, call the redo() method on the Notepad instance
        redoItem.addActionListener(e -> notepad.redo());

        // Create and configure the "Cut" menu item
        JMenuItem cutItem = new JMenuItem("Cut");
        // Set keyboard shortcut: Ctrl+X
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        // When clicked, call the cut() method on the Notepad instance
        cutItem.addActionListener(e -> notepad.cut());

        // Create and configure the "Copy" menu item
        JMenuItem copyItem = new JMenuItem("Copy");
        // Set keyboard shortcut: Ctrl+C
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        // When clicked, call the copy() method on the Notepad instance
        copyItem.addActionListener(e -> notepad.copy());

        // Create and configure the "Paste" menu item
        JMenuItem pasteItem = new JMenuItem("Paste");
        // Set keyboard shortcut: Ctrl+V
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        // When clicked, call the paste() method on the Notepad instance
        pasteItem.addActionListener(e -> notepad.paste());

        // Create and configure the "Delete" menu item
        JMenuItem deleteItem = new JMenuItem("Delete");
        // Set keyboard shortcut: Delete key
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        // When clicked, call the delete() method on the Notepad instance
        deleteItem.addActionListener(e -> notepad.delete());

        // Create and configure the "Find" menu item
        JMenuItem findItem = new JMenuItem("Find...");
        // Set keyboard shortcut: Ctrl+F
        findItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        // When clicked, call the find() method on the Notepad instance
        findItem.addActionListener(e -> notepad.find());

        // Create and configure the "Replace" menu item
        JMenuItem replaceItem = new JMenuItem("Replace...");
        // Set keyboard shortcut: Ctrl+H
        replaceItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        // When clicked, call the replace() method on the Notepad instance
        replaceItem.addActionListener(e -> notepad.replace());

        // Create and configure the "Select All" menu item
        JMenuItem selectAllItem = new JMenuItem("Select All");
        // Set keyboard shortcut: Ctrl+A
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        // When clicked, call the selectAll() method on the Notepad instance
        selectAllItem.addActionListener(e -> notepad.selectAll());

        // Add all menu items to the Edit menu with separators for logical grouping
        add(undoItem);
        add(redoItem);
        addSeparator();
        add(cutItem);
        add(copyItem);
        add(pasteItem);
        add(deleteItem);
        addSeparator();
        add(findItem);
        add(replaceItem);
        addSeparator();
        add(selectAllItem);
    }

    // Getter for the Undo menu item (used to enable/disable it externally)
    public JMenuItem getUndoItem() {
        return undoItem;
    }

//    Getter for the Redo menu item (used to enable/disable it externally)
    public JMenuItem getRedoItem() {
        return redoItem;
    }
}