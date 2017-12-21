package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.admin.dongjinjinjongdongapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import bean.JGGBean;

/**
 * 类的用途：
 * 实现思路：九宫格的Adapter
 * 时间：2017/11/10
 * 作者：董金金
 */

public class MyJGGAdapter extends RecyclerView.Adapter<MyJGGAdapter.MyViewHolder> {
    private Context context;
    private List<JGGBean.DataBean> data;
    private ImageView jgg_img;
    private TextView jgg_tv;

    public MyJGGAdapter(Context context, List<JGGBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.jgg_item,null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(context).load(data.get(position).getIcon()).into(jgg_img);
        jgg_tv.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if(data!=null){
            return data.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
            jgg_img = itemView.findViewById(R.id.jgg_img);
            jgg_tv = itemView.findViewById(R.id.jgg_tv);
        }
    }
}
