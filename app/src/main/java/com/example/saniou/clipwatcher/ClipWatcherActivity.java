package com.example.saniou.clipwatcher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.TextView;

public final class ClipWatcherActivity extends AppCompatActivity {

  private final static String KEY_CONTENT = "content";
  private TextView mTextView;

  public static void startForContent(Context context, String content) {
    Intent intent = new Intent(context, ClipWatcherActivity.class);
    intent.putExtra(KEY_CONTENT, content);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    mTextView = findViewById(R.id.text_view);

    Intent intent = getIntent();
    Utils.printIntent("ClipWatcherActivity::onCreate()", intent);

    tryToShowContent(intent);
    ListenClipboardService.start(this);
  }

  @Override protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);

    Utils.printIntent("ClipWatcherActivity::onNewIntent()", intent);

    tryToShowContent(intent);
  }

  private void tryToShowContent(Intent intent) {
    String content = intent.getStringExtra(KEY_CONTENT);
    if (!TextUtils.isEmpty(content)) {
      mTextView.setText(content);
    }
  }

}