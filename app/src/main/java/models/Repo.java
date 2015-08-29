package models;

import java.util.List;

/**
 * Created by deepeshnaini on 28/08/15.
 */
public class Repo {
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


    @Override
    public String toString() {
        return "Repo{" +
                "files=" + files +
                ", users=" + users +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
