package Winners;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;


public class ImageMerger {
    ImageMerger(String charater1,String charater2,String charater3,String charater4,String charater5,String charater6,String charater7,String charater8){
        BufferedImage bg =Init.getBackgroundimage();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = sdf.format(new Date());
        JPanel char1 = Init.getPanel1();
        JPanel char2 = Init.getPanel2();
        JPanel char3 = Init.getPanel3();
        JPanel char4 = Init.getPanel4();
        JPanel char5 = Init.getPanel5();
        JPanel char6 = Init.getPanel6();
        JPanel char7 = Init.getPanel7();
        JPanel char8 = Init.getPanel8();

        Component[] char1Comp = getPanelsComponets(char1);
        Component[] char2Comp = getPanelsComponets(char2);
        Component[] char3Comp = getPanelsComponets(char3);
        Component[] char4Comp = getPanelsComponets(char4);
        Component[] char5Comp = getPanelsComponets(char5);
        Component[] char6Comp = getPanelsComponets(char6);
        Component[] char7Comp = getPanelsComponets(char7);
        Component[] char8Comp = getPanelsComponets(char8);

        JLabel first = makeCharacterPortraitWithPlacementAndUsername(
                charaterPNGChooser(getFighter(char1Comp)),
                char1,
                char1Comp);
        JLabel sec = makeCharacterPortraitWithPlacementAndUsername(
                charaterPNGChooser(getFighter(char2Comp)),
                char2,
                char2Comp);
        JLabel third = makeCharacterPortraitWithPlacementAndUsername(
                charaterPNGChooser(getFighter(char3Comp)),
                char3,
                char3Comp);
        JLabel forth = makeCharacterPortraitWithPlacementAndUsername(
                charaterPNGChooser(getFighter(char4Comp)),
                char4,
                char4Comp);
        JLabel fith = makeCharacterPortraitWithPlacementAndUsername(
                charaterPNGChooser(getFighter(char5Comp)),
                char5,
                char5Comp);
        JLabel sixth = makeCharacterPortraitWithPlacementAndUsername(
                charaterPNGChooser(getFighter(char6Comp)),
                char6,
                char6Comp);
        JLabel seventh = makeCharacterPortraitWithPlacementAndUsername(
                charaterPNGChooser(getFighter(char7Comp)),
                char7,
                char7Comp);
        JLabel eightth = makeCharacterPortraitWithPlacementAndUsername(
                charaterPNGChooser(getFighter(char8Comp)),
                char8,
                char8Comp);
        JLabel finalMerged = mergeAllCharactersOnBackground(
                bg, first, sec, third, forth, fith, sixth, seventh, eightth
        );
        JOptionPane.showMessageDialog(null, finalMerged);

    }
    public Component[] getPanelsComponets(JPanel p){
        return p.getComponents();
    }
    public String getFighter(Component[] components){
        for(Component component : components){
            if(component instanceof JComboBox && component.getName().contains("fighterDropdown")){return ((JComboBox) component).getSelectedItem().toString();}
        }
        return "";
    }
    public double characterSizeScaler(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int avgSize = (width + height) / 2;

        double scale;

        if (avgSize > 2000) {scale = 10;}
        else if (avgSize > 1500) {scale = 8;}
        else if (avgSize > 1000) {scale = 6;}
        else if (avgSize > 500) {scale = 1;}
        else {scale = 0.5;}

        return scale;
    }
    public String charaterPNGChooser(String charater){
        String pic = "";
        switch(charater){
            case "Banjo & Kazooie" -> pic = "Banjo_&_Kazooie_SSBU.png";
            case "Bayonetta" -> pic = "Bayonetta.png";
            case "Bowser" -> pic = "Bowser.png";
            case "Bowser Jr." -> pic = "Bowser Jr.png";
            case "Byleth" -> pic = "Byleth_SSBU.png";
            case "Captain Falcon" -> pic = "Capt Falc.png";
            case "Charizard" -> pic = "Pokemon.png";
            case "Chrom" -> pic = "Chrom_SSBU.png";
            case "Cloud" -> pic = "Cloud.png";
            case "Corrin" -> pic = "Corrin.png";
            case "Daisy" -> pic = "Daisy.png";
            case "Dark Pit" -> pic = "Dark Pit.png";
            case "Dark Samus" -> pic = "Dark_Samus_SSBU.png";
            case "Diddy Kong" -> pic = "Diddy.png";
            case "Donkey Kong" -> pic = "DK.png";
            case "Dr. Mario" -> pic = "DrMario.png";
            case "Duck Hunt" -> pic = "Duck Hunt.png";
            case "Falco" -> pic = "Falco.png";
            case "Fox" -> pic = "Fox.png";
            case "Ganondorf" -> pic = "Gannon.png";
            case "Greninja" -> pic = "Ninja.png";
            case "Hero" -> pic = "Luminary_SSBU.png";
            case "Ice Climbers" -> pic = "Ice Climbers.png";
            case "Ike" -> pic = "Ike.png";
            case "Incineroar" -> pic = "Incineroar_SSBU.png";
            case "Inkling" -> pic = "Inkling.png";
            case "Isabelle" -> pic = "";
            case "Ivysaur" -> pic = "Pokemon.png";
            case "Jigglypuff" -> pic = "Jiggly.png";
            case "Joker" -> pic = "Joker_SSBU.png";
            case "Kazuya" -> pic = "SSBU_Kazuya.png";
            case "Ken" -> pic = "Ken_SSBU.png";
            case "King Dedede" -> pic = "King Dede.png";
            case "King K. Rool" -> pic = "King_K_Rool_SSBU.png";
            case "Kirby" -> pic = "Kirby.png";
            case "Link" -> pic = "Link.png";
            case "Little Mac" -> pic = "Little Mac.png";
            case "Lucario" -> pic = "Lucario.png";
            case "Lucas" -> pic = "Lucas.png";
            case "Lucina" -> pic = "Lucina.png";
            case "Luigi" -> pic = "Luigi.png";
            case "Mario" -> pic = "Mario.png";
            case "Marth" -> pic = "Marth.png";
            case "Mega Man" -> pic = "MegaMan.png";
            case "Meta Knight" -> pic = "Meta Knight.png";
            case "Mewtwo" -> pic = "MewTwo.png";
            case "Mii Brawler" -> pic = "Wii Fighters.png"; //probably better png to get
            case "Mii Gunner" -> pic = "Wii Fighters.png";
            case "Mii Swordfighter" -> pic = "Wii Fighters.png";
            case "Min Min" -> pic = "Min_Min_SSBU.png";
            case "Mr. Game & Watch" -> pic = "GameNWatch.png";
            case "Mythra" -> pic = "SSBU_Pyra_Mythra.png";
            case "Ness" -> pic = "Ness.png";
            case "Olimar" -> pic = "Olimar.png";
            case "Pac-Man" -> pic = "PacMan.png";
            case "Palutena" -> pic = "Palla.png";
            case "Peach" -> pic = "Peach.png";
            case "Pichu" -> pic = "Pika.png";
            case "Pikachu" -> pic = "Pichu.png";
            case "Piranha Plant" -> pic = "Piranha_Plant_SSBU.png";
            case "Pit" -> pic = "Pit.png";
            case "Pyra" -> pic = "SSBU_Pyra_Mythra.png";
            case "Richter" -> pic = "Richter_Belmont_SSBU.png";
            case "Ridley" -> pic = "Ridley.png";
            case "R.O.B." -> pic = "Rob.png";
            case "Robin" -> pic = "Robin.png";
            case "Rosalina & Luma" -> pic = "Rosa.png";
            case "Roy" -> pic = "Roy.png";
            case "Ryu" -> pic = "Ryu.png";
            case "Samus" -> pic = "Samus.png";
            case "Sephiroth" -> pic = "SSBU_Sephiroth.png";
            case "Sheik" -> pic = "Sheik.png";
            case "Shulk" -> pic = "Shulk.png";
            case "Simon" -> pic = "Simon_Belmont_SSBU.png";
            case "Snake" -> pic = "Snake.png";
            case "Sonic" -> pic = "Sanic.png";
            case "Sora" -> pic = "Sora_SSBU.png";
            case "Squirtle" -> pic = "Pokemon.png";
            case "Steve" -> pic = "SSBU_Steve.png";
            case "Terry" -> pic = "Terry_SSBU.png";
            case "Toon Link" -> pic = "Toon Link.png";
            case "Villager" -> pic = "Villiger.png";
            case "Wario" -> pic = "Wario.png";
            case "Wii Fit Trainer" -> pic = "Wii Fit.png";
            case "Wolf" -> pic = "Wolf.png";
            case "Yoshi" -> pic = "Yoshi.png";
            case "Young Link" -> pic = "Young Link.png";
            case "Zelda" -> pic = "Zelda.png";
            case "Zero Suit Samus" -> pic = "Zero Sute.png";
        }
        return pic;
    }
    public JLabel makeCharacterPortraitWithPlacementAndUsername(String characterPng, JPanel sourcePanel,Component[] comps) {
        try {
            BufferedImage original = ImageIO.read(getClass().getClassLoader().getResource(characterPng));
            int width = original.getWidth();
            int height = original.getHeight();

            double ovalWidth = width * 0.8;
            double ovalHeight = height;
            double x = (width - ovalWidth) / 2;
            double y = 0;

            BufferedImage ovalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = ovalImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setClip(new Ellipse2D.Double(x, y, ovalWidth, ovalHeight));
            g2d.drawImage(original, 0, 0, null);
            g2d.dispose();

            double scale = characterSizeScaler(original);
            int scaledWidth = (int) (width / scale);
            int scaledHeight = (int) (height / scale);

            BufferedImage scaled = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = scaled.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage(ovalImage, 0, 0, scaledWidth, scaledHeight, null);
            g2.dispose();

            JLabel placement = placementLabel(sourcePanel);
            JLabel username = usernameLabel(comps);

            String placementText = placement.getText();
            Color placementColor = placement.getForeground();

            String usernameText = username.getText();
            Color usernameColor = Color.WHITE;

            BufferedImage combined = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = combined.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g.drawImage(scaled, 0, 0, null);

            if (placementText != null && !placementText.isEmpty()) {
                g.setFont(new Font("SansSerif", Font.BOLD, 40));
                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(placementText);
                int xText = 5; //left juistied
                int yText = fm.getAscent() + 10; //places at top

                g.setColor(Color.BLACK);
                g.drawString(placementText, xText + 2, yText + 2);
                g.setColor(placementColor);
                g.drawString(placementText, xText, yText);
            }

            if (usernameText != null && !usernameText.isEmpty()) {
                g.setFont(new Font("SansSerif", Font.BOLD, 40));
                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(usernameText);
                int xText = (scaledWidth - textWidth) / 2;
                int yText = scaledHeight - fm.getDescent() - 10; //places at bottom

                g.setColor(Color.BLACK);
                g.drawString(usernameText, xText + 2, yText + 2);
                g.setColor(usernameColor);
                g.drawString(usernameText, xText, yText);
            }

            g.dispose();

            ImageIO.write(combined, "png", new File("picture_with_placement_and_username.png"));

            return new JLabel(new ImageIcon(combined));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public JLabel mergeAllCharactersOnBackground(BufferedImage bg,
                                                 JLabel first, JLabel sec, JLabel third, JLabel forth,
                                                 JLabel fith, JLabel sixth, JLabel seventh, JLabel eighth) {

        JLabel[] portraits = {first, sec, third, forth, fith, sixth, seventh, eighth};

        int total = portraits.length;
        int cols = 4;
        int rows = (int) Math.ceil(total / (double) cols);

        int bgWidth = bg.getWidth();
        int bgHeight = bg.getHeight();

        BufferedImage finalImage = new BufferedImage(bgWidth, bgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = finalImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.drawImage(bg, 0, 0, null);

        int cellWidth = bgWidth / cols;
        int cellHeight = bgHeight / rows;

        for (int i = 0; i < total; i++) {
            int col = i % cols;
            int row = i / cols;


            ImageIcon icon = (ImageIcon) portraits[i].getIcon();
            Image portrait = icon.getImage();

            int portraitWidth = icon.getIconWidth();
            int portraitHeight = icon.getIconHeight();

            double scale = Math.min((double) cellWidth / portraitWidth, (double) cellHeight / portraitHeight);
            int newWidth = (int) (portraitWidth * scale);
            int newHeight = (int) (portraitHeight * scale);

            int x = col * cellWidth + (cellWidth - newWidth) / 2;
            int y = row * cellHeight + (cellHeight - newHeight) / 2;

            g.drawImage(portrait, x, y, newWidth, newHeight, null);
        }

        g.dispose();

        String filepath = System.getProperty("user.home").toString() + "/Pictures";
        try {
            ImageIO.write(finalImage, "png", new File(filepath,"Top 8 Picture"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JLabel(new ImageIcon(finalImage));
    }

    public JLabel usernameLabel(Component[] comps){
        JLabel usernameLabel = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        usernameLabel.setBorder(border);
        usernameLabel.setForeground(Color.WHITE);

        for(Component component : comps){
            if(component instanceof JTextField){
                usernameLabel.setText(((JTextField) component).getText());

            }
        }
        return usernameLabel;
    }
    public JLabel placementLabel(JPanel panel){
        JLabel placement = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        placement.setBorder(border);
        placement.setForeground(Color.WHITE);

        Component[] components = panel.getComponents();
        for(Component component : components){
            if(component instanceof JTextField){
                placement.setText(((JTextField) component).getText());
            }
            if(component instanceof JComboBox<?> && component.getName().contains("Placement")){
                if(Objects.equals(((JComboBox<?>) component).getSelectedItem().toString(), "1")){placement.setForeground(Color.YELLOW); placement.setText(((JComboBox<?>) component).getSelectedItem().toString() +"st");}
                if(Objects.equals(((JComboBox<?>) component).getSelectedItem().toString(), "2")){placement.setForeground(Color.GRAY);placement.setText(((JComboBox<?>) component).getSelectedItem().toString()+"nd");}
                if(Objects.equals(((JComboBox<?>) component).getSelectedItem().toString(), "3")){placement.setForeground(Color.ORANGE);placement.setText(((JComboBox<?>) component).getSelectedItem().toString()+"rd");}
                if(Objects.equals(((JComboBox<?>) component).getSelectedItem().toString(), "4")){placement.setText(((JComboBox<?>) component).getSelectedItem().toString()+"th");}
                if(Objects.equals(((JComboBox<?>) component).getSelectedItem().toString(), "5")){placement.setText(((JComboBox<?>) component).getSelectedItem().toString()+"th");}
                if(Objects.equals(((JComboBox<?>) component).getSelectedItem().toString(), "6")){placement.setText(((JComboBox<?>) component).getSelectedItem().toString()+"th");}
                if(Objects.equals(((JComboBox<?>) component).getSelectedItem().toString(), "7")){placement.setText(((JComboBox<?>) component).getSelectedItem().toString()+"th");}
                if(Objects.equals(((JComboBox<?>) component).getSelectedItem().toString(), "8")){placement.setText(((JComboBox<?>) component).getSelectedItem().toString()+"th");}
            }
        }
        return placement;
    }
//    public JLabel makeCharaterPortrait(String Charaterpng, JPanel sourcePanel){
//        try {
//            BufferedImage original = ImageIO.read(getClass().getClassLoader().getResource(Charaterpng));
//            int width = original.getWidth();
//            int height = original.getHeight();
//
//            double ovalWidth = width * 0.8;
//            double ovalHeight = height;
//            double x = (width - ovalWidth) / 2;
//            double y = 0;
//
//            BufferedImage ovalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//
//            Graphics2D g2d = ovalImage.createGraphics();
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//            Ellipse2D.Double oval = new Ellipse2D.Double(x, y, ovalWidth, ovalHeight);
//            g2d.setClip(oval);
//            g2d.drawImage(original, 0, 0, null);
//            g2d.dispose();
//
//            double scaledWidth = width / characterSizeScaler(original);
//            double scaledHeight = height / characterSizeScaler(original);
//
//            BufferedImage scaled = new BufferedImage((int)scaledWidth, (int)scaledHeight, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g2 = scaled.createGraphics();
//            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2.drawImage(ovalImage, 0, 0, (int)scaledWidth, (int)scaledHeight, null);
//            g2.dispose();
//
//            //ImageIO.write(scaled, "png", new File("picture_oval_small.png"));
//
//            return new JLabel(new ImageIcon(scaled));
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public JLabel makeCharacterPortraitWithPlacement(String characterPng, JPanel sourcePanel) {
//        try {
//            BufferedImage original = ImageIO.read(getClass().getClassLoader().getResource(characterPng));
//            int width = original.getWidth();
//            int height = original.getHeight();
//
//            double ovalWidth = width * 0.8;
//            double ovalHeight = height;
//            double x = (width - ovalWidth) / 2;
//            double y = 0;
//
//            BufferedImage ovalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g2d = ovalImage.createGraphics();
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2d.setClip(new Ellipse2D.Double(x, y, ovalWidth, ovalHeight));
//            g2d.drawImage(original, 0, 0, null);
//            g2d.dispose();
//
//            double scale = characterSizeScaler(original);
//            int scaledWidth = (int) (width / scale);
//            int scaledHeight = (int) (height / scale);
//
//            BufferedImage scaled = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g2 = scaled.createGraphics();
//            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2.drawImage(ovalImage, 0, 0, scaledWidth, scaledHeight, null);
//            g2.dispose();
//
//            JLabel placement = placementLabel(sourcePanel);
//            String text = placement.getText();
//            Color textColor = placement.getForeground();
//
//            BufferedImage combined = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g = combined.createGraphics();
//            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//            g.drawImage(scaled, 0, 0, null);
//
//            if (text != null && !text.isEmpty()) {
//                g.setFont(new Font("SansSerif", Font.BOLD, 40));
//                FontMetrics fm = g.getFontMetrics();
//                int textWidth = fm.stringWidth(text);
//                int xText = (scaledWidth - textWidth) / 2;
//                int yText = fm.getAscent() + 10;
//
//                g.setColor(Color.BLACK);
//                g.drawString(text, xText + 2, yText + 2);
//
//                g.setColor(textColor);
//                g.drawString(text, xText, yText);
//            }
//
//            g.dispose();
//
//            ImageIO.write(combined, "png", new File("picture_with_placement.png"));
//
//            return new JLabel(new ImageIcon(combined));
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
