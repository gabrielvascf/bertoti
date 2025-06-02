package src;

import javax.swing.*;
public class TodoListApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskController());
    }
}