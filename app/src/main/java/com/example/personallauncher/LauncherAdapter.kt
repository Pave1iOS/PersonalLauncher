package com.example.personallauncher

import android.content.pm.ResolveInfo
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LauncherAdapter(val activities: List<ResolveInfo>): RecyclerView.Adapter<LauncherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LauncherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(
            android.R.layout.simple_list_item_1,
            parent,
            false
        )

        return LauncherViewHolder(view)
    }

    override fun getItemCount(): Int = activities.size

    override fun onBindViewHolder(holder: LauncherViewHolder, position: Int) {
        val resolveInfo = activities[position]

        holder.bind(resolveInfo)
    }
}