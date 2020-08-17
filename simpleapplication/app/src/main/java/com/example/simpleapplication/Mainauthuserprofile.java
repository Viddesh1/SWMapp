package com.example.simpleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Mainauthuserprofile extends AppCompatActivity {

    Button _btnInsertprofile, _btnDelete, _btnUpdate;
    EditText _txtID, _txtusername, _txtpassword;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_userprofile);
        _btnDelete = (Button) findViewById(R.id.btn4delete);
        _btnUpdate = (Button) findViewById(R.id.btn3update);
        _txtusername = (EditText) findViewById(R.id.txtusername);
        _txtpassword = (EditText) findViewById(R.id.txtpassword);
        openHelper = new DatabaseHelper(this);
        _btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() | !validatePassword()) {
                    confirmInput();
                } else {
                    String username = _txtusername.getText().toString();
                    String password = _txtpassword.getText().toString();

                    db = openHelper.getWritableDatabase();
                    updateData(username, password);
                    Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                }
            }
        });

        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername()) {
                    validateUsername();
                } else {
                    db = openHelper.getWritableDatabase();
                    String id = _txtusername.getText().toString();
                    String pass = _txtpassword.getText().toString();
                    deleteData(id);
                    Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
                }
            }
        }

        );


    }


    private boolean validateUsername() {
        String usernameInput = _txtusername.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            _txtusername.setError("Field can't be empty");
            return false;
        }else if (usernameInput.matches("[0-9 ]+")) {
            _txtusername.setError("Cannot enter number");
            return false;
        } else if (usernameInput.length() >= 15) {
            _txtusername.setError("Username too long more than 15");
            return false;
        } else {
            _txtusername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = _txtpassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            _txtpassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            _txtpassword.setError("Password too weak try adding any alphabatical letter,number,no spaces and atleast 4 characters");
            return false;
        } else {
            _txtpassword.setError(null);
            return true;
        }
    }



    public void confirmInput() {
        if (!validateUsername() | !validatePassword()) {
            return;
        }

        String input = "Username: " + _txtusername.getText().toString();
        input += "\n";
        input += "Password: " + _txtpassword.getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

    public  Boolean verification(String username,String password)
    {
        // SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from Profile where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0) return true;
        else return false;

    }

    public boolean updateData(String username,String password)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COLS_2, username);
        contentValues.put(DatabaseHelper.COLS_3, password);
        String id=_txtusername.getText().toString();
        return db.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.COLS_2+ "=?" , new String[]{id})>0;
    }

    public boolean deleteData(String id)
    {
        // return db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLS_2+"=?"+DatabaseHelper.COLS_3+"=?", new String[]{id,pass})>0;
        return db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLS_2+"=?", new String[]{id})>0;

    }

    public void appinfoclick(View view)
    {
        Intent intern = new Intent(this, Mainappinfo.class);
        startActivity(intern);

    }



}
