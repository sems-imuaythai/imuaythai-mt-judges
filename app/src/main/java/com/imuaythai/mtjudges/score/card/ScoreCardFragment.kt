package com.imuaythai.mtjudges.score.card

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseFragment
import com.imuaythai.mtjudges.databinding.ScoreCardFragmentBinding
import com.imuaythai.mtjudges.score.card.injection.ScoreCardModule
import kotlinx.android.synthetic.main.score_card_fragment.*

class ScoreCardFragment constructor(): BaseFragment<ScoreCardViewModel>(){

    override fun provideViewLayout() = R.layout.score_card_fragment

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(ScoreCardModule()).inject(this)

    override fun provideViewModel(provider: ViewModelProvider): ScoreCardViewModel = provider.get(ScoreCardViewModel::class.java)

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, viewModel: ScoreCardViewModel): View {
        val binding: ScoreCardFragmentBinding = DataBindingUtil.inflate(inflater, provideViewLayout(), container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onBindView(viewModel: ScoreCardViewModel) {
        radio_red.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton? = view!!.findViewById(checkedId)
            if(radioButton != null) {
                viewModel.setRedPoints(Integer.parseInt(radioButton.text.toString()))
            }
        }

        radio_blu.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton? = view!!.findViewById(checkedId)
            if(radioButton != null) {
                viewModel.setBluePoints(Integer.parseInt(radioButton.text.toString()))
            }
        }

        Red_accept.setOnCheckedChangeListener(this::onCheckedAcceptButton)
        Blu_accept.setOnCheckedChangeListener(this::onCheckedAcceptButton)

        viewModel.scoreCardVisibility.observe(this, Observer {
            if(it == View.GONE){
                radio_red.check(-1)
                radio_blu.check(-1)
            }
        })
    }

    private fun onCheckedAcceptButton(buttonView: CompoundButton, isChecked: Boolean){
        if(Red_accept.isChecked && Blu_accept.isChecked){
            viewModel.sendAcceptedPoints()
        }
    }

    override fun setArguments(viewModel: ScoreCardViewModel) { }
}