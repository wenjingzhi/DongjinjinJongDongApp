package com.baway.admin.dongjinjinjongdongapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bean.Souhuo;
import zhifubao.PayDemoActivity;

public class Payactivity extends AppCompatActivity implements View.OnClickListener {

    private ListView pay_listview;
    private Button pay_tijiao;
    private String path="http://169.254.239.49/mobile/index.php?act=member_buy&op=buy_step1";
    private ImageView pay_image;
    private String s="http://169.254.239.49/mobile/index.php?act=member_address&op=address_list";
//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 0:
//                    String obj = (String) msg.obj;
//                    Log.i("sdf",obj);
//                    Gson gson=new Gson();
//                    souhuo = gson.fromJson(obj, Souhuo.class);
//                    System.out.println("==========souhuo============"+souhuo.toString());
//                    datas = souhuo.getDatas();
//                    System.out.println("==========datas============="+datas.toString());
//                    address_list = datas.getAddress_list();
//                    System.out.println("===========address_list=========="+address_list);
//                    addressListBean = address_list.get(0);
//                    System.out.println("===========addressListBean======="+addressListBean);
//                    String true_name = addressListBean.getTrue_name();
//                    String area_info = addressListBean.getArea_info();
//                    String mob_phone = addressListBean.getMob_phone();
//                    pay_shzhe.setText(true_name);
//                    System.out.println("=========true_name==========="+true_name);
//                    pay_address.setText(area_info);
//                    System.out.println("========pay_address========"+pay_address);
//                    pay_phonenum.setText(mob_phone);
//                    System.out.println("==========pay_phonenum==========="+pay_phonenum);
//                    break;
//                case 1:
//                    String obj1 = (String) msg.obj;
//                    Log.i("dingdan",obj1);
//                    break;
//            }
//        }
//    };
    private TextView pay_shzhe;
    private TextView pay_address;
    private TextView pay_phonenum;
    private String card_id;
    private Souhuo.DatasBean.AddressListBean addressListBean;
    private List<Souhuo.DatasBean.AddressListBean> address_list;
    private Souhuo.DatasBean datas;
    private Souhuo souhuo;
    private TextView tv_sum;
    private SharedPreferences flag;
    private String trueNameEditText;
    private String addressEditText;
    private ImageView dayu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payactivity);
        //获取id
        pay_listview = (ListView) findViewById(R.id.pay_listview);
        pay_tijiao = (Button) findViewById(R.id.pay_tijiao);
        pay_image = (ImageView) findViewById(R.id.pay_image);
        pay_shzhe = (TextView) findViewById(R.id.pay_shzhe);
        dayu = (ImageView) findViewById(R.id.dayu);
        dayu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        flag = Payactivity.this.getSharedPreferences("flag", Context.MODE_PRIVATE);
        trueNameEditText = flag.getString("trueNameEditText", null);
        pay_shzhe.setText(trueNameEditText);
        System.out.println("=========姓名：========="+ trueNameEditText);
        // String addressEditText = flag.getString("addressEditText", null);
        pay_address = (TextView) findViewById(R.id.pay_address);
        flag = Payactivity.this.getSharedPreferences("flag", Context.MODE_PRIVATE);
        addressEditText = flag.getString("addressEditText", null);
        pay_address.setText(addressEditText);
        System.out.println("=========收货地址========="+ addressEditText);
        pay_phonenum = (TextView) findViewById(R.id.pay_phonenum);
        tv_sum = (TextView) findViewById(R.id.sum);
        Intent intent=getIntent();
        String zong=intent.getStringExtra("s");

        tv_sum.setText(zong);

//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("bundle");
//        List<GoodsBean> goodsBean = (List<GoodsBean>) bundle.getSerializable("goodsBean");
//        Log.i("list",goodsBean.toString());
//        card_id = null;
//        for(int i=0;i<goodsBean.size();i++){
//            if(i==0){
//              card_id =goodsBean.get(i).getCard_id()+"|"+goodsBean.get(i).getCount();
//            }else{
//                card_id +=","+goodsBean.get(i).getCard_id()+"|"+goodsBean.get(i).getCount();
//            }
//        }
//        PayApder apder=new PayApder(goodsBean,Payactivity.this);
//        pay_listview.setAdapter(apder);
        pay_tijiao.setOnClickListener(this);
        pay_image.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //getaddress(s);
    }

//    private void getaddress(String s) {
//        SharedUtil instances = SharedUtil.getInstances();
//        //String key = (String) instances.getValueByKey(Payactivity.this, "loginkey", "sdfg");
//        Object key = instances.getValueByKey(Payactivity.this, "loginKey", "sdfg");
//        Map<String,String> map=new HashMap<>();
//        map.put("key", (String) key);
//        OkHttp3Utils.doPost(s, map, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String  od=response.body().string();
//                Message message=new Message();
//                message.what=0;
//                message.obj=od;
//                handler.sendMessage(message);
//            }
//        });
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pay_tijiao:
                //getTijiao(path);
                Intent intent1=new Intent(Payactivity.this, PayDemoActivity.class);
                startActivity(intent1);
            break;
            case R.id.pay_image:
                Toast.makeText(Payactivity.this,"点击了吗？",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Payactivity.this,Shouhuodizhi.class);
                startActivity(intent);
                break;
        }
    }

//    private void getTijiao(String path) {
//        SharedUtil instances = SharedUtil.getInstances();
//        //String key = (String) instances.getValueByKey(Payactivity.this, "loginkey", "sdfg");
//        Object key = instances.getValueByKey(Payactivity.this, "loginKey", "sdfg");
//        Map<String,String> map=new HashMap<>();
//        map.put("key", (String) key);
//        map.put("cart_id",card_id);
//        map.put("ifcart","1");
//
//        OkHttp3Utils.doPost(path, map, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String  od=response.body().string();
//                Message message=new Message();
//                message.what=1;
//                message.obj=od;
//                handler.sendMessage(message);
//            }
//        });
//    }
}
