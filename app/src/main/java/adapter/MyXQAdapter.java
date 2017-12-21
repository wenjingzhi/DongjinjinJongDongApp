package adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.admin.dongjinjinjongdongapp.R;
import com.facebook.drawee.view.SimpleDraweeView;

import bean.SouSuoBean;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/14
 * 作者：董金金
 */

public class MyXQAdapter extends RecyclerView.Adapter<MyXQAdapter.MyViewHolder> {
    private Context context;
    private SouSuoBean.DataBean data;
    private SimpleDraweeView draweeView;
    private TextView xq_tv;
    private String imageUrl;

    public MyXQAdapter(Context context, SouSuoBean.DataBean data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.xq_item,null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        imageUrl = data.getImages().split("\\|")[0];
        Uri uri = Uri.parse(imageUrl);
        draweeView.setImageURI(uri);
        xq_tv.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        if(data!=null){
            return data.getTitle().length();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.xq_sdv);
            xq_tv = itemView.findViewById(R.id.xq_tv);
        }
    }
}
