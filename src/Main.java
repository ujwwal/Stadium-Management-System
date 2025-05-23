package src;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            DbConnection.getCreds();
            DatabaseInitializer.getCreds();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (DatabaseInitializer.initialize() == 0) {
            return;
        }
        // Frame setup
        JFrame frame = new JFrame("Stadium Management System");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Stadium Management System", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 25));
        headerLabel.setForeground(Color.WHITE);

        ImagePanel headingPanel = new ImagePanel("imgs\\pitch.png");
        headingPanel.setPreferredSize(new Dimension(1000, 70));
        // headingPanel.add(imgLabel, BorderLayout.WEST);
        headingPanel.add(headerLabel, BorderLayout.CENTER);

        // GridBagLayout main panel
        ImagePanel mainPanel = new ImagePanel("imgs\\staduim.jpg"); // Use your image path here
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // spacing

        // Login Panel
        AdminLogin loginPanel = new AdminLogin();
        loginPanel.setOpaque(true);
        loginPanel.setBackground(new Color(0, 0, 0, 0));
        loginPanel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(loginPanel, gbc);

        // Event List Panel
        JPanel evlist = new JPanel(new BorderLayout());
        evlist.setPreferredSize(new Dimension(500, 200));
        evlist.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        ActiveEventPanel activeEventPanel = new ActiveEventPanel(500, 200);
        activeEventPanel.setOpaque(false);
        activeEventPanel.setBackground(new Color(0, 0, 0, 0));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.3;
        mainPanel.add(activeEventPanel, gbc);

        // Assemble it all
        frame.add(headingPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
