package nikola.bottomnavigationview.Model;

/**
 * Created by Nikola on 4/28/2017.
 */

public class Bioskop {
    private String name;
    private String desc;

    public Bioskop(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
