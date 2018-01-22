package com.example.saniou.clipwatcher.clipboard;

import android.content.Context;
import java.util.ArrayList;

public abstract class ClipboardManagerCompat {

  protected final ArrayList<OnPrimaryClipChangedListener> mPrimaryClipChangedListeners =
      new ArrayList<OnPrimaryClipChangedListener>();

  public static ClipboardManagerCompat create(Context context) {
    return new ClipboardManagerImpl(context);
  }

  public void addPrimaryClipChangedListener(OnPrimaryClipChangedListener listener) {
    synchronized (mPrimaryClipChangedListeners) {
      mPrimaryClipChangedListeners.add(listener);
    }
  }

  protected final void notifyPrimaryClipChanged() {
    synchronized (mPrimaryClipChangedListeners) {
      for (int i = 0; i < mPrimaryClipChangedListeners.size(); i++) {
        mPrimaryClipChangedListeners.get(i).onPrimaryClipChanged();
      }
    }
  }

  public void removePrimaryClipChangedListener(OnPrimaryClipChangedListener listener) {
    synchronized (mPrimaryClipChangedListeners) {
      mPrimaryClipChangedListeners.remove(listener);
    }
  }

  public abstract CharSequence getText();

  public interface OnPrimaryClipChangedListener {
    void onPrimaryClipChanged();
  }
}
