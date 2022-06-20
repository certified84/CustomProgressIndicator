package com.certified.customcomponents

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.certified.customcomponents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            binding.btnShowIndicator.setOnClickListener {
                if (!indicator.isVisible) {
                    indicator.visibility = View.VISIBLE
                    indicator.startAnimation()
                }
                else {
                    indicator.visibility = View.GONE
                    indicator.stopAnimation()
                }
            }
        }
    }

    //    @SuppressLint("ResourceType")
    override fun onResume() {
        super.onResume()
        binding.indicator.apply {
//            setTextSize(resources.getDimension(com.intuit.sdp.R.dimen._30sdp))
            setTextColor(ResourcesCompat.getColorStateList(resources, R.color.white, null)!!)
            setTypeface(R.font.space_grotesk_light)
            setProgressIndicatorColor("#FFFFFF")
//            setText("Unruly...")
            setImageResource(R.drawable.avatar)
            setTrackColor("#B32821")
        }
    }

    override fun onPause() {
        super.onPause()
        binding.indicator.apply {
            stopAnimation()
        }
    }

//    override fun onResume() {
//        super.onResume()
//
//        val show =
//            findViewById<ConstraintLayout>(R.id.custom_progress_indicator_root).visibility == View.VISIBLE
//        val imageView = findViewById<ShapeableImageView>(R.id.iv_logo)
//
//        val animZoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
//        val animZoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
//        CoroutineScope(Dispatchers.Main).launch {
//            while (show) {
//                delay(800L)
//                imageView.startAnimation(animZoomOut)
//                delay(800L)
//                imageView.startAnimation(animZoomIn)
////                delay(500L)
//            }
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}