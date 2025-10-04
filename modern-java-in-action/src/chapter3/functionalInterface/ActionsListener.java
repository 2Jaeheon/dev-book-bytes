package chapter3.functionalInterface;

import java.awt.event.ActionEvent;
import java.util.EventListener;

public interface ActionsListener extends EventListener {

    void actionPerformed(ActionEvent e);
}
