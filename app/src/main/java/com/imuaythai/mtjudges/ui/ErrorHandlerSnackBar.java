package com.imuaythai.mtjudges.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.imuaythai.mtjudges.R;

public class ErrorHandlerSnackBar extends BaseTransientBottomBar<ErrorHandlerSnackBar> {

    /**
     * Constructor for the transient bottom bar.
     *
     * @param parent The parent for this transient bottom bar.
     * @param content The content view for this transient bottom bar.
     * @param contentViewCallback The content view callback for this transient bottom bar.
     */
    private ErrorHandlerSnackBar(ViewGroup parent, View content, ContentViewCallback contentViewCallback) {
        super(parent, content, contentViewCallback);
    }

    private static class ContentViewCallback implements BaseTransientBottomBar.ContentViewCallback {

        // view inflated from custom layout
        private View messageView;
        private View actionView;

        private ContentViewCallback(View messageView, View actionView) {
            this.messageView = messageView;
            this.actionView = actionView;
        }

        public void animateContentIn(int delay, int duration) {
            this.messageView.setAlpha(0.0F);
            this.messageView.animate().alpha(1.0F).setDuration((long)duration).setStartDelay((long)delay).start();
            if (this.actionView.getVisibility() == View.VISIBLE) {
                this.actionView.setAlpha(0.0F);
                this.actionView.animate().alpha(1.0F).setDuration((long)duration).setStartDelay((long)delay).start();
            }

        }

        public void animateContentOut(int delay, int duration) {
            this.messageView.setAlpha(1.0F);
            this.messageView.animate().alpha(0.0F).setDuration((long)duration).setStartDelay((long)delay).start();
            if (this.actionView.getVisibility() == View.VISIBLE) {
                this.actionView.setAlpha(1.0F);
                this.actionView.animate().alpha(0.0F).setDuration((long)duration).setStartDelay((long)delay).start();
            }

        }

    }

    public static ErrorHandlerSnackBar make(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View content = inflater.inflate(R.layout.error_handler_snack_bar, parent, false);

        ErrorHandlerSnackBar customSnackbar = new ErrorHandlerSnackBar(
                parent, content, new ContentViewCallback(
                    content.findViewById(R.id.snackbar_text),
                    content.findViewById(R.id.snackbar_action)
                )
        );

        customSnackbar.setDuration(BaseTransientBottomBar.LENGTH_LONG);
        return customSnackbar;
    }

    public ErrorHandlerSnackBar setError(Throwable throwable) {
        TextView textView = getView().findViewById(R.id.snackbar_text);
        textView.setText(throwable.getMessage());
        return this;
    }

    public ErrorHandlerSnackBar setText(CharSequence text) {
        TextView textView = getView().findViewById(R.id.snackbar_text);
        textView.setText(text);
        return this;
    }

    public ErrorHandlerSnackBar setAction(CharSequence text, final View.OnClickListener listener) {
        Button actionView = getView().findViewById(R.id.snackbar_action);
        actionView.setText(text);
        actionView.setVisibility(View.VISIBLE);
        actionView.setOnClickListener(view1 -> {
            listener.onClick(view);
            dismiss();
        });
        return this;
    }
}
