package com.example.compradoresdecoconutnut;

/**
 * https://www.youtube.com/watch?v=w0AOGeqOnFY
 */

public class Client {
    private int _id;
    public String _name;
    private String _address;
    private String _phone;
    private String _email;
    private int _coconuts;

    public Client(){}

    public Client(String _name, String _address, String _phone, String _email, int _coconuts) {
        this._name = _name;
        this._address = _address;
        this._phone = _phone;
        this._email = _email;
        this._coconuts = _coconuts;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public int get_coconuts() {
        return _coconuts;
    }

    public void set_coconuts(int _coconuts) {
        this._coconuts = _coconuts;
    }
}
