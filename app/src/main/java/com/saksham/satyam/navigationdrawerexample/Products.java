package com.saksham.satyam.navigationdrawerexample;

/**
 * Created by Satyam on 05-01-2017.
 **/
public class Products {
    private int _id;
    private String _fcode;
    private String _fname;

    public Products(){
    }

    public Products(String _fcode, String _fname) {
       // this._id = _id;
        this._fcode = _fcode;
        this._fname = _fname;
    }
/*
    public Products(String fname) {
        this._fname = fname;
    }*/

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_fcode(String _fcode) {
        this._fcode = _fcode;
    }

    public void set_fname(String _fname) {
        this._fname = _fname;
    }

    public int get_id() {
        return _id;
    }

    public String get_fname() {
        return _fname;
    }

    public String get_fcode() { return _fcode;  }
}
