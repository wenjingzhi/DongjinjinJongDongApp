package fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.admin.dongjinjinjongdongapp.LoginActivity;
import com.baway.admin.dongjinjinjongdongapp.R;
import com.baway.admin.dongjinjinjongdongapp.SheZhiActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static android.app.Activity.RESULT_OK;

/**
 * 类的用途：我的页面
 * 实现思路：
 * 时间：2017/11/9
 * 作者：董金金
 */

public class Fragment5 extends Fragment implements View.OnClickListener {

    private TextView login_regist;
    private ImageView yhtx;
    private ImageView shezhi;
    private SharedPreferences flag;
    private Bitmap head;// 头像Bitmap
    private static String path = "/storage/self/primary/mnt/user/0/primary/storage/emulated/0/myHead";// sd路径

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag5,null);
        //登录注册的TextView
        login_regist = view.findViewById(R.id.login_regist);
        yhtx=view.findViewById(R.id.icon);
        login_regist.setOnClickListener(this);
        yhtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.icon:// 更换头像
                        showTypeDialog();
                        break;
                }
            }
        });
        shezhi = view.findViewById(R.id.shezhi);
        shezhi.setOnClickListener(this);
        flag = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
        String login_et_name = flag.getString("login_et_name", null);
        System.out.println("======接收到的用户名：========"+ LoginActivity.login_et_name);
        login_regist.setText(login_et_name+"");
        initView();
        return view;
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getActivity(), R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });

        tv_select_camera.setOnClickListener(new View.OnClickListener() {//调用相机
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });

        dialog.setView(view);
        dialog.show();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);// 保存在SD卡中
                        yhtx.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
//            try {
//                // 关闭流
//                //b.flush();
//                //b.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    private void initView() {
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            yhtx.setImageDrawable(drawable);
            System.out.println("========yhtx======="+yhtx);
        } else {
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_regist:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.shezhi:
                //点击会跳转到设置的Activity
                String tv_name=login_regist.getText().toString();
                System.out.println("======tv_name====="+tv_name);
                Intent intent1 = new Intent(getActivity(),SheZhiActivity.class);
                intent1.putExtra("name",tv_name);
                intent1.putExtra("touxiang",yhtx.getDrawingCache());
                System.out.println("======yhtx.getDrawingCache()======="+yhtx.getDrawingCache());
                startActivity(intent1);
                break;
        }

    }
}
