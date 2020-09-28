package com.itheima.down;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private ImageButton btn_arrow;
    private EditText et_kuang;
    private List<String> list;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_kuang = findViewById(R.id.et_kuang);


        btn_arrow = findViewById(R.id.btn_arrow);


        initData();

    }

    private void initData() {
        final ListView listView = new ListView(this);
        listView.setDividerHeight(0);
        listView.setBackgroundResource(R.drawable.listview_background);
        list = new ArrayList<>();
        for (int i = 0; i < 900; i++) {
            list.add(i + "");
        }

        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

        btn_arrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                popupWindow = new PopupWindow(listView, et_kuang.getWidth(), 300);

                popupWindow.showAsDropDown(et_kuang,0,0);
            }
        });
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        et_kuang.setText(list.get(i));
        popupWindow.dismiss();
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null){
                view = View.inflate(getApplicationContext(), R.layout.item, null);
                viewHolder = new ViewHolder();
                viewHolder.tv_string = view.findViewById(R.id.tv_string);
                viewHolder.iv_image = view.findViewById(R.id.iv_image);
                viewHolder.btn_delete = view.findViewById(R.id.btn_delete);

                view.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.tv_string.setText(list.get(i));

            return view;
        }
    }
    static class ViewHolder{
        TextView tv_string;
        ImageView iv_image;
        ImageButton btn_delete;
    }
}