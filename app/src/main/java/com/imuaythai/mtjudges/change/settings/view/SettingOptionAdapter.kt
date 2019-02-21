package com.imuaythai.mtjudges.change.settings.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.change.settings.model.SettingsListItem

class SettingOptionAdapter constructor(
    private val dataSet: List<SettingsListItem>,
    private val selectSettingOptionListener: (SettingsListItem) -> Unit
): RecyclerView.Adapter<SettingOptionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingOptionViewHolder{
        val textView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.setting_option_radio_item, parent, false) as TextView
        return SettingOptionViewHolder(textView){ position: Int ->
            selectSettingOptionListener(dataSet[position])
            setSelectedValue(dataSet[position].value)
        }
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(viewHolder: SettingOptionViewHolder, position: Int) {
        viewHolder.onBindItem(dataSet[position])
    }

    fun setSelectedValue(item: String?) {
        dataSet.forEach {
            it.isSelected = item != null && item == it.value
        }
        notifyDataSetChanged()
    }

}