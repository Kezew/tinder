
package tinderproject;

import java.util.HashSet;

/**
 *
 * @author User
 */
public class User {
   
    private int id;
    private String name;
    private GenderType gender;
    private int age;
    private HairColor hair;
    private EyeColor eye;
    private HashSet<Integer> likes;
    private HashSet<Integer> disLikes;
    private HashSet<Integer> whoLikesMe;

    public User(int id, String name, GenderType gender, int age, HairColor hair, EyeColor eye) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.hair = hair;
        this.eye = eye;
        this.likes = new HashSet<>();
        this.disLikes = new HashSet<>();
        this.whoLikesMe = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GenderType getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public HairColor getHair() {
        return hair;
    }

    public EyeColor getEye() {
        return eye;
    }

    public HashSet<Integer> getLikes() {
        return likes;
    }

    public HashSet<Integer> getDisLikes() {
        return disLikes;
    }

    public HashSet<Integer> getWhoLikesMe() {
        return whoLikesMe;
    }
    
    
    
    
    
    
    
    
}
