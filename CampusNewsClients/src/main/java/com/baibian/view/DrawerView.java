package com.baibian.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baibian.adapter.Guide_adapter;
import com.baibian.adapter.Profile_Drawer_Right_ViewPager_Adapter;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.baibian.Login4Activity;
import com.baibian.R;
import com.baibian.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * �Զ���SlidingMenu �����˵���
 * */
public class DrawerView implements OnClickListener{
	private final Activity activity;
	SlidingMenu localSlidingMenu;
	private ImageView baibian_btn;//�ٱ��¼��ť
	private LinearLayout left_top_layout;
	private SwitchButton night_mode_btn;
	private TextView night_mode_text;
	private RelativeLayout setting_btn;
	private Button exit_btn;
	private AlertDialog alert;
	private AlertDialog.Builder builder;
    private ViewPager profile_drawer_right_viewpager;
	private List<View> viewList;
	private Button buttonTest;
	private ActionSheetDialog actionSheetDialog;
	String[] items;

	private LinearLayout logout_layout_not_login;//未登录布局
	private LinearLayout login_layout;//带有圆形头像的布局，用来更换原来的布局


	protected final int REQUESTCODE=11;//baibian_btn��¼�ķ���ֵ
	public DrawerView(Activity activity) {
		this.activity = activity;
	}

	public SlidingMenu initSlidingMenu() {
		localSlidingMenu = new SlidingMenu(activity);
		localSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);//�������һ��˵�
		localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);//����Ҫʹ�˵�������������Ļ�ķ�Χ
//		localSlidingMenu.setTouchModeBehind(SlidingMenu.SLIDING_CONTENT);//������������ȡ�����˵�����Ľ��㣬������ע�͵�
		localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);//������ӰͼƬ�Ŀ��
		localSlidingMenu.setShadowDrawable(R.drawable.shadow);//������ӰͼƬ
		localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu����ʱ��ҳ����ʾ��ʣ����
		localSlidingMenu.setFadeDegree(0.35F);//SlidingMenu����ʱ�Ľ���̶�
		localSlidingMenu.attachToActivity(activity, SlidingMenu.RIGHT);//ʹSlidingMenu������Activity�ұ�
//		localSlidingMenu.setBehindWidthRes(R.dimen.left_drawer_avatar_size);//����SlidingMenu�˵��Ŀ��
		localSlidingMenu.setMenu(R.layout.left_drawer_fragment);//����menu�Ĳ����ļ�
//		localSlidingMenu.toggle();//��̬�ж��Զ��رջ���SlidingMenu
		localSlidingMenu.setSecondaryMenu(R.layout.profile_drawer_right);
		localSlidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);
		/**
		 * 退出对话框
		 */




		localSlidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
					public void onOpened() {
						
					}
				});
		localSlidingMenu.setOnClosedListener(new OnClosedListener() {
			
			@Override
			public void onClosed() {
				// TODO Auto-generated method stub
				
			}
		});
		initView();
		return localSlidingMenu;
	}
	private void initView() {
		exit_btn=(Button) localSlidingMenu.findViewById(R.id.exit_btn);
		baibian_btn=(ImageView) localSlidingMenu.findViewById(R.id.baibian_btn);
		night_mode_btn = (SwitchButton)localSlidingMenu.findViewById(R.id.night_mode_btn);
		night_mode_text = (TextView)localSlidingMenu.findViewById(R.id.night_mode_text);
		left_top_layout=(LinearLayout) localSlidingMenu.findViewById(R.id.left_top_layout);
		viewList=new ArrayList<View>();
		View view1=View.inflate(activity,R.layout.profile_drawers_right_pagerview1,null);
		View view2=View.inflate(activity,R.layout.profile_drawers_right_pagerview2,null);
		viewList.add(view1);
		viewList.add(view2);
		profile_drawer_right_viewpager=(ViewPager)localSlidingMenu.findViewById(R.id.profile_drawer_right_viewpager);//这个地方一定要在Adapter实例化之前  否则会崩溃
		Profile_Drawer_Right_ViewPager_Adapter myPagerAdapter=new Profile_Drawer_Right_ViewPager_Adapter(viewList,activity);
		profile_drawer_right_viewpager.setAdapter(myPagerAdapter);

		night_mode_btn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					night_mode_text.setText(activity.getResources().getString(R.string.action_night_mode));
				}else{
					night_mode_text.setText(activity.getResources().getString(R.string.action_day_mode));
				}
			}
		});
		night_mode_btn.setChecked(false);
		if(night_mode_btn.isChecked()){
			night_mode_text.setText(activity.getResources().getString(R.string.action_night_mode));
		}else{
			night_mode_text.setText(activity.getResources().getString(R.string.action_day_mode));
		}
		
		setting_btn =(RelativeLayout)localSlidingMenu.findViewById(R.id.setting_btn);
		setting_btn.setOnClickListener(this);
		baibian_btn.setOnClickListener(this);
		exit_btn.setOnClickListener(this);

	}
	private void init_exit_btn(){
		items=new String[2];
		items[0]=activity.getString(R.string.close_Baibai);
		items[1]=activity.getString(R.string.exit_not_account);
		actionSheetDialog=new ActionSheetDialog(activity,items,exit_btn);
		actionSheetDialog.setOnOperItemClickL(new OnOperItemClickL() {
			@Override
			public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
				actionSheetDialog.dismiss();
				switch (position) {
					case 0://退出事件
						AlertDialog.Builder builder=new AlertDialog.Builder(activity);
						builder.setMessage(R.string.sure_exit)
								.setIcon(R.drawable.icon2)
								.setTitle(R.string.frindly_reminder)
								.setCancelable(false)
								.setPositiveButton(R.string.sure,new DialogInterface.OnClickListener(){
									public void onClick(DialogInterface dialog,int id ){
										//确认后的事件
										activity.finish();
									}
								})
								.setNegativeButton(R.string.cancel,new  DialogInterface.OnClickListener(){
									public void onClick(DialogInterface dialog,int id ){
										dialog.cancel();//取消后的事件
									}

								} );
						AlertDialog alert=builder.create();
						alert.show();
						break;
					case 1://注销事件
						login_layout=(LinearLayout) activity.findViewById(R.id.login_layout);
						logout_layout_not_login=(LinearLayout) activity.findViewById(R.id.logout_layout_not_login);
						if ((logout_layout_not_login.getVisibility()==View.GONE)&&(login_layout.getVisibility()==View.VISIBLE)){

							login_layout.setVisibility(View.GONE);
							logout_layout_not_login.setVisibility(View.VISIBLE);
						}
						else if ((logout_layout_not_login.getVisibility()==View.VISIBLE)){
							Toast.makeText(activity,"您尚未登录，无法注销",Toast.LENGTH_SHORT).show();
						}
						else{
							Toast.makeText(activity,"注销失败",Toast.LENGTH_SHORT).show();
						}


						break;
					default:
						break;
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.baibian_btn:

			Intent intent_baibian_btn=new Intent(activity,Login4Activity.class);
			activity.startActivityForResult(intent_baibian_btn,REQUESTCODE);
				break;
		case R.id.setting_btn:
			activity.startActivity(new Intent(activity,SettingsActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			break;
			case R.id.exit_btn:
				init_exit_btn();
				actionSheetDialog.isTitleShow(false).show();
				break;
		default:
			break;
		}
	}
}
