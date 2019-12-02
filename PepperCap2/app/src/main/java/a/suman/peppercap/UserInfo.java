package a.suman.peppercap;

class UserInfo extends  Throwable{
    String name;
    String id;
    String level;
    String email;
    String assistid;
    String Nusers;
    String[] Users;

    public String getNusers() {
        return Nusers;
    }

    public void setNusers(String nusers) {
        Nusers = nusers;
    }

    public String[] getUsers() {
        return Users;
    }

    public void setUsers(String[] users) {
        Users = users;
    }

    UserInfo(){}

    public String getAssistid(){ return assistid;}

    public void setAssistid(String assistid){this.assistid=assistid;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
