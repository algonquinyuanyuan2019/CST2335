package com.example.androidlabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private List<MessageModel> messageModels;
    private Context context;
    private LayoutInflater inflater;

    public ChatAdapter(List<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messageModels.size();
    }//return the number of items

    @Override
    public Object getItem(int position) {
        return messageModels.get(position);
    }

    @Override
    public long getItemId(int position) { return position;  }

    @Override
    public View getView(int position, View oldView, ViewGroup parent) {
        //View view = convertView;
        View newView;
        if (oldView == null){
            if (messageModels.get(position).isSend()){
                newView = inflater.inflate(R.layout.activity_main_send, null);

            }else {
                newView = inflater.inflate(R.layout.activity_main_receive, null);
            }
            TextView  messageText = (TextView)newView.findViewById(R.id.textViewMessage);
            messageText.setText(messageModels.get(position).getMessage());
        }
        else newView = oldView;
        return newView;
    }
}