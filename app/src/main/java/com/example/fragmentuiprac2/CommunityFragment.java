package com.example.fragmentuiprac2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class CommunityFragment extends Fragment
{
    // 200대 가량 조종 가능한 CommunityFragment 클래스
    // 배열로 여러 ip에 전달하기 위해 ip주소는 쓰레드 안에서 처리, 공통 주석은 Drone1Fragment 참고
    Context context;
    public static final int sPORT = 8889;
    public SendData Sendcommand = null;
    public String command = null;
    public boolean ismsg= false;
    private Animation animation;
    public CommunityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        context = container.getContext();
        View view = inflater.inflate(R.layout.communityui, container, false);
        final ImageView commandBtn = view.findViewById(R.id.command3);
        final ImageView takeoffBtn = view.findViewById(R.id.takeoff3);
        final ImageView landBtn = view.findViewById(R.id.land3);
        final ImageView forwardBtn = view.findViewById(R.id.forward3);
        final ImageView rightBtn = view.findViewById(R.id.right3);
        final ImageView leftBtn = view.findViewById(R.id.left3);
        final ImageView backwardBtn = view.findViewById(R.id.back3);
        final ImageView cwBtn = view.findViewById(R.id.cw3);
        final ImageView ccwBtn = view.findViewById(R.id.ccw3);
        final ImageView upBtn = view.findViewById(R.id.up3);
        final ImageView downBtn = view.findViewById(R.id.down3);
        final ImageView fliplBtn = view.findViewById(R.id.flipl3);
        final ImageView fliprBtn = view.findViewById(R.id.flipr3);
        final ImageView flipfBtn = view.findViewById(R.id.flipf3);
        final ImageView flipbBtn = view.findViewById(R.id.flipb3);
        final ImageView help2Btn = view.findViewById(R.id.help2);
        final ImageView dr = view.findViewById(R.id.DroneImage3);

        help2Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, GuideActivity2.class);
                startActivity(intent);
            }
        });

        commandBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "command";
                ismsg =true;
                //textView.setText("Received ");
                Sendcommand = new SendData();
                Sendcommand.start();
                animation = AnimationUtils.loadAnimation(context,R.anim.shake);
                commandBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.shake);
                dr.startAnimation(animation);
            }
        });

        takeoffBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "takeoff";
                ismsg =true;
                animation = AnimationUtils.loadAnimation(context,R.anim.forward);
                takeoffBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.forward);
                dr.startAnimation(animation);

            }
        });

        landBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "land";
                ismsg =true;
                animation = AnimationUtils.loadAnimation(context,R.anim.back);
                landBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.back);
                dr.startAnimation(animation);

            }
        });

        forwardBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "forward 50";
                ismsg =true;
                animation = AnimationUtils.loadAnimation(context,R.anim.forward);
                forwardBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.forward);
                dr.startAnimation(animation);

            }
        });

        rightBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "right 50";
                ismsg =true;
                animation = AnimationUtils.loadAnimation(context,R.anim.right);
                rightBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.right);
                dr.startAnimation(animation);

            }
        });

        leftBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "left 50";
                ismsg =true;
                animation = AnimationUtils.loadAnimation(context,R.anim.left);
                leftBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.left);
                dr.startAnimation(animation);
            }
        });

        backwardBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "back 50";
                ismsg =true;
                animation = AnimationUtils.loadAnimation(context,R.anim.back);
                backwardBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.back);
                dr.startAnimation(animation);

            }
        });

        cwBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "cw 90";
                ismsg =true;
                animation = AnimationUtils.loadAnimation(context,R.anim.cw);
                cwBtn.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(context,R.anim.cw);
                dr.startAnimation(animation);
            }
        });

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
        return view;
    }
    class SendData extends Thread
    {
        public void run()
        {
            DatagramSocket socket=null;
            try
            {
                socket = new DatagramSocket();
                // 최대 200대 까지 드론을 조종하기 위해 200으로 설정
                int NUM_CONNECTED=200;
                //연결 확인할 TELLO_IP_ADDRESS[] 초기화
                String TELLO_IP_ADDRESS[] = new String[NUM_CONNECTED];
                // 200대 드론에게 순차적으로 명령 전달
                for (int i=0; i<NUM_CONNECTED; i++)
                {
                    TELLO_IP_ADDRESS[i] = "192.168.0." + Integer.toString(i + 7);
                }
                while(true)
                {
                    if(ismsg)
                    {
                        if(command.equals("quit")) break;
                        byte[] sendMsg;
                        String k = command;

                        // 연결된 드론에게 송신하기 위한 ip주소 설정
                        for (int i=0; i<NUM_CONNECTED; i++)
                        {
                            InetAddress address = InetAddress.getByName(TELLO_IP_ADDRESS[i]);
                            sendMsg = k.getBytes();
                            DatagramPacket packet = new DatagramPacket(sendMsg, sendMsg.length, address, sPORT);
                            socket.send(packet);
                        }
                        ismsg = false;
                    }
                }
            } catch (SocketException | UnknownHostException e)
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
}