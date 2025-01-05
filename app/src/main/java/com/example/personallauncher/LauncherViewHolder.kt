package com.example.personallauncher

import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Insets
import android.graphics.Rect
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsAnimation.Bounds
import android.widget.ImageView
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
        val appIcon = resolveInfo.loadIcon(packageManager)

        nameTV.text = appName
        nameTV.setCompoundDrawablesWithIntrinsicBounds(appIcon, null, null, null)
    }

    override fun onClick(view: View) {
        val activityInfo = resolveInfo.activityInfo

        val intent = Intent(Intent.ACTION_MAIN).apply {
            setClassName(activityInfo.applicationInfo.packageName, activityInfo.name)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        val context = view.context
        context.startActivity(intent)
    }

    companion object {
        private const val TAG = "LauncherViewHolder"
    }
}