import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            MedicalSystemGUI app = new MedicalSystemGUI();
            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}