package model;

public abstract class User {
    private String username;
    private String email;
    private Image avatar;

    public User(String username, String email, Image avatar) {
        this.username = username;
        this.email = email;
        this.avatar = avatar;
    }
    
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }
    
}
