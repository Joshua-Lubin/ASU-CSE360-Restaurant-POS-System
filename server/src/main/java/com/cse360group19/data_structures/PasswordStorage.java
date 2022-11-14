package com.cse360group19.data_structures;

public class PasswordStorage {
    private String password;

    public PasswordStorage(String password) {
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
