package etec.com.br.victor.mvvm_foodapp.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import etec.com.br.victor.mvvm_foodapp.R
import etec.com.br.victor.mvvm_foodapp.databinding.ActivityMealBinding
import etec.com.br.victor.mvvm_foodapp.fragments.HomeFragment
import etec.com.br.victor.mvvm_foodapp.pojo.Meal
import etec.com.br.victor.mvvm_foodapp.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {

    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var youtubeLink: String
    private lateinit var binding: ActivityMealBinding
    private lateinit var mealMvvm: MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProvider(this)[MealViewModel::class.java]

        getMealInformationFromIntent()

        setInformationInViews()

        loadingCase()
        mealMvvm.getMealDetail(mealId)
        observerMealDetailsLiveData()

        onYoutubeImageClick()
    }

    private fun onYoutubeImageClick() {
        binding.imgYt.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDetailsLiveData() {
        mealMvvm.observerMealDetailLiveData().observe(this, object : Observer<Meal>{
            override fun onChanged(t: Meal?) {
                onResponseCase()
                val meal = t

                binding.txtCategory.text = "Category: ${meal!!.strCategory}"
                binding.txtArea.text = "Area: ${meal.strArea}"
                binding.txtIntructionsTips.text = meal.strInstructions

                youtubeLink = meal.strYoutube
            }
        })
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsingToolBar.title = mealName
        binding.collapsingToolBar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolBar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!

    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE

        binding.btnAddToFavourite.visibility = View.INVISIBLE
        binding.txtInstruction.visibility = View.INVISIBLE
        binding.txtCategory.visibility = View.INVISIBLE
        binding.txtArea.visibility = View.INVISIBLE
        binding.imgYt.visibility = View.INVISIBLE
    }
    private fun onResponseCase(){
        binding.progressBar.visibility = View.INVISIBLE

        binding.btnAddToFavourite.visibility = View.VISIBLE
        binding.txtInstruction.visibility = View.VISIBLE
        binding.txtCategory.visibility = View.VISIBLE
        binding.txtArea.visibility = View.VISIBLE
        binding.imgYt.visibility = View.VISIBLE
    }
}