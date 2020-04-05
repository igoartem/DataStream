package org.example.DataStreams.menu;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItem {
    private static final Logger log = LoggerFactory.getLogger(MenuItem.class);
    private Object obj;
    private String label;
    private String target;
    private boolean isExitItem;

    public MenuItem(String label) {
        this(label, null, null);
    }

    public MenuItem(String label, Object obj, String target) {
        this.label = label;
        this.obj = obj;
        this.target = target;
    }
    
    void invoke() {
        if (target == null)
            return;
        try {
            Method method = obj.getClass().getMethod(target);
            method.invoke(obj);
        } catch (Exception ex) {
            log.error("", ex);
        }
    }

}