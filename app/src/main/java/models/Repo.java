package models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by deepeshnaini on 28/08/15.
 */
public class Repo implements Serializable,Comparable<Repo> {
    public List<File> files;
    public List<User> users;
    public String name;
    public Double score;

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "files=" + files +
                ", users=" + users +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Repo rhs) {
        Repo lhs=this;
        if(lhs.getScore()<rhs.getScore())
            return 1;
        else if(lhs.getScore()>rhs.getScore())
            return -1;
        else
            return 0;
    }
}
