package com.example.personallauncher

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Insets
import android.graphics.Rect
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsAnimation.Bounds
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LauncherViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener
{

    private val container: LinearLayout = itemView as LinearLayout

    private val nameTV = TextView(itemView.context).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textSize = 24f
        setPadding(20, 0, 0, 0)
    }

    private val iconIV = ImageView(itemView.context).apply {
        layoutParams = ViewGroup.LayoutParams(150, 150)
    }

    private lateinit var resolveInfo: ResolveInfo

    init {
        itemView.setOnClickListener(this)

        container.addView(iconIV)
        container.addView(nameTV)
    }

    fun bind(resolveInfo: ResolveInfo) {
        this.resolveInfo = resolveInfo
        val packageManager = itemView.context.packageManager
        val appName = resolveInfo.loadLabel(packageManager).toString()
        val appIcon = resolveInfo.loadIcon(packageManager)

        iconIV.setImageDrawable(appIcon)
        nameTV.text = appName
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

        fun createContainer(context: Context): LinearLayout {
            return LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                setPadding(26, 16, 16, 16)
            }
        }
    }
}