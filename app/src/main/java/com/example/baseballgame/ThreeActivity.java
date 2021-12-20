package com.example.baseballgame;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThreeActivity extends AppCompatActivity {

    int dap1, dap2, dap3;
    int count;
    EditText et1, et2, et3;
    TextView tvCount, tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_set);

        tvCount = findViewById(R.id.tvCount);
        tvResult = findViewById(R.id.tvResult);

        count = 0;
    }

    public void btnOnStart(View v) {

        if (count == 0) {          // 카운트가 0일 경우
            tvResult.setText("");  // tvResult를 초기화한다
            tvResult.setText("빈 칸에 중복되지 않는 숫자 3개를 입력하고 확인을 눌러주세요");

            dap1 = (int) (Math.random() * 9) + 1;
            dap2 = (int) (Math.random() * 9) + 1;
            dap3 = (int) (Math.random() * 9) + 1;

            while (dap2 == dap1)                     // 중복방지
                dap2 = (int) (Math.random() * 9) + 1;

            while (dap3 == dap2 || dap3 == dap1)
                dap2 = (int) (Math.random() * 9) + 1;

            System.out.println("답1 : " + dap1);
            System.out.println("답2 : " + dap2);
            System.out.println("답3 : " + dap3);
        }

            else      // 카운트가 1이상일 경우
            Toast.makeText(ThreeActivity.this, "진행중인 게임이 있습니다", Toast.LENGTH_SHORT).show();
        }

        public void btnOnCheck(View v) {

            et1 = findViewById(R.id.et1);
            et2 = findViewById(R.id.et2);
            et3 = findViewById(R.id.et3);

            int userNum1 = Integer.parseInt(et1.getText().toString());  // 입력값 int로 받아오기
            int userNum2 = Integer.parseInt(et2.getText().toString());
            int userNum3 = Integer.parseInt(et3.getText().toString());

            int strike = 0;
            int ball = 0;

            if (dap1 == userNum1)    // 입력한 값과 랜덤숫자 3개를 비교
                strike++;
            else if (dap1 == userNum2 || dap1 == userNum3)
                ball++;

            if (dap2 == userNum2)
                strike++;
            else if (dap2 == userNum1 || dap2 == userNum3)
                ball++;

            if (dap3 == userNum3)
                strike++;
            else if (dap3 == userNum1 || dap3 == userNum3)
                ball++;


            count++;   // 카운트 수 증가
            tvCount.setText(count + "번째");

            String resultStr = "\n# " + count + " : ";  // 결과 스트링 생성

            if (strike == 0 && ball == 0)  // 3개 다 오답이면 결과에 "아웃: 저장
                resultStr += "아웃";

            else if (strike == 3) {  // 3개다 정답이면 결과에 "성공" 저장하고 카운트 수 초기화
                resultStr += "성공";
                count = 0;

            }

            else    // S, B 존재할 경우 저장
                resultStr += strike + "S, " +ball + "B";

            // 결과 메시지 출력
            if (tvResult.getText().toString() == null || tvResult.getText().toString() == "")
                tvResult.setText(resultStr);
            else
                tvResult.append(resultStr);

            if (count == 10) {      // 카운트 수가 10일경우 초기화
                tvResult.append("\n 기회는 10회까지입니다");
                count = 0;
                dap1 = 0;
                dap2 = 0;
                dap3 = 0;
            }

        }

    }



