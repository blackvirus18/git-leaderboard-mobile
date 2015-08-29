package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by deepeshnaini on 28/08/15.
 */
public class User {
    public String email;
    public Double score;
    public String image;
    public List<File> files;
    public List<Repo> repos;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String email) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.reset();
        m.update(email.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hash = bigInt.toString(16);
        while(hash.length() < 32 ){
            hash = "0"+hash;
        }
        String image="http://www.gravatar.com/avatar/"+hash+"?s=40&d=identicon&f=y";
        this.image = image;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Repo> getRepos() {
        return repos;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }
}
