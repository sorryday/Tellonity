package com.example.fragmentuiprac2;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Drone1Fragment extends Fragment
{
    //Fragment 화면 전환을 위한 Context
    Context context;

    //1번드론 IP주소, MAC주소를 이용하여 정적으로 입력
    public static final String sIP = "192.168.0.7";

    //데이터 보낼 클래스
    public SendData Sendcommand = null;
    public status ReceivedStatus = null;
    //화면 표시용
    public TextView textView = null;
    public String command = null;
    public boolean ismsg = false;
    private Animation animation;

    public Drone1Fragment() {}

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState)
    {
        context = container.getContext();
        //1번드론의 UI를 drone1ui로 표현 및 ID값 받아오기
        View view = inflater.inflate(R.layout.drone1ui, container, false);
        final ImageView dr = view.findViewById(R.id.DroneImage);
        final ImageView commandBtn = view.findViewById(R.id.command);
        final ImageView takeoffBtn = view.findViewById(R.id.takeoff);
        final ImageView landBtn = view.findViewById(R.id.land);
        final ImageView forwardBtn = view.findViewById(R.id.forward);
        final ImageView rightBtn = view.findViewById(R.id.right);
        final ImageView leftBtn = view.findViewById(R.id.left);
        final ImageView backwardBtn = view.findViewById(R.id.back);
        final ImageView cwBtn = view.findViewById(R.id.cw);
        final ImageView ccwBtn = view.findViewById(R.id.ccw);
        final ImageView upBtn = view.findViewById(R.id.up);
        final ImageView downBtn = view.findViewById(R.id.down);
        final ImageView statusBtn = view.findViewById(R.id.status);
        final ImageView fliplBtn = view.findViewById(R.id.flipl);
        final ImageView fliprBtn = view.findViewById(R.id.flipr);
        final ImageView flipfBtn = view.findViewById(R.id.flipf);
        final ImageView flipbBtn = view.findViewById(R.id.flipb);
        final ImageView helpBtn = view.findViewById(R.id.help);

        // 도움말 버튼
        helpBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, GuideActivity.class);
                startActivity(intent);
            }
        });

        //커맨드 버튼
        commandBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "command";
                // 반복적으로 명령어를 전달하기위해 ismsg를 true로 설정
                ismsg = true;
                // Sendcommand 쓰레드 실행 
                Sendcommand = new SendData();
                Sendcommand.start();
                // 버튼에 맞는 애니메이션 동작 수행 (드론 애니메이션, 버튼애니메이션)
                animation = AnimationUtils.loadAnimation(context,R.anim.shake);
                commandBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.shake);
                dr.startAnimation(animation);
            }
        });

        //착륙 버튼
        takeoffBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "takeoff";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.forward);
                takeoffBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.forward);
                dr.startAnimation(animation);
            }
        });

        //이륙 버튼
        landBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "land";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.back);
                landBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.back);
                dr.startAnimation(animation);

            }
        });

        //전진 버튼
        forwardBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "forward 50";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.forward);
                forwardBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.forward);
                dr.startAnimation(animation);

            }
        });

        //오른쪽 버튼
        rightBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "right 50";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.right);
                rightBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.right);
                dr.startAnimation(animation);

            }
        });

        //왼쪽 버튼
        leftBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "left 50";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.left);
                leftBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.left);
                dr.startAnimation(animation);
            }
        });

        //후진 버튼
        backwardBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "back 50";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.back);
                backwardBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.back);
                dr.startAnimation(animation);

            }
        });

        //시계방향 회전 버튼
        cwBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "cw 90";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.cw);
                cwBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.cw);
                dr.startAnimation(animation);


            }
        });

        //반시계방향 회전 버튼
        ccwBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "ccw 90";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.ccw);
                ccwBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.ccw);
                dr.startAnimation(animation);

            }
        });

        //위로 가는 버튼
        upBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "up 50";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.up);
                upBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.up);
                dr.startAnimation(animation);
            }
        });

        //아래로 가는 버튼
        downBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "down 50";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.down);
                downBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.down);
                dr.startAnimation(animation);
            }
        });

        //왼쪽으로 플립하는 버튼
        fliplBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "flip l";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.shake);
                fliplBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.flipl);
                dr.startAnimation(animation);
            }
        });

        //오른쪽으로 플립하는 버튼
        fliprBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "flip r";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.shake);
                fliprBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.flipr);
                dr.startAnimation(animation);
            }
        });

        //앞으로 플립하는 버튼
        flipfBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "flip f";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.shake);
                flipfBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.flipf);
                dr.startAnimation(animation);
            }
        });

        //뒤로 플립하는 버튼
        flipbBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "flip b";
                ismsg = true;
                animation = AnimationUtils.loadAnimation(context,R.anim.shake);
                flipbBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.flipb);
                dr.startAnimation(animation);
            }
        });

        //배터리 상태 값 확인 버튼
        statusBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "status";
                ismsg = true;
                // ReceivedStatus 쓰레드 실행
                ReceivedStatus = new status();
                ReceivedStatus.start();
                animation = AnimationUtils.loadAnimation(context,R.anim.shake);
                statusBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.shake);
                dr.startAnimation(animation);
            }
        });
        return view;
    }

    // 포트번호 초기화
    int sPORT = 0;
    
    // 드론과 명령어 송수신을 위한 클래스
    
    class SendData extends Thread
    {
        public void run()
        {
            DatagramSocket socket = null;
            try
            {
                socket = new DatagramSocket();
                InetAddress address = InetAddress.getByName(sIP);
                while (true)
                {
                    sPORT = 8889;  
                    if (ismsg)
                    {
                        if (command.equals("quit")) break;
                        if (!command.equals("status"))
                        {
                            byte[] sendMsg;
                            // 스트링 변수 k에 명령어값 전달
                            String k = command;
                            //Byte배열 변환
                            sendMsg = k.getBytes();
                            //수신측 PORT번호 , 패킷구성
                            DatagramPacket packet = new DatagramPacket(sendMsg, sendMsg.length, address, sPORT);
                            //패킷전송
                            socket.send(packet);
                            //TELLO에서 response 받아 버퍼에 저장
                            byte[] buffer = new byte[1024];
                            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                            socket.receive(response);
                            //response에 있는 Data부분만 msg에 String으로 저장
                            String msg = new String(response.getData());
                            msg.getBytes("UTF-8");
                            msg.trim();
                            //화면에 출력
                            ismsg = false;
                            command = "";
                        }
                        else
                            {
                            ismsg = false;
                            }
                    }
                }
            }
            catch (SocketException | UnknownHostException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                socket.close();
            }
        }
    }

    // 상태 값을 받아오기 위한 클래스
    class status extends Thread
    {
        public void run()
        {
            DatagramSocket a = null;
            // status값을 받아오기 위한 String 변수 mm
            String mm;
            try
            {
                a = new DatagramSocket(8890);
                byte[] msg = new byte[256];
                DatagramPacket packet = new DatagramPacket(msg, msg.length);
                a.receive(packet);
                mm = new String(packet.getData());
                    //소켓 종료
                    a.close();
                    // 값을 StatusDialog 클래스에 전달하기 위한 Bundle 객체 사용
                    Bundle args = new Bundle();
                    args.putString("status", mm);
                    StatusDialog sDialog = new StatusDialog();
                    sDialog.setArguments(args);
                    sDialog.show(getFragmentManager(), "Sample Dialog Fragment");
            }
            catch (SocketException | UnknownHostException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}