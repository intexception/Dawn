package wtf.infamous.awt.utils;

import java.awt.*;

public class RainbowUtil
{
    public static int getRainbow(final float seconds, final float saturation, final float brightness) {
        final float hue = System.currentTimeMillis() % (int)(seconds * 4000.0f) / (seconds * 4000.0f);
        final int color = Color.HSBtoRGB(hue, saturation, brightness);
        return color;
    }
    
    public static int getRainbow(final float seconds, final float saturation, final float brightness, final long index) {
        final float hue = (System.currentTimeMillis() + index) % (int)(seconds * 4000.0f) / (seconds * 4000.0f);
        final int color = Color.HSBtoRGB(hue, saturation, brightness);
        return color;
    }
}
