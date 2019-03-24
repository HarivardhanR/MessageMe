package com.app.harish.messageme;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.provider.PicassoProvider;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Messages> messagesList;

    public MessageAdapter(List<Messages> messagesList) {
        this.messagesList = messagesList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_single_message,parent,false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        Messages c = messagesList.get(position);
        try {
            if (c.getType().equals("text")) {
                holder.senderimg.setVisibility(View.GONE);
                holder.recieverimg.setVisibility(View.GONE);
                if (c.getFrom().equals("receiver")) {
                    holder.sendermessagetxt.setVisibility(View.GONE);
                    holder.recmessagetxt.setText(c.getMessage());
                } else {
                    holder.recmessagetxt.setVisibility(View.GONE);
                    holder.sendermessagetxt.setText(c.getMessage());
                }
            } else if (c.getType().equals("image")) {
                holder.sendermessagetxt.setVisibility(View.GONE);
                holder.recmessagetxt.setVisibility(View.GONE);

                if (c.getFrom().equals("receiver")) {
                    holder.senderimg.setVisibility(View.INVISIBLE);
                    PicassoProvider.get().load(c.getMessage()).into(holder.recieverimg);
                } else {
                    holder.recieverimg.setVisibility(View.GONE);
                    PicassoProvider.get().load(c.getMessage()).into(holder.senderimg);
                }
            } else {
                holder.senderimg.setVisibility(View.GONE);
                holder.recieverimg.setVisibility(View.GONE);
                holder.sendermessagetxt.setVisibility(View.GONE);
                holder.recmessagetxt.setVisibility(View.GONE);
                holder.descText.setText(c.getMessage());
            }
        }catch (NullPointerException e){
            holder.senderimg.setVisibility(View.GONE);
            holder.recieverimg.setVisibility(View.GONE);
            holder.sendermessagetxt.setVisibility(View.GONE);
            holder.recmessagetxt.setVisibility(View.GONE);
            holder.descText.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{

        TextView recmessagetxt;
        TextView sendermessagetxt;
        ImageView senderimg;
        ImageView recieverimg;
        TextView descText;
        public MessageViewHolder(View itemView) {
            super(itemView);

            recmessagetxt = itemView.findViewById(R.id.messagetxtlayout);
            sendermessagetxt = itemView.findViewById(R.id.sendermessagetxtlayout);
            senderimg = itemView.findViewById(R.id.imgmsg);
            recieverimg = itemView.findViewById(R.id.recimgmsg);
            descText = itemView.findViewById(R.id.description);
        }
    }


}
