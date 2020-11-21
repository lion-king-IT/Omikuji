package com.reo.running.omikuji

import android.app.Application
import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.Toast

class OmikujiBox : Animation.AnimationListener{


    //画像の設定
    lateinit var omikujiView: ImageView

    //おみくじ終了判定
    var flag = false

    //くじ番号
    val number: Int
        get() {
            val rnd = java.util.Random()
            return rnd.nextInt(20)
        }

    //おみくじ処理
    fun shake() {
        val translate = TranslateAnimation(0f, 0f, 0f, -200f)
        translate.repeatMode = Animation.REVERSE
        translate.repeatCount = 5
        translate.duration = 100
        translate.startOffset = 300

        val rotate = RotateAnimation(0f, 130f, omikujiView.width / 2f, omikujiView.height / 2f)
        rotate.duration = 200

        val set = AnimationSet(true)

        set.addAnimation(rotate)
        set.setAnimationListener(this)
        set.addAnimation(translate)

        omikujiView.startAnimation(set)

        flag = true
    }

    //おみくじ処理開始時
    override fun onAnimationStart(animation: Animation?) {
        omikujiView.setImageResource(R.drawable.omikuji1)
    }
    //おじくじ処理終了時
    override fun onAnimationEnd(animation: Animation?) {
        omikujiView.setImageResource(R.drawable.omikuji2)
    }
    //おじくじ処理繰り返し時、恐らくrepeatCountと関係している
    override fun onAnimationRepeat(animation: Animation?) {

    }
}
