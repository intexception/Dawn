package optfine;

import java.util.*;

public class FileUploadThread extends Thread
{
    private String urlString;
    private Map headers;
    private byte[] content;
    private IFileUploadListener listener;
    
    public FileUploadThread(final String p_i33_1_, final Map p_i33_2_, final byte[] p_i33_3_, final IFileUploadListener p_i33_4_) {
        this.urlString = p_i33_1_;
        this.headers = p_i33_2_;
        this.content = p_i33_3_;
        this.listener = p_i33_4_;
    }
    
    @Override
    public void run() {
        try {
            HttpUtils.post(this.urlString, this.headers, this.content);
            this.listener.fileUploadFinished(this.urlString, this.content, null);
        }
        catch (Exception exception) {
            this.listener.fileUploadFinished(this.urlString, this.content, exception);
        }
    }
    
    public String getUrlString() {
        return this.urlString;
    }
    
    public byte[] getContent() {
        return this.content;
    }
    
    public IFileUploadListener getListener() {
        return this.listener;
    }
}
