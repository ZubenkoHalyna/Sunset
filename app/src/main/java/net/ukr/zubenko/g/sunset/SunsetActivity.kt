package net.ukr.zubenko.g.sunset

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

class SunsetActivity : SingleFragmentActivity() {
    override fun createFragment() = SunsetFragment()

}
