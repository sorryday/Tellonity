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

public class Drone2Fragment extends Fragment
{
    Context context;
    // 공통 주석으로 Drone1Fragment 참고
    // 정적 IP주소를 192.168.0.8로 설정하여 2번드론으로 만든다.
    public static final String sIP = "192.168.0.8";
    public SendData Sendcommand = null;
    public status ReceivedStatus = null;
    public TextView textView = null;
    public String command = null;
    public boolean ismsg = false;
    private Animation animation;

    public Drone2Fragment() {}

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState)
    {
        context = container.getContext();
        View view = inflater.inflate(R.layout.drone2ui, container, false);
        final ImageView dr = view.findViewById(R.id.DroneImage2);
        final ImageView commandBtn = view.findViewById(R.id.command2);
        final ImageView takeoffBtn = view.findViewById(R.id.takeoff2);
        final ImageView landBtn = view.findViewById(R.id.land2);
        final ImageView forwardBtn = view.findViewById(R.id.forward2);
        final ImageView rightBtn = view.findViewById(R.id.right2);
        final ImageView leftBtn = view.findViewById(R.id.left2);
        final ImageView backwardBtn = view.findViewById(R.id.back2);
        final ImageView cwBtn = view.findViewById(R.id.cw2);
        final ImageView ccwBtn = view.findViewById(R.id.ccw2);
        final ImageView upBtn = view.findViewById(R.id.up2);
        final ImageView downBtn = view.findViewById(R.id.down2);
        final ImageView statusBtn = view.findViewById(R.id.status2);
        final ImageView fliplBtn = view.findViewById(R.id.flipl2);
        final ImageView fliprBtn = view.findViewById(R.id.flipr2);
        final ImageView flipfBtn = view.findViewById(R.id.flipf2);
        final ImageView flipbBtn = view.findViewById(R.id.flipb2);
        final ImageView helpBtn = view.findViewById(R.id.help2);

        commandBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "command";
                ismsg = true;
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
                ismsg = true;
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
                ismsg = true;
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
                ismsg = true;
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
                ismsg = true;
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
                ismsg = true;
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
                ismsg = true;
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
                ismsg = true;
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

        statusBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                command = "status";
                ismsg = true;
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

    int sPORT = 0;

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
                            String k = command;
                            sendMsg = k.getBytes();
                            DatagramPacket packet = new DatagramPacket(sendMsg, sendMsg.length, address, sPORT);
                            socket.send(packet);
                            byte[] buffer = new byte[1024];
                            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                            socket.receive(response);
                            String msg = new String(response.getData());
                            msg.getBytes("UTF-8");
                            msg.trim();
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

    class status extends Thread
    {
        public void run()
        {
            DatagramSocket a = null;
            String mm;
            try
            {
                a = new DatagramSocket(8890);
                byte[] msg = new byte[256];
                DatagramPacket packet = new DatagramPacket(msg, msg.length);
                a.receive(packet);
                mm = new String(packet.getData());
                a.close();
                Bundle args = new Bundle();
                args.putString("status",mm);
                StatusDialog qwer = new StatusDialog();
                qwer.setArguments(args);
                qwer.show(getFragmentManager(), "Sample Dialog Fragment");
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