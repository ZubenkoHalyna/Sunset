package net.ukr.zubenko.g.sunset

import android.animation.ArgbEvaluator
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.animation.ObjectAnimator
import android.view.animation.AccelerateInterpolator
import android.animation.AnimatorSet




class SunsetFragment: Fragment() {
    private lateinit var mSceneView: View
    private lateinit var mSunView: View
    private lateinit var mSkyView: View
    private var mBlueSkyColor: Int = 0
    private var mSunsetSkyColor: Int = 0
    private var mNightSkyColor: Int = 0

    companion object {
        fun newInstance() = SunsetFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sunset, container, false)

        mSceneView = view
        mSunView = view.findViewById(R.id.sun)
        mSkyView = view.findViewById(R.id.sky)
        context?.let { context ->
            mBlueSkyColor = context.getColor(R.color.blue_sky)
            mSunsetSkyColor = context.getColor(R.color.sunset_sky)
            mNightSkyColor = context.getColor(R.color.night_sky)
        }
        mSceneView.setOnClickListener { startAnimation() }
        return view
    }

    private fun startAnimation() {
        val sunYStart = mSunView.top.toFloat()
        val sunYEnd = mSkyView.height.toFloat()

        val heightAnimator = ObjectAnimator
            .ofFloat(mSunView, "y", sunYStart, sunYEnd)
            .setDuration(3000)
        heightAnimator.interpolator = AccelerateInterpolator()

        val sunsetSkyAnimator = ObjectAnimator
            .ofInt(mSkyView, "backgroundColor", mBlueSkyColor, mSunsetSkyColor)
            .setDuration(3000)
        sunsetSkyAnimator.setEvaluator(ArgbEvaluator())

        val nightSkyAnimator = ObjectAnimator
            .ofInt(mSkyView, "backgroundColor", mSunsetSkyColor, mNightSkyColor)
            .setDuration(1500)
        nightSkyAnimator.setEvaluator(ArgbEvaluator())

        val animatorSet = AnimatorSet()
        animatorSet
            .play(heightAnimator)
            .with(sunsetSkyAnimator)
            .before(nightSkyAnimator)
        animatorSet.start()
    }
}