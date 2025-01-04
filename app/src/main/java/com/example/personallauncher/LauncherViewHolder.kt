package com.example.personallauncher

import android.content.pm.ResolveInfo
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LauncherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val nameTV = itemView as TextView
    private lateinit var resolveInfo: ResolveInfo

    fun bind(resolveInfo: ResolveInfo) {
        this.resolveInfo = resolveInfo
        val packageManager = itemView.context.packageManager
        val appName = resolveInfo.loadLabel(packageManager).toString()
        nameTV.text = appName
    }
}