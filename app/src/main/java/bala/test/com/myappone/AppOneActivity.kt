package bala.test.com.myappone

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class AppOneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_one)

        supportFragmentManager.beginTransaction().add(R.id.frameLayoutFO, AppOneLoginFragment.newInstance("", "")).commit()
    }
}
