package ch.benedict;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class FormularFenster extends JFrame {

    public FormularFenster(PersonenStorage storage, final Controller controller) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.formularFensterSchliessen(FormularFenster.this);
            }
        });
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
