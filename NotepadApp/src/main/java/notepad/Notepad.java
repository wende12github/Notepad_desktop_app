package notepad;

import notepad.components.AboutDialog;
import notepad.components.FindDialog;
import notepad.components.ReplaceDialog;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Notepad {
    private NotepadFrame frame;
    private File currentFile;
    private boolean isModified;
    private UndoManager undoManager;
    private boolean isUndoRedoOperation = false;
    private JMenuItem undoItem;
    private JMenuItem redoItem;

    public Notepad() {
        frame = new NotepadFrame(this);
        currentFile = null;
        isModified = false;

        undoItem = frame.getUndoItem();
        redoItem = frame.getRedoItem();

        undoManager = new UndoManager();
        frame.getTextArea().getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                if (!isUndoRedoOperation) {
                    undoManager.addEdit(e.getEdit());
                    updateUndoRedoState();
                    setModified(true);
                }
            }
        });

        updateUndoRedoState();
        frame.setVisible(true);
    }

    private void updateUndoRedoState() {
        undoItem.setEnabled(undoManager.canUndo());
        redoItem.setEnabled(undoManager.canRedo());

        if (undoManager.canUndo()) {
            undoItem.setText("Undo " + undoManager.getUndoPresentationName());
        } else {
            undoItem.setText("Undo");
        }

        if (undoManager.canRedo()) {
            redoItem.setText("Redo " + undoManager.getRedoPresentationName());
        } else {
            redoItem.setText("Redo");
        }
    }

    public void newFile() {
        if (checkSave()) {
            frame.getTextArea().setText("");
            currentFile = null;
            frame.setTitle("Untitled");
            isModified = false;
            undoManager.discardAllEdits();
            updateUndoRedoState();
        }
    }

    public void open() {
        if (!checkSave()) return;

        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            try {
                currentFile = fileChooser.getSelectedFile();
                // Using FileInputStream with BufferedReader for efficient reading
                try (FileInputStream fis = new FileInputStream(currentFile);
                     InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                     BufferedReader reader = new BufferedReader(isr)) {

                    StringBuilder content = new StringBuilder();
                    String line;

                    // Read file line by line
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }

                    // Set text and update UI
                    frame.getTextArea().setText(content.toString());
                    frame.setTitle(currentFile.getName());
                    isModified = false;
                    undoManager.discardAllEdits();
                    updateUndoRedoState();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void save() {
        if (currentFile == null) {
            saveAs();
        } else {
            try {
                // Using FileOutputStream with BufferedWriter for efficient writing
                try (FileOutputStream fos = new FileOutputStream(currentFile);
                     OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);  // UTF-8 writer
                     BufferedWriter writer = new BufferedWriter(osw)) {

                    writer.write(frame.getTextArea().getText());
                    writer.flush();  // Ensure all data is written to disk

                    // Update UI state
                    isModified = false;
                    frame.setTitle(currentFile.getName());
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void saveAs() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            frame.setTitle(currentFile.getName());
            save();
        }
    }

    public void exit() {
        if (checkSave()) {
            System.exit(0);
        }
    }

    private boolean checkSave() {
        if (isModified) {
            int result = JOptionPane.showConfirmDialog(
                    frame,
                    "Do you want to save changes?",
                    "Notepad",
                    JOptionPane.YES_NO_CANCEL_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                save();
                return !isModified;
            } else return result == JOptionPane.NO_OPTION;
        }
        return true;
    }

    public void undo() {
        if (undoManager.canUndo()) {
            isUndoRedoOperation = true;
            undoManager.undo();
            isUndoRedoOperation = false;
            updateUndoRedoState();
        }
    }

    public void redo() {
        if (undoManager.canRedo()) {
            isUndoRedoOperation = true;
            undoManager.redo();
            isUndoRedoOperation = false;
            updateUndoRedoState();
        }
    }

    public void cut() {
        frame.getTextArea().cut();
    }

    public void copy() {
        frame.getTextArea().copy();
    }

    public void paste() {
        frame.getTextArea().paste();
    }

    public void delete() {
        frame.getTextArea().replaceSelection("");
    }

    public void selectAll() {
        frame.getTextArea().selectAll();
    }

    public void find() {
        new FindDialog(frame, frame.getTextArea()).setVisible(true);
    }

    public void replace() {
        new ReplaceDialog(frame, frame.getTextArea()).setVisible(true);
    }

    public void wordWrap() {
        boolean wrap = !frame.getTextArea().getLineWrap();
        frame.getTextArea().setLineWrap(wrap);
        frame.getTextArea().setWrapStyleWord(wrap);
    }

    public void setFont(Font font) {
        frame.getTextArea().setFont(font);
    }

    public void showAbout() {
        new AboutDialog(frame).setVisible(true);
    }

    public JTextArea getTextArea() {
        return frame.getTextArea();
    }

    public void setModified(boolean modified) {
        isModified = modified;
        if (modified) {
            frame.setTitle("*" + frame.getTitle().replace("*", ""));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
            new Notepad();
        });
    }
}