package com.reo.running.omikuji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.fortune.*
import kotlinx.android.synthetic.main.omikuji.*

class MainActivity : AppCompatActivity() {

    //おみくじ棚の配列
    val omikujiShelf = Array<OmikujiParts>(20)
    //コンストラクタの設定（全添え字に吉と良縁が～に設定。）
    { OmikujiParts(R.drawable.result2, R.string.contents1) }
    //おみくじ番号の初期化
    var omikujiNumber = -1
    //OmikujiBoxのインスタンス化
    val omikujiBox = OmikujiBox()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.omikuji)

        /*test_button.setOnClickListener {
            val intent = Intent(this, TestClass::class.java)
            startActivity(intent)
        }*/

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val value = pref.getBoolean("button",false)

        button.visibility = if (value) View.VISIBLE else View.INVISIBLE

        omikujiBox.omikujiView = imageView

        omikujiShelf[0].drawID = R.drawable.result1
        omikujiShelf[0].fortuneID = R.string.contents2
        omikujiShelf[1].drawID = R.drawable.result3
        omikujiShelf[1].fortuneID = R.string.contents9
        omikujiShelf[2].fortuneID = R.string.contents3
        omikujiShelf[3].fortuneID = R.string.contents4
        omikujiShelf[4].fortuneID = R.string.contents5
        omikujiShelf[5].fortuneID = R.string.contents6
        omikujiShelf[6].fortuneID = R.string.contents7
        omikujiShelf[7].fortuneID = R.string.contents8

        /*
        //くじ番号の取得
        val rnd = Random
        val number = rnd.nextInt(20)

        おじくじの棚
        val omikujiShelf = Array<String>(20,{"吉"})
        omikujiShelf[0] = "大吉"
        omikujiShelf[19] = "凶"

        おじくじ棚から取得
        val str = omikujiShelf[number]

        hello_view.text = str
        */
    }

    fun onButtonClick(v:View){
        //ボタンが押されたら、おみくじ処理を開始する
        omikujiBox.shake()

        /*
        val translate = TranslateAnimation(0f,0f,0f,-200f)
        translate.repeatMode = Animation.REVERSE
        translate.repeatCount = 5
        translate.duration = 100
        translate.startOffset = 300

        val rotate = RotateAnimation(0f,130f,imageView.width/2f,imageView.height/2f)
        rotate.duration = 200

        val set = AnimationSet(true)

        set.addAnimation(rotate)
        set.addAnimation(translate)

        imageView.startAnimation(set)

        */
        //imageView.setImageResource(R.drawable.result1)
    }

    //TODO　menu.xmlで記述したおみくじアプリについての箇所をクリックした際に明示的IntentでAboutActivityを呼び出す。しかし、呼び出せずエミュレータが落ちてしまう
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.item1){
            val intent = Intent(this,OmikujiPreferenceActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this,AboutActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun drawResult(){
        omikujiNumber = omikujiBox.number
        val op = omikujiShelf[omikujiNumber]
        setContentView(R.layout.fortune)
        imageView2.setImageResource(op.drawID)
        textView.setText(op.fortuneID)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_DOWN){
            if (omikujiNumber < 0 && omikujiBox.flag){
                drawResult()
            }
        }
        return super.onTouchEvent(event)
    }


}