package com.ayoka.vdfreport;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoka.Adapters.MainActivityAdapter;
import com.ayoka.Interfaces.InterfaceController;
import com.ayoka.Model.DepartmanModel;
import com.ayoka.Model.LoginInfoModel;
import com.ayoka.Model.LoginInfoResponse;
import com.ayoka.Model.LoginUserRequest;
import com.ayoka.Model.ResponseMessage;
import com.ayoka.common.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    public RestAdapter restAdapter;
    public InterfaceController restInterface;
    public ProgressDialog progressDialog;
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.input_username);
        mPasswordView = (EditText) findViewById(R.id.input_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if ( id == EditorInfo.IME_NULL || id== EditorInfo.IME_ACTION_SEND) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    private void attemptLogin() {


        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.username_required));
            focusView = mUsernameView;
            cancel = true;
        }
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.password_required));
            focusView = mPasswordView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Constants.URL)
                    .build();

            restInterface = restAdapter.create(InterfaceController.class);
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Giriş yapılıyor..");
            progressDialog.setCancelable(false);
            progressDialog.show();
            LoginUserRequest req = new LoginUserRequest();
            req.setUsername(username);
            req.setPassword(password);
            restInterface.LoginUser(req,new Callback<ResponseMessage<LoginInfoResponse>>() {
                @Override
                public void success(ResponseMessage<LoginInfoResponse> responseMessage, Response response) {
                    progressDialog.cancel();
                    if (responseMessage.getErrorCode()==0) {
                        Intent intent = new Intent(getApplicationContext(), MainNewActivity.class);
                        intent.putExtra("FullName",responseMessage.getMessage().getFullName());
                        intent.putExtra("Mail",responseMessage.getMessage().getMail());
                        intent.putExtra("UserId",responseMessage.getMessage().getUserId().toString());

//                        intent.putExtra("Email", loginInfoModel.getEmail());
//                        intent.putExtra("IsDealer", loginInfoModel.getIsDealer());
//                        if(loginInfoModel.getIsDealer())
//                        {
//                            intent.putExtra("DealerName", loginInfoModel.getDealerName());
//                            intent.putExtra("DealerId", loginInfoModel.getDealerId());
//                        }

                        startActivity(intent);
                    }
                    else
                    {
                        mPasswordView.setError(getString(R.string.incorrect_password));
                    }

                }
                @Override
                public void failure(RetrofitError retrofitError) {
                    progressDialog.cancel();
                    retrofitError.printStackTrace(); //to see if you have errors
                    String merror = retrofitError.getMessage();
                    Toast.makeText(getApplicationContext(),merror,Toast.LENGTH_LONG).show();
                }
            });


        }
    }



}

