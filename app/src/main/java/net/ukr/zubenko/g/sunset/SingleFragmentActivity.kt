package net.ukr.zubenko.g.sunset

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

abstract class SingleFragmentActivity : AppCompatActivity(){
    abstract fun createFragment(): Fragment

    @LayoutRes
    protected open fun getLayoutResId() = R.layout.activity_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null)
            supportFragmentManager.
                beginTransaction().
                add(R.id.fragment_container, createFragment()).
                commit()
    }
}