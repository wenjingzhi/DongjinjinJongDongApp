package fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baway.admin.dongjinjinjongdongapp.R;
import com.baway.admin.dongjinjinjongdongapp.SouSuoActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.stx.xhb.xbanner.XBanner;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.MyTuiJianAdapter;
import bean.TuiJianBean;
import presenter.TuiJianPresenter;
import view.TuiJianView;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/9
 * 作者：董金金
 */

public class Fragment1 extends Fragment implements TuiJianView, View.OnClickListener {

    private XBanner xBanner;
    private List<String> imgesUrl;
    private View view;
    private RecyclerView rv;
    private ImageView img;
    private ViewPager fg1_vp;
    private LinearLayout ll;
    private List<ImageView> img_list;
    private SimpleDraweeView sdv;
    private DraweeController mDraweeController;
    private EditText head_sou;

    private TextView shi;
    private TextView fen;
    private TextView miao;

    private long mHour = 22;
    private long mMin = 54;
    private long mSecond = 36;
    private boolean isRun = true;

//    private float aa=0;
//    private float bb=21;
//    private float cc=(aa/bb);

    //倒计时
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                computeTime();
                if (mHour<10){
                    shi.setText("0"+mHour+"");
                }else {
                    shi.setText("0"+mHour+"");
                }
                if (mMin<10){
                    fen.setText("0"+mMin+"");
                }else {
                    fen.setText(mMin+"");
                }
                if (mSecond<10){
                    miao.setText("0"+mSecond+"");
                }else {
                    miao.setText(mSecond+"");
                }
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.frag1,null);
        //shi.setText(cc+"");
        //获取控件
        initView();
        //为轮播图添加数据
        initData();
        //添加小圆点数据
        addDot();
        //加载动图的方法
        initDongImg();
        //开启倒计时
        startRun();

        //vp的数据
        MyVPAdapter myVPAdapter=new MyVPAdapter(getFragmentManager());
        fg1_vp.setAdapter(myVPAdapter);

        fg1_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ll.getChildCount(); i++) {
                    ImageView img= (ImageView) ll.getChildAt(i);
                    if(i==position%img_list.size()){
                        img.setImageResource(R.drawable.slidingr);
                    }else{
                        img.setImageResource(R.drawable.sliding);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //为你推荐数据
        TuiJianPresenter tuiJianPresenter=new TuiJianPresenter(getActivity(),this);
        tuiJianPresenter.TuiJianQQ();

        xBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Picasso.with(getActivity()).load(imgesUrl.get(position)).into((ImageView) view);
            }
        });

        return view;
    }

    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }

    //加载gif动图的数据
    private void initDongImg() {
        mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                //设置uri,加载本地的gif资源
               // .setUri(Uri.parse("res://"+getPackageName()+"/"+ R.drawable.a))//设置uri
                .setUri(Uri.parse("res://"+getActivity().getPackageName()+"/"+R.drawable.a))
                .build();
        sdv.setController(mDraweeController);
    }

    //添加小圆点的方法
    private void addDot() {
        img_list=new ArrayList<>();
        for (int i = 0; i <=1; i++) {
            ImageView iv=new ImageView(getActivity());
            if(i==0){
                iv.setImageResource(R.drawable.slidingr);
            }else{
                iv.setImageResource(R.drawable.sliding);
            }
            LinearLayout.LayoutParams pp=new LinearLayout.LayoutParams(20,20);
            pp.setMargins(8,0,8,0);
            ll.addView(iv,pp);
            img_list.add(iv);
        }
    }

    //获取控件信息
    private void initView() {
        xBanner = view.findViewById(R.id.xb);
        rv = view.findViewById(R.id.rv);
        img = view.findViewById(R.id.sys);
        fg1_vp = view.findViewById(R.id.fg1_vp);
        ll = view.findViewById(R.id.ll);
        sdv = view.findViewById(R.id.sdv);
        shi = view.findViewById(R.id.shi);
        fen = view.findViewById(R.id.fen);
        miao = view.findViewById(R.id.miao);
        head_sou = view.findViewById(R.id.head_sou);
        head_sou.setOnClickListener(this);
    }

    //轮播图数据
    private void initData() {
        imgesUrl = new ArrayList<>();
        imgesUrl.add("http://120.27.23.105/images/ad/0.jpg");
        imgesUrl.add("http://120.27.23.105/images/ad/1.jpg");
        imgesUrl.add("http://120.27.23.105/images/ad/2.jpg");
        imgesUrl.add("http://120.27.23.105/images/ad/3.jpg");
        xBanner.setData(imgesUrl);
    }

    //为你推荐的数据
    @Override
    public void getInTuiJian(final List<TuiJianBean.TuijianBean.ListBean> list) {
        if(getActivity()!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
                    rv.setLayoutManager(manager);
                    MyTuiJianAdapter myTuiJianAdapter=new MyTuiJianAdapter(getActivity(),list);
                    rv.setAdapter(myTuiJianAdapter);
                }
            });
        }
    }

    //二维码扫描
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ErWeiMaFF();
    }

    //二维码扫描
    private void ErWeiMaFF() {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCameraIntent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
    }

    //搜索的点击事件
    @Override
   public void onClick(View view) {
        Intent intent=new Intent(getActivity(), SouSuoActivity.class);
        startActivity(intent);
    }

    //九宫格的数据
    public class MyVPAdapter extends FragmentPagerAdapter{

        public MyVPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;
            switch (position){
                case 0:
                    fragment= new Frag1();
                    break;
                case 1:
                    fragment=new Frag2();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
