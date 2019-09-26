package com.imuaythai.mtjudges.point.judge

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.databinding.PointJudgeActivityBinding
import com.imuaythai.mtjudges.draw.card.DrawCardFragment
import com.imuaythai.mtjudges.knockout.card.KnockoutCardFragment
import com.imuaythai.mtjudges.point.judge.injection.PointJudgeModule
import com.imuaythai.mtjudges.point.judge.model.PointCardType
import com.imuaythai.mtjudges.score.card.ScoreCardFragment

class PointJudgeActivity : BaseActivity<PointJudgeViewModel>() {

    lateinit var binding: PointJudgeActivityBinding

    override fun provideViewLayout(): Int = R.layout.point_judge_activity

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(PointJudgeModule()).inject(this)

    override fun provideViewModel(provider: ViewModelProvider): PointJudgeViewModel = provider.get(PointJudgeViewModel::class.java)

    override fun onInitializeDataBinding(int: Int) {
        binding = DataBindingUtil.setContentView(this, provideViewLayout())
    }

    override fun onBindView(viewModel: PointJudgeViewModel) {
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.pointCardType.observe(this, Observer { cardType ->
            when(cardType){
                PointCardType.SCORE_CARD -> {
                    displayFragment(ScoreCardFragment())
                }
                PointCardType.KNOCKOUT_CARD -> {
                    displayFragment(KnockoutCardFragment())
                }
                PointCardType.DRAW_CARD -> {
                    displayFragment(DrawCardFragment())
                }
                else -> { }
            }
        })
    }

    private fun displayFragment(fragment: Fragment){
        val fragmentTag = fragment::class.java.simpleName
        if(supportFragmentManager.findFragmentByTag(fragmentTag) == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,fragment,fragmentTag)
                .commit()
        }
    }

}