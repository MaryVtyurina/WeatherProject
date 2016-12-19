package Data;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Maria on 17/12/16.
 */
@Table(name = "Cities")
public class CityActive extends Model implements Serializable {
    @Column(name = "name")
    public String name;

    public static List getAllWeather() {
        return new Select().from(CityActive.class).execute();
    }

    public CityActive() {
        super();
    }

    public CityActive(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
