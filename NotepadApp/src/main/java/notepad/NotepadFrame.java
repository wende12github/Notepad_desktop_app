package notepad;

import notepad.menus.EditMenu;
import notepad.menus.FileMenu;
import notepad.menus.FormatMenu;
import notepad.menus.HelpMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NotepadFrame extends JFrame {
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private Notepad notepad;
    private EditMenu editMenu;

    public NotepadFrame(Notepad notepad) {
        this.notepad = notepad;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Untitled");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                notepad.exit();
            }
        });

        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        editMenu = new EditMenu(notepad);
        menuBar.add(new FileMenu(notepad));
        menuBar.add(editMenu);
        menuBar.add(new FormatMenu(notepad));
        menuBar.add(new HelpMenu(notepad));

        setJMenuBar(menuBar);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }

    public JMenuItem getUndoItem() {
        return editMenu.getUndoItem();
    }

    public JMenuItem getRedoItem() {
        return editMenu.getRedoItem();
    }
}