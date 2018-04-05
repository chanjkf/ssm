package xyz.chanjkf.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.chanjkf.utils.JsonUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Created by yi on 2018/4/3.
 */
@Controller
@RequestMapping(value = "/verify")
public class VerificationController {
    public static final int WIDTH = 120;
    public static final int HEIGHT = 30;

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody
    public String checkImage(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "randCode") String randCode) {
        Map<String, Object> map = new HashMap<>(10);
        String checkcode = (String) request.getSession().getAttribute("checkcode");
        if (randCode == null) {
            map.put("result", "验证码为空");
        } else if (StringUtils.equalsIgnoreCase(checkcode, randCode)) {
            map.put("result", "success");
        } else {
            map.put("result", "验证码错误");
        }
        return JsonUtil.getJsonStr(map);
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void drawImage(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            // 创建缓存
            BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            // 获得画布
            Graphics g = bi.getGraphics();
            // 设置背影色
            setBackGround(g);
            // 设置边框
            setBorder(g);
            // 画干扰线
            drawRandomLine(g);
            // 写随机数
            String random = drawRandomNum((Graphics2D) g);
            // 将随机汉字存在session中
            request.getSession().setAttribute("checkcode", random);
            // 将图形写给浏览器
            response.setContentType("image/jpeg");
            // 发头控制浏览器不要缓存
            response.setDateHeader("expries", -1);
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            // 将图片写给浏览器
            ImageIO.write(bi, "jpg", response.getOutputStream());
        } catch (IOException e) {
        }
    }



    /**
     * 设置背景色
     *
     * @param g
     */
    private void setBackGround(Graphics g) {
        // 设置颜色
        g.setColor(new Color(249, 249, 249));
        // 填充区域
        g.fillRect(0, 0, WIDTH, HEIGHT);

    }

    /**
     * 设置边框
     *
     * @param g
     */
    private void setBorder(Graphics g) {
        // 设置边框颜色
        // RGB
        g.setColor(new Color(210, 209, 205));
        // 边框区域
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
    }

    /**
     * 画随机线条
     *
     * @param g
     */
    private void drawRandomLine(Graphics g) {
        // 设置颜色
        g.setColor(Color.GRAY);
        // 设置线条个数并画线
        for (int i = 0; i < 5; i++) {
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    private String drawRandomNum(Graphics2D g) {
        StringBuffer sb = new StringBuffer();
        // 设置字体
        g.setFont(new Font("宋体", Font.BOLD, 20));
        // 准备常用字符集
        String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int x = 5;
        // 控制字数
        for (int i = 0; i < 4; i++) {
            // 生成0-255的随机数
            int R = new Random().nextInt(256);
            int G = new Random().nextInt(256);
            int B = new Random().nextInt(256);
            // 设置每个随机数的颜色
            g.setColor(new Color(R, G, B));
            // 设置字体旋转角度
            int degree = new Random().nextInt() % 30;
            // 截取汉字
            String ch = base.charAt(new Random().nextInt(base.length())) + "";
            sb.append(ch);
            // 正向角度
            g.rotate(degree * Math.PI / 180, x, 20);
            g.drawString(ch, x, 20);
            // 反向角度
            g.rotate(-degree * Math.PI / 180, x, 20);
            x += 30;
        }
        return sb.toString();
    }
}

