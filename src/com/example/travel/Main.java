package com.example.travel;

import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.RelativeLayout;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;

public class Main extends FragmentActivity {
	private LoginButton loginBtn;
	private UiLifecycleHelper uiHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(this, statusCallback);
		uiHelper.onCreate(savedInstanceState);

		setContentView(R.layout.main_activity);
		loginBtn = (LoginButton) findViewById(R.id.btnFb);
		loginBtn.setReadPermissions(Arrays.asList("email"));
		loginBtn.setUserInfoChangedCallback(new UserInfoChangedCallback() {
			@Override
			public void onUserInfoFetched(GraphUser user) {

			}
		});
		RelativeLayout view = (RelativeLayout) findViewById(R.id.background);
		view.setBackgroundResource(R.drawable.balloon_bg);

	}

	private Session.StatusCallback statusCallback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			if (state.isOpened()) {
				Log.d("Main", "Facebook session opened.");
			} else if (state.isClosed()) {
				Log.d("Main", "Facebook session closed.");
			}
		}
	};

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);
		if (Session.getActiveSession() != null
				|| Session.getActiveSession().isOpened()) {
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
		}

	}

	@Override
	public void onSaveInstanceState(Bundle savedState) {
		super.onSaveInstanceState(savedState);
		uiHelper.onSaveInstanceState(savedState);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
