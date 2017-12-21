package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.admin.dongjinjinjongdongapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

import bean.Frag3Bean;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/19
 * 作者：董金金
 */

public class MyFrg3Adapter extends RecyclerView.Adapter<MyFrg3Adapter.MyViewHolder> {
    private Context context;
    private List<Frag3Bean.DataBean> data;
    private JCVideoPlayerStandard player;
    private TextView tv;

    public MyFrg3Adapter(Context context, List<Frag3Bean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.frg3_item,null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        tv.setText(data.get(position).getTitle());
        boolean setUp = player.setUp(data.get(position).getVedio_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {
            Glide.with(context).load(data.get(position).getImage_url()).into(player.thumbImageView);
        }
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
            player = (JCVideoPlayerStandard)itemView.findViewById(R.id.player_list_video);
            tv = itemView.findViewById(R.id.frag3_tv);
        }
    }
}
