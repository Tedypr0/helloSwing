package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DialogExample {
    private static List<PageData> pageDataList;
    private static int currentPageIndex;

    public static void main(String[] args) {
        // Create example page data
        createExamplePageData();

        // Initialize current page index
        currentPageIndex = 0;

        JFrame frame = new JFrame("Dialog Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = 800;
        int frameHeight = 600;
        frame.setSize(frameWidth,frameHeight);
        frame.setLayout(new BorderLayout());

        // Left Panel
        JPanel leftPanel = new JPanel(new GridLayout(3, 1));
        leftPanel.setPreferredSize(new Dimension(100, 0));
        leftPanel.setBackground(Color.CYAN);
        Border leftPanelBorder = BorderFactory.createLineBorder(Color.BLUE, 2);
        leftPanel.setBorder(leftPanelBorder);
        JLabel label1 = new JLabel("Label 1");
        JLabel label2 = new JLabel("Label 2");
        JLabel label3 = new JLabel("Label 3");
        leftPanel.add(label1);
        leftPanel.add(label2);
        leftPanel.add(label3);

        // Right Panel
        RightPanel rightPanel = new RightPanel();
        rightPanel.updatePageData(pageDataList.get(currentPageIndex));

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.YELLOW);
        Border bottomPanelBorder = BorderFactory.createLineBorder(Color.RED, 2);
        bottomPanel.setBorder(bottomPanelBorder);
        JButton skipButton = new JButton("Skip");
        JLabel hintLabel = new JLabel("Intro panel can be opened by selecting Help > Open Intro");
        hintLabel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");
        JButton startButton = new JButton("Start");
        //skipButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        //Fonts
        Font buttonFont = skipButton.getFont().deriveFont(Font.ITALIC, 12);
        skipButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
        nextButton.setFont(buttonFont);
        startButton.setFont(buttonFont);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        bottomPanel.add(skipButton, BorderLayout.WEST);
        bottomPanel.add(hintLabel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        // Button Listeners
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog
                frame.dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the previous page if not on the first page
                if (currentPageIndex > 0) {
                    currentPageIndex--;
                    rightPanel.updatePageData(pageDataList.get(currentPageIndex));
                    nextButton.setEnabled(true);
                    nextButton.setText("Next");
                    if(currentPageIndex < pageDataList.size()-1){
                        startButton.setVisible(false);
                        buttonPanel.remove(startButton);
                        nextButton.setVisible(true);
                        buttonPanel.add(nextButton);
                    }
                }

                // Hide the back button if on the first page
                backButton.setVisible(currentPageIndex != 0);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go to the next page if not on the last page
                 if (currentPageIndex < pageDataList.size() - 1) {
                    currentPageIndex++;
                    rightPanel.updatePageData(pageDataList.get(currentPageIndex));
                    backButton.setVisible(true);
                    nextButton.setEnabled(true);
                    nextButton.setText("Next");
                     if (currentPageIndex == pageDataList.size() - 1) {
                         // Show the Start button on the last page
                         nextButton.setVisible(false);
                         startButton.setVisible(true);
                         buttonPanel.remove(nextButton);
                         buttonPanel.add(startButton);
                     }
                 } else if (currentPageIndex == pageDataList.size()) {
                    // Close the dialog if Start button is clicked on the last page
                    frame.dispose();
                }
            }
        });

        startButton.addActionListener(e -> frame.dispose());

        // Add panels to the frame
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Set frame background color and border
        frame.getContentPane().setBackground(Color.GRAY);
        Border frameBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        frame.getRootPane().setBorder(frameBorder);

        // Set initial button states
        backButton.setVisible(false);
        nextButton.setEnabled(pageDataList.size() > 1);

        frame.setVisible(true);
    }

    private static void createExamplePageData() {
        pageDataList = new ArrayList<>();
        pageDataList.add(new PageData("Page 1", "Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1Hint 1", "C:\\Users\\tzhelyazkov\\IdeaProjects\\SudokuSolver\\src\\main\\java\\org\\example\\image1.png"));
        pageDataList.add(new PageData("Page 2", "Hint 2", "C:\\Users\\tzhelyazkov\\IdeaProjects\\SudokuSolver\\src\\main\\java\\org\\example\\image2.png"));
        pageDataList.add(new PageData("Page 3", "Hint 3", "C:\\Users\\tzhelyazkov\\IdeaProjects\\SudokuSolver\\src\\main\\java\\org\\example\\image3.jpg"));
        // Add more pages as needed
    }

    private static class PageData {
        private String title;
        private String hint;
        private String imageFileName;

        public PageData(String title, String hint, String imageFileName) {
            this.title = title;
            this.hint = hint;
          //  this.imageFileName = imageFileName;
        }

        public String getTitle() {
            return title;
        }

        public String getHint() {
            return hint;
        }

        public String getImageFileName() {
            return imageFileName;
        }
    }

    private static class RightPanel extends JPanel {
        private JLabel titleLabel;
        private JLabel hintLabel;
        private JLabel imageLabel;

        public RightPanel() {
            setLayout(new BorderLayout());
            setBackground(Color.WHITE);
            Border rightPanelBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
            setBorder(rightPanelBorder);
            setPreferredSize(new Dimension(300, 0)); // Adjust the preferred width as needed

            JPanel pageDataPanel = new JPanel(new GridBagLayout());
            pageDataPanel.setOpaque(false);
            pageDataPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Add left padding of 10px

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.NORTH;

            titleLabel = new JLabel();
            titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setVerticalAlignment(SwingConstants.TOP); // Allow vertical wrapping
            pageDataPanel.add(titleLabel, gbc);

            gbc.gridy = 1;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(10,0,0,0);

            hintLabel = new JLabel();
            hintLabel.setFont(new Font("Verdana", Font.ITALIC, 14));
            hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
            hintLabel.setVerticalAlignment(SwingConstants.TOP); // Allow vertical wrapping
            pageDataPanel.add(hintLabel, gbc);

            gbc.gridy = 2;
            gbc.weighty = 0.0;
            gbc.insets = new Insets(10, 0, 0, 0); // Add top padding of 10px

            imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the image
            pageDataPanel.add(imageLabel, gbc);

            add(pageDataPanel, BorderLayout.CENTER);
        }

        public void updatePageData(PageData pageData) {
            titleLabel.setText("<html>" + pageData.getTitle() + "</html>"); // Wrap the title text
            hintLabel.setText("<html>" + pageData.getHint() + "</html>"); // Wrap the hint text
          //  String imagePath = new File(pageData.getImageFileName()).getAbsolutePath();
//            ImageIcon imageIcon = new ImageIcon(imagePath);
//            imageLabel.setIcon(imageIcon);
        }
    }
}