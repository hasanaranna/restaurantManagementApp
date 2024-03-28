package Threads;

import server.Manager;
import util.NetworkUtil;

public class updateFileThread implements Runnable {
    private Manager manager;
    private NetworkUtil networkUtil;

    public updateFileThread(NetworkUtil networkUtil, Manager manager) {
        this.manager = manager;
        this.networkUtil = networkUtil;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object object = networkUtil.read();
                if(object instanceof String)
                {
                    String str = (String) object;
                    if(str.equals("NEW FOOD")){
                        System.out.println("new food paici");
                        manager.readFiles();
                    }
                    else if(str.equals("NEW RESTAURANT"))
                    {
                        System.out.println("new res paici");
                        manager.readFiles();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
