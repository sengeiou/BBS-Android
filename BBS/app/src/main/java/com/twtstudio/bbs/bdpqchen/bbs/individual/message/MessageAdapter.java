package com.twtstudio.bbs.bdpqchen.bbs.individual.message;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twtstudio.bbs.bdpqchen.bbs.R;
import com.twtstudio.bbs.bdpqchen.bbs.commons.base.BaseAdapter;
import com.twtstudio.bbs.bdpqchen.bbs.commons.base.BaseViewHolder;
import com.twtstudio.bbs.bdpqchen.bbs.commons.utils.ImageUtil;
import com.twtstudio.bbs.bdpqchen.bbs.commons.utils.StampUtil;
import com.twtstudio.bbs.bdpqchen.bbs.forum.boards.thread.ThreadActivity;
import com.twtstudio.bbs.bdpqchen.bbs.individual.message.model.MessageModel;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ricky on 2017/5/19.
 */

public class MessageAdapter extends BaseAdapter<MessageModel> {


    LayoutInflater inflater;

    public MessageAdapter(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentView(inflater.inflate(R.layout.item_rv_message_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        LogUtil.dd("datalistsize", String.valueOf(mDataSet.size()));
        MessageModel item = mDataSet.get(position);
        CommentView iHolder = (CommentView) holder;
        if (item.getContent_model() != null) {
            int tag = mDataSet.get(position).getTag();
            if (tag == 2 || tag == 3) {
                MessageModel.ContentModel model = item.getContent_model();
                iHolder.mTvDatetime.setText(StampUtil.getDatetimeByStamp(model.getT_create()));
                iHolder.mTvSummary.setText(model.getContent());
                ImageUtil.loadAvatarAsBitmapByUid(mContext, item.getAuthor_id(), iHolder.mCivMessage);
                iHolder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(mContext, ThreadActivity.class);
                    intent.putExtra(ThreadActivity.INTENT_THREAD_ID, model.getThread_id());
                    intent.putExtra(ThreadActivity.INTENT_THREAD_TITLE, model.getThread_title());
                    intent.putExtra(ThreadActivity.INTENT_THREAD_FLOOR, model.getFloor());
                    mContext.startActivity(intent);
                });
                if (item.getRead() == 0) {
                    iHolder.mTvRedDot.setVisibility(View.VISIBLE);
                }

                String preCom = item.getAuthor_name() + "  在  " + model.getThread_title();
                if (tag == 2) {
                    String composed = preCom + "  中评论了你";
                    iHolder.mTvComposeTitle.setText(composed);
                }
                if (tag == 3) {
                    String com = preCom + "楼回复了你" + model.getFloor();
                    iHolder.mTvComposeTitle.setText(com);
                }
            }
        }
    }

    static class CommentView extends BaseViewHolder {
        @BindView(R.id.tv_summary)
        TextView mTvSummary;
        @BindView(R.id.tv_datetime)
        TextView mTvDatetime;
        @BindView(R.id.red_dot)
        View mTvRedDot;
        @BindView(R.id.tv_compose_title)
        TextView mTvComposeTitle;
        @BindView(R.id.civ_message)
        CircleImageView mCivMessage;

        public CommentView(View itemView) {
            super(itemView);
        }
    }

    // TODO: 17-5-29 不同类型的消息进行判断
  /*  @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
*/

}
