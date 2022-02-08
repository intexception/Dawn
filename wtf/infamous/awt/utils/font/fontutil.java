package wtf.infamous.awt.utils.font;

import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import java.io.*;
import java.util.*;

public class fontutil
{
    public static volatile int completed;
    public static MinecraftFontRenderer normal;
    private static Font normal_;
    
    private static Font getFont(final Map<String, Font> locationMap, final String location, final int size) {
        Font font = null;
        try {
            if (locationMap.containsKey(location)) {
                font = locationMap.get(location).deriveFont(0, (float)size);
            }
            else {
                final InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("dawn/" + location)).getInputStream();
                font = Font.createFont(0, is);
                locationMap.put(location, font);
                font = font.deriveFont(0, (float)size);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 1, 20);
        }
        return font;
    }
    
    public static boolean hasLoaded() {
        return fontutil.completed >= 3;
    }
    
    public static void bootstrap() {
        final HashMap<String, Font> locationMap;
        new Thread(() -> {
            locationMap = new HashMap<String, Font>();
            fontutil.normal_ = getFont(locationMap, "font", 32);
            ++fontutil.completed;
            return;
        }).start();
        final HashMap<String, Font> locationMap2;
        new Thread(() -> {
            locationMap2 = new HashMap<String, Font>();
            ++fontutil.completed;
            return;
        }).start();
        final HashMap<String, Font> locationMap3;
        new Thread(() -> {
            locationMap3 = new HashMap<String, Font>();
            ++fontutil.completed;
            return;
        }).start();
        while (!hasLoaded()) {
            try {
                Thread.sleep(5L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fontutil.normal = new MinecraftFontRenderer(fontutil.normal_, true, true);
    }
}
