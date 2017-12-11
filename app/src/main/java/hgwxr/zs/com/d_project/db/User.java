package hgwxr.zs.com.d_project.db;

/**
 * Created by hgwxr on 2017/12/11.
 */

public class User {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    private int id;
    private String name;
    private String psd;

}
