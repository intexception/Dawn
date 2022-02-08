package wtf.infamous.awt.gui.settings;

import wtf.infamous.awt.module.*;
import java.util.*;
import wtf.infamous.awt.*;

public class SettingsManager
{
    private ArrayList<Setting> settings;
    
    public SettingsManager() {
        this.settings = new ArrayList<Setting>();
    }
    
    public void rSetting(final Setting in) {
        this.settings.add(in);
    }
    
    public ArrayList<Setting> getSettings() {
        return this.settings;
    }
    
    public ArrayList<Setting> getSettingsByMod(final Module mod) {
        final ArrayList<Setting> out = new ArrayList<Setting>();
        for (final Setting s : this.getSettings()) {
            if (s.getParentMod().equals(mod)) {
                out.add(s);
            }
        }
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }
    
    public Setting getSettingByName(final String name) {
        for (final Setting set : this.getSettings()) {
            if (set.getName().equalsIgnoreCase(name)) {
                return set;
            }
        }
        System.err.println("[" + Dawn.name + "] Error Setting NOT found: '" + name + "'!");
        return null;
    }
}
