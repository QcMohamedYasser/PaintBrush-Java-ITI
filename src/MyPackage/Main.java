package MyPackage;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");			// the title of program is set as My Drawing
        frame.setSize(1200, 800);					// the size of the frame is set to 1200w, 800h

        frame.setBackground(Color.white);
        viewpanel mp =new viewpanel();

                frame.setContentPane(mp);
                frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		// the operation will automatically off when user clicked exit button

            }
        }