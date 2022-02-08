package wtf.infamous.awt.gui.clickgui.elements;

import wtf.infamous.awt.module.*;
import wtf.infamous.awt.gui.clickgui.*;
import net.minecraft.client.*;
import wtf.infamous.awt.*;
import wtf.infamous.awt.gui.settings.*;
import wtf.infamous.awt.gui.clickgui.elements.menu.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import wtf.infamous.awt.gui.clickgui.util.*;
import java.io.*;

public class ModuleButton
{
    public Module mod;
    public ArrayList<Element> menuelements;
    public Panel parent;
    public double x;
    public double y;
    public double width;
    public double height;
    public boolean extended;
    public boolean listening;
    
    public ModuleButton(final Module imod, final Panel pl) {
        this.extended = false;
        this.listening = false;
        this.mod = imod;
        this.height = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2;
        this.parent = pl;
        this.menuelements = new ArrayList<Element>();
        if (Dawn.settingsManager.getSettingsByMod(imod) != null) {
            for (final Setting s : Dawn.settingsManager.getSettingsByMod(imod)) {
                if (s.isCheck()) {
                    this.menuelements.add(new ElementCheckBox(this, s));
                }
                else if (s.isSlider()) {
                    this.menuelements.add(new ElementSlider(this, s));
                }
                else {
                    if (!s.isCombo()) {
                        continue;
                    }
                    this.menuelements.add(new ElementComboBox(this, s));
                }
            }
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final Color temp = ColorUtil.getClickGUIColor();
        final int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150).getRGB();
        int textcolor = -5263441;
        if (this.mod.isToggled()) {
            Gui.drawRect(this.x - 2.0, this.y, this.x + this.width + 2.0, this.y + this.height + 1.0, color);
            textcolor = -1052689;
        }
        if (this.isHovered(mouseX, mouseY)) {
            Gui.drawRect(this.x - 2.0, this.y, this.x + this.width + 2.0, this.y + this.height + 1.0, 1427181841);
        }
        FontUtil.drawTotalCenteredStringWithShadow(this.mod.getName(), this.x + this.width / 2.0, this.y + 1.0 + this.height / 2.0, textcolor);
    }
    
    public boolean mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (!this.isHovered(mouseX, mouseY)) {
            return false;
        }
        if (mouseButton == 0) {
            this.mod.toggle();
            if (Dawn.settingsManager.getSettingByName("Sound").getValBoolean()) {
                Minecraft.getMinecraft().thePlayer.playSound("random.click", 0.5f, 0.5f);
            }
        }
        else if (mouseButton == 1) {
            if (this.menuelements != null && this.menuelements.size() > 0) {
                final boolean b = !this.extended;
                Dawn.clickGui.closeAllSettings();
                this.extended = b;
                if (Dawn.settingsManager.getSettingByName("Sound").getValBoolean()) {
                    if (this.extended) {
                        Minecraft.getMinecraft().thePlayer.playSound("tile.piston.out", 1.0f, 1.0f);
                    }
                    else {
                        Minecraft.getMinecraft().thePlayer.playSound("tile.piston.in", 1.0f, 1.0f);
                    }
                }
            }
        }
        else if (mouseButton == 2) {
            this.listening = true;
        }
        return true;
    }
    
    public boolean keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (this.listening) {
            if (keyCode != 1) {
                this.mod.setKey(keyCode);
            }
            else {
                this.mod.setKey(0);
            }
            this.listening = false;
            return true;
        }
        return false;
    }
    
    public boolean isHovered(final int mouseX, final int mouseY) {
        return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
    }
}
