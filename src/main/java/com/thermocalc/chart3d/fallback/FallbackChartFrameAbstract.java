package com.thermocalc.chart3d.fallback;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JFrame;

import org.jzy3d.chart.Chart;
import org.jzy3d.ui.LookAndFeel;

public abstract class FallbackChartFrameAbstract extends JFrame {
    private static final long serialVersionUID = 2241304275629861521L;

    public FallbackChartFrameAbstract(Chart... charts) {
        this(Arrays.asList(charts));
    }

    public FallbackChartFrameAbstract(Collection<? extends Chart> charts) {
        LookAndFeel.apply();

        setLayout(charts);

        windowExitListener();
        this.pack();
        setVisible(true);
        setBounds(new java.awt.Rectangle(10, 10, 800, 600));
        
        render(charts); // Generate the first image once the frame is loaded with its final dimension
    }
    
    protected abstract void setLayout(Collection<? extends Chart> charts);

    
    public FallbackChartFrameAbstract() throws HeadlessException {
        super();
    }

    public FallbackChartFrameAbstract(GraphicsConfiguration gc) {
        super(gc);
    }

    public FallbackChartFrameAbstract(String title) throws HeadlessException {
        super(title);
    }

    public FallbackChartFrameAbstract(String title, GraphicsConfiguration gc) {
        super(title, gc);
    }

    protected void render(Collection<? extends Chart> charts) {
        for (Chart c : charts) {
            c.render();
        }
    }

    public void windowExitListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FallbackChartFrameAbstract.this.dispose();
                System.exit(0);
            }
        });
    }

}