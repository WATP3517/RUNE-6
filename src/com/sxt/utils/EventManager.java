package com.sxt.utils;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class EventManager {
    
    public static void clearKeyEvents() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        
        AWTEventListener listener = event -> {
            if (event instanceof KeyEvent) {
                ((KeyEvent) event).consume();
            }
        };
        
        long eventMask = AWTEvent.KEY_EVENT_MASK;
        toolkit.addAWTEventListener(listener, eventMask);
        toolkit.removeAWTEventListener(listener);
    }
    
    public static void executeTimerSequence(TimerStep[] steps) {
        if (steps == null || steps.length == 0) return;
        executeStep(steps, 0);
    }
    
    private static void executeStep(TimerStep[] steps, int index) {
        if (index >= steps.length) return;
        
        TimerStep step = steps[index];
        new Timer(step.delay, e -> {
            ((Timer) e.getSource()).stop();
            step.action.run(); 
            executeStep(steps, index + 1); 
        }).start();
    }
    
    public static class TimerStep {
        public int delay;
        public Runnable action;
        
        public TimerStep(int delay, Runnable action) {
            this.delay = delay;
            this.action = action;
        }
    }
}
    