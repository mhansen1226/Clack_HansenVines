package data;

import java.util.Date;

public class ClackData {
    private String userName;
    private int type;
    private Date date;

    public ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        // need to add date
    }

    public ClackData(int type) {
        this("Anon", type);
    }

    public ClackData() {
        this(0); // need to decide default value
    }

    public int getType() {
        return this.type;
    }

    public String getUserName() {
        return this.userName;
    }

    public Date getDate() {
        return this.date;
    }

//    public getData () {
//        // not sure what this is
//    }
}
