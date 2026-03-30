package bank.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JWindow {

    public SplashScreen() {

        setSize(850, 480);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 850) / 2;
        int y = (screenSize.height - 480) / 2;
        setLocation(x, y);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);


        ImageIcon bgIcon;
        try {
            bgIcon = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        } catch (Exception e) {

            bgIcon = null;
        }

        JLabel backgroundLabel;
        if (bgIcon != null) {
            Image scaledImage = bgIcon.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT);
            backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        } else {
            // Simple blue gradient background
            backgroundLabel = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 51, 102),
                            850, 480, new Color(0, 102, 204));
                    g2d.setPaint(gradient);
                    g2d.fillRect(0, 0, 850, 480);
                }
            };
        }
        backgroundLabel.setBounds(0, 0, 850, 480);
        mainPanel.add(backgroundLabel);

        // Add bank logo
        ImageIcon bankIcon = null;
        try {
            bankIcon = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        } catch (Exception e) {

        }

        JLabel logoLabel;
        if (bankIcon != null) {
            Image scaledLogo = bankIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            logoLabel = new JLabel(new ImageIcon(scaledLogo));
        } else {
            logoLabel = new JLabel("BANK");
            logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
            logoLabel.setForeground(Color.WHITE);
        }
        logoLabel.setBounds(375, 50, 100, 100);
        backgroundLabel.add(logoLabel);

        // Welcome message (bigger than Login page)
        JLabel welcomeLabel = new JLabel("WELCOME TO ATM");
        welcomeLabel.setFont(new Font("AvantGrade", Font.BOLD, 48));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(200, 170, 500, 50);
        backgroundLabel.add(welcomeLabel);

        // Loading message
        JLabel loadingLabel = new JLabel("System is starting...");
        loadingLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        loadingLabel.setForeground(Color.YELLOW);
        loadingLabel.setBounds(340, 240, 300, 30);
        backgroundLabel.add(loadingLabel);

        // Simple progress bar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(250, 300, 350, 25);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(0, 200, 0));
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setFont(new Font("Arial", Font.BOLD, 12));
        backgroundLabel.add(progressBar);

        // Loading status text
        JLabel statusLabel = new JLabel("Please wait while we initialize the ATM...");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        statusLabel.setForeground(new Color(200, 200, 200));
        statusLabel.setBounds(300, 340, 400, 20);
        backgroundLabel.add(statusLabel);


        ImageIcon cardIcon = null;
        try {
            cardIcon = new ImageIcon(ClassLoader.getSystemResource("icon/card.png"));
        } catch (Exception e) {

        }

        if (cardIcon != null) {
            Image scaledCard = cardIcon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
            JLabel cardLabel = new JLabel(new ImageIcon(scaledCard));
            cardLabel.setBounds(385, 400, 80, 80);
            backgroundLabel.add(cardLabel);
        }

        setContentPane(mainPanel);

        // Start the loading animation
        startLoadingAnimation(progressBar, loadingLabel, statusLabel);

        setVisible(true);
    }

    private void startLoadingAnimation(JProgressBar progressBar, JLabel loadingLabel, JLabel statusLabel) {
        Timer timer = new Timer(50, new ActionListener() {
            int progress = 0;
            String[] loadingMessages = {
                    "Initializing system...",
                    "Loading security module...",
                    "Connecting to bank server...",
                    "Checking hardware...",
                    "Almost ready..."
            };
            int messageIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                progress += 1;

                if (progress <= 100) {
                    progressBar.setValue(progress);
                    progressBar.setString(progress + "%");

                    // Update loading message every 20%
                    if (progress % 20 == 0 && messageIndex < loadingMessages.length) {
                        loadingLabel.setText(loadingMessages[messageIndex]);
                        messageIndex++;
                    }

                    // Update status based on progress
                    if (progress < 30) {
                        statusLabel.setText("Please insert your card...");
                    } else if (progress < 60) {
                        statusLabel.setText("Verifying connection...");
                    } else if (progress < 90) {
                        statusLabel.setText("Loading user interface...");
                    } else {
                        statusLabel.setText("Ready to login...");
                    }
                }

                // When loading is complete
                if (progress >= 100) {
                    ((Timer)e.getSource()).stop();

                    // Small delay before showing login
                    Timer delayTimer = new Timer(800, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose(); // Close splash screen

                            // Open login screen
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    new Login().setVisible(true);
                                }
                            });
                        }
                    });
                    delayTimer.setRepeats(false);
                    delayTimer.start();
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        // Start the splash screen
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SplashScreen();
            }
        });
    }
}