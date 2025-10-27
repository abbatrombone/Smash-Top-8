package Winners;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Init {

    private static final Logger logger = Logger.getLogger(com.github.lushen124.BRACKET.Init.class.getName());
    private static final JFrame frame = new JFrame();
    private static final JPanel mainPanel = new JPanel();
    private static final JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 5));
    private static final JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 5));
    private static final String[] fighters = {"Banjo & Kazooie","Bayonetta","Bowser","Bowser Jr.","Byleth","Captain Falcon","Charizard","Chrom","Cloud","Corrin","Daisy","Dark Pit","Dark Samus","Diddy Kong","Donkey Kong","Dr. Mario","Duck Hunt","Falco","Fox","Ganondorf","Greninja","Hero","Ice Climbers","Ike","Incineroar","Inkling","Isabelle","Ivysaur","Jigglypuff","Joker","Kazuya","Ken","King Dedede","King K. Rool","Kirby","Link","Little Mac","Lucario","Lucas","Lucina","Luigi","Mario","Marth","Mega Man","Meta Knight","Mewtwo","Mii Brawler","Mii Gunner","Mii Swordfighter","Min Min","Mr. Game & Watch","Mythra","Ness","Olimar","Pac-Man","Palutena","Peach","Pichu","Pikachu","Piranha Plant","Pit","Pyra","Richter","Ridley","R.O.B.","Robin","Rosalina & Luma","Roy","Ryu","Samus","Sephiroth","Sheik","Shulk","Simon","Snake","Sonic","Sora","Squirtle","Steve","Terry","Toon Link","Villager,","Wario","Wii Fit Trainer","Wolf","Yoshi","Young Link,","Zelda","Zero Suit Samus"};
    private static int startingIndex = 0;
    private static final JPanel panel1 = new JPanel();
    private static final JPanel panel2 = new JPanel();
    private static final JPanel panel3 = new JPanel();
    private static final JPanel panel4 = new JPanel();
    private static final JPanel panel5 = new JPanel();
    private static final JPanel panel6 = new JPanel();
    private static final JPanel panel7 = new JPanel();
    private static final JPanel panel8 = new JPanel();

    public static BufferedImage getBackgroundimage() {
        return backgroundimage;
    }

    private static BufferedImage backgroundimage;
    private String titleName;

    public Init() {
        logger.info("Init constructor started");
        frame.setTitle("Top 8 Poster Maker");
        frame.setMinimumSize(new Dimension(640, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1042, 600);

        try {
            backgroundimage = ImageIO.read( Objects.requireNonNull(Init.class.getResourceAsStream("/blank.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mainPanel.setLayout(new GridLayout(10, 1, 0, 5)); // 1 label row + 8 data rows

        List<String> labelList = Arrays.asList("Player Name", "Fighter", "Placement");

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));
        panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));

        panel1.setName("1");
        panel2.setName("2");
        panel3.setName("3");
        panel4.setName("4");
        panel5.setName("5");
        panel6.setName("6");
        panel7.setName("7");
        panel8.setName("8");
        labelPanel.setName("11");

        updatePanel(panel1);
        updatePanel(panel2);
        updatePanel(panel3);
        updatePanel(panel4);
        updatePanel(panel5);
        updatePanel(panel6);
        updatePanel(panel7);
        updatePanel(panel8);

        mainPanel.add(createLablRow(labelList));
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panel5);
        mainPanel.add(panel6);
        mainPanel.add(panel7);
        mainPanel.add(panel8);
        mainPanel.add(makeButtonPanel());

        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public JPanel makeButtonPanel(){
        JPanel pb = new JPanel();
        pb.setLayout(new BoxLayout(pb, BoxLayout.X_AXIS));
        JButton backgrondImageButton = new JButton("Background Picture");
        JButton titleButton = new JButton("Make Title");
        JButton generateButton = new JButton("Generate Image");
        backgrondImageButton.addActionListener(e -> {getBackgroundImage();});
        titleButton.addActionListener(e -> titleName = JOptionPane.showInputDialog(frame, "What Do You Want As A Title?","Set Title",JOptionPane.PLAIN_MESSAGE));
        BufferedImage br;
        try {
            br = ImageIO.read(new File("/home/trazodone/Downloads/blank.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JComboBox comboBoxSelection1 = (JComboBox) panel1.getComponent(1);
        JComboBox comboBoxSelection2 = (JComboBox) panel2.getComponent(1);
        JComboBox comboBoxSelection3 = (JComboBox) panel3.getComponent(1);
        JComboBox comboBoxSelection4 = (JComboBox) panel4.getComponent(1);
        JComboBox comboBoxSelection5 = (JComboBox) panel5.getComponent(1);
        JComboBox comboBoxSelection6 = (JComboBox) panel6.getComponent(1);
        JComboBox comboBoxSelection7 = (JComboBox) panel7.getComponent(1);
        JComboBox comboBoxSelection8 = (JComboBox) panel8.getComponent(1);

        generateButton.addActionListener(e ->  new ImageMerger(comboBoxSelection1.getSelectedItem().toString(),comboBoxSelection2.getSelectedItem().toString(),comboBoxSelection3.getSelectedItem().toString(),comboBoxSelection4.getSelectedItem().toString(),comboBoxSelection5.getSelectedItem().toString(),comboBoxSelection6.getSelectedItem().toString(),comboBoxSelection7.getSelectedItem().toString(),comboBoxSelection8.getSelectedItem().toString()));

        pb.add(backgrondImageButton);
        pb.add(Box.createRigidArea(new Dimension(50,10)));
        pb.add(titleButton);
        pb.add(Box.createRigidArea(new Dimension(50,10)));
        pb.add(generateButton);

        return pb;
    }
    public JPanel updatePanel(JPanel p){

        String[] placement = {"1", "2","3","4","5","6","7","8"};
        JComboBox<String> placementDropdown = new JComboBox<>(placement);
        placementDropdown.setSelectedIndex(startingIndex);
        placementDropdown.setName("Placement");
        JComboBox<String> fighterDropdown = new JComboBox<>(fighters);
        fighterDropdown.setName("fighterDropdown" + startingIndex);
        JTextField name = new JTextField();
        name.setMaximumSize(new Dimension(400, 50));
        fighterDropdown.setMaximumSize(new Dimension(400, 50));
        placementDropdown.setMaximumSize(new Dimension(400, 50));
        name.setName(String.valueOf(startingIndex));
        name.addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e) {}
           @Override
           public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                int i = Integer.parseInt(name.getName()) + 1;
                Component[] components = mainPanel.getComponents();
                for(Component component : components){
                    if(Integer.parseInt(component.getName()) == i ){
                        if (i < 0 || i +1 >= components.length) return;
                        Component[] subComponents = ((JPanel) components[i+1]).getComponents();
                        for(Component c : subComponents){if(c instanceof JTextField){c.requestFocus();}}
                    }
                }
            }
           }
           @Override
           public void keyReleased(KeyEvent e) {}
       });

        p.add(name);
        p.add(fighterDropdown);
        p.add(placementDropdown);
        startingIndex++;
        return p;
    }
    private static JComponent createLabelComponent(String s) {
        JLabel l = new JLabel(s);
        l.setBorder(BorderFactory.createMatteBorder(5,5,5,5,
                Color.DARK_GRAY));
        l.setHorizontalAlignment(JLabel.LEFT);
        return l;
    }
    private JPanel createLablRow(List<String> labels) {
        for (String label : labels) {
            labelPanel.add(createLabelComponent(label));
            labelPanel.add(Box.createRigidArea(new Dimension(100, 0)));
        }
        labelPanel.setMaximumSize(new Dimension(640, 50));

        return labelPanel;
    }
    private static void getBackgroundImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"), "Pictures"));
        fileChooser.showOpenDialog(null);

        if (fileChooser.getSelectedFile() != null) {
            String selectedFile = fileChooser.getSelectedFile().toPath().toString();

            try {
                backgroundimage = ImageIO.read(new File(selectedFile));
                Dimension bBestSize = Toolkit.getDefaultToolkit().getBestCursorSize(500, 500);
                Image scaled = backgroundimage.getScaledInstance(bBestSize.width, bBestSize.height, Image.SCALE_SMOOTH);
                backgroundimage= new BufferedImage(
                        bBestSize.width, bBestSize.height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = backgroundimage.createGraphics();
                g2d.drawImage(scaled, 0, 0, null);
                g2d.dispose();

                JDialog dialog = new JDialog();
                dialog.setTitle("Image Display");
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setSize(500, 500);
                //add button if this background image is ok

                JLabel label = new JLabel(new ImageIcon(backgroundimage));
                dialog.getContentPane().add(label);
                dialog.setVisible(true);

            } catch (IOException e) {
                throw new RuntimeException("Failed to load cursor image: " + e);
            }
        }
    }
    public static JPanel getPanel1(){
        return panel1;
    }
    public static JPanel getPanel2(){
        return panel2;
    }
    public static JPanel getPanel3(){
        return panel3;
    }
    public static JPanel getPanel4(){
        return panel4;
    }
    public static JPanel getPanel5(){
        return panel5;
    }
    public static JPanel getPanel6(){
        return panel6;
    }
    public static JPanel getPanel7(){
        return panel7;
    }
    public static JPanel getPanel8(){
        return panel8;
    }
    public String getTitleName() {
        return titleName;
    }
}
