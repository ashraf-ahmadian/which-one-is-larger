package com.example.whichoneislargerwithfragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GetUserNameDialog extends Dialog {

    private EditText userName;
    private Button confirm;

    private String playerName;

    PlayerNameListener listener;

    public GetUserNameDialog(Context context, PlayerNameListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_get_user_name);
        findView();
        clickOnConfirmName();
    }

    private void findView(){
        userName = findViewById(R.id.get_user_name_box);
        confirm = findViewById(R.id.name_confirm_btn);
    }

    private void clickOnConfirmName(){
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerName = userName.getText().toString();
                listener.getPlayerName(playerName);
                dismiss();
            }
        });
    }
}
