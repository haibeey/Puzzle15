package com.example.haibeey.puzzle15;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class BlankFragment extends Fragment {

    int visibleSet=0;
    View view;
    ImageView one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,twenty;


    public BlankFragment() {
        // Required empty public constructor
    }

    public void toggleVisibility(int val,View v){
        if(val==0){
            one.setVisibility(View.VISIBLE);
            two.setVisibility(View.VISIBLE);
            three.setVisibility(View.VISIBLE);
            four.setVisibility(View.VISIBLE);
            five.setVisibility(View.VISIBLE);
            six.setVisibility(View.GONE);
            seven.setVisibility(View.GONE);
            eight.setVisibility(View.GONE);
            nine.setVisibility(View.GONE);
            ten.setVisibility(View.GONE);
            eleven.setVisibility(View.GONE);
            twelve.setVisibility(View.GONE);
            thirteen.setVisibility(View.GONE);
            fourteen.setVisibility(View.GONE);
            fifteen.setVisibility(View.GONE);
            sixteen.setVisibility(View.GONE);
            seventeen.setVisibility(View.GONE);
            eighteen.setVisibility(View.GONE);
            nineteen.setVisibility(View.GONE);
            twenty.setVisibility(View.GONE);
        }
        else if(val==1){
            one.setVisibility(View.GONE);
            two.setVisibility(View.GONE);
            three.setVisibility(View.GONE);
            four.setVisibility(View.GONE);
            five.setVisibility(View.GONE);
            six.setVisibility(View.VISIBLE);
            seven.setVisibility(View.VISIBLE);
            eight.setVisibility(View.VISIBLE);
            nine.setVisibility(View.VISIBLE);
            ten.setVisibility(View.VISIBLE);
            eleven.setVisibility(View.GONE);
            twelve.setVisibility(View.GONE);
            thirteen.setVisibility(View.GONE);
            fourteen.setVisibility(View.GONE);
            fifteen.setVisibility(View.GONE);
            sixteen.setVisibility(View.GONE);
            seventeen.setVisibility(View.GONE);
            eighteen.setVisibility(View.GONE);
            nineteen.setVisibility(View.GONE);
            twenty.setVisibility(View.GONE);
        }
        else if(val==2){
            one.setVisibility(View.GONE);
            two.setVisibility(View.GONE);
            three.setVisibility(View.GONE);
            four.setVisibility(View.GONE);
            five.setVisibility(View.GONE);
            six.setVisibility(View.GONE);
            seven.setVisibility(View.GONE);
            eight.setVisibility(View.GONE);
            nine.setVisibility(View.GONE);
            ten.setVisibility(View.GONE);
            eleven.setVisibility(View.VISIBLE);
            twelve.setVisibility(View.VISIBLE);
            thirteen.setVisibility(View.VISIBLE);
            fourteen.setVisibility(View.VISIBLE);
            fifteen.setVisibility(View.VISIBLE);
            sixteen.setVisibility(View.GONE);
            seventeen.setVisibility(View.GONE);
            eighteen.setVisibility(View.GONE);
            nineteen.setVisibility(View.GONE);
            twenty.setVisibility(View.GONE);
        }
        else if(val==3){
            sixteen.setVisibility(View.VISIBLE);
            seventeen.setVisibility(View.VISIBLE);
            eighteen.setVisibility(View.VISIBLE);
            nineteen.setVisibility(View.VISIBLE);
            twenty.setVisibility(View.VISIBLE);
            one.setVisibility(View.GONE);
            two.setVisibility(View.GONE);
            three.setVisibility(View.GONE);
            four.setVisibility(View.GONE);
            five.setVisibility(View.GONE);
            six.setVisibility(View.GONE);
            seven.setVisibility(View.GONE);
            eight.setVisibility(View.GONE);
            nine.setVisibility(View.GONE);
            ten.setVisibility(View.GONE);
            eleven.setVisibility(View.GONE);
            twelve.setVisibility(View.GONE);
            thirteen.setVisibility(View.GONE);
            fourteen.setVisibility(View.GONE);
            fifteen.setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle b=getArguments();
        final int p=b.getInt("pos");
        if(p==0) {
            view = inflater.inflate(R.layout.fragment_blank, container, false);
            ImageView im = (ImageView) view.findViewById(R.id.imbig);

            Button b1 = (Button) view.findViewById(R.id.b1);
            Button b2 = (Button) view.findViewById(R.id.b2);

            one = (ImageView) view.findViewById(R.id.im1);
            one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 10);
                    startActivity(I);
                }
            });
            two = (ImageView) view.findViewById(R.id.im2);
            two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 11);
                    startActivity(I);
                }
            });
            three = (ImageView) view.findViewById(R.id.im3);
            three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 12);
                    startActivity(I);
                }
            });
            four = (ImageView) view.findViewById(R.id.im4);
            four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 13);
                    startActivity(I);
                }
            });
            five = (ImageView) view.findViewById(R.id.im5);
            five.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 14);
                    startActivity(I);
                }
            });
            six = (ImageView) view.findViewById(R.id.im6);
            six.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 15);
                    startActivity(I);
                }
            });
            seven = (ImageView) view.findViewById(R.id.im7);
            seven.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 16);
                    startActivity(I);
                }
            });
            eight = (ImageView) view.findViewById(R.id.im8);
            eight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 17);
                    startActivity(I);
                }
            });
            nine = (ImageView) view.findViewById(R.id.im9);
            nine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 18);
                    startActivity(I);
                }
            });
            ten = (ImageView) view.findViewById(R.id.im10);
            ten.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 19);
                    startActivity(I);
                }
            });
            eleven = (ImageView) view.findViewById(R.id.im11);
            eleven.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 20);
                    startActivity(I);
                }
            });
            twelve = (ImageView) view.findViewById(R.id.im12);
            twelve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 21);
                    startActivity(I);
                }
            });
            thirteen = (ImageView) view.findViewById(R.id.im13);
            thirteen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 22);
                    startActivity(I);
                }
            });
            fourteen = (ImageView) view.findViewById(R.id.im14);
            fourteen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 23);
                    startActivity(I);
                }
            });
            fifteen = (ImageView) view.findViewById(R.id.im15);
            fifteen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 24);
                    startActivity(I);
                }
            });
            sixteen = (ImageView) view.findViewById(R.id.im16);
            sixteen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 25);
                    startActivity(I);
                }
            });
            seventeen = (ImageView) view.findViewById(R.id.im17);
            seventeen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 26);
                    startActivity(I);
                }
            });
            eighteen = (ImageView) view.findViewById(R.id.im18);
            eighteen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 27);
                    startActivity(I);
                }
            });
            nineteen = (ImageView) view.findViewById(R.id.im19);
            nineteen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 28);
                    startActivity(I);
                }
            });
            twenty = (ImageView) view.findViewById(R.id.im20);
            twenty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getContext(), MainActivity.class);
                    I.putExtra("data", 29);
                    startActivity(I);
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ++visibleSet;
                    visibleSet=visibleSet%4;
                    toggleVisibility(visibleSet%4, view);
                }
            });

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(visibleSet>0){
                        visibleSet--;
                        visibleSet=visibleSet%4;
                        toggleVisibility(visibleSet%4, view);
                    } ;
                }
            });


            im.setImageResource(b.getInt("data"));
            ImageView a=(ImageView)view.findViewById(R.id.anim);
            ImageView bb=(ImageView)view.findViewById(R.id.anim1);

            a.setImageResource(R.drawable.b);
            bb.setImageResource(R.drawable.w);
            return view;
        }else{
            View view=inflater.inflate(R.layout.fragment_blank2,container,false);
            ImageView im1=(ImageView)view.findViewById(R.id.imm1);
            im1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I=new Intent(getContext(),MainActivity.class);
                    I.putExtra("data",1);
                    startActivity(I);
                }
            });

            ImageView im2=(ImageView)view.findViewById(R.id.imm2);
            im2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I=new Intent(getContext(),MainActivity.class);
                    I.putExtra("data",2);
                    startActivity(I);
                }
            });

            /*ImageView im3=(ImageView)view.findViewById(R.id.imm3);
            im3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I=new Intent(getContext(),internet.class);
                    startActivity(I);
                }
            });*/

            ImageView im4=(ImageView)view.findViewById(R.id.imm4);
            im4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I=new Intent(getContext(),MainActivity.class);
                    I.putExtra("data",4);
                    startActivity(I);
                }
            });
            ImageView im5=(ImageView)view.findViewById(R.id.imm5);
            im5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });

            ImageView a=(ImageView)view.findViewById(R.id.animm);
            ImageView bb=(ImageView)view.findViewById(R.id.animm1);
            a.setImageResource(R.drawable.w);
            bb.setImageResource(R.drawable.b);

            return view;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        try {
            finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }




}
