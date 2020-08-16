package jum.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TestTask {

    @Scheduled( cron = "${TestTask.Scheduled}")
    public  void run()
    {
        System.out.println("this is a task test"+ new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
