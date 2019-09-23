package com.imuaythai.mtjudges.point.judge

import android.widget.CompoundButton
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.databinding.ScoreCardFragmentBinding
import com.imuaythai.mtjudges.point.judge.injection.PointJudgeModule
import kotlinx.android.synthetic.main.score_card_fragment.*

class PointJudgeActivity : BaseActivity<PointJudgeViewModel>() {

    lateinit var binding: ScoreCardFragmentBinding

    override fun provideViewLayout(): Int = R.layout.score_card_fragment

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(PointJudgeModule()).inject(this)

    override fun provideViewModel(provider: ViewModelProvider): PointJudgeViewModel = provider.get(PointJudgeViewModel::class.java)

    override fun onInitializeDataBinding(int: Int) {
        binding = DataBindingUtil.setContentView(this, provideViewLayout())
    }

    override fun onBindView(viewModel: PointJudgeViewModel) {
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        radio_red.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton = findViewById(checkedId)
            viewModel.setRedPoints(Integer.parseInt(radioButton.text.toString()))
        }

        radio_blu.setOnCheckedChangeListener { group, checkedId ->
            val radioButton: RadioButton = findViewById(checkedId)
            viewModel.setBluePoints(Integer.parseInt(radioButton.text.toString()))
        }

        Red_accept.setOnCheckedChangeListener(this::onCheckedAcceptButton)
        Blu_accept.setOnCheckedChangeListener(this::onCheckedAcceptButton)
    }

    private fun onCheckedAcceptButton(buttonView: CompoundButton, isChecked: Boolean){
        if(Red_accept.isChecked && Blu_accept.isChecked){
            viewModel.sendAcceptedPoints()
        }
    }

}