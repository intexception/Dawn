package wtf.infamous.awt.utils;

import java.awt.*;

public class ColourUtil
{
    public static int SkyRainbow(final int var2, final float bright, final float st) {
        double v1 = Math.ceil((double)(System.currentTimeMillis() + var2 * 109L)) / 20.0;
        return Color.getHSBColor(((float)((v1 %= 360.0) / 360.0) < 0.5) ? (-(float)(v1 / 360.0)) : ((float)(v1 / 360.0)), st, bright).getRGB();
    }
}
