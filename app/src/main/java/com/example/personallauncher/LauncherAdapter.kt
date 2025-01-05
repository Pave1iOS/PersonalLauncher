package com.example.personallauncher

import android.content.pm.ResolveInfo
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LauncherAdapter(private val activities: List<ResolveInfo>): RecyclerView.Adapter<LauncherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LauncherViewHolder {
        val view = LauncherViewHolder.createContainer(parent.context)

        return LauncherViewHolder(view)
    }

    override fun getItemCount(): Int = activities.size

    override fun onBindViewHolder(holder: LauncherViewHolder, position: Int) {
        val resolveInfo = activities[position]

        holder.bind(resolveInfo)
    }
}