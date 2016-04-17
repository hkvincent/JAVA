/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO.OpenFile;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import static sun.misc.ClassFileTransformer.add;

/**
 *
 * @author Administrator
 */
public class ProgressFrame extends JFrame implements ActionListener {

    private JButton btn = new JButton("Start");
    private JProgressBar bar = new JProgressBar() {
        public void paint(Graphics g) {
            super.paint(g);
            System.out.println("paint");
        }
    };

    public ProgressFrame() {
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        add(btn);
        add(bar);
        btn.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    bar.setValue(i * 10);
                }
            }
        };
        new Thread(runnable).start();
    }


public static void main(String[] args) {
        new ProgressFrame();
    }

}
