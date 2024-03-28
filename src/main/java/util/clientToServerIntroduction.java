package util;

import java.io.Serializable;

public class clientToServerIntroduction implements Serializable {
    private String userName;
    private String what;

    public clientToServerIntroduction(String userName, String what)
    {
        this.userName = userName;
        this.what = what;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getUserName() {
        return userName;
    }

    public String getWhat() {
        return what;
    }
}
