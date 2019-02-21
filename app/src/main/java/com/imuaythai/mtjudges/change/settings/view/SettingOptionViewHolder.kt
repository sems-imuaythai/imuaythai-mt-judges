package com.imuaythai.mtjudges.change.settings.view

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imuaythai.mtjudges.change.settings.model.SettingsListItem


class SettingOptionViewHolder(
    val textView: TextView,
    val selectSettingOptionListener: (Int) -> Unit
) : RecyclerView.ViewHolder(textView){

    fun onBindItem(settingsListItem: SettingsListItem){
        textView.text = settingsListItem.label
        textView.isSelected = settingsListItem.isSelected
        textView.setOnClickListener{
            selectSettingOptionListener(adapterPosition)
        }
    }

}