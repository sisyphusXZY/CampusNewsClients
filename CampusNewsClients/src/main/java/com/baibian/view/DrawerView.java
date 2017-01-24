package com.baibian.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.baibian.Login4Activity;
import com.baibian.R;
import com.baibian.SettingsActivity;

/**
 * �Զ���SlidingMenu �����˵���
 * */
public class DrawerView implements OnClickListener{
	private final Activity activity;
	SlidingMenu localSlidingMenu;
	private ImageView baibian_btn;//�ٱ��¼��ť
	private LinearLayout left_top_layout;//�໬�˵��ж����Ĳ��֣���¼��Ҫ����
	private SwitchButton night_mode_btn;
	private TextView night_mode_text;
	private RelativeLayout setting_btn;
	private Button exit_btn;
	private AlertDialog alert;
	private AlertDialog.Builder builder;

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
		default:
			break;
		}
	}
}
