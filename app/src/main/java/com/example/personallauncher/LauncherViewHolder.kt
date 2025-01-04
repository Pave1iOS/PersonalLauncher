package com.example.personallauncher

import android.content.Intent
import android.content.pm.ResolveInfo
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LauncherViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener
{
    private val nameTV = itemView as TextView
    private lateinit var resolveInfo: ResolveInfo

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(resolveInfo: ResolveInfo) {
        this.resolveInfo = resolveInfo
        val packageManager = itemView.context.packageManager
        val appName = resolveInfo.loadLabel(packageManager).toString()
        nameTV.text = appName
    }

    override fun onClick(view: View) {
        val activityInfo = resolveInfo.activityInfo

        val intent = Intent(Intent.ACTION_MAIN).apply {
            setClassName(activityInfo.applicationInfo.packageName, activityInfo.name)
        }

        val context = view.context
        context.startActivity(intent)
    }
}