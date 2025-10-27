package com.github.lushen124.BRACKET;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class WidgetPanel extends JPanel{

   private static final Logger logger = Logger.getLogger(Init.class.getName());
   private static final JFrame frame = new JFrame();
   private static final JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 5));
   private static final JPanel mainPanel = new JPanel();
   private final HashMap<JTextField, JTextField> playerFieldMap = new HashMap<>();

    public WidgetPanel(){
       logger.info("Wideget Panel constructor started");
       frame.setTitle("Who Made it to Top 8");
       frame.setMinimumSize(new Dimension(640, 400));
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       frame.setSize(1042, 600);
       frame.setVisible(true);
       mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

       List<String> labelList = Arrays.asList("Player Name", "Logo Path");
       mainPanel.add(createLablRow(labelList));

//        for (int i = 0; i < 8; i++) {
//            addPlayerRow();
//        }
       frame.add(mainPanel);

       frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {savePlayerData();}
        });
   }
    public void addPlayerRow() {
        JPanel row = createPlayerRow();
        mainPanel.add(row);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    private JPanel createLablRow(List<String> labels) {
        for (String label : labels) {
            rowPanel.add(createLabelComponent(label));
            rowPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        }
        return rowPanel;
    }
    private JPanel createPlayerRow() {
        JTextField nameField = new JTextField(15);
        JTextField filePathField = new JTextField(30);
        JButton addLogoButton = new JButton("Add Icon");

        addLogoButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "bmp"));
            chooser.setCurrentDirectory(new File(System.getProperty("user.home"), "Downloads"));
            int result = chooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                filePathField.setText(selectedFile.getAbsolutePath());
            }
            playerFieldMap.put(nameField, filePathField);
        });

        JPanel rows = new JPanel();
        rows.setLayout(new BoxLayout(rows, BoxLayout.X_AXIS));
        rows.add(Box.createRigidArea(new Dimension(20, 0)));
        rows.add(nameField);
        rows.add(filePathField);
        rows.add(addLogoButton);

        return rows;
    }
    private static JComponent createLabelComponent(String s) {
        JLabel l = new JLabel(s);
        l.setBorder(BorderFactory.createMatteBorder(5,5,5,5,
                Color.DARK_GRAY));
        l.setHorizontalAlignment(JLabel.LEFT);
        return l;
    }
    private static JComponent createTextComponent(String s) {
        JTextField t = new JTextField(s);
        t.setBorder(BorderFactory.createMatteBorder(5,5,5,5,
                Color.DARK_GRAY));
        t.setAlignmentX(JLabel.LEFT);
        t.setName(s);
        return t;
    }
    private void savePlayerData() {
        logger.info("Saving player data...");

        for (Map.Entry<JTextField, JTextField> entry : playerFieldMap.entrySet()) {
            String playerName = entry.getKey().getText().trim();
            String logoPath = entry.getValue().getText().trim();

            // Only log meaningful entries
            if (!playerName.isEmpty() || !logoPath.isEmpty()) {
                System.out.println("Player: " + playerName + " → " + logoPath);
            }
        }
    }
    public boolean isVisible() {
        return frame.isVisible();
    }

    public void toFront() {
        frame.toFront();
        frame.requestFocus();
    }
    public Map<String, String> getPlayerData() {
        Map<String, String> data = new HashMap<>();
        for (Map.Entry<JTextField, JTextField> entry : playerFieldMap.entrySet()) {
            String name = entry.getKey().getText().trim();
            String logo = entry.getValue().getText().trim();
            if (!name.isEmpty() || !logo.isEmpty()) {
                data.put(name, logo);
            }
        }
        return data;
    }
}
