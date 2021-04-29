package net.fahmi.terakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.Html.fromHtml;

public class RegisterActivity extends AppCompatActivity {
    private EditText username,password1,password2;
    private TextView login;
    private Button register;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.ETRusername);
        password1 = (EditText) findViewById(R.id.ETRpassword1);
        password2 = (EditText) findViewById(R.id.ETRpassword2);
        login = (TextView) findViewById(R.id.TVRlogin);
        register = (Button) findViewById(R.id.BRregister);

        dbHelper = new DBHelper(this);

        login.setText(fromHtml("Back to " +
                "</font><font color='#3b5998'>Login</font>"));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pass1 = password1.getText().toString().trim();
                String pass2 = password2.getText().toString().trim();

                ContentValues values = new ContentValues();
                if(!pass1.equals(pass2)){
                    Toast.makeText(RegisterActivity.this,"Password not match",Toast.LENGTH_SHORT).show();
                }else if(pass1.equals("") || user.equals("")){
                    Toast.makeText(RegisterActivity.this,"Empty value not permitted",Toast.LENGTH_SHORT).show();
                }else{
                    values.put(DBHelper.row_username, user);
                    values.put(DBHelper.row_password,pass1);
                    dbHelper.insertData(values);

                    Toast.makeText(RegisterActivity.this,"Register successful",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    public static Spanned fromHtml(String html){
        Spanned result;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else{
            result = Html.fromHtml(html);
        }
        return result;
    }
}