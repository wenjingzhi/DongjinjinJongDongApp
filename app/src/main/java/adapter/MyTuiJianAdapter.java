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

import bean.TuiJianBean;

/**
 * 类的用途：
 * 实现思路：推荐的Adapter
 * 时间：2017/11/10
 * 作者：董金金
 */

public class MyTuiJianAdapter extends RecyclerView.Adapter<MyTuiJianAdapter.MyViewHolder> {
    private Context context;
    private List<TuiJianBean.TuijianBean.ListBean> list;
    private ImageView tuijian_img;
    private TextView tuijian_tv;
    private String imageUrl;

    public MyTuiJianAdapter(Context context, List<TuiJianBean.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.tuijian_item,null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //因为接口的原因所以加载的图片数据需要分割一下才可以展示数据
        imageUrl = list.get(position).getImages().split("\\|")[0];
        Picasso.with(context).load(imageUrl).into(tuijian_img);
        tuijian_tv.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
            tuijian_img = itemView.findViewById(R.id.tuijian_img);
            tuijian_tv = itemView.findViewById(R.id.tuijian_tv);
        }
    }
}
