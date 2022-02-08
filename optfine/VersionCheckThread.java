package optfine;

import java.net.*;
import net.minecraft.client.*;
import java.io.*;

public class VersionCheckThread extends Thread
{
    @Override
    public void run() {
        HttpURLConnection httpurlconnection = null;
        try {
            Config.dbg("Checking for new version");
            final URL url = new URL("http://optifine.net/version/1.8.8/HD_U.txt");
            httpurlconnection = (HttpURLConnection)url.openConnection();
            if (Config.getGameSettings().snooperEnabled) {
                httpurlconnection.setRequestProperty("OF-MC-Version", "1.8.8");
                httpurlconnection.setRequestProperty("OF-MC-Brand", new StringBuilder().append(ClientBrandRetriever.getClientModName()).toString());
                httpurlconnection.setRequestProperty("OF-Edition", "HD_U");
                httpurlconnection.setRequestProperty("OF-Release", "E1");
                httpurlconnection.setRequestProperty("OF-Java-Version", new StringBuilder().append(System.getProperty("java.version")).toString());
                httpurlconnection.setRequestProperty("OF-CpuCount", new StringBuilder().append(Config.getAvailableProcessors()).toString());
                httpurlconnection.setRequestProperty("OF-OpenGL-Version", new StringBuilder().append(Config.openGlVersion).toString());
                httpurlconnection.setRequestProperty("OF-OpenGL-Vendor", new StringBuilder().append(Config.openGlVendor).toString());
            }
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(false);
            httpurlconnection.connect();
            try {
                final InputStream inputstream = httpurlconnection.getInputStream();
                final String s = Config.readInputStream(inputstream);
                inputstream.close();
                final String[] astring = Config.tokenize(s, "\n\r");
                if (astring.length >= 1) {
                    final String s2 = astring[0];
                    Config.dbg("Version found: " + s2);
                    if (Config.compareRelease(s2, "E1") <= 0) {
                        return;
                    }
                    Config.setNewRelease(s2);
                    return;
                }
            }
            finally {
                if (httpurlconnection != null) {
                    httpurlconnection.disconnect();
                }
            }
            if (httpurlconnection != null) {
                httpurlconnection.disconnect();
            }
        }
        catch (Exception exception) {
            Config.dbg(String.valueOf(exception.getClass().getName()) + ": " + exception.getMessage());
        }
    }
}
