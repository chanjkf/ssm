/*
 *  ———————————————————————————
 *  Copyright  2017 Hangzhou DtDream Technology Co.,Lt d. All rights reserved.
 *   ———————————————————————————
 *        Product: datamall
 *    Module Name: dmall
 *   Date Created: 2017-4-25
 *    Description:
 *   ———————————————————————————
 *  Modification History
 *  DATE            Name           Description
 *   ———————————————————————————
 *  2017-4-25    z0253
 *   ———————————————————————————
 */

package xyz.chanjkf.utils;

import org.springframework.security.web.DefaultRedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectStrategy extends DefaultRedirectStrategy {
    private String targetUrlParameter = null;

    public String getTargetUrlParameter() {
        return targetUrlParameter;
    }

    public void setTargetUrlParameter(String targetUrlParameter) {
        this.targetUrlParameter = targetUrlParameter;
    }

    public RedirectStrategy() {
    }

    public RedirectStrategy(String targetUrlParameter) {
        this.targetUrlParameter = targetUrlParameter;
    }

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        url = "http://"+ PropertiesUtils.serverHost + ":" + PropertiesUtils.serverPort + "/photo/index";
        super.sendRedirect(request, response, url);
    }
}