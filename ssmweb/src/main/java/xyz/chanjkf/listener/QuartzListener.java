package xyz.chanjkf.listener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by YI on 2017/8/1.
 */
public class QuartzListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0) {
        //TODO

    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {

            Scheduler sched = new StdSchedulerFactory().getScheduler();
            sched.getContext().put("servletContext", servletContextEvent.getServletContext());

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}