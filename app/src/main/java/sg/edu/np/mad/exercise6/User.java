package sg.edu.np.mad.exercise6;

public class User {
    public String name;
    public String description;
    public int id;
    public boolean followed;

    public User() {
    }

    public User(String name, String desc, int id, boolean parseBoolean) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getID() { return id; }

    public void setID(int id) {
        this.id = id;
    }

    public boolean getFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }
}