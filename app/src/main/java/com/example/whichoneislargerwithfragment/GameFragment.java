package com.example.whichoneislargerwithfragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameFragment extends Fragment {

    private final int CLICK_LEFT_BUTTON = 0;
    private final int CLICK_RIGHT_BUTTON = 1;
    private final int CLICK_EQUAL_BUTTON = 2;

    private TextView userLevelView;
    private TextView userPointView;
    private TextView gameTime;
    private Button leftButton;
    private Button rightButton;
    private Button equalButton;
    private TextView finishText;

    private int userPoint = 0;
    private int userLevel = 1;

    private boolean gameFinish = false;

    CountDownTimer countDownTimer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        setButtonNumber();
        startGame();
        generateNewLevel();
    }

    private void findView(View view) {
        userLevelView = view.findViewById(R.id.user_level_view);
        userPointView = view.findViewById(R.id.user_point_view);
        gameTime = view.findViewById(R.id.time_view);
        leftButton = view.findViewById(R.id.left_number_btn);
        rightButton = view.findViewById(R.id.right_number_btn);
        equalButton = view.findViewById(R.id.equal_btn);
        finishText = view.findViewById(R.id.finish);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        int randomNumber;
        randomNumber = random.nextInt();
        randomNumber = randomNumber % 100;
        if (randomNumber < 0) {
            randomNumber = randomNumber * -1;
        }
        return randomNumber;
    }

    private void setButtonNumber() {
        leftButton.setText(String.valueOf(generateRandomNumber()));
        rightButton.setText(String.valueOf(generateRandomNumber()));
    }

    private void generateNewLevel() {
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserPoint(CLICK_LEFT_BUTTON);
                if (!gameFinish) {
                    userLevel++;
                    userLevelView.setText(getString(R.string.user_level, userLevel));
                }
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserPoint(CLICK_RIGHT_BUTTON);
                if (!gameFinish) {
                    userLevel++;
                    userLevelView.setText(getString(R.string.user_level, userLevel));
                }
            }
        });
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserPoint(CLICK_EQUAL_BUTTON);
                if (!gameFinish) {
                    userLevel++;
                    userLevelView.setText(getString(R.string.user_level, userLevel));
                }
            }
        });

    }

    private void setUserPoint(int userClick) {
        int leftNumber;
        int rightNumber;
        if (!gameFinish) {
            leftNumber = Integer.parseInt(leftButton.getText().toString());
            rightNumber = Integer.parseInt(rightButton.getText().toString());
            if (userClick == CLICK_LEFT_BUTTON) {
                if (leftNumber > rightNumber) {
                    userPoint++;
                }
            }
            if (userClick == CLICK_RIGHT_BUTTON) {
                if (rightNumber > leftNumber) {
                    userPoint++;
                }
            }
            if (userClick == CLICK_EQUAL_BUTTON) {
                if (leftNumber == rightNumber) {
                    userPoint++;
                }
            }
            userPointView.setText(getString(R.string.user_point, userPoint));
            setButtonNumber();
        } else {
            return;
        }
    }

    private void startGame() {
        countDownTimer = new CountDownTimer(12000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                gameTime.setText(getString(R.string.remainTime, (int) millisUntilFinished / 1000 - 1));
            }

            @Override
            public void onFinish() {
                gameFinish = true;
                finishText.setText(R.string.finishGame);
                updateUserData();

            }
        };
        countDownTimer.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    private void updateUserData() {
        PlayerAttributes player = new PlayerAttributes();
        PlayerRankedList playerList = MyPreferencesManager.setInstance(getActivity()).getPlayerListManager();
        player.setName(getArguments().getString("player_name", null));
        player.setScore(userPoint);
        player.setLevel(userLevel);
        playerList.addToRankedList(player);
        MyPreferencesManager.setInstance(getActivity()).putPlayerListManager(playerList);
    }
}

