package com.example.sharingcard;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MySharingCard extends RelativeLayout implements View.OnClickListener {

    private static final long REVEAL_DURATION = 700; //ms

    View rootView;

    private ImageView imgCover, imgProfile, iconSocial;
    RelativeLayout layoutReveal;
    LinearLayout layoutBtns;
    Button socialBtn1, socialBtn2, socialBtn3;


    public MySharingCard(Context context) {
        super(context);
        init(context);
    }

    public MySharingCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        rootView=inflate(context,R.layout.layout_sharing_card,this);
        imgProfile=rootView.findViewById(R.id.img_profile);
        iconSocial=rootView.findViewById(R.id.social_icon);
        imgCover=rootView.findViewById(R.id.cover_image);
        layoutBtns=rootView.findViewById(R.id.layout_btns);
        layoutReveal=rootView.findViewById(R.id.layout_reveal);
        socialBtn1=rootView.findViewById(R.id.social_btn_1);
        socialBtn2=rootView.findViewById(R.id.social_btn_2);
        socialBtn3=rootView.findViewById(R.id.social_btn_3);
        iconSocial.setOnClickListener(this);

    }

    public ImageView getImgCover() {
        return imgCover;
    }

    public ImageView getImgProfile() {
        return imgProfile;
    }

    public Button getSocialBtn1() {
        return socialBtn1;
    }

    public Button getSocialBtn2() {
        return socialBtn2;
    }

    public Button getSocialBtn3() {
        return socialBtn3;
    }

    @Override
    public void onClick(View v) {

        //int centerX=imgCover.getRight();
        // int centerY=imgCover.getBottom();
        //float radius= (float) Math.hypot(imgCover.getWidth(), imgCover.getHeight());
        int centerX = (iconSocial.getRight()+iconSocial.getLeft())/2;
        int centerY = (iconSocial.getBottom()+iconSocial.getTop())/2;
        float radius = (float) Math.hypot(centerX-imgCover.getLeft(), imgCover.getHeight());

        if(layoutReveal.getVisibility()==VISIBLE){
            hide(centerX, centerY, radius);
        } else {
            show(centerX, centerY, radius);
        }
        /*if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){

        }*/
    }

    private void hide(int centerX, int centerY, float radius) {
        iconSocial.setImageResource(R.drawable.cup_white);
        iconSocial.setBackgroundResource(R.drawable.social_icon_normal_bg);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Animator animator=
                    ViewAnimationUtils.createCircularReveal(
                            layoutReveal, centerX, centerY, radius, 0);
            animator.setDuration(REVEAL_DURATION);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    iconSocial.setImageResource(R.drawable.cup_white);
                    iconSocial.setBackgroundResource(R.drawable.social_icon_normal_bg);

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    layoutReveal.setVisibility(GONE);
                    layoutBtns.setVisibility(GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();
        }else{
            Animation animation=AnimationUtils.loadAnimation(getContext(),R.anim.fadeout);
            //animation.setDuration(REVEAL_DURATION/2);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    layoutReveal.setVisibility(GONE);
                    layoutBtns.setVisibility(GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            layoutReveal.startAnimation(animation);
        }
    }

    private void show(int centerX, int centerY, float radius) {
        iconSocial.setImageResource(R.drawable.cancel);
        iconSocial.setBackgroundResource(R.drawable.social_icon_cancel_bg);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Animator animator=
                    ViewAnimationUtils.createCircularReveal(
                            layoutReveal, centerX, centerY,0, radius);
            animator.setDuration(REVEAL_DURATION);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    layoutReveal.setVisibility(VISIBLE);
                    iconSocial.setImageResource(R.drawable.cancel);
                    iconSocial.setBackgroundResource(R.drawable.social_icon_cancel_bg);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    layoutBtns.setVisibility(VISIBLE);
                    Animation fadein = AnimationUtils.loadAnimation(rootView.getContext(),R.anim.fadein);
                    layoutBtns.startAnimation(fadein);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();
        }else{
            Animation animation= AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
            //animation.setDuration(REVEAL_DURATION *3/4);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    //layoutReveal.setVisibility(VISIBLE);

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    layoutBtns.setVisibility(VISIBLE);
                    Animation fadein = AnimationUtils.loadAnimation(rootView.getContext(),R.anim.fadein);
                    //fadein.setDuration(REVEAL_DURATION * 3/8);
                    layoutBtns.startAnimation(fadein);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            layoutReveal.setVisibility(VISIBLE);
            layoutReveal.startAnimation(animation);
        }
    }
}
