package com.example.fragmentuiprac2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class StatusDialog extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //각 프레그먼트에서 드론의 상태값을 받아온다.
        Bundle mArgs = getArguments();
        String mValue = mArgs.getString("status");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog, null);

        //받아온 값을 dialog.xml에 전달
        TextView stext1 = view.findViewById(R.id.stext1);
        TextView stext2 = view.findViewById(R.id.stext2);
        TextView stext3 = view.findViewById(R.id.stext3);
        TextView stext4 = view.findViewById(R.id.stext4);
        TextView stext5 = view.findViewById(R.id.stext5);
        TextView stext6 = view.findViewById(R.id.stext6);
        TextView stext7 = view.findViewById(R.id.stext7);
        TextView stext8 = view.findViewById(R.id.stext8);
        
        // split을 이용하여 원하는 값 추출한 후 String 배열 a에 저장
        String[] a = mValue.split(";");
        
        // 실시간 값의 자릿수가 다를 수 있으므로 확인을 하기 위해 int 변수에 저장
        int s = a[15].length();
        int sa = a[12].length();
        int sa2 = a[11].length();
        int sa3 = a[17].length();
        int sa4 = a[14].length();
        int sa5 = a[13].length();
        int sa6 = a[18].length();
        int sa7 = a[19].length();
        int sa8 = a[20].length();
        int sa9 = a[16].length();

        //append, substring를 이용하여 기존 TextView에 추가 데이터 입력
        stext1.append(a[15].substring(4,s)+"%");
        stext2.append(a[12].substring(6,sa)+"도");
        stext3.append(a[11].substring(6,sa2)+"도");
        stext4.append(a[17].substring(5,sa3)+"초");
        stext5.append(a[14].substring(2,sa4)+"cm");
        stext6.append(a[13].substring(4,sa5)+"cm");
        stext7.append(" x축 : "+a[18].substring(4,sa6) + " y축 : " + a[19].substring(4,sa7) + " z축 : " +a[20].substring(4,sa8));
        stext8.append(a[16].substring(5,sa9)+"cm");

        builder.setView(view);

        return builder.create();
    }
}
