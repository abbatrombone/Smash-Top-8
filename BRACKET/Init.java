package com.github.lushen124.BRACKET;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class Init {
    //private final SettingsManager settingsManager = new SettingsManager();
    //private final Properties settings;
    //private final File settingsFile = new File(settingsManager.getSettingsFileToString());
    private static final Logger logger = Logger.getLogger(Init.class.getName());
    private static final JFrame frame = new JFrame();
    private WidgetPanel widgetPanel;
    private BracketPanel bracketPanel;

    public Init() {
        logger.info("Init constructor started");
        frame.setTitle("Top 8 Bracket");
        frame.setMinimumSize(new Dimension(640, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1042, 600);

        JPanel mainPanel = new JPanel();
        JPanel buttonPanel = makeButtonPane();
        bracketPanel = new BracketPanel();

        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(new JScrollPane(bracketPanel));
        mainPanel.add(buttonPanel);
        frame.add(mainPanel);

        //SET panel to be a top 8 bracket
        //move randomize button, and add players to the bottom.

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu profileMenu = new JMenu("Profile");
        JMenuItem addProfile = new JMenuItem("Add Profile");
        JMenu settingsMenu = new JMenu("Settings");
        JMenuItem logLevelItem = new JMenuItem("Log Level");
        file.add(addProfile);
        settingsMenu.add(logLevelItem);
        menuBar.add(file);
        menuBar.add(profileMenu);
        menuBar.add(settingsMenu);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    public JPanel makeButtonPane(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton addPlayers = new JButton("Add Top 8");
        JButton randomzie = new JButton("Randomzie");

        buttonPanel.add(addPlayers);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(randomzie);

        addPlayers.addActionListener(e -> {
    if (widgetPanel == null) {widgetPanel = new WidgetPanel();}  // first time
         else {
            if (!widgetPanel.isVisible()) {widgetPanel = new WidgetPanel();}// if the window was disposed, make a new one
                else {widgetPanel.toFront();} // bring it to the front if already open
        }
    });
        randomzie.addActionListener(e -> {
            if (bracketPanel == null) {bracketPanel = new BracketPanel();}
            bracketPanel.updateBracket(widgetPanel.getPlayerData());
        });
        return buttonPanel;
    }

}
