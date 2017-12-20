package xyz.chanjkf.listener;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;
import xyz.chanjkf.entity.ProgressEntity;

@Component
public class FileUploadProgressListener implements ProgressListener {
    private HttpSession session;
    public void setSession(HttpSession session){
        this.session=session;
        ProgressEntity status = new ProgressEntity();//保存上传状态
        session.setAttribute("status", status);
    }
    public void update(long bytesRead, long contentLength, int items) {
        ProgressEntity progress = (ProgressEntity) session.getAttribute("status");
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.setBytesRead(bytesRead);
        progress.setContentLength(contentLength);
        progress.setItems(items);

    }

}