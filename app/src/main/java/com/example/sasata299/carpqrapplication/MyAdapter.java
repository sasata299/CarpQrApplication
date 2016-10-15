package com.example.sasata299.carpqrapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sasata299 on 2016/10/10.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<ScoreReport> scoreReports;

    public MyAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setScoreReports(ArrayList<ScoreReport> scoreReports) {
        this.scoreReports = scoreReports;
    }

    @Override
    public int getCount() {
        return scoreReports.size();
    }

    @Override
    public Object getItem(int position) {
        return scoreReports.get(position);
    }

    @Override
    public long getItemId(int position) {
        return scoreReports.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.score_report, parent, false);

            holder = new ViewHolder();

            // ホルダークラスにレイアウト内のビューを設定する
            // findViewByIdを初回だけにする
            holder.iconView = (ImageView) convertView.findViewById(R.id.icon);
            holder.inningView = (TextView) convertView.findViewById(R.id.inning);
            holder.detailView = (TextView) convertView.findViewById(R.id.detail);

            // タグにホルダークラスを設定する
            convertView.setTag(holder);
        } else {
            // 2回目以降はビューが設定されているので、タグからホルダークラスを取得する
            holder = (ViewHolder) convertView.getTag();
        }

        ScoreReport scoreReport = scoreReports.get(position);
//        holder.iconView.setImageBitmap(scoreReport.getIcon());
        holder.inningView.setText(scoreReport.getInning());
        holder.detailView.setText(scoreReport.getDetail());

        return convertView;
    }
}
