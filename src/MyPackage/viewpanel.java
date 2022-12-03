package MyPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class viewpanel extends JPanel {


    ArrayList<Shape> componnet;
    int width, height, x1, y1, x2, y2;
    Color color;
    boolean isDragged, isDotted, isFilled;
    SelectShape selected;
    int switcher = 1;


    JButton red;
    JButton green;
    JButton blue;
    JButton rectangle;
    JButton oval;
    JButton line;
    JButton eraser;
    JButton clear;
    JButton freehand;
    Checkbox dotted;
    Checkbox filled;




    public viewpanel() {


        this.setBackground(Color.white);
        setLayout(new GridLayout(1, 8, 10, 0));
        setLayout(new FlowLayout(FlowLayout.LEADING, 2, 0));

        this.setVisible(true);

        componnet = new ArrayList<>();
        red = new JButton("RED");
        green = new JButton("green");
        blue = new JButton("Blue");
        oval = new JButton("oval");
        rectangle = new JButton("rectangle");
        line = new JButton("line");
        eraser = new JButton("eraser");
        dotted = new Checkbox("dotted");
        freehand = new JButton("Free");
        filled = new Checkbox("filled");
        clear = new JButton("clear");


        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.red;

            }
        });

        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.green;

            }
        });
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = Color.blue;

            }
        });



        rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = SelectShape.Rectangle;
            }
        });


        oval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = SelectShape.Oval;
            }
        });


        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = SelectShape.Line;
            }
        });
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = SelectShape.Eraser;
            }
        });
        freehand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = SelectShape.Freehand;
            }
        });
        filled.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    isFilled = true;
                } else isFilled = false;
            }
        });
        dotted.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    isDotted = true;
                } else isDotted = false;
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componnet.clear();
                updateUI();
            }
        });
        this.add(rectangle);
        this.add(oval);
        this.add(line);
        this.add(eraser);
        this.add(freehand);
        this.add(clear);
        this.add(red);
        this.add(green);
        this.add(blue);
        this.add(filled);
        this.add(dotted);


        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (selected == SelectShape.Line) {
                    x1 = e.getX();
                    y1 = e.getY();
                    isDragged = true;
                    updateUI();
                }
                if (selected == SelectShape.Rectangle )
                {
                    x1 = e.getX();
                    y1 = e.getY();
                    isDragged = true;
                    updateUI();
                }

                if (selected== SelectShape.Oval)
                {

                    x1 = e.getX();
                    y1 = e.getY();

                    isDragged = false;
                    updateUI();


                }
                if (selected == SelectShape.Eraser) {
                    componnet.add(new Eraser(x1,y1));
                    isDragged = true;
                }
                if (selected == SelectShape.Freehand) {

                    componnet.add(new FreeHand(x1,y1,color));
                    isDragged = true;
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selected == SelectShape.Line) {
                    x2 = e.getX();
                    y2 = e.getY();
                    componnet.add(new Line(x1,y1,x2,y2,color,isDotted));
                    isDragged = true;
                    repaint();
                }
                if (selected == SelectShape.Rectangle) {
                    width = Math.abs(x1 - x2);
                    height = Math.abs(y1 - y2);
                    componnet.add(new Rectangle(Math.min(x1, x2), Math.min(y1, y2), width, height, color, isFilled, isDotted));
                    isDragged = true;
                    repaint();
                }
                if (selected == SelectShape.Oval) {
                    width = Math.abs(x1 - x2);
                    height = Math.abs(y1 - y2);

                    componnet.add(new Oval(Math.min(x1, x2), Math.min(y1, y2), width, height, color, isDotted, isFilled));
                    isDragged = true;
                    repaint();
                }


            }

            @Override
            public void mouseEntered(MouseEvent e) {


            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selected == SelectShape.Line) {

                    x2=getX();
                    y2=getY();
                    isDragged = true;
                    updateUI();
                }
                if (selected == SelectShape.Rectangle) {
                    x2 = e.getX();
                    y2 = e.getY();
                    isDragged = true;
                    updateUI();
                }
                if ( selected == SelectShape.Oval) {
                    x2 = e.getX();
                    y2 = e.getY();
                    isDragged = true;
                    updateUI();
                }
                if (selected == SelectShape.Eraser) {
                    componnet.add(new Eraser(e.getX(),e.getY()));
                    isDragged = true;
                    updateUI();

                }
                if (selected == SelectShape.Freehand) {

                    componnet.add(new FreeHand(e.getX(),e.getY(),color));
                    isDragged = true;
                    updateUI();
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if(switcher == 0) {
            return;
        }else{
            for (Shape sh : componnet) {
                g.setColor(sh.getColor());
                if (sh.isDotted()) {
                    g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10, new float[]{9}, 0));
                } else
                    g2d.setStroke(new BasicStroke(0));
                if (sh instanceof Line) {

                    g.drawLine(((Line) sh).getXx1(), ((Line) sh).getYy1(), ((Line) sh).getXx2(), ((Line) sh).getYy2());
                }


                if (sh instanceof Rectangle) {
                    if (((Rectangle) sh).isFilled())
                        g.fillRect(((Rectangle) sh).getXx(), ((Rectangle) sh).getYy(), ((Rectangle) sh).getWidth(), ((Rectangle) sh).getHeight());
                    else
                        g.drawRect(((Rectangle) sh).getXx(), ((Rectangle) sh).getYy(), ((Rectangle) sh).getWidth(), ((Rectangle) sh).getHeight());
                }
                if (sh instanceof Oval) {
                    if (((Oval) sh).isFilled()) {
                        g.fillOval(((Oval) sh).getXx(), ((Oval) sh).getYy(), ((Oval) sh).getWidth(), ((Oval) sh).getHeight());
                    } else
                        g.drawOval(((Oval) sh).getXx(), ((Oval) sh).getYy(), ((Oval) sh).getWidth(), ((Oval) sh).getHeight());

                }
                if (sh instanceof Eraser) {
                    g.fillRect(((Eraser) sh).getXx(), ((Eraser) sh).getYy(), ((Eraser) sh).getWidth(), ((Eraser) sh).getHeight());
                }
            }
        }
    }
}
