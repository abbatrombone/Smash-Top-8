package com.github.lushen124.BRACKET;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BracketPanel extends JPanel {

    private final List<MatchPanel> quarterFinals = new ArrayList<>();
    private final List<MatchPanel> semiFinals = new ArrayList<>();
    private final List<MatchPanel> finals = new ArrayList<>();

    public BracketPanel() {
        setLayout(null); // we’ll use absolute positioning for precise line control
        setPreferredSize(new Dimension(1000, 600));

        int xSpacing = 300;
        int yStart = 50;
        int yGap = 120;

        // Quarterfinals (4 matches)
        for (int i = 0; i < 4; i++) {
            MatchPanel m = new MatchPanel("Team " + (i * 2 + 1), "Team " + (i * 2 + 2),false);
            m.setBounds(50, yStart + i * yGap, 200, 60);
            quarterFinals.add(m);
            add(m);
        }

        // Semifinals (2 matches)
        for (int i = 0; i < 2; i++) {
            MatchPanel m = new MatchPanel("Winner", "Winner",false);
            m.setBounds(50 + xSpacing, yStart + i * (2 * yGap), 200, 60);
            semiFinals.add(m);
            add(m);
        }

        // Finals (1 match)
        MatchPanel finalMatch = new MatchPanel("Winner", "Winner",true);
        finalMatch.setBounds(50 + 2 * xSpacing, yStart + yGap, 200, 60);
        finals.add(finalMatch);
        add(finalMatch);
        setupWinnerCallbacks();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(2));

        // Connect quarterfinals to semifinals
        for (int i = 0; i < 4; i += 2) {
            MatchPanel q1 = quarterFinals.get(i);
            MatchPanel q2 = quarterFinals.get(i + 1);
            MatchPanel semi = semiFinals.get(i / 2);

            Point p1 = getMatchCenterRight(q1);
            Point p2 = getMatchCenterRight(q2);
            Point dest = getMatchCenterLeft(semi);

            int midY = (p1.y + p2.y) / 2;

            g2.drawLine(p1.x, p1.y, p1.x + 40, p1.y);
            g2.drawLine(p2.x, p2.y, p2.x + 40, p2.y);
            g2.drawLine(p1.x + 40, p1.y, p1.x + 40, p2.y); // vertical connector
            g2.drawLine(p1.x + 40, midY, dest.x, dest.y);  // to next match
        }

        // Connect semifinals to final
        for (int i = 0; i < 2; i += 2) {
            MatchPanel s1 = semiFinals.get(i);
            MatchPanel s2 = semiFinals.get(i + 1);
            MatchPanel f = finals.get(0);

            Point p1 = getMatchCenterRight(s1);
            Point p2 = getMatchCenterRight(s2);
            Point dest = getMatchCenterLeft(f);

            int midY = (p1.y + p2.y) / 2;

            g2.drawLine(p1.x, p1.y, p1.x + 40, p1.y);
            g2.drawLine(p2.x, p2.y, p2.x + 40, p2.y);
            g2.drawLine(p1.x + 40, p1.y, p1.x + 40, p2.y); // vertical connector
            g2.drawLine(p1.x + 40, midY, dest.x, dest.y);  // to next match
        }
    }

    private Point getMatchCenterRight(JPanel match) {
        return new Point(match.getX() + match.getWidth(), match.getY() + match.getHeight() / 2);
    }

    private Point getMatchCenterLeft(JPanel match) {
        return new Point(match.getX(), match.getY() + match.getHeight() / 2);
    }
    public void updateBracket(Map<String, String> playerData) {
        int i = 0;
        for (Map.Entry<String, String> entry : playerData.entrySet()) {
            String name = entry.getKey();
            String iconPath = entry.getValue();

            MatchPanel match = quarterFinals.get(i / 2);
            if (i % 2 == 0) match.setTeam1(name, iconPath);
            else match.setTeam2(name, iconPath);

            i++;
            if (i >= 8) break; // Only top 8 players
        }

        repaint();
        revalidate();
    }public void setQuarterfinalWinner(int matchIndex, boolean isTeam1Winner) {
        MatchPanel match = quarterFinals.get(matchIndex);
        String winnerName = isTeam1Winner ? match.getTeam1Name() : match.getTeam2Name();
        String winnerIcon = isTeam1Winner ? match.getTeam1IconPath() : match.getTeam2IconPath();

        int semiIndex = matchIndex / 2;
        MatchPanel semi = semiFinals.get(semiIndex);

        if (matchIndex % 2 == 0) semi.setTeam1(winnerName, winnerIcon);
        else semi.setTeam2(winnerName, winnerIcon);

        semi.repaint();
        semi.revalidate();
    }

    public void setSemifinalWinner(int semiIndex, boolean isTeam1Winner) {
        MatchPanel semi = semiFinals.get(semiIndex);
        String winnerName = isTeam1Winner ? semi.getTeam1Name() : semi.getTeam2Name();
        String winnerIcon = isTeam1Winner ? semi.getTeam1IconPath() : semi.getTeam2IconPath();

        MatchPanel f = finals.get(0);
        if (semiIndex == 0) f.setTeam1(winnerName, winnerIcon);
        else f.setTeam2(winnerName, winnerIcon);

        f.repaint();
        f.revalidate();
    }
    private void setupWinnerCallbacks() {
        // Quarterfinals
        for (int i = 0; i < 4; i++) {
            final int matchIndex = i;
            MatchPanel q = quarterFinals.get(i);
            q.setWinnerCallback(() -> {
                int choice = JOptionPane.showOptionDialog(
                        this,
                        "Who won this match?",
                        "Select Winner",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{q.getTeam1Name(), q.getTeam2Name()},
                        q.getTeam1Name()
                );
                if (choice == 0) setQuarterfinalWinner(matchIndex, true);
                else if (choice == 1) setQuarterfinalWinner(matchIndex, false);
            });
        }

        // Semifinals
        for (int i = 0; i < 2; i++) {
            final int semiIndex = i;
            MatchPanel s = semiFinals.get(i);
            s.setWinnerCallback(() -> {
                int choice = JOptionPane.showOptionDialog(
                        this,
                        "Who won this match?",
                        "Select Winner",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{s.getTeam1Name(), s.getTeam2Name()},
                        s.getTeam1Name()
                );
                if (choice == 0) setSemifinalWinner(semiIndex, true);
                else if (choice == 1) setSemifinalWinner(semiIndex, false);

            });
        }

        final MatchPanel f = finals.get(0);
        f.setWinnerCallback(() -> {
            int choice = JOptionPane.showOptionDialog(
                    this,
                    "Who is the champion?",
                    "Select Winner",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{f.getTeam1Name(), f.getTeam2Name()},
                    f.getTeam1Name()
            );
            String champName = choice == 0 ? f.getTeam1Name() : f.getTeam2Name();
            String champIcon = choice == 0 ? f.getTeam1IconPath() : f.getTeam2IconPath();
            JOptionPane.showMessageDialog(this, "🏆 Champion: " + champName + " 🏆");
            // optionally store champion info for further use
        });

    }

    // Match panel UI
    private static class MatchPanel extends JPanel {
        private final JLabel team1Label;
        private final JLabel team2Label;
        private final JLabel team1IconLabel;
        private final JLabel team2IconLabel;

        private String team1IconPath;
        private String team2IconPath;

        private Runnable onWinnerChosen; // callback to bracket panel

        public MatchPanel(String team1, String team2, boolean isFinal) {
            setLayout(new GridLayout(2, 1));
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
            setBackground(new Color(245, 245, 245));

            JPanel slot1 = createTeamSlot(team1, "logo1.png");
            JPanel slot2 = createTeamSlot(team2, "logo2.png");

            team1IconLabel = (JLabel) slot1.getComponent(0);
            team1Label = (JLabel) slot1.getComponent(2);
            team2IconLabel = (JLabel) slot2.getComponent(0);
            team2Label = (JLabel) slot2.getComponent(2);

            add(slot1);
            add(slot2);

            JButton winnerBtn = new JButton(isFinal ? "Pick Champion" : "Pick Winner");
            winnerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            winnerBtn.setMaximumSize(new Dimension(150, 25)); // prevent cutting off
            winnerBtn.addActionListener(e -> {
                if (onWinnerChosen != null) onWinnerChosen.run();
            });
            add(Box.createVerticalStrut(5));
            add(winnerBtn);
        }

        private JPanel createTeamSlot(String name, String logoPath) {
            JPanel slot = new JPanel(new FlowLayout(FlowLayout.LEFT));
            slot.setOpaque(false);

            ImageIcon icon;
            try {
                icon = new ImageIcon(new ImageIcon(logoPath)
                        .getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
            } catch (Exception e) {
                icon = new ImageIcon(new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB));
            }

            JLabel logoLabel = new JLabel(icon);
            JLabel nameLabel = new JLabel(name);
            nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

            //JButton winnerBtn = new JButton("Winner");
//            winnerBtn.setMargin(new Insets(2,2,2,2));
//            winnerBtn.addActionListener(e -> {
//                if (onWinnerChosen != null) onWinnerChosen.run();
//            });

            slot.add(logoLabel);
            slot.add(Box.createHorizontalStrut(5));
            slot.add(nameLabel);
            slot.add(Box.createHorizontalStrut(10));
            //slot.add(winnerBtn);

            return slot;
        }

        public void setTeam1(String name, String iconPath) {
            team1Label.setText(name);
            team1IconPath = iconPath;
            setIcon(team1IconLabel, iconPath);
        }

        public void setTeam2(String name, String iconPath) {
            team2Label.setText(name);
            team2IconPath = iconPath;
            setIcon(team2IconLabel, iconPath);
        }

        private void setIcon(JLabel label, String path) {
            if (path == null || path.isEmpty()) return;
            ImageIcon icon = new ImageIcon(new ImageIcon(path)
                    .getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
            label.setIcon(icon);
        }

        public String getTeam1Name() { return team1Label.getText(); }
        public String getTeam2Name() { return team2Label.getText(); }
        public String getTeam1IconPath() { return team1IconPath; }
        public String getTeam2IconPath() { return team2IconPath; }

        public void setWinnerCallback(Runnable r) { onWinnerChosen = r; }
    }
}
