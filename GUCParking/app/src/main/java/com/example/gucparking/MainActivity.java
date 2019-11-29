package com.example.gucparking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  private String BASE_URL = "http://10.0.2.2:3000";
  private GoogleSignInClient mGoogleSignInClient;
  int RC_SIGN_IN = 0;
  private CallbackManager mCallbackManager;
  private static final String AUTH_TYPE = "rerequest";
  private static final String EMAIL = "email";
  LoginButton loginButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FacebookSdk.sdkInitialize(this.getApplicationContext());
    setContentView(R.layout.activity_main);

    // Configure sign-in to request the user's ID, email address, and basic
    // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestEmail()
      .build();

    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    // Set the dimensions of the sign-in button.
    SignInButton signInButton = findViewById(R.id.sign_in_button);
    signInButton.setSize(SignInButton.SIZE_STANDARD);

    findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        switch (v.getId()) {
          case R.id.sign_in_button:
            signIn();
            break;
        }
      }
    });

    mCallbackManager = CallbackManager.Factory.create();
    loginButton = findViewById(R.id.login_button);
    loginButton.setReadPermissions(Arrays.asList(EMAIL));

    loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
      @Override
      public void onSuccess(LoginResult loginResult) {
        setResult(RESULT_OK);
        finish();
      }

      @Override
      public void onCancel() {
        setResult(RESULT_CANCELED);
        finish();
      }

      @Override
      public void onError(FacebookException e) {
        // Handle exception
      }
    });
  }


  private void signIn() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
      mCallbackManager.onActivityResult(requestCode, resultCode, data);
      super.onActivityResult(requestCode, resultCode, data);

    // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
    if (requestCode == RC_SIGN_IN) {
      // The Task returned from this call is always completed, no need to attach
      // a listener.
      Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
      handleSignInResult(task);
    }
  }


  private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
    try {
      GoogleSignInAccount account = completedTask.getResult(ApiException.class);

      // Signed in successfully, show authenticated UI.
      //updateUI(account);
      Intent intent  = new Intent(getApplicationContext(), LoggedIn.class);
      startActivity(intent);
    } catch (ApiException e) {
      // The ApiException status code indicates the detailed failure reason.
      // Please refer to the GoogleSignInStatusCodes class reference for more information.
      Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
      //updateUI(null);
    }
  }

  public void googleSignIn(View v) {
        signIn();
    }
}
