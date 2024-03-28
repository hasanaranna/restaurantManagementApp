package util;

import java.io.Serializable;

public class newRestaurant implements Serializable {
    private String from;
    private String flag;
    public newRestaurant(String from, String flag)
    {
        this.flag = flag;
        //this.from = from;
    }
    public String getFlag() {
        return flag;
    }

    public String getFrom() {
        return from;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
