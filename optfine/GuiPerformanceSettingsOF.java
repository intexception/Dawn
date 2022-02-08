package optfine;

import net.minecraft.client.settings.*;
import net.minecraft.client.resources.*;
import net.minecraft.client.gui.*;

public class GuiPerformanceSettingsOF extends GuiScreen
{
    private GuiScreen prevScreen;
    protected String title;
    private GameSettings settings;
    private static GameSettings.Options[] enumOptions;
    private int lastMouseX;
    private int lastMouseY;
    private long mouseStillTime;
    
    static {
        GuiPerformanceSettingsOF.enumOptions = new GameSettings.Options[] { GameSettings.Options.SMOOTH_FPS, GameSettings.Options.SMOOTH_WORLD, GameSettings.Options.FAST_RENDER, GameSettings.Options.FAST_MATH, GameSettings.Options.CHUNK_UPDATES, GameSettings.Options.CHUNK_UPDATES_DYNAMIC, GameSettings.Options.LAZY_CHUNK_LOADING };
    }
    
    public GuiPerformanceSettingsOF(final GuiScreen p_i37_1_, final GameSettings p_i37_2_) {
        this.title = "Performance Settings";
        this.lastMouseX = 0;
        this.lastMouseY = 0;
        this.mouseStillTime = 0L;
        this.prevScreen = p_i37_1_;
        this.settings = p_i37_2_;
    }
    
    @Override
    public void initGui() {
        int i = 0;
        GameSettings.Options[] enumOptions;
        for (int length = (enumOptions = GuiPerformanceSettingsOF.enumOptions).length, l = 0; l < length; ++l) {
            final GameSettings.Options gamesettings$options = enumOptions[l];
            final int j = this.width / 2 - 155 + i % 2 * 160;
            final int k = this.height / 6 + 21 * (i / 2) - 10;
            if (!gamesettings$options.getEnumFloat()) {
                this.buttonList.add(new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options, this.settings.getKeyBinding(gamesettings$options)));
            }
            else {
                this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options));
            }
            ++i;
        }
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) {
        if (button.enabled) {
            if (button.id < 200 && button instanceof GuiOptionButton) {
                this.settings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
                button.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            }
            if (button.id == 200) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.prevScreen);
            }
            if (button.id != GameSettings.Options.CLOUD_HEIGHT.ordinal()) {
                final ScaledResolution scaledresolution = new ScaledResolution(this.mc);
                final int i = scaledresolution.getScaledWidth();
                final int j = scaledresolution.getScaledHeight();
                this.setWorldAndResolution(this.mc, i, j);
            }
        }
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.title, (float)(this.width / 2), 20.0f, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (Math.abs(mouseX - this.lastMouseX) <= 5 && Math.abs(mouseY - this.lastMouseY) <= 5) {
            final int i = 700;
            if (System.currentTimeMillis() >= this.mouseStillTime + i) {
                final int j = this.width / 2 - 150;
                int k = this.height / 6 - 5;
                if (mouseY <= k + 98) {
                    k += 105;
                }
                final int l = j + 150 + 150;
                final int i2 = k + 84 + 10;
                final GuiButton guibutton = this.getSelectedButton(mouseX, mouseY);
                if (guibutton != null) {
                    final String s = this.getButtonName(guibutton.displayString);
                    final String[] astring = this.getTooltipLines(s);
                    if (astring == null) {
                        return;
                    }
                    this.drawGradientRect(j, k, l, i2, -536870912, -536870912);
                    for (int j2 = 0; j2 < astring.length; ++j2) {
                        final String s2 = astring[j2];
                        this.fontRendererObj.drawStringWithShadow(s2, (float)(j + 5), (float)(k + 5 + j2 * 11), 14540253);
                    }
                }
            }
        }
        else {
            this.lastMouseX = mouseX;
            this.lastMouseY = mouseY;
            this.mouseStillTime = System.currentTimeMillis();
        }
    }
    
    private String[] getTooltipLines(final String p_getTooltipLines_1_) {
        return (String[])(p_getTooltipLines_1_.equals("Smooth FPS") ? new String[] { "Stabilizes FPS by flushing the graphic driver buffers", "  OFF - no stabilization, FPS may fluctuate", "  ON - FPS stabilization", "This option is graphics driver dependant and its effect", "is not always visible" } : (p_getTooltipLines_1_.equals("Smooth World") ? new String[] { "Removes lag spikes caused by the internal server.", "  OFF - no stabilization, FPS may fluctuate", "  ON - FPS stabilization", "Stabilizes FPS by distributing the internal server load.", "Effective only for local worlds (single player)." } : (p_getTooltipLines_1_.equals("Load Far") ? new String[] { "Loads the world chunks at distance Far.", "Switching the render distance does not cause all chunks ", "to be loaded again.", "  OFF - world chunks loaded up to render distance", "  ON - world chunks loaded at distance Far, allows", "       fast render distance switching" } : (p_getTooltipLines_1_.equals("Preloaded Chunks") ? new String[] { "Defines an area in which no chunks will be loaded", "  OFF - after 5m new chunks will be loaded", "  2 - after 32m  new chunks will be loaded", "  8 - after 128m new chunks will be loaded", "Higher values need more time to load all the chunks", "and may decrease the FPS." } : (p_getTooltipLines_1_.equals("Chunk Updates") ? new String[] { "Chunk updates", " 1 - slower world loading, higher FPS (default)", " 3 - faster world loading, lower FPS", " 5 - fastest world loading, lowest FPS", "Number of chunk updates per rendered frame,", "higher values may destabilize the framerate." } : (p_getTooltipLines_1_.equals("Dynamic Updates") ? new String[] { "Dynamic chunk updates", " OFF - (default) standard chunk updates per frame", " ON - more updates while the player is standing still", "Dynamic updates force more chunk updates while", "the player is standing still to load the world faster." } : (p_getTooltipLines_1_.equals("Lazy Chunk Loading") ? new String[] { "Lazy Chunk Loading", " OFF - default server chunk loading", " ON - lazy server chunk loading (smoother)", "Smooths the integrated server chunk loading by", "distributing the chunks over several ticks.", "Turn it OFF if parts of the world do not load correctly.", "Effective only for local worlds and single-core CPU." } : (p_getTooltipLines_1_.equals("Fast Math") ? new String[] { "Fast Math", " OFF - standard math (default)", " ON - faster math", "Uses optimized sin() and cos() functions which can", "better utilize the CPU cache and increase the FPS." } : (p_getTooltipLines_1_.equals("Fast Render") ? new String[] { "Fast Render", " OFF - standard rendering (default)", " ON - optimized rendering (faster)", "Uses optimized rendering algorithm which decreases", "the GPU load and may substantionally increase the FPS." } : null)))))))));
    }
    
    private String getButtonName(final String p_getButtonName_1_) {
        final int i = p_getButtonName_1_.indexOf(58);
        return (i < 0) ? p_getButtonName_1_ : p_getButtonName_1_.substring(0, i);
    }
    
    private GuiButton getSelectedButton(final int p_getSelectedButton_1_, final int p_getSelectedButton_2_) {
        for (int i = 0; i < this.buttonList.size(); ++i) {
            final GuiButton guibutton = this.buttonList.get(i);
            final int j = GuiVideoSettings.getButtonWidth(guibutton);
            final int k = GuiVideoSettings.getButtonHeight(guibutton);
            final boolean flag = p_getSelectedButton_1_ >= guibutton.xPosition && p_getSelectedButton_2_ >= guibutton.yPosition && p_getSelectedButton_1_ < guibutton.xPosition + j && p_getSelectedButton_2_ < guibutton.yPosition + k;
            if (flag) {
                return guibutton;
            }
        }
        return null;
    }
}
