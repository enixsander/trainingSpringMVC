package site.model;

public class UserModel {
    private String name;
    private int _id;
    private int age;

    public UserModel() {
    }

    public UserModel(String name, int _id, int age) {
        this.name = name;
        this._id = _id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
