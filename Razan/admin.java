package Razan;

import java.util.List;

// child class of user 
//عشان يورث كل حاجة من اليوزر

public class admin extends user {

    public admin() {
        super();
    }

    public admin(int id, String name, String email, String password, String role) {
        super(id, name, email, password , "Admin");
    }

    public void addUser(List<user> list, user u) { list.add(u); }
    public void deleteUser(List<user> list, user u) { list.remove(u); }
    public void updateUser(user u, String newName) { u.name = newName; }
    // كده اديناله صلاحيات انه يضيف ويشيل ويعدل في اليوزرز

}
