package models;

import java.io.Serializable;

/**
 * Created by deepeshnaini on 28/08/15.
 */
public class File implements Serializable {
    public String name;
    public Double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
