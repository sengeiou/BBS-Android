package com.twtstudio.bbs.bdpqchen.bbs.main.mainV3

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cn.edu.twt.retrox.recyclerviewdsl.Item
import cn.edu.twt.retrox.recyclerviewdsl.ItemController
import com.twtstudio.bbs.bdpqchen.bbs.R
import com.twtstudio.bbs.bdpqchen.bbs.commons.utils.ImageUtil
import com.twtstudio.bbs.bdpqchen.bbs.commons.utils.IntentUtil
import com.twtstudio.bbs.bdpqchen.bbs.commons.utils.StampUtil
import com.twtstudio.bbs.bdpqchen.bbs.main.latest.LatestEntity
import org.jetbrains.anko.layoutInflater

class MainV3ThreadItem(val latest: LatestEntity, val context: Context, val uid: Int) : Item {
    override val controller: ItemController
        get() = Controller

    companion object Controller : ItemController {
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Item) {
            holder as ViewHolder
            item as MainV3ThreadItem
            ImageUtil.loadAvatar(item.context, item.uid, holder.avatar)
            holder.avatar.setOnClickListener {
                val intent = IntentUtil.toPeople(item.context, item.uid)
                item.context.startActivity(intent)
            }
            holder.name.text = item.latest.author_name
            holder.threadType.text = item.latest.board_name
            holder.threadType.setOnClickListener {
                item.context.startActivity(IntentUtil.toThreadList(item.context, item.latest.board_id, item.latest.board_name, item.latest.anonymous))
            }
            holder.title.text = item.latest.title
            holder.title.setOnClickListener {
                item.context.startActivity(IntentUtil.toThread(item.context, item.latest.id, item.latest.title, item.latest.board_id, item.latest.board_name))
            }
            holder.time.text = StampUtil.getTimeFromNow(item.latest.t_create, item.latest.t_reply)
            holder.favorNum.text = "" + item.latest.like
            holder.commentNum.text = "" + item.latest.c_post
        }

        override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val layoutInflater = parent.context.layoutInflater
            val view = layoutInflater.inflate(R.layout.item_main_v3_thread, parent, false)
            val avtar: ImageView = view.findViewById(R.id.item_main_thread_avatar)
            val name: TextView = view.findViewById(R.id.item_main_thread_username)
            val threadType: TextView = view.findViewById(R.id.item_main_thread_type)
            val title: TextView = view.findViewById(R.id.item_main_thread_title)
            val commentNum: TextView = view.findViewById(R.id.item_main_thread_comment_num)
            val favorNum: TextView = view.findViewById(R.id.item_main_thread_favor_num)
            val time: TextView = view.findViewById(R.id.item_main_thread_time)
            return ViewHolder(view, avtar, name, threadType, title, commentNum, favorNum, time)
        }

    }

    class ViewHolder(itemView: View, val avatar: ImageView, val name: TextView, val threadType: TextView,
                     val title: TextView, val commentNum: TextView, val favorNum: TextView,
                     val time: TextView) : RecyclerView.ViewHolder(itemView)
}